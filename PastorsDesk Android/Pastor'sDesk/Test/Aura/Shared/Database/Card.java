//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Database;


// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Represents an entry in card table.
*/
public class Card   
{
    /**
    * Card id
    */
    private long __Id;
    public long getId() {
        return __Id;
    }

    public void setId(long value) {
        __Id = value;
    }

    /**
    * Card type
    * 
    * For character cards this is the chard card id.
    * Pets are using different numbers, we default this to 102
    * for pets and partners.
    */
    private int __Type;
    public int getType() {
        return __Type;
    }

    public void setType(int value) {
        __Type = value;
    }

    /**
    * Race for this card
    * 
    * Race of the pet/partner, or 0 for char cards.
    */
    private int __Race;
    public int getRace() {
        return __Race;
    }

    public void setRace(int value) {
        __Race = value;
    }

    public Card() throws Exception {
    }

    public Card(int type, int race) throws Exception {
        this.setType(type);
        this.setRace(race);
    }

    public Card(long id, int type, int race) throws Exception {
        this(type, race);
        this.setId(id);
    }

}


