//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Const;

import Aura.Shared.Mabi.Const.Pocket;

/**
* Extensions for Pocket enum.
*/
public class PocketExtensions   
{
    /**
    * Returns true if pocket is an equipment pocket (incl Face and Hair).
    * 
    *  @param pocket 
    *  @return
    */
    public static boolean isEquip(/* parameter modifiers are not yet supported this */Pocket pocket) throws Exception {
        if ((pocket >= Pocket.Face && pocket <= Pocket.Accessory2) || (pocket >= Pocket.ArmorStyle && pocket <= Pocket.RobeStyle))
            return true;
         
        return false;
    }

    /**
    * Returns true if pocket is between min and max bag.
    * 
    *  @param pocket 
    *  @return
    */
    public static boolean isBag(/* parameter modifiers are not yet supported this */Pocket pocket) throws Exception {
        return (pocket >= Pocket.ItemBags && pocket <= Pocket.ItemBagsMax);
    }

}


