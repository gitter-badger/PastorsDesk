//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Const;


// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Various ids used by Mabi.
*/
public class MabiId   
{
    // These ids are used as packet ids if there's no specific
    // target for the packet, or in situations where you're technically not
    // "identifiable" by a character id.
    public static final long None = 0x0000000000000000;
    public static final long Login = 0x1000000000000010;
    public static final long Channel = 0x1000000000000001;
    public static final long Broadcast = 0x3000000000000000;
    // Id range starts
    public static final long Cards = 0x0001000000000001;
    public static final long Characters = 0x0010000000000001;
    public static final long Pets = 0x0010010000000001;
    public static final long Partners = 0x0010030000000001;
    public static final long Npcs = 0x0010F00000000001;
    public static final long Guilds = 0x0300000000500000;
    public static final long Items = 0x0050000000000001;
    public static final long QuestItems = 0x005000F000000001;
    public static final long TmpItems = 0x0050F00000000001;
    // 00XX<short:region><short:area><short:id>
    public static final long ClientProps = 0x00A0000000000000;
    public static final long ServerProps = 0x00A1000000000000;
    public static final long AreaEvents = 0x00B0000000000000;
    public static final long Parties = 0x0040000000000001;
    // Quests is probably 0x0060000000000001, but we'll leave some space
    // between quests and (quest) items, just in case.
    public static final long Quests = 0x006000F000000001;
    public static final long QuestsTmp = 0x0060F00000000001;
    public static final long QuestItemOffset = 0x0010000000000000;
    public static final long Instances = 0x0100000000000001;
    // Default type for pet/partner cards.
    public static final int PetCardType = 102;
    // NPCs
    public static final long Nao = 0x0010FFFFFFFFFFFF;
    public static final long Tin = 0x0010FFFFFFFFFFFE;
}


