//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import java.util.Random;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Thread-safe provider for "Random" instances. Use whenever no custom
* seed is required.
*/
public class RandomProvider   
{
    private static final Random _seed = new Random();
    private static ThreadLocal<Random> randomWrapper = new ThreadLocal<Random>(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "() => {
        synchronized (_seed)
        {
            return new Random(_seed.nextInt());
        }
    }" */);
    /**
    * Returns an instance of Random for the calling thread.
    * 
    *  @return
    */
    public static Random get() throws Exception {
        return randomWrapper.Value;
    }

}


