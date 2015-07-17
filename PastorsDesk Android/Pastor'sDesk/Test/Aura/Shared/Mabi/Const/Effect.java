//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Const;


// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
// No enum because we don't need type safety and string conversion,
// but something that can be passed to a function. (No casts ftw.)
public class Effect   
{
    public static final int Revive = 4;
    /**
    * Used when picking up a dungeon key.
    * int:itemId, int:?, int:?, int:?
    */
    public static final int PickUpKey = 8;
    /**
    * Logged values: "", "healing", "flashing", "thunder", "icespear"
    * string:?
    */
    public static final int SkillInit = 11;
    /**
    * Logged values: "healing", "thunder", "icespear"
    * string:?
    */
    public static final int HoldMagic = 12;
    /**
    * Logged values: "healing_stack"
    * string:?, byte:amount?, byte:0
    */
    public static final int StackUpdate = 13;
    /**
    * Logged values: "healing_firstaid", "healing", "healing_phoenix", "thunder"
    * string:?, long:targetCreatureId
    * Healing Effects?
    */
    public static final int UseMagic = 14;
    /**
    * b:type, i|s:song, i:?, si:?, i:?, b:quality?, b:instrument, b:?, b:?, b:loops
    */
    public static final int PlayMusic = 17;
    /**
    * On music complete
    */
    public static final int StopMusic = 18;
    /**
    * Used for various pet actions, like dancing, admiring, etc.
    * long:masterId?, byte:0~?, byte:0
    */
    public static final int PetAction = 19;
    /**
    * White flash.
    * int:duration, int:0
    */
    public static final int ScreenFlash = 27;
    /**
    * int:region, float:x, float:y, byte:type (0=monster,1=pet,2=pet_despawn,3=monster_despawn,4=golem,5=golem_despawn)
    */
    public static final int Spawn = 29;
    /**
    * Sent by thunder
    */
    public static final int Lightningbolt = 30;
    /**
    * Fireball in the air. int:Region, float:fromx, float:fromy, float:tox, float:toy, int:time, byte:0
    */
    public static final int FireballFly = 39;
    /**
    * The frozen effect of Ice Spear
    */
    public static final int IcespearFreeze = 65;
    public static final int IcespearBoom = 66;
    // [190100, NA201 (2015-02-14)] Something was added somewhere,
    // which increased all following values by 1. Yay enums. Spawn
    // and Flash still worked though, it was afterwards.
    /**
    * The teleport effect for Silent Move and Final Hit
    */
    public static final int SilentMoveTeleport = 68;
    /**
    * Effect shown while Final Hit is active.
    */
    public static final int FinalHit = 70;
    /**
    * Chef Owl
    */
    public static final int ChefOwl = 122;
    /**
    * Blue Aura used when activating Mana Shield
    * 
    * According to older logs, this should've been 121,
    * something had been added when we got to implement it.
    */
    public static final int ManaShield = 123;
    /**
    * Parameters: None
    */
    public static final int AwakeningOfLight1 = 174;
    /**
    * Parameters: None
    */
    public static final int AwakeningOfLight2 = 177;
    public static final int SupportShot = 241;
    /**
    * ?
    */
    public static final int Casting = 248;
    /**
    * Shadow Bunshin casting, clones, etc.
    */
    public static final int ShadowBunshin = 263;
    /**
    * Used in thunder's final stage
    */
    public static final int Thunderbolt = 298;
    /**
    * Cherry blossoms falling onto the character.
    * byte:1|0 (on/off)
    */
    public static final int CherryBlossoms = 346;
    /**
    * Used for Outfit Action.
    * byte:1|0 (on/off)
    */
    public static final int OutfitAction = 366;
}


