//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;


// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* A few commonly used math-related functions.
*/
public class Math2   
{
    /**
    * Returns min, if val is lower than min, max, if val is
    * greater than max, or simply val.
    * 
    *  @param min 
    *  @param max 
    *  @param val 
    *  @return
    */
    public static int clamp(int min, int max, int val) throws Exception {
        if (val < min)
            return min;
         
        if (val > max)
            return max;
         
        return val;
    }

    /**
    * Returns min, if val is lower than min, max, if val is
    * greater than max, or simply val.
    * 
    *  @param min 
    *  @param max 
    *  @param val 
    *  @return
    */
    public static float clamp(float min, float max, float val) throws Exception {
        if (val < min)
            return min;
         
        if (val > max)
            return max;
         
        return val;
    }

    /**
    * Returns min, if val is lower than min, max, if val is
    * greater than max, or simply val.
    * 
    *  @param min 
    *  @param max 
    *  @param val 
    *  @return
    */
    public static long clamp(long min, long max, long val) throws Exception {
        if (val < min)
            return min;
         
        if (val > max)
            return max;
         
        return val;
    }

    /**
    * Returns true if val is between min and max (incl).
    * 
    *  @param val 
    *  @param min 
    *  @param max 
    *  @return
    */
    public static boolean between(int val, int min, int max) throws Exception {
        return (val >= min && val <= max);
    }

}


