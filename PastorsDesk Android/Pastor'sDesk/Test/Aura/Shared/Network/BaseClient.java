//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:14 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.ClientState;
import Aura.Shared.Network.Crypto.MabiCrypto;
import Aura.Shared.Network.Packet;
import Aura.Shared.Util.Log;
import CS2JNet.System.Net.Sockets.NetSocket;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Base client, for specialized client classes in the servers.
*/
public abstract class BaseClient   
{
    // Largest known packet is composing on R1, up to ~3700 bytes.
    private static final int BufferDefaultSize = 4096;
    private NetSocket __Socket;
    public NetSocket getSocket() {
        return __Socket;
    }

    public void setSocket(NetSocket value) {
        __Socket = value;
    }

    private byte[] __Buffer;
    public byte[] getBuffer() {
        return __Buffer;
    }

    public void setBuffer(byte[] value) {
        __Buffer = value;
    }

    private ClientState __State = ClientState.BeingChecked;
    public ClientState getState() {
        return __State;
    }

    public void setState(ClientState value) {
        __State = value;
    }

    private MabiCrypto __Crypto;
    public MabiCrypto getCrypto() {
        return __Crypto;
    }

    public void setCrypto(MabiCrypto value) {
        __Crypto = value;
    }

    private String _address;
    public String getAddress() throws Exception {
        if (_address == null)
        {
            try
            {
                _address = this.getSocket().getRemoteEndPoint().toString();
            }
            catch (Exception __dummyCatchVar0)
            {
                _address = "?";
            }
        
        }
         
        return _address;
    }

    protected BaseClient() throws Exception {
        this.setBuffer(new byte[BufferDefaultSize]);
        this.setCrypto(new MabiCrypto(0x0, true));
    }

    // 0xAura 0x41757261
    /**
    * Sends buffer (duh).
    * 
    *  @param buffer
    */
    public void send(byte[] buffer) throws Exception {
        if (this.getState() == ClientState.Dead)
            return ;
         
        this.encodeBuffer(buffer);
        try
        {
            //Log.Debug("out: " + BitConverter.ToString(buffer));
            this.getSocket().send(buffer);
        }
        catch (Exception ex)
        {
            Log.error("Unable to send packet to '{0}'. ({1})",this.getAddress(),ex.getMessage());
        }
    
    }

    /**
    * Builds buffer from packet and sends it.
    * 
    *  @param packet
    */
    public void send(Packet packet) throws Exception {
        // Don't log internal packets
        //if (packet.Op < Op.Internal.ServerIdentify)
        //    Log.Debug("S: " + packet);
        this.send(this.buildPacket(packet));
    }

    /**
    * Encodes buffer.
    * 
    *  @param buffer
    */
    protected abstract void encodeBuffer(byte[] buffer) throws Exception ;

    public abstract void decodeBuffer(byte[] buffer) throws Exception ;

    /**
    * Builds packet, appending the overall header and checksum.
    * 
    *  @param packet 
    *  @return
    */
    protected abstract byte[] buildPacket(Packet packet) throws Exception ;

    /**
    * Kills client connection.
    */
    public void kill() throws Exception {
        if (this.getState() != ClientState.Dead)
        {
            try
            {
                this.getSocket().Shutdown(SocketShutdown.Both);
                this.getSocket().close();
            }
            catch (Exception __dummyCatchVar1)
            {
            }

            // Naturally, we have to clean up after killing somebody.
            this.cleanUp();
            this.setState(ClientState.Dead);
        }
        else
        {
            Log.warning("Client got killed multiple times." + System.getProperty("line.separator") + Environment.StackTrace);
        } 
    }

    /**
    * Takes care of client's remains (saving chars, etc)
    */
    public void cleanUp() throws Exception {
    }

}


