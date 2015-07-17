//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Const;


public enum CombatActionType
{
    // Most likely flags
    None,
    /**
    * Target takes simple hit (0x01)
    */
    TakeHit,
    /**
    * Simple hit by Source (0x02)
    */
    Hit,
    __dummyEnum__0,
    __dummyEnum__1,
    __dummyEnum__2,
    /**
    * Both hit at the same time (0x06)
    */
    SimultaneousHit,
    __dummyEnum__3,
    __dummyEnum__4,
    __dummyEnum__5,
    __dummyEnum__6,
    __dummyEnum__7,
    __dummyEnum__8,
    __dummyEnum__9,
    __dummyEnum__10,
    __dummyEnum__11,
    __dummyEnum__12,
    __dummyEnum__13,
    __dummyEnum__14,
    /**
    * Alternative target type for Counter? (0x13)
    */
    CounteredHit2,
    __dummyEnum__15,
    __dummyEnum__16,
    __dummyEnum__17,
    __dummyEnum__18,
    __dummyEnum__19,
    __dummyEnum__20,
    __dummyEnum__21,
    __dummyEnum__22,
    __dummyEnum__23,
    __dummyEnum__24,
    __dummyEnum__25,
    __dummyEnum__26,
    __dummyEnum__27,
    __dummyEnum__28,
    __dummyEnum__29,
    __dummyEnum__30,
    __dummyEnum__31,
    __dummyEnum__32,
    __dummyEnum__33,
    __dummyEnum__34,
    __dummyEnum__35,
    __dummyEnum__36,
    __dummyEnum__37,
    __dummyEnum__38,
    __dummyEnum__39,
    __dummyEnum__40,
    __dummyEnum__41,
    __dummyEnum__42,
    __dummyEnum__43,
    __dummyEnum__44,
    /**
    * Smash/Counter (0x32)
    */
    HardHit,
    /**
    * Target type Defense (0x33)
    */
    Defended,
    __dummyEnum__45,
    __dummyEnum__46,
    __dummyEnum__47,
    __dummyEnum__48,
    __dummyEnum__49,
    __dummyEnum__50,
    __dummyEnum__51,
    __dummyEnum__52,
    __dummyEnum__53,
    __dummyEnum__54,
    __dummyEnum__55,
    __dummyEnum__56,
    __dummyEnum__57,
    __dummyEnum__58,
    // Target type with Mana Shield
    // ??? = 0x41,
    /**
    * Passive Damage, Shadow Bunshin/Fireball (0x42)
    */
    SpecialHit,
    __dummyEnum__59,
    __dummyEnum__60,
    __dummyEnum__61,
    __dummyEnum__62,
    __dummyEnum__63,
    __dummyEnum__64,
    __dummyEnum__65,
    __dummyEnum__66,
    __dummyEnum__67,
    __dummyEnum__68,
    __dummyEnum__69,
    __dummyEnum__70,
    __dummyEnum__71,
    __dummyEnum__72,
    __dummyEnum__73,
    __dummyEnum__74,
    /**
    * Target type for Counter (0x53)
    */
    CounteredHit,
    __dummyEnum__75,
    __dummyEnum__76,
    __dummyEnum__77,
    __dummyEnum__78,
    __dummyEnum__79,
    __dummyEnum__80,
    __dummyEnum__81,
    __dummyEnum__82,
    __dummyEnum__83,
    __dummyEnum__84,
    __dummyEnum__85,
    __dummyEnum__86,
    __dummyEnum__87,
    __dummyEnum__88,
    __dummyEnum__89,
    __dummyEnum__90,
    __dummyEnum__91,
    __dummyEnum__92,
    __dummyEnum__93,
    __dummyEnum__94,
    __dummyEnum__95,
    __dummyEnum__96,
    __dummyEnum__97,
    __dummyEnum__98,
    __dummyEnum__99,
    __dummyEnum__100,
    __dummyEnum__101,
    __dummyEnum__102,
    __dummyEnum__103,
    __dummyEnum__104,
    /**
    * Magicbolt, range, doing Counter? (0x72)
    */
    RangeHit
}

//DefendedHit = 0x73, // ?