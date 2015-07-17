//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;


public enum ChannelState
{
    /**
    * Server is offline for maint
    */
    Maintenance,
    /**
    * Server is online and stress is [0,40) (less than 40%)
    */
    Normal,
    /**
    * Server is online and stress is [40,70) (between 40% and 70%)
    */
    Busy,
    /**
    * Server is online and stress is [70,95) (between 70% and 95%)
    */
    Full,
    /**
    * Server is online and stress is [95,+∞) (greater than 95%)
    * 
    * In this state, the client won't allow you to move to the channel
    */
    Bursting,
    /**
    * This state has never been directly observed. Maybe used internally,
    * or possibly if a channel crashes.
    * 
    * Shows up as [Maintenance] clientside.
    */
    Booting,
    /**
    * Any other value is interpreted as [Error], unknown if this affects
    * client behavior.
    */
    Error
}

