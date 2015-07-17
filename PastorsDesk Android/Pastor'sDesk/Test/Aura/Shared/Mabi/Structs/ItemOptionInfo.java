//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Structs;

import Aura.Shared.Mabi.Const.AttackSpeed;
import Aura.Shared.Mabi.Const.ItemFlags;
import Aura.Shared.Mabi.Const.Pocket;

/**
* Private item info.
* 
* Explicit structure, to write LinkedPocketId as an int.
*/
public class ItemOptionInfo   
{
    public ItemOptionInfo() {
    }

    public ItemFlags Flags = ItemFlags.Unknown;
    public byte __unknown15;
    public byte __unknown16;
    public byte __unknown17;
    public int Price;
    public int SellingPrice;
    public Pocket LinkedPocketId = Pocket.None;
    public int Durability;
    public int DurabilityMax;
    public int DurabilityOriginal;
    public ushort AttackMin = new ushort();
    public ushort AttackMax = new ushort();
    public ushort InjuryMin = new ushort();
    public ushort InjuryMax = new ushort();
    public byte Balance;
    public sbyte Critical;
    public byte __unknown24;
    public byte __unknown25;
    public int Defense;
    public short Protection;
    public short EffectiveRange;
    public AttackSpeed AttackSpeed = AttackSpeed.VeryFast;
    public byte KnockCount;
    public short Experience;
    public byte EP;
    public byte Upgraded;
    public byte UpgradeMax;
    public byte WeaponType;
    public int Grade;
    public ushort Prefix = new ushort();
    public ushort Suffix = new ushort();
    public short Elemental;
    public short __unknown31;
    public int ExpireTime;
    public int StackRemainingTime;
    public int JoustPointPrice;
    public int DucatPrice;
    public int StarPrice;
    public int PonsPrice;
    public int __unknown3;
}


