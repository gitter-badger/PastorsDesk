//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network.Crypto;

import Aura.Shared.Network.Crypto.MabiAesEngine;
import Aura.Shared.Network.Crypto.MabiCipherEngine;
import Aura.Shared.Network.Crypto.MabiKeystreamGenerator;
import java.util.Random;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public final class MabiCrypto   
{
    private boolean __ForServer;
    public boolean getForServer() {
        return __ForServer;
    }

    public void setForServer(boolean value) {
        __ForServer = value;
    }

    private uint __Seed;
    public uint getSeed() {
        return __Seed;
    }

    public void setSeed(uint value) {
        __Seed = value;
    }

    private final MabiCipherEngine _mabiCipher;
    private final MabiAesEngine _aesEngine;
    private static final Random _rnd = new Random();
    /**
    * Initializes a new instance of Crypto with a random seed
    * 
    *  @param forServer True if this instance will be responsible for packets
    * sent by the server
    */
    public MabiCrypto(boolean forServer) throws Exception {
        this(getRandomSeed(), forServer);
    }

    /**
    * Initializes a new instance of Crypto with the specified seed
    * 
    *  @param forServer True if this instance will be responsible for packets
    * sent by the server
    */
    public MabiCrypto(uint seed, boolean forServer) throws Exception {
        this.setSeed(seed);
        this.setForServer(forServer);
        MabiKeystreamGenerator keyGen = new MabiKeystreamGenerator(seed);
        _aesEngine = new MabiAesEngine(forServer,keyGen.AesKey);
        _mabiCipher = new MabiCipherEngine(keyGen);
    }

    /**
    * Returns a random seed
    * 
    *  @return
    */
    private static uint getRandomSeed() throws Exception {
        return (uint)_rnd.Next(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
    * Applies the appropriate transformation to a packet that travels
    * from the server to client
    * 
    *  @param packet 
    *  @param offset 
    *  @param count
    */
    public void fromServer(byte[] packet, int offset, int count) throws Exception {
        _aesEngine.processPacket(packet,offset,count);
    }

    /**
    * Applies the appropriate transformation to a packet that travels
    * from the server to client
    * 
    * This version uses the defaults for a standard Mabi packet
    * 
    *  @param packet
    */
    public void fromServer(byte[] packet) throws Exception {
        fromServer(packet,6,packet.length - 6);
    }

    /**
    * Applies the appropriate transformation to a packet that travels
    * from the client to server.
    * 
    *  @param packet 
    *  @param offset 
    *  @param count
    */
    public void fromClient(byte[] packet, int offset, int count) throws Exception {
        _mabiCipher.processPacket(packet,offset,count);
    }

    /**
    * Applies the appropriate transformation to a packet that travels
    * from the client to server.
    * 
    * This version uses the defaults for a standard Mabi packet
    * 
    *  @param packet
    */
    public void fromClient(byte[] packet) throws Exception {
        fromClient(packet,6,packet.length);
    }

}


