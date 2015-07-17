//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi;

import CS2JNet.System.LCC.Disposable;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Wrapper around zip working with strings.
* 
* Compression used for MML code. Little more than basic zlib,
* includes a header with the length of the uncompressed data.
*/
public class MabiZip   
{
    /**
    * Returns compressed version of given string.
    * 
    *  @param str 
    *  @return
    */
    public static String compress(String str) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ barr = Encoding.Unicode.GetBytes(str + '\0');
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ mout = new MemoryStream();
        try
        {
            {
                // Deflate should use optimal compression level by default (0, as defined by .NET 4.5).
                /* [UNSUPPORTED] 'var' as type is unsupported "var" */ df = new DeflateStream(mout, CompressionMode.Compress);
                try
                {
                    {
                        // Write compressed data to memory stream.
                        df.Write(barr, 0, barr.Length);
                    }
                }
                finally
                {
                    if (df != null)
                        Disposable.mkDisposable(df).dispose();
                     
                }
                // Compression method
                int cmf = 8;
                // cm
                cmf += 7 << 4;
                // cinfo
                // Flags
                int flg = 2 << 6;
                // Compression level
                int n = ((cmf * 256) + flg) % 31;
                if (n != 0)
                    flg += 31 - n;
                 
                return String.Format("{0};{1:x02}{2:x02}{3}{4:x08}", barr.Length, cmf, flg, BitConverter.ToString(mout.ToArray()).Replace("-", "").ToLower(), ComputeAdler32(barr));
            }
        }
        finally
        {
            // Check bits
            // <length>;<cmf><flg><data><checksum>
            if (mout != null)
                Disposable.mkDisposable(mout).dispose();
             
        }
    }

    /**
    * Returns decompressed version of given string.
    * 
    *  @param str 
    *  @return
    */
    public static String decompress(String str) throws Exception {
        if (str.length() < 12)
            throw new InvalidDataException("Compressed data seems too short.");
         
        // zlib header + checksum
        // Strip length and zlib header
        Int32 pos = str.indexOf(';');
        if (pos == -1)
            pos = str.indexOf("S");
         
        str = str.substring((pos > -1 ? 4 + pos + 1 : 4));
        // Hex string to byte array
        int len = str.length();
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ barr = new byte[len >> 1];
        for (int i = 0;i < len;i += 2)
            barr[i >> 1] = Convert.ToByte(str.substring(i, (i) + (2)), 16);
        // Decompress and return
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ mout = new MemoryStream();
        try
        {
            {
                /* [UNSUPPORTED] 'var' as type is unsupported "var" */ min = new MemoryStream(barr);
                try
                {
                    {
                        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ df = new DeflateStream(min, CompressionMode.Decompress);
                        try
                        {
                            {
                                df.CopyTo(mout);
                                return Encoding.Unicode.GetString(mout.ToArray());
                            }
                        }
                        finally
                        {
                            if (df != null)
                                Disposable.mkDisposable(df).dispose();
                             
                        }
                    }
                }
                finally
                {
                    if (min != null)
                        Disposable.mkDisposable(min).dispose();
                     
                }
            }
        }
        finally
        {
            if (mout != null)
                Disposable.mkDisposable(mout).dispose();
             
        }
    }

    /**
    * Returns Adler32 hash for the given byte array.
    * 
    *  @param bar 
    *  @return
    */
    public static uint computeAdler32(byte[] bar) throws Exception {
        ushort sum1 = 1, sum2 = 0;
        for (int i = 0;i < bar.length;i++)
        {
            sum1 = (ushort)((sum1 + bar[i]) % 65521);
            sum2 = (ushort)((sum1 + sum2) % 65521);
        }
        return (uint)((sum2 << 16) | sum1);
    }

}


