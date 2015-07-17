using System;
using System.Net;
using System.Net.Sockets;
using System.Windows;
using PastorsDesk.Network.Handlers;
using PastorsDesk.Shared.Network;
using PastorsDesk.Shared.Util;

namespace PastorsDesk.Network
{
    public class PdServer
    {
        private Socket _socket;

        public PdClient Client { get; set; }

        public event ServerConnectionEventHandler ServerConnected;
        public event ServerConnectionEventHandler ServerDisconnected;
        public event ServerConnectionEventHandler ServerLostConnection;

        public PastorsDeskPacketHandlers Handlers { get; set; }

        public PdServer()
        {
            _socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp)
            {
                NoDelay = true
            };
            Client = new PdClient();
        }

        public void Start(int port = 37214)
        {
            if (Handlers == null)
                return;
            Start(new IPEndPoint(IPAddress.Parse("127.0.0.1"), port));
        }


        public void Start(string host = "127.0.0.1", int port = 37214)
        {
            if (Handlers == null)
                return;
            Start(new IPEndPoint(IPAddress.Parse(host), port));
        }

        private void Start(IPEndPoint endPoint)
        {
            try
            {
                _socket.BeginConnect(endPoint, OnConnect, _socket);
            }
            catch (Exception ex)
            {
                Log.Exception(ex, "Unable to connect to server.");
            }
        }

        private void OnConnect(IAsyncResult ar)
        {
            PdClient instance = new PdClient();
            try
            {
                instance.Socket = (Socket) ar.AsyncState;
                instance.Socket.EndConnect(ar);
                instance.Socket.BeginReceive(instance.Buffer, 0, instance.Buffer.Length, SocketFlags.None, OnReceive,
                    instance);
                Client = instance;
                OnServerConnected(instance);
            }
            catch (ObjectDisposedException ex)
            {
            }
            catch (Exception ex)
            {
                MessageBox.Show(string.Format("Error occured when attempting to connect to the server.\r\n{0}\r\n{1}", ex.Message, ex.StackTrace));
                Log.Exception(ex);
            }
        }

        private void OnReceive(IAsyncResult ar)
        {
            var client = ar.AsyncState as PdClient;

            try
            {
                int bytesReceived = client.Socket.EndReceive(ar);
                int ptr = 0;

                if (bytesReceived == 0)
                {
                    Log.Info("Connection closed to '{0}.", client.Address);
                    KillAndRemoveClient(client);
                    OnServerDisconnected(client);
                    return;
                }

                while (bytesReceived > 0)
                {
                    int length = this.GetPacketLength(client.Buffer, ptr);

                    if (length > client.Buffer.Length)
                        throw new Exception(string.Format("Buffer too small to receive full packet ({0}).", length));

                    var buffer = new byte[length];
                    Buffer.BlockCopy(client.Buffer, ptr, buffer, 0, length);
                    bytesReceived -= length;
                    ptr += length;

                    this.HandleBuffer(client, buffer);
                }

                if (client.State == ClientState.Dead)
                {
                    Log.Info("Killed connection to '{0}'.", client.Address);
                    this.RemoveClient(client);
                    this.OnServerDisconnected(client);
                    return;
                }

                client.Socket.BeginReceive(client.Buffer, 0, client.Buffer.Length, SocketFlags.None, this.OnReceive,
                    client);
            }
            catch (SocketException)
            {
                Log.Info("Lost connection to '{0}'.", client.Address);
                this.OnServerLostConnection(client);
                return;
            }
            catch (Exception)
            {

            }
        }

        private void HandleBuffer(PdClient client, byte[] buffer)
        {
            var length = buffer.Length;

            Array.Resize(ref buffer, length -= 4);

            BitConverter.GetBytes(length).CopyTo(buffer, 1);

            if (buffer[5] == 0x01)
            {
                BitConverter.GetBytes(BitConverter.ToUInt32(buffer, 6) ^ 0x98BADCFE).CopyTo(buffer, 6);
                client.Socket.Send(buffer);
            }
            else
            {
                // First packet, skip challenge and send success msg.
                if (client.State == ClientState.BeingChecked)
                {
                    client.Send(new byte[] {0x88, 0x07, 0x00, 0x00, 0x00, 0x00, 0x07});

                    client.State = ClientState.LoggingIn;
                }
                // Actual packets
                else
                {
                    // Start reading after the protocol header
                    var packet = new Packet(buffer, 6);

                    //Logger.Debug(packet);

                    try
                    {
                        this.Handlers.Handle(client, packet);
                    }
                    catch (Exception ex)
                    {
                        Log.Exception(ex, "There has been a problem while handling '{0:X4}'.", packet.Op);
                    }
                }
            }
        }

        private void KillAndRemoveClient(PdClient client)
        {
            client.Kill();
            RemoveClient(client);
        }

        private void RemoveClient(PdClient client)
        {
            Client = null;
        }

        private int GetPacketLength(byte[] buffer, int ptr)
        {
            return buffer[ptr + 1] 
                + (buffer[ptr + 2] << 8) 
                + (buffer[ptr + 3] << 16) 
                + (buffer[ptr + 4] << 24);
        }

        protected virtual void OnServerLostConnection(PdClient client)
        {
            if (ServerLostConnection == null)
            {
                this.OnServerDisconnected(client);
                return;
            }
            ServerLostConnection(client);
        }

        protected virtual void OnServerDisconnected(PdClient client)
        {
            if (ServerDisconnected == null) return;
            ServerDisconnected(client);
        }

        protected virtual void OnServerConnected(PdClient client)
        {
            if (ServerConnected == null) return;
            ServerConnected(client);
        }

        public delegate void ServerConnectionEventHandler(PdClient client);
    }
}
