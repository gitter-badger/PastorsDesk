//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.Packet;
import Aura.Shared.Network.PacketElementType;
import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.JavaSupport.language.ReturnPreOrPostValue;
import CS2JNet.JavaSupport.util.LocaleSupport;
import CS2JNet.System.ArgumentException;
import CS2JNet.System.IntegerSupport;
import CS2JNet.System.Net.IPAddress;
import CS2JNet.System.NumberSupport;
import CS2JNet.System.StringSupport;
import CS2JNet.System.Text.EncodingSupport;
import java.util.Date;
import java.util.Locale;
import System.BitConverter;

/**
* General packet, used by Login and World.
*/
public class Packet   
{
    /**
    * Default size for the buffer
    */
    private static final int DefaultSize = 1024 * 2;
    /**
    * Size added, every time the buffer runs out of space
    */
    private static final int AddSize = 1024;
    protected byte[] _buffer;
    protected int _ptr;
    protected int _bodyStart;
    private int _elements, _bodyLen;
    /**
    * Packet's op code
    */
    private int __Op;
    public int getOp() {
        return __Op;
    }

    public void setOp(int value) {
        __Op = value;
    }

    /**
    * Usually sender or receiver
    */
    private long __Id;
    public long getId() {
        return __Id;
    }

    public void setId(long value) {
        __Id = value;
    }

    public Packet(int op, long id) throws Exception {
        this.setOp(op);
        this.setId(id);
        _buffer = new byte[DefaultSize];
    }

    public Packet(byte[] buffer, int offset) throws Exception {
        _buffer = buffer;
        _ptr = offset;
        Int32 length = buffer.length;
        this.setOp(IPAddress.NetworkToHostOrder(BitConverter.toInt32(_buffer,_ptr)));
        this.setId(IPAddress.NetworkToHostOrder(BitConverter.toInt64(_buffer,_ptr +)));
        _ptr += 12;
        RefSupport<Integer> refVar___0 = new RefSupport<Integer>(_ptr);
        _bodyLen = this.readVarInt(_buffer,refVar___0);
        _ptr = refVar___0.getValue();
        RefSupport<Integer> refVar___1 = new RefSupport<Integer>(_ptr);
        _elements = this.readVarInt(_buffer,refVar___1);
        _ptr = refVar___1.getValue();
        _ptr++;
        // 0x00
        _bodyStart = _ptr;
    }

    /**
    * Resets packet to zero while setting a new op and id,
    * without allocating a new buffer.
    * 
    *  @param op 
    *  @param id
    */
    public void clear(int op, long id) throws Exception {
        this.setOp(op);
        this.setId(id);
        Array.Clear(_buffer, 0, _buffer.length);
        _ptr = 0;
        _bodyStart = 0;
        _elements = 0;
        _bodyLen = 0;
    }

    /**
    * Returns the next element's type.
    * 
    *  @return
    */
    public PacketElementType peek() throws Exception {
        if (_ptr + 2 > _buffer.length)
            return PacketElementType.None;
         
        return (PacketElementType)_buffer[_ptr];
    }

    /**
    * Returns true if the next element to be read is of type.
    * 
    *  @param type 
    *  @return
    */
    public boolean nextIs(PacketElementType type) throws Exception {
        return (this.peek() == type);
    }

    /**
    * Returns new empty packet.
    * 
    *  @return
    */
    public static Packet empty() throws Exception {
        return new Packet(0,0);
    }

    // Write
    // ------------------------------------------------------------------
    /**
    * Adds one byte for type and the bytes in val to buffer.
    * 
    *  @param type 
    *  @param val 
    *  @return
    */
    protected Packet putSimple(PacketElementType type, byte... val) throws Exception {
        Int32 len = 1 + val.length;
        this.ensureSize(len);
        _buffer[_ptr++] = (byte)(((Enum)type).ordinal());
        Buffer.BlockCopy(val, 0, _buffer, _ptr, val.length);
        _ptr += val.length;
        _elements++;
        _bodyLen += len;
        return this;
    }

    /**
    * Adds one byte for type, 2 bytes for the length of the val bytes,
    * and the vals itself to buffer.
    * 
    *  @param type 
    *  @param val 
    *  @return
    */
    protected Packet putWithLength(PacketElementType type, byte[] val) throws Exception {
        Int32 len = 1 + + val.length;
        this.ensureSize(len);
        _buffer[_ptr++] = (byte)(((Enum)type).ordinal());
        Buffer.BlockCopy(BitConverter.GetBytes(IPAddress.HostToNetworkOrder((short)val.length)), 0, _buffer, _ptr);
        _ptr += 2;
        Buffer.BlockCopy(val, 0, _buffer, _ptr, val.length);
        _ptr += val.length;
        _elements++;
        _bodyLen += len;
        return this;
    }

    /**
    * Writes val to buffer.
    */
    public Packet putByte(byte val) throws Exception {
        return this.putSimple(PacketElementType.Byte,val);
    }

    /**
    * Writes val as byte to buffer.
    */
    public Packet putByte(boolean val) throws Exception {
        return this.putByte(val ? (byte)1 : (byte)0);
    }

    /**
    * Writes val to buffer.
    */
    public Packet putShort(short val) throws Exception {
        return this.PutSimple(PacketElementType.Short, BitConverter.GetBytes(IPAddress.HostToNetworkOrder(val)));
    }

    /**
    * Writes val to buffer.
    */
    public Packet putUShort(ushort val) throws Exception {
        return this.putShort((short)val);
    }

    /**
    * Writes val to buffer.
    */
    public Packet putInt(int val) throws Exception {
        return this.PutSimple(PacketElementType.Int, BitConverter.GetBytes(IPAddress.HostToNetworkOrder(val)));
    }

    /**
    * Writes val to buffer.
    */
    public Packet putUInt(uint val) throws Exception {
        return this.putInt((int)val);
    }

    /**
    * Writes val to buffer.
    */
    public Packet putLong(long val) throws Exception {
        return this.PutSimple(PacketElementType.Long, BitConverter.GetBytes(IPAddress.HostToNetworkOrder(val)));
    }

    /**
    * Writes val to buffer.
    */
    public Packet putULong(ulong val) throws Exception {
        return this.putLong((long)val);
    }

    /**
    * Writes val as long to buffer.
    */
    public Packet putLong(Date val) throws Exception {
        return this.putLong((long)(val.Ticks / 10000));
    }

    /**
    * Writes val to buffer.
    */
    public Packet putFloat(float val) throws Exception {
        return this.putSimple(PacketElementType.Float,BitConverter.getBytes(val));
    }

    /**
    * Writes val to buffer.
    */
    public Packet putFloat(double val) throws Exception {
        return this.putFloat((float)val);
    }

    /**
    * Writes val as null-terminated UTF8 string to buffer.
    */
    public Packet putString(String val) throws Exception {
        return this.putWithLength(PacketElementType.String,EncodingSupport.GetEncoder("UTF-8").getBytes(val + "\0"));
    }

    /**
    * Writes val as null-terminated UTF8 string to buffer.
    */
    public Packet putString(String format, Object... args) throws Exception {
        return this.putString(String.format(StringSupport.CSFmtStrToJFmtStr((format != null ? format : "")),args));
    }

    /**
    * Writes val to buffer.
    */
    public Packet putBin(byte[] val) throws Exception {
        return this.putWithLength(PacketElementType.Bin,val);
    }

    /**
    * Writes bin containing a single 0 to buffer.
    */
    public Packet putBin() throws Exception {
        return this.putBin(new byte[]{ 0 });
    }

    /**
    * Converts struct to byte array and writes it as byte array to buffer.
    */
    public Packet putBin(Object val) throws Exception {
        Type type = val.getClass();
        if (!type.IsValueType || type.IsPrimitive)
            throw new Exception("PutBin only takes byte[] and structs.");
         
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ size = Marshal.SizeOf(val);
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ arr = new byte[size];
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ ptr = Marshal.AllocHGlobal(size);
        Marshal.StructureToPtr(val, ptr, true);
        Marshal.Copy(ptr, arr, 0, size);
        Marshal.FreeHGlobal(ptr);
        return this.putBin(arr);
    }

    /**
    * Writes packet as bin and the length of it as int to buffer.
    */
    public Packet putBin(Packet packet) throws Exception {
        Byte[] val = packet.build();
        return this.putInt(val.length).putBin(val);
    }

    /**
    * Resizes buffer, if there's not enough space for the required
    * amount of bytes.
    * 
    *  @param required
    */
    protected void ensureSize(int required) throws Exception {
        if (_ptr + required >= _buffer.length)
        {
            RefSupport refVar___2 = new RefSupport(_buffer);
            Array.Resize(refVar___2, _buffer.length + Math.max(AddSize,required * 2));
            _buffer = refVar___2.getValue();
        }
         
    }

    // Read
    // ------------------------------------------------------------------
    /**
    * Reads and returns byte from buffer.
    * 
    *  @return
    */
    public byte getByte() throws Exception {
        if (this.peek() != PacketElementType.Byte)
            throw new Exception("Expected Byte, got " + this.peek() + ".");
         
        _ptr += 1;
        return _buffer[_ptr++];
    }

    /**
    * Reads and returns bool (byte) from buffer.
    * 
    *  @return
    */
    public boolean getBool() throws Exception {
        return (this.getByte() != 0);
    }

    /**
    * Reads and returns short from buffer.
    * 
    *  @return
    */
    public short getShort() throws Exception {
        if (this.peek() != PacketElementType.Short)
            throw new Exception("Expected Short, got " + this.peek() + ".");
         
        _ptr += 1;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ val = IPAddress.HostToNetworkOrder(BitConverter.toInt16(_buffer,_ptr));
        _ptr += ;
        return val;
    }

    /**
    * Reads and returns ushort from buffer.
    * 
    *  @return
    */
    public ushort getUShort() throws Exception {
        return (ushort)this.getShort();
    }

    /**
    * Reads and returns int from buffer.
    * 
    *  @return
    */
    public int getInt() throws Exception {
        if (this.peek() != PacketElementType.Int)
            throw new Exception("Expected Int, got " + this.peek() + ".");
         
        _ptr += 1;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ val = IPAddress.HostToNetworkOrder(BitConverter.toInt32(_buffer,_ptr));
        _ptr += ;
        return val;
    }

    /**
    * Reads and returns uint from buffer.
    * 
    *  @return
    */
    public uint getUInt() throws Exception {
        return (uint)this.getInt();
    }

    /**
    * Reads and returns long from buffer.
    * 
    *  @return
    */
    public long getLong() throws Exception {
        if (this.peek() != PacketElementType.Long)
            throw new Exception("Expected Long, got " + this.peek() + ".");
         
        _ptr += 1;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ val = IPAddress.HostToNetworkOrder(BitConverter.toInt64(_buffer,_ptr));
        _ptr += ;
        return val;
    }

    /**
    * Reads and returns float from buffer.
    * 
    *  @return
    */
    public float getFloat() throws Exception {
        if (this.peek() != PacketElementType.Float)
            throw new Exception("Expected Float, got " + this.peek() + ".");
         
        _ptr += 1;
        Single val = BitConverter.toSingle(_buffer,_ptr);
        _ptr += 4;
        return val;
    }

    /**
    * Reads and returns string from buffer.
    * 
    *  @return
    */
    public String getString() throws Exception {
        if (this.peek() != PacketElementType.String)
            throw new ArgumentException("Expected String, got " + this.peek() + ".");
         
        _ptr += 1;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ len = IPAddress.NetworkToHostOrder(BitConverter.toInt16(_buffer,_ptr));
        _ptr += 2;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ val = EncodingSupport.GetEncoder("UTF-8").GetString(_buffer, _ptr, len - 1);
        _ptr += len;
        return val;
    }

    /**
    * Reads and returns bin from buffer.
    * 
    *  @return
    */
    public byte[] getBin() throws Exception {
        if (this.peek() != PacketElementType.Bin)
            throw new ArgumentException("Expected Bin, got " + this.peek() + ".");
         
        _ptr += 1;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ len = IPAddress.NetworkToHostOrder(BitConverter.toInt16(_buffer,_ptr));
        _ptr += 2;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ val = new byte[len];
        Buffer.BlockCopy(_buffer, _ptr, val, 0, len);
        _ptr += len;
        return val;
    }

    // ------------------------------------------------------------------
    /**
    * Reads variable lengthed number from buffer.
    * 
    * Used for the counters after op and id.
    * http://en.wikipedia.org/wiki/LEB128
    * 
    *  @param buffer 
    *  @param ptr 
    *  @return
    */
    private int readVarInt(byte[] buffer, RefSupport<Integer> ptr) throws Exception {
        Integer result = 0;
        for (Integer i = 0;;++i)
        {
            result |= (buffer[ptr.getValue()] & 0x7f) << (i * 7);
            if ((buffer[ptr.setValue(ptr.getValue() + 1, ReturnPreOrPostValue.POST)] & 0x80) == 0)
                break;
             
        }
        return result;
    }

    /**
    * Writes variable lengthed number to buffer.
    * 
    * Used for the counters after op and id.
    * http://en.wikipedia.org/wiki/LEB128
    * 
    *  @param value 
    *  @param buffer 
    *  @param ptr
    */
    private void writeVarInt(int value, byte[] buffer, RefSupport<Integer> ptr) throws Exception {
        do
        {
            buffer[ptr.setValue(ptr.getValue() + 1, ReturnPreOrPostValue.POST)] = (byte)(value > 0x7F ? (0x80 | (value & 0xFF)) : value & 0xFF);
        }
        while ((value >>= 7) != 0);
    }

    // ------------------------------------------------------------------
    /**
    * Returns size of the whole packet, incl header.
    * 
    *  @return
    */
    public Integer getSize() throws Exception {
        Int32 i = 4 + 8;
        // op + id + body
        Integer n = _bodyLen;
        do
        {
            // + body len
            i++;
            n >>= 7;
        }
        while (n != 0);
        n = _elements;
        do
        {
            // + number of elements
            i++;
            n >>= 7;
        }
        while (n != 0);
        ++i;
        // + zero
        i += _bodyLen;
        return i;
    }

    // + body
    /**
    * Returns complete packet as byte array.
    * 
    *  @return
    */
    public byte[] build() throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ result = new byte[this.getSize()];
        RefSupport refVar___3 = new RefSupport(result);
        this.Build(refVar___3, 0);
        result = refVar___3.getValue();
        return result;
    }

    /**
    * Returns complete packet as byte array.
    * 
    *  @return
    */
    public void build(RefSupport<byte[]> buffer, int offset) throws Exception {
        if (buffer.getValue().length < offset + this.getSize())
            throw new Exception("Buffer too small for packet, use GetSize().");
         
        Int32 length = _bodyLen;
        {
            // Header
            // Op + Id
            Buffer.BlockCopy(BitConverter.GetBytes(IPAddress.HostToNetworkOrder(this.getOp())), 0, buffer.getValue(), offset);
            Buffer.BlockCopy(BitConverter.GetBytes(IPAddress.HostToNetworkOrder(this.getId())), 0, buffer.getValue(), offset +);
            offset += 12;
            // Body len
            RefSupport<Integer> refVar___4 = new RefSupport<Integer>(offset);
            this.writeVarInt(_bodyLen,buffer.getValue(),refVar___4);
            offset = refVar___4.getValue();
            // Element amount
            RefSupport<Integer> refVar___5 = new RefSupport<Integer>(offset);
            this.writeVarInt(_elements,buffer.getValue(),refVar___5);
            offset = refVar___5.getValue();
            buffer.getValue()[offset++] = 0;
            length += offset;
        }
        // Body
        //_bodyStart = offset;
        Buffer.BlockCopy(_buffer, _bodyStart, buffer.getValue(), offset, _bodyLen);
    }

    /**
    * Returns true if type is a valid value of the enum and not None.
    * 
    *  @param type 
    *  @return
    */
    private boolean isValidType(PacketElementType type) throws Exception {
        return (type >= PacketElementType.Byte && type <= PacketElementType.Bin);
    }

    public String toString() {
        try
        {
            StringBuilder result = new StringBuilder();
            Int32 prevPtr = _ptr;
            _ptr = _bodyStart;
            result.append(String.format(StringSupport.CSFmtStrToJFmtStr("Op: {0:X08}, Id: {1:X016}" + System.getProperty("line.separator")),this.getOp(),this.getId()));
            PacketElementType type = PacketElementType.None;
            for (int i = 1;(this.IsValidType(type = this.peek()) && _ptr < _buffer.length);++i)
            {
                if (type == PacketElementType.Byte)
                {
                    Byte data = this.getByte();
                    result.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0:000} [{1}] Byte   : {2}"),i,StringSupport.PadLeft(NumberSupport.format(data, "X2"), 16, '.'),data));
                }
                else if (type == PacketElementType.Short)
                {
                    Int16 data = this.getShort();
                    result.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0:000} [{1}] Short  : {2}"),i,StringSupport.PadLeft(NumberSupport.format(data, "X4"), 16, '.'),data));
                }
                else if (type == PacketElementType.Int)
                {
                    Int32 data = this.getInt();
                    result.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0:000} [{1}] Int    : {2}"),i,StringSupport.PadLeft(IntegerSupport.mkString(data, "X8"), 16, '.'),data));
                }
                else if (type == PacketElementType.Long)
                {
                    Int64 data = this.getLong();
                    result.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0:000} [{1}] Long   : {2}"),i,NumberSupport.format(data, "X16"),data));
                }
                else if (type == PacketElementType.Float)
                {
                    Single data = this.getFloat();
                    result.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0:000} [{1}] Float  : {2}"),i,(BitConverter.DoubleToInt64Bits(data) >> 32).ToString("X8").PadLeft(16, '.'),data.ToString("0.0####", LocaleSupport.INVARIANT)));
                }
                else if (type == PacketElementType.String)
                {
                    String data = this.getString();
                    result.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0:000} [................] String : {1}"),i,data));
                }
                else if (type == PacketElementType.Bin)
                {
                    String data = BitConverter.toString(this.getBin());
                    String[] splitted = StringSupport.Split(data, '-');
                    result.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0:000} [................] Bin    : "),i));
                    for (Int32 j = 1;j <= splitted.length;++j)
                    {
                        result.append(splitted[j - 1]);
                        if (j < splitted.length)
                            if (j % 16 == 0)
                                result.append(StringSupport.PadRight(System.getProperty("line.separator"), 34, ' '));
                            else
                                result.append(' '); 
                         
                    }
                }
                       
                result.AppendLine();
            }
            _ptr = prevPtr;
            return result.toString();
        }
        catch (RuntimeException __dummyCatchVar0)
        {
            throw __dummyCatchVar0;
        }
        catch (Exception __dummyCatchVar0)
        {
            throw new RuntimeException(__dummyCatchVar0);
        }
    
    }

}


