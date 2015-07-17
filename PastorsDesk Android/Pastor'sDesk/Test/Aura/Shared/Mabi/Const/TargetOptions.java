//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Const;


public enum TargetOptions
{
    None,
    /**
    * Displays crit effect
    */
    Critical,
    /**
    * Displays "Clean Hit"
    */
    CleanHit,
    /**
    * Displays "First Hit"
    */
    FirstHit,
    /**
    * Displays "Finish"
    * 
    * This alone removes any delays between dmg and animation.
    */
    Finished,
    /**
    * ?
    * 
    * Some have it, some don't.
    */
    Result,
    /**
    * Knocks target down
    */
    KnockDownFinish,
    /**
    * ?
    * 
    * Used by Smash sometimes, looks like a knock down.
    */
    Smash,
    /**
    * Sliding back animation
    */
    KnockBack,
    /**
    * Just another knock down
    */
    KnockDown,
    /**
    * Displays "Last Blow"
    */
    FinishingHit,
    /**
    * For blue numbers
    * 
    * Damage has to be 0 for this to work, otherweise the client
    * displays that value instead, a little delayed.
    */
    ManaShield,
    // ??? = 0x4000000 // logged on a counter hit / using mana shield
    /**
    * Finished | KnockDownFinish | FinishingHit = 0x1110
    * 
    * Always active when creature dies.
    */
    FinishingKnockDown
}

