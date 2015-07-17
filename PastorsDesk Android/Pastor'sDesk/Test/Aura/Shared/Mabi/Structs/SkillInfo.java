//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi.Structs;

import Aura.Shared.Mabi.Const.SkillFlags;
import Aura.Shared.Mabi.Const.SkillId;
import Aura.Shared.Mabi.Const.SkillRank;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Information about a skill and its current rank
* 
* MaxRank: This probably regulates the Advance button, officials set
* it to the next valid rank (current one if you can't advance yet).
* Experience: This is the progress, in 1/1000. If it reaches 100
* (100000), the advance button appears. It will be enabled if the
* corrosponding Flag is set, though the button works either way.
* ConditionCountX: This is the progress in a specific training method,
* but in reverse. The count is set to the max and decrements afterwards.
* The condition is done once it reaches 0.
*/
public class SkillInfo   
{
    public SkillInfo() {
    }

    public SkillId Id = SkillId.None;
    public short Version;
    public SkillRank Rank = SkillRank.Novice;
    public SkillRank MaxRank = SkillRank.Novice;
    private byte __unknown6;
    private byte __unknown7;
    public int Experience;
    public short Count;
    public SkillFlags Flag = SkillFlags.Shown;
    public long LastPromotionTime;
    public short PromotionCount;
    public short __unknown26;
    // -1
    public int PromotionExp;
    public short ConditionCount1;
    public short ConditionCount2;
    public short ConditionCount3;
    public short ConditionCount4;
    public short ConditionCount5;
    public short ConditionCount6;
    public short ConditionCount7;
    public short ConditionCount8;
    public short ConditionCount9;
    public short __unknown50;
}


// 113