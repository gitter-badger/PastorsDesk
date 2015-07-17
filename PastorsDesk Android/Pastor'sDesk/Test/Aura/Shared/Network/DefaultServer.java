//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.BaseClient;
import Aura.Shared.Network.BaseServer;
import Aura.Shared.Network.ClientState;
import Aura.Shared.Network.Packet;
import Aura.Shared.Util.Log;
import CS2JNet.JavaSupport.language.RefSupport;
import System.BitConverter;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Normal Mabi server (Login, Channel).
*/
public class DefaultServer <TClient extends BaseClient> extends BaseServer<TClient> 
{
    protected int getPacketLength(byte[] buffer, int ptr) throws Exception {
        return (buffer[ptr + 1] << 0) + (buffer[ptr + 2] << 8) + (buffer[ptr + 3] << 16) + (buffer[ptr + 4] << 24);
    }

    // <0x??><int:length><...>
    protected void handleBuffer(TClient client, byte[] buffer) throws Exception {
        Int32 length = buffer.length;
        // Cut 4 bytes at the end (checksum?)
        RefSupport<byte[]> refVar___0 = new RefSupport<byte[]>(buffer);
        Array.Resize(refVar___0, length -= 4);
        buffer = refVar___0.getValue();
        // Write new length into the buffer.
        BitConverter.getBytes(length).CopyTo(buffer, 1);
        // Decrypt packet if crypt flag isn't 3.
        if (buffer[5] != 0x03)
            client.DecodeBuffer(buffer);
         
        //Log.Debug("in:  " + BitConverter.ToString(buffer));
        // Flag 1 is a heartbeat, acknowledge and send back.
        if (buffer[5] == 0x01)
        {
            BitConverter.getBytes(BitConverter.toUInt32(buffer,6) ^ 0x98BADCFE).CopyTo(buffer, 6);
            client.Socket.Send(buffer);
        }
        else
        {
            // First packet, skip challenge and send success msg.
            if (client.State == ClientState.BeingChecked)
            {
                client.Send(new byte[]{ 0x88, 0x07, 0x00, 0x00, 0x00, 0x00, 0x07 });
                client.State = ClientState.LoggingIn;
            }
            else
            {
                // Actual packets
                // Start reading after the protocol header
                Packet packet = new Packet(buffer,6);
                try
                {
                    //Logger.Debug(packet);
                    this.getHandlers().handle(client,packet);
                }
                catch (Exception ex)
                {
                    Log.exception(ex,"There has been a problem while handling '{0:X4}'.",packet.getOp());
                }
            
            } 
        } 
    }

    protected void onClientConnected(TClient client) throws Exception {
        // Send seed
        client.Socket.Send(BitConverter.GetBytes(client.Crypto.Seed));
        super.OnClientConnected(client);
    }

}


