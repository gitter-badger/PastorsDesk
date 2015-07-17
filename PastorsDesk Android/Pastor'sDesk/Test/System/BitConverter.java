//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package System;

import CS2JNet.System.ArgumentException;
import System.BitConverter;

// Decompiled with JetBrains decompiler
// Type: System.BitConverter
// Assembly: mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089
// MVID: 25527D36-097C-455A-9C79-EEA1AD75C849
// Assembly location: C:\Windows\Microsoft.NET\Framework\v4.0.30319\mscorlib.dll
public class BitConverter   
{
    public static final boolean IsLittleEndian = true;
    public static byte[] getBytes(boolean value) throws Exception {
        return new byte[]{ value ? (byte)1 : (byte)0 };
    }

    public static byte[] getBytes(char value) throws Exception {
        return BitConverter.getBytes((short)value);
    }

    public static byte[] getBytes(short value) throws Exception {
        byte[] numArray = new byte[2];

        return numArray;
    }

    public static byte[] getBytes(int value) throws Exception {
        byte[] numArray = new byte[4];

        return numArray;
    }

    public static byte[] getBytes(long value) throws Exception {
        byte[] numArray = new byte[8];

        return numArray;
    }

    public static byte[] getBytes(ushort value) throws Exception {
        return BitConverter.getBytes((short)value);
    }

    public static byte[] getBytes(uint value) throws Exception {
        return BitConverter.getBytes((int)value);
    }

    public static byte[] getBytes(ulong value) throws Exception {
        return BitConverter.getBytes((long)value);
    }

    public static byte[] getBytes(float value) throws Exception {
        return BitConverter.GetBytes(/* [UNSUPPORTED] the pointer indirection operator is not supported "*(int*)/* [UNSUPPORTED] the address of operator is not supported "&value" */" */);
    }

    public static byte[] getBytes(double value) throws Exception {
        return BitConverter.GetBytes(/* [UNSUPPORTED] the pointer indirection operator is not supported "*(long*)/* [UNSUPPORTED] the address of operator is not supported "&value" */" */);
    }

    public static char toChar(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 2)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
        return (char)BitConverter.toInt16(value,startIndex);
    }

    public static short toInt16(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 2)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
    
    }

    public static int toInt32(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 4)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
    
    }

    public static long toInt64(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 8)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
    
    }

    public static ushort toUInt16(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 2)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
        return (ushort)BitConverter.toInt16(value,startIndex);
    }

    public static uint toUInt32(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 4)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
        return (uint)BitConverter.toInt32(value,startIndex);
    }

    public static ulong toUInt64(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 8)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
        return (ulong)BitConverter.toInt64(value,startIndex);
    }

    public static float toSingle(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 4)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
        return /* [UNSUPPORTED] the pointer indirection operator is not supported "*(float*)/* [UNSUPPORTED] the address of operator is not supported "&BitConverter.toInt32(value,startIndex)" */" */;
    }

    public static double toDouble(byte[] value, int startIndex) throws Exception {
        if (value == null)
            ThrowHelper.ThrowArgumentNullException(ExceptionArgument.value);
         
        if ((long)(uint)startIndex >= (long)value.length)
            ThrowHelper.ThrowArgumentOutOfRangeException(ExceptionArgument.startIndex, ExceptionResource.ArgumentOutOfRange_Index);
         
        if (startIndex > value.length - 8)
            ThrowHelper.ThrowArgumentException(ExceptionResource.Arg_ArrayPlusOffTooSmall);
         
        return /* [UNSUPPORTED] the pointer indirection operator is not supported "*(double*)/* [UNSUPPORTED] the address of operator is not supported "&BitConverter.toInt64(value,startIndex)" */" */;
    }

    public static String toString(byte[] value, int startIndex, int length) throws Exception {
        if (value == null)
            throw new ArgumentNullException("byteArray");
         
        if (startIndex < 0 || startIndex >= value.length && startIndex > 0)
            throw new ArgumentOutOfRangeException("startIndex", Environment.GetResourceString("ArgumentOutOfRange_StartIndex"));
         
        if (length < 0)
            throw new ArgumentOutOfRangeException("length", Environment.GetResourceString("ArgumentOutOfRange_GenericPositive"));
         
        if (startIndex > value.length - length)
            throw new ArgumentException(Environment.GetResourceString("Arg_ArrayPlusOffTooSmall"));
         
        if (length == 0)
            return "";
         
        if (length > 715827882)
            throw new ArgumentOutOfRangeException("length", Environment.GetResourceString("ArgumentOutOfRange_LengthTooLarge", (Object)715827882));
         
        int length1 = length * 3;
        char[] chArray = new char[length1];
        int num1 = startIndex;
        int index = 0;
        while (index < length1)
        {
            byte num2 = value[num1++];
            chArray[index] = BitConverter.getHexValue((int)num2 / 16);
            chArray[index + 1] = BitConverter.getHexValue((int)num2 % 16);
            chArray[index + 2] = '-';
            index += 3;
        }
        return new String(chArray, 0, chArray.length - 1);
    }

    public static String toString(byte[] value) throws Exception {
        if (value == null)
            throw new ArgumentNullException("value");
         
        return BitConverter.toString(value,0,value.length);
    }

    public static String toString(byte[] value, int startIndex) throws Exception {
        if (value == null)
            throw new ArgumentNullException("value");
         
        return BitConverter.toString(value,startIndex,value.length - startIndex);
    }

    public static boolean toBoolean(byte[] value, int startIndex) throws Exception {
        if (value == null)
            throw new ArgumentNullException("value");
         
        if (startIndex < 0)
            throw new ArgumentOutOfRangeException("startIndex", Environment.GetResourceString("ArgumentOutOfRange_NeedNonNegNum"));
         
        if (startIndex > value.length - 1)
            throw new ArgumentOutOfRangeException("startIndex", Environment.GetResourceString("ArgumentOutOfRange_Index"));
         
        return (int)value[startIndex] != 0;
    }

    public static long doubleToInt64Bits(double value) throws Exception {
        return /* [UNSUPPORTED] the pointer indirection operator is not supported "*(long*)/* [UNSUPPORTED] the address of operator is not supported "&value" */" */;
    }

    public static double int64BitsToDouble(long value) throws Exception {
        return /* [UNSUPPORTED] the pointer indirection operator is not supported "*(double*)/* [UNSUPPORTED] the address of operator is not supported "&value" */" */;
    }

    private static char getHexValue(int i) throws Exception {
        if (i < 10)
            return (char)(i + 48);
         
        return (char)(i - 10 + 65);
    }

}


