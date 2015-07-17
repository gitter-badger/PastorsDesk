//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Const;


public enum CreatureStates
{
    // Copyright (c) Aura development team - Licensed under GNU GPL
    // For more information, see license file in the main folder
    Initialized,
    Dead,
    SitDown,
    Spawned,
    EverEnteredWorld,
    NoRespawnPenalty,
    InventoryViewDisabled,
    PetFinishMode,
    FreeRebirth,
    JustRebirthed,
    LevelResetRebirth,
    EnableCommonPvp,
    JournalVisible,
    TransformCutsceneSkip,
    EscortNpc,
    UntouchableNpc,
    InstantNpc,
    /**
    * Attackable, if not active.
    */
    GoodNpc,
    /**
    * If not active, name == race name.
    * Also required for conversation.
    */
    NamedNpc,
    /**
    * Enables conversation and name lookup
    * (in combination with NamedNpc).
    */
    Npc
}

