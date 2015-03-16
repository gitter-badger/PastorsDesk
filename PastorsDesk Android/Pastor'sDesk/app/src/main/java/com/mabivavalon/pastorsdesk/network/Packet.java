package com.mabivavalon.pastorsdesk.network;

import com.mabivavalon.pastorsdesk.Util._;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Darnell on 3/15/2015.
 */
public class Packet
{
    // Default size for the buffer
    private final int DefaultSize = 1024 * 2;

    // Size added, every time the buffer runs out of space
    private final int AddSize = 1024;

    // Packet's op code
    public int Op;

    // Usually sender or receiver
    public long Id;

    protected byte[] _buffer;
    protected int _ptr;
    protected int _bodyStart;
    private int _elements, _bodyLen;

    public Packet(int op, long id)
    {
        this.Op = op;
        this.Id = id;

        _buffer = new byte[DefaultSize];
    }

    public Packet(byte[] buffer, int offset)
    {
        _buffer = buffer;
        _ptr = offset;

        int length = buffer.length;

        ByteBuffer readBuffer = ByteBuffer.allocate(length).order(ByteOrder.BIG_ENDIAN);
        readBuffer.flip();

        this.Op = readBuffer.getInt(_ptr);
        this.Id = readBuffer.getInt(_ptr + 4);
        _ptr += 12;

        _<Integer> ptrRef = new _<Integer>(_ptr);

        _bodyLen = this.ReadVarInt(_buffer, ptrRef);
        _elements = this.ReadVarInt(_buffer, ptrRef);
        _ptr++; // 0x00

        _bodyStart = _ptr;
    }


}
