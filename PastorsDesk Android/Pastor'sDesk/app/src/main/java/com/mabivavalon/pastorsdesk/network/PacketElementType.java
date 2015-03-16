package com.mabivavalon.pastorsdesk.network;

/**
 * Created by Darnell on 3/15/2015.
 */
public enum PacketElementType
{
    NONE("None", (byte)0),
    BYTE("Byte", (byte)1),
    SHORT("Short", (byte)2),
    INT("Int", (byte)3),
    LONG("Long", (byte)4),
    FLOAT("Float", (byte)5),
    String("String", (byte)6),
    Bin("Bin", (byte)7);

    private final String name;
    private final byte value;

    private PacketElementType(String name, byte value)
    {
        this.name = name;
        this.value = value;
    }
}
