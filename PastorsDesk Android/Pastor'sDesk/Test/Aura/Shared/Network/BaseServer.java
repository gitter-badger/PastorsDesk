//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:14 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.BaseClient;
import Aura.Shared.Network.ClientState;
import Aura.Shared.Network.PacketHandlerManager;
import Aura.Shared.Util.CliUtil;
import Aura.Shared.Util.Log;
import CS2JNet.JavaSupport.util.ListSupport;
import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.Net.IPAddress;
import CS2JNet.System.Net.IPEndPoint;
import CS2JNet.System.Net.Sockets.AddressFamily;
import CS2JNet.System.Net.Sockets.NetSocket;
import CS2JNet.System.Net.Sockets.ProtocolType;
import CS2JNet.System.Net.Sockets.SocketFlags;
import CS2JNet.System.Net.Sockets.SocketType;
import CS2JNet.System.ObjectDisposedException;
import CS2JNet.System.StringSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Base server, for specialized servers to inherit from.
*/
public abstract class BaseServer <TClient extends BaseClient>  
{
    private NetSocket _socket;
    private CSList<TClient> __Clients;
    public CSList<TClient> getClients() {
        return __Clients;
    }

    public void setClients(CSList<TClient> value) {
        __Clients = value;
    }

    private PacketHandlerManager<TClient> __Handlers;
    public PacketHandlerManager<TClient> getHandlers() {
        return __Handlers;
    }

    public void setHandlers(PacketHandlerManager<TClient> value) {
        __Handlers = value;
    }

    /**
    * Raised when client successfully connected.
    */
    public ClientConnectionEventHandler ClientConnected;
    /**
    * Raised when client disconnected for any reason.
    */
    public ClientConnectionEventHandler ClientDisconnected;
    protected BaseServer() throws Exception {
        _socket = new NetSocket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        _socket.NoDelay = true;
        this.setClients(new CSList<TClient>());
    }

    /**
    * Starts listener.
    * 
    *  @param host 
    *  @param port
    */
    public void start(int port) throws Exception {
        if (this.getHandlers() == null)
        {
            Log.error("No packet handler manager set, start canceled.");
            return ;
        }
         
        this.start(new IPEndPoint(IPAddress.Any, port));
    }

    /**
    * Starts listener.
    * 
    *  @param host 
    *  @param port
    */
    public void start(String host, int port) throws Exception {
        if (this.getHandlers() == null)
        {
            Log.error("No packet handler manager set, start canceled.");
            return ;
        }
         
        this.start(new IPEndPoint(IPAddress.Parse(host), port));
    }

    /**
    * Starts listener.
    * 
    *  @param endPoint
    */
    private void start(IPEndPoint endPoint) throws Exception {
        try
        {
            _socket.bind(endPoint);
            _socket.listen(10);
            _socket.BeginAccept(this.OnAccept, _socket);
            Log.status("Server ready, listening on {0}.",_socket.getLocalEndPoint());
        }
        catch (Exception ex)
        {
            Log.exception(ex,"Unable to set up socket; perhaps you're already running a server?");
            CliUtil.Exit(1);
        }
    
    }

    /**
    * Stops listener.
    */
    public void stop() throws Exception {
        try
        {
            _socket.Shutdown(SocketShutdown.Both);
            _socket.close();
        }
        catch (Exception __dummyCatchVar0)
        {
        }
    
    }

    /**
    * Handles incoming connections.
    * 
    *  @param result
    */
    private void onAccept(IAsyncResult result) throws Exception {
        TClient client = new TClient();
        try
        {
            client.Socket = (result.AsyncState instanceof NetSocket ? (NetSocket)result.AsyncState : (NetSocket)null).EndAccept(result);
            // We don't need this here, since it's inherited from the parent
            // client.Socket.NoDelay = true;
            client.Socket.BeginReceive(client.Buffer, 0, client.Buffer.Length, SocketFlags.None, this.OnReceive, client);
            this.addClient(client);
            Log.info("Connection established from '{0}.",client.Address);
            this.onClientConnected(client);
        }
        catch (ObjectDisposedException __dummyCatchVar1)
        {
        }
        catch (Exception ex)
        {
            Log.exception(ex,"While accepting connection.");
        }
        finally
        {
            _socket.BeginAccept(this.OnAccept, _socket);
        }
    }

    /**
    * Starts receiving for client.
    * 
    *  @param client
    */
    public void addReceivingClient(TClient client) throws Exception {
        client.Socket.BeginReceive(client.Buffer, 0, client.Buffer.Length, SocketFlags.None, this.OnReceive, client);
    }

    /**
    * Handles sending packets, obviously.
    * 
    *  @param result
    */
    protected void onReceive(IAsyncResult result) throws Exception {
        TClient client = result.AsyncState instanceof TClient ? (TClient)result.AsyncState : (TClient)null;
        try
        {
            int bytesReceived = client.Socket.EndReceive(result);
            int ptr = 0;
            if (bytesReceived == 0)
            {
                Log.info("Connection closed from '{0}.",client.Address);
                this.killAndRemoveClient(client);
                this.onClientDisconnected(client);
                return ;
            }
             
            while (bytesReceived > 0)
            {
                // Handle all received bytes
                // Length of new packet
                int length = this.GetPacketLength(client.Buffer, ptr);
                // Shouldn't actually happen...
                if (length > client.Buffer.Length)
                    throw new Exception(String.format(StringSupport.CSFmtStrToJFmtStr("Buffer too small to receive full packet ({0})."),length));
                 
                // Read whole packet and ...
                /* [UNSUPPORTED] 'var' as type is unsupported "var" */ buffer = new byte[length];
                Buffer.BlockCopy(client.Buffer, ptr, buffer, 0, length);
                bytesReceived -= length;
                ptr += length;
                // Handle it
                this.HandleBuffer(client, buffer);
            }
            // Stop if client was killed while handling.
            if (client.State == ClientState.Dead)
            {
                Log.info("Killed connection from '{0}'.",client.Address);
                this.removeClient(client);
                this.onClientDisconnected(client);
                return ;
            }
             
            // Round $round+1, receive!
            client.Socket.BeginReceive(client.Buffer, 0, client.Buffer.Length, SocketFlags.None, this.OnReceive, client);
        }
        catch (SocketException __dummyCatchVar2)
        {
            Log.info("Connection lost from '{0}'.",client.Address);
            this.killAndRemoveClient(client);
            this.onClientDisconnected(client);
        }
        catch (ObjectDisposedException __dummyCatchVar3)
        {
        }
        catch (Exception ex)
        {
            Log.exception(ex,"While receiving data from '{0}'.",client.Address);
            this.killAndRemoveClient(client);
            this.onClientDisconnected(client);
        }
    
    }

    /**
    * Kills and removes client from server.
    * 
    *  @param client
    */
    protected void killAndRemoveClient(TClient client) throws Exception {
        client.Kill();
        this.removeClient(client);
    }

    /**
    * Adds client to list.
    * 
    *  @param client
    */
    protected void addClient(TClient client) throws Exception {
        synchronized (this.getClients())
        {
            {
                this.getClients().add(client);
            }
        }
    }

    //Log.Status("Connected clients: {0}", _clients.Count);
    /**
    * Removes client from list.
    * 
    *  @param client
    */
    protected void removeClient(TClient client) throws Exception {
        synchronized (this.getClients())
        {
            {
                this.getClients().remove(client);
            }
        }
    }

    //Log.Status("Connected clients: {0}", _clients.Count);
    /**
    * Returns length of the new incoming packet, so it can be received.
    * 
    *  @param buffer 
    *  @param ptr 
    *  @return
    */
    protected abstract int getPacketLength(byte[] buffer, int ptr) throws Exception ;

    protected abstract void handleBuffer(TClient client, byte[] buffer) throws Exception ;

    protected void onClientConnected(TClient client) throws Exception {
        if (this.ClientConnected != null)
            this.ClientConnected.invoke(client);
         
    }

    protected void onClientDisconnected(TClient client) throws Exception {
        if (this.ClientDisconnected != null)
            this.ClientDisconnected.invoke(client);
         
    }

    public static class __MultiClientConnectionEventHandler   implements ClientConnectionEventHandler
    {
        public void invoke(TClient client) throws Exception {
            List<ClientConnectionEventHandler> copy, members = this.getInvocationList();
            synchronized (members)
            {
                copy = new LinkedList<ClientConnectionEventHandler>(members);
            }
            for (ClientConnectionEventHandler d : copy)
            {
                d.invoke(client);
            }
        }

        private List<ClientConnectionEventHandler> _invocationList = new ArrayList<ClientConnectionEventHandler>();
        public static ClientConnectionEventHandler combine(ClientConnectionEventHandler a, ClientConnectionEventHandler b) throws Exception {
            if (a == null)
                return b;
             
            if (b == null)
                return a;
             
            __MultiClientConnectionEventHandler ret = new __MultiClientConnectionEventHandler();
            ret._invocationList = a.getInvocationList();
            ret._invocationList.addAll(b.getInvocationList());
            return ret;
        }

        public static ClientConnectionEventHandler remove(ClientConnectionEventHandler a, ClientConnectionEventHandler b) throws Exception {
            if (a == null || b == null)
                return a;
             
            List<ClientConnectionEventHandler> aInvList = a.getInvocationList();
            List<ClientConnectionEventHandler> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
            if (aInvList == newInvList)
            {
                return a;
            }
            else
            {
                __MultiClientConnectionEventHandler ret = new __MultiClientConnectionEventHandler();
                ret._invocationList = newInvList;
                return ret;
            } 
        }

        public List<ClientConnectionEventHandler> getInvocationList() throws Exception {
            return _invocationList;
        }
    
    }

    public static interface ClientConnectionEventHandler   
    {
        void invoke(TClient client) throws Exception ;

        List<ClientConnectionEventHandler> getInvocationList() throws Exception ;
    
    }

}


