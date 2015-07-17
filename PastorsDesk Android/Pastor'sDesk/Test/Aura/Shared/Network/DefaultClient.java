//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.BaseClient;
import Aura.Shared.Network.Packet;
import CS2JNet.JavaSupport.language.RefSupport;
import System.BitConverter;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Normal Mabi client (Login, Channel).
*/
public class DefaultClient  extends BaseClient 
{
    protected void encodeBuffer(byte[] buffer) throws Exception {
        //this.Crypto.FromServer(buffer);
        // Set raw flag
        buffer[5] = 0x03;
    }

    public void decodeBuffer(byte[] buffer) throws Exception {
        this.getCrypto().fromClient(buffer);
    }

    protected byte[] buildPacket(Packet packet) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ result = new byte[6 + packet.getSize() + 4];
        // header + packet + checksum
        result[0] = 0x88;
        System.Buffer.BlockCopy(BitConverter.GetBytes(result.Length), 0, result, 1);
        RefSupport refVar___0 = new RefSupport(result);
        packet.Build(refVar___0, 6);
        result = refVar___0.getValue();
        return result;
    }

}


