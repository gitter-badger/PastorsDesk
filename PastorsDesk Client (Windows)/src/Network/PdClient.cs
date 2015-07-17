using System;
using System.Net.Sockets;
using PastorsDesk.Shared.Network;
using PastorsDesk.Shared.Util;

namespace PastorsDesk.Network
{
    public class PdClient
    {
        private const int BufferDefaultSize = 4096;

        private string _address;

        public Socket Socket { get; set; }

        public byte[] Buffer { get; set; }

        public ClientState State { get; set; }

        public PacketCrypto Crypto { get; set; }

        public string Address
        {
            get
            {
                if (_address != null)
                    return _address;

                try
                {
                    _address = Socket.RemoteEndPoint.ToString();
                }
                catch
                {
                    _address = "?";
                }
                return _address;
            }
        }

        public PdClient()
        {
            Buffer = new byte[4096];
            Crypto = new PacketCrypto();
        }

        public void Send(Packet packet)
        {
            this.Send(this.BuildPacket(packet));
        }

        public void Send(byte[] buffer)
        {
            if (State == ClientState.Dead)
                return;
            EncodeBuffer(ref buffer);
            try
            {
                Socket.Send(buffer);
            }
            catch (Exception ex)
            {
                Log.Error("Unable to send packet to '{0}'. ({1})", _address, ex.Message);
            }
        }

        public void Kill()
        {
            if (this.State != ClientState.Dead)
            {
                try
                {
                    this.Socket.Shutdown(SocketShutdown.Both);
                    this.Socket.Close();
                }
                catch
                {
                }
                this.CleanUp();
                this.State = ClientState.Dead;
            }
            else
                Log.Warning("Client got killed multiple times." + Environment.NewLine + Environment.StackTrace);
        }


        private void EncodeBuffer(ref byte[] buffer)
        {
            buffer[5] = 3;
        }

        private byte[] BuildPacket(Packet packet)
        {
            byte[] buffer = new byte[6 + packet.GetSize() + 4];
            buffer[0] = 136;
            System.Buffer.BlockCopy(BitConverter.GetBytes(buffer.Length), 0, buffer, 1, 4);
            packet.Build(ref buffer, 6);
            return buffer;
        }

        public void CleanUp()
        {
        }
    }
}
