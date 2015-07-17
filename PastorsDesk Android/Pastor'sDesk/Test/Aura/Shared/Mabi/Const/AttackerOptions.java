//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Const;


public enum AttackerOptions
{
    // Copyright (c) Aura development team - Licensed under GNU GPL
    // For more information, see license file in the main folder
    None,
    /**
    * Difference between KBH 1 and 2 unknown. (0x02)
    */
    KnockBackHit1,
    /**
    * Difference between KBH 1 and 2 unknown. (0x04)
    * 
    * Required for knock back hit animation.
    */
    KnockBackHit2,
    /**
    * Prop effect? Thunderbolts and fireball explosion (0x08)
    */
    UseEffect,
    /**
    * Req for some skills (0x20)
    * 
    * Didn't show for fireball or thunder.
    * Seems to be required for proper knock back.
    */
    Result,
    /**
    * Set when using two weapons (0x40)
    */
    DualWield,
    /**
    * Shows "First Hit"? (0x400)
    */
    FirstHit
}

