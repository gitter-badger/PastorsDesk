package CS2JNet.System;

import java.util.Arrays;
import java.util.IllegalFormatException;

/**
 * Created by Darnell on 3/22/2015.
 */
public class BitConverter {
    public static final int FLAG_JAVA = 0;
    public static final int FLAG_REVERSE = -1;

    /**
     * short -&gt; byte[]
     */
    public static byte[] getBytes(short s) {
        return getBytes(s, FLAG_JAVA);
    }

    /**
     * short -&gt; byte[]
     */
    public static byte[] getBytes(short s, int flag) {
        byte[] b = new byte[2];
        switch (flag) {
            case BitConverter.FLAG_JAVA:
                b[0] = (byte) ((s >> 8) & 0xff);
                b[1] = (byte) (s & 0xff);
                break;

            case BitConverter.FLAG_REVERSE:
                b[1] = (byte) ((s >> 8) & 0xff);
                b[0] = (byte) (s & 0xff);
                break;
            default:
                break;
        }
        return b;
    }

    public static byte[] getBytes(float i) { return getBytes((int)i); }

    public static byte[] getBytes(float i, int flag) {return getBytes((int)i, flag);}

    /**
     * int -&gt; byte[]
     */
    public static byte[] getBytes(int i) {
        return getBytes(i, FLAG_JAVA);
    }

    /**
     * int -&gt; byte[]
     */
    public static byte[] getBytes(int i, int flag) {
        byte[] b = new byte[4];
        switch (flag) {
            case BitConverter.FLAG_JAVA:
                b[0] = (byte) ((i >> 24) & 0xff);
                b[1] = (byte) ((i >> 16) & 0xff);
                b[2] = (byte) ((i >> 8) & 0xff);
                b[3] = (byte) (i & 0xff);
                break;
            case BitConverter.FLAG_REVERSE:
                b[3] = (byte) ((i >> 24) & 0xff);
                b[2] = (byte) ((i >> 16) & 0xff);
                b[1] = (byte) ((i >> 8) & 0xff);
                b[0] = (byte) (i & 0xff);
                break;
            default:
                break;
        }
        return b;
    }

    /**
     * long -&gt; byte[]
     */
    public static byte[] getBytes(long i) {
        return getBytes(i, FLAG_JAVA);
    }

    /**
     * long -&gt; byte[]
     */
    public static byte[] getBytes(long i, int flag) {
        byte[] b = new byte[8];
        switch (flag) {
            case BitConverter.FLAG_JAVA:
                b[0] = (byte) ((i >> 56) & 0xff);
                b[1] = (byte) ((i >> 48) & 0xff);
                b[2] = (byte) ((i >> 40) & 0xff);
                b[3] = (byte) ((i >> 32) & 0xff);
                b[4] = (byte) ((i >> 24) & 0xff);
                b[5] = (byte) ((i >> 16) & 0xff);
                b[6] = (byte) ((i >> 8) & 0xff);
                b[7] = (byte) ((i) & 0xff);
                break;
            case BitConverter.FLAG_REVERSE:
                b[7] = (byte) ((i >> 56) & 0xff);
                b[6] = (byte) ((i >> 48) & 0xff);
                b[5] = (byte) ((i >> 40) & 0xff);
                b[4] = (byte) ((i >> 32) & 0xff);
                b[3] = (byte) ((i >> 24) & 0xff);
                b[2] = (byte) ((i >> 16) & 0xff);
                b[1] = (byte) ((i >> 8) & 0xff);
                b[0] = (byte) ((i) & 0xff);
                break;
            default:
                break;
        }
        return b;
    }

    public static short toShort(byte[] b) {
        return toShort(b, 0);
    }
    /**
     * byte[] -&gt; short
     */
    public static short toShort(byte[] b, int index) {
        return toShort(b, index, FLAG_JAVA);
    }
    /**
     * byte[] -&gt; short
     */
    public static short toShort(byte[] b, int index, int flag) {
        if (b == null)
            throw new ArgumentNullException("b");
        if (index >= b.length)
            throw new ArgumentOutOfRangeException("index");
        if (index > b.length - 4)
            throw new ArgumentException("Array too small");

        switch (flag) {
            case BitConverter.FLAG_JAVA:
                return (short) (((b[0] & 0xff) << 8) | (b[1] & 0xff));
            case BitConverter.FLAG_REVERSE:
                return (short) (((b[1] & 0xff) << 8) | (b[0] & 0xff));
            default:
                throw new IllegalArgumentException("BitConverter:toShort");
        }
    }

    public static int toInt(byte[] b) {
        return toInt(b, 0, FLAG_JAVA);
    }

    public static int toInt(byte[] b, int index) {return toInt(b, index, FLAG_JAVA); }

    public static int toInt(byte[] b, int index, int flag) {
        if (b == null)
            throw new ArgumentNullException("b");
        if (index >= b.length)
            throw new ArgumentOutOfRangeException("index");
        if (index > b.length - 4)
            throw new ArgumentException("Array too small");

        b = Arrays.copyOfRange(b, index, 4);

        switch (flag) {
            case BitConverter.FLAG_JAVA:
                return (b[0] & 0xff) << 24 | (b[1] & 0xff) << 16 | (b[2] & 0xff) << 8 | (b[3] & 0xFF);
            case BitConverter.FLAG_REVERSE:
                return (b[3] & 0xff) << 24 | (b[2] & 0xff) << 16 | (b[1] & 0xff) << 8 | (b[0] & 0xFF);
            default:
                throw new IllegalArgumentException("BitConverter:toInt");
        }
    }

    public static long toLong(byte[] b) {
        return toLong(b, 0, FLAG_JAVA);
    }

    public static long toLong(byte[] b, int index) {return toLong(b, index, FLAG_JAVA); }

    public static long toLong(byte[] b, int index, int flag) {
        if (b == null)
            throw new ArgumentNullException("b");
        if (index >= b.length)
            throw new ArgumentOutOfRangeException("index");
        if (index > b.length - 8)
            throw new ArgumentException("Array too small");

        b = Arrays.copyOfRange(b, index, 8);

        switch (flag) {
            case BitConverter.FLAG_JAVA:
                return ((long)b[0] & 0xff) << 56 |
                        (long)(b[1] & 0xff) << 48 |
                        (long)(b[2] & 0xff) << 40 |
                        (long)(b[3] & 0xFF) << 32 |
                        (long)(b[4] & 0xFF) << 24 |
                        (long)(b[5] & 0xFF) << 16 |
                        (long)(b[6] & 0xFF) << 8 |
                        (long)(b[7] & 0xFF);
            case BitConverter.FLAG_REVERSE:
                return ((long)b[7] & 0xff) << 56 |
                        (long)(b[6] & 0xff) << 48 |
                        (long)(b[5] & 0xff) << 40 |
                        (long)(b[4] & 0xFF) << 32 |
                        (long)(b[3] & 0xFF) << 24 |
                        (long)(b[2] & 0xFF) << 16 |
                        (long)(b[1] & 0xFF) << 8 |
                        (long)(b[0] & 0xFF);
            default:
                throw new IllegalArgumentException("BitConverter:toInt");
        }
    }
}
