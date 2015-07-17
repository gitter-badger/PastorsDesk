//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import java.util.Random;

/**
* Extensions for Random.
*/
public class RandomExtension   
{
    /**
    * Returns random long.
    * 
    *  @param rnd 
    *  @return
    */
    public static long nextInt64(/* parameter modifiers are not yet supported this */Random rnd) throws Exception {
        return (((long)rnd.nextInt() << 8 * 4 - 1) + rnd.nextInt());
    }

}


