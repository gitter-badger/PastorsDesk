//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi;


// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public class MabiMath   
{
    /**
    * Converts Mabi's byte direction into a radian.
    * 
    * While entity packets use a byte from 0-255 for the direction,
    * props are using radian floats.
    * 
    *  @param direction 
    *  @return
    */
    public static float byteToRadian(byte direction) throws Exception {
        return (float)Math.PI * 2 / 255 * direction;
    }

    /**
    * Converts vector direction into Mabi's byte direction.
    * 
    *  @param x 
    *  @param y 
    *  @return
    */
    public static byte directionToByte(double x, double y) throws Exception {
        return (byte)(Math.Floor(Math.Atan2(y, x) / 0.02454369260617026));
    }

    /**
    * Calculates the stat bonus for eating food.
    * 
    * Formula: (Stat Boost * Hunger Filled) / (Hunger Fill * 20 * Current Age of Character)
    * Reference: http://wiki.mabinogiworld.com/view/Food_List
    * 
    *  @param boost 
    *  @param hunger 
    *  @param hungerFilled 
    *  @param age 
    *  @return
    */
    public static float foodStatBonus(double boost, double hunger, double hungerFilled, int age) throws Exception {
        return (float)((boost * hungerFilled) / (hunger * 20 * age));
    }

}


