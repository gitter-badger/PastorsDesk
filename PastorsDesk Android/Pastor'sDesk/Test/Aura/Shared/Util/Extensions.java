//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import Aura.Shared.Util.RandomProvider;
import java.util.List;
import java.util.Random;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see licence.txt in the main folder
public class Extensions   
{
    /**
    * Calculates differences between 2 strings.
    * 
    * http://en.wikipedia.org/wiki/Levenshtein_distance
    * 
    *  {@code 
    * "test".LevenshteinDistance("test")       // == 0
    * "test1".LevenshteinDistance("test2")     // == 1
    * "test1".LevenshteinDistance("test1 asd") // == 4
    * }
    */
    public static int levenshteinDistance(/* parameter modifiers are not yet supported this */String str, String compare, boolean caseSensitive) throws Exception {
        if (!caseSensitive)
        {
            str = str.toLowerCase();
            compare = compare.toLowerCase();
        }
         
        Int32 sLen = str.length();
        Int32 cLen = compare.length();
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ result = new int[sLen + 1, cLen + 1];
        if (sLen == 0)
            return cLen;
         
        if (cLen == 0)
            return sLen;
         
        for (int i = 0;i <= sLen;result[i, 0] = i++)
            ;
        for (int i = 0;i <= cLen;result[0, i] = i++)
            ;
        for (int i = 1;i <= sLen;i++)
        {
            for (int j = 1;j <= cLen;j++)
            {
                Int32 cost = (compare.charAt(j - 1) == str.charAt(i - 1)) ? 0 : 1;
                result[i, j] = Math.Min(Math.Min(result[i - 1, j] + 1, result[i, j - 1] + 1), result[i - 1, j - 1] + cost);
            }
        }
        return result[sLen, cLen];
    }

    /**
    * Returns a random item from the given IList
    * 
    *  @param list The list.
    *  @return
    */
    public static <T>T random(/* parameter modifiers are not yet supported this */List<T> list) throws Exception {
        return list.get(RandomProvider.get().nextInt(list.size()));
    }

    /**
    * Returns a random number between min and max (incl).
    * 
    *  @param rnd 
    *  @param min 
    *  @param max 
    *  @return
    */
    public static float between(/* parameter modifiers are not yet supported this */Random rnd, float min, float max) throws Exception {
        return (float)(min + (rnd.nextDouble() * (max - min)));
    }

}


