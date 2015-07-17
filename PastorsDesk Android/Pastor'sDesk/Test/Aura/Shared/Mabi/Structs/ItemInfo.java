//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Structs;

import Aura.Shared.Mabi.Const.Pocket;

// Copyright (c) Aura development team - Licensed nder GNU GPL
// For more information, see license file in the main folder
/**
* Public item info.
*/
public class ItemInfo   
{
    public ItemInfo() {
    }

    public Pocket Pocket = Pocket.None;
    private byte __unknown2;
    private byte __unknown3;
    private byte __unknown4;
    public int Id;
    public uint Color1;
    public uint Color2;
    public uint Color3;
    public ushort Amount = new ushort();
    private short __unknown7;
    public int Region;
    public int X;
    public int Y;
    /**
    * State of the item? (eg. hoods and helmets)
    */
    public byte State;
    // FigureA
    /**
    * - Ego aura level (0-21)
    * - Related to giant's beards
    */
    public byte FigureB;
    public byte FigureC;
    public byte FigureD;
    public byte KnockCount;
    private byte __unknown12;
    private byte __unknown13;
    private byte __unknown14;
}


