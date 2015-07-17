//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network.Crypto;

import Aura.Shared.Network.Crypto.MabiKeystreamGenerator;
import System.BitConverter;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Implementation of Mabi's custom cipher
*/
public final class MabiCipherEngine   
{
    private int _idx;
    private final byte[] _keystream;
    private final MabiKeystreamGenerator _keyGen;
    public MabiCipherEngine(MabiKeystreamGenerator keyGen) throws Exception {
        _keyGen = keyGen;
        _keystream = new byte[128];
        for (Int32 i = 31;i >= 0;i--)
        {
            BitConverter.getBytes(_keyGen.getNextKey()).CopyTo(_keystream, i * 4);
        }
        _idx = 31;
    }

    public void processPacket(byte[] packet, int offset, int len) throws Exception {
        len -= 4;
        int i;
        Int32 tmpIdx = _idx * 4;
        for (i = offset;i < len;i += 4)
        {
            // Convert idx into a byte index
            Int32 j = tmpIdx & 0x1F;
            // Convert tmpIdx into a value [0, 32)
            // this is, in effect, the first 32 bytes of the keystream
            tmpIdx += 4;
            // Advance the counter by 4
            packet[i] = packet[i] ^ _keystream[j];
            packet[i + 1] = packet[i + 1] ^ _keystream[j + 1];
            packet[i + 2] = packet[i + 2] ^ _keystream[j + 2];
            packet[i + 3] = packet[i + 3] ^ _keystream[j + 3];
        }
        len += 4;
        while (i < len)
        {
            // Process the final block, which may not be a multiple of 4
            packet[i] = packet[i] ^ _keystream[tmpIdx & 0x7F];
            // Convert the counter into a value [0, 128)
            // this is, in effect, anywhere along the key
            i++;
            tmpIdx++;
        }
        // Replace a subset of the key. Idx will always be on the range [0, 32)
        // So this effectively changes 4 bytes of the "Main" key and doesn't touch the remainder
        BitConverter.getBytes(_keyGen.getNextKey()).CopyTo(_keystream, _idx);
        if (_idx == 0)
            _idx = 31;
        else
            _idx--; 
    }

}


