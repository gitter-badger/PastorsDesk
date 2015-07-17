//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Const;


public enum Locks
{
    // Copyright (c) Aura development team - Licensed under GNU GPL
    // For more information, see license file in the main folder
    /**
    * This list is based on vague information found in the client,
    * aside from the values at the end. Testing needed.
    */
    MoveRun,
    MoveWalk,
    MoveAll,
    SkillAll,
    // ...
    Summon,
    Ride,
    AviationTakeoff,
    AviationLand,
    AviationAll,
    PvpShowdown,
    PvpAll,
    ChatSend,
    ChatRecv,
    // &
    ChatNormal,
    ChatParty,
    ChatWhipser,
    ChatAll,
    // ------------------------------------------------------------------
    /**
    * Chat, Moving, [...] (0xEFFFFFFE)
    * 
    * Set on login, warping, etc.
    */
    Default,
    /**
    * Set on take off and landing
    */
    Flying
}

