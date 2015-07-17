//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network.Crypto;

import Aura.Shared.Network.Crypto.AesFastEngine;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Implements a Mabinogi-style AES cipher
*/
public final class MabiAesEngine   
{
    /**
    * Any data left over after processing all multiples of 16
    * is XORed with this array.
    */
    private static final byte[] _remainderMask = { 0xfe, 0xdc, 0xba, 0x98, 0x76, 0x54, 0x32, 0x10, 0xf, 0x1e, 0x2d, 0x3c, 0x4b, 0x5a, 0x69, 0x78 };
    private final AesFastEngine _aesEngine;
    /**
    * Creates a new instance of the mabi cipher
    * 
    *  @param forEncryption True if the cipher will be used to encrypt data, false otherwise
    *  @param key The key, in little endian form
    */
    public MabiAesEngine(boolean forEncryption, byte[] key) throws Exception {
        _aesEngine = new AesFastEngine();
        _aesEngine.init(forEncryption,key);
    }

    /**
    * Transforms a packet
    * 
    *  @param packet 
    *  @param offset 
    *  @param count
    */
    public void processPacket(byte[] packet, int offset, int count) throws Exception {
        int ptr = offset, ctr = count;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ temp = new byte[16];
    
    }

}


