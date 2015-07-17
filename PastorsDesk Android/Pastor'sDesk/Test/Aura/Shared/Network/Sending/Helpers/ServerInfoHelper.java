//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network.Sending.Helpers;

import Aura.Shared.Network.ChannelInfo;
import Aura.Shared.Network.Packet;
import Aura.Shared.Network.Sending.Helpers.ServerInfoType;
import Aura.Shared.Network.ServerInfo;
import CS2JNet.System.Collections.LCC.ICollection;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public class ServerInfoHelper   
{
    /**
    * Adds list of server and channel information to packet.
    * 
    *  @param packet 
    *  @param serverList 
    *  @param type
    */
    public static void addServerList(/* parameter modifiers are not yet supported this */Packet packet, ICollection<ServerInfo> serverList, ServerInfoType type) throws Exception {
        packet.putByte((byte)serverList.Count);
        for (ServerInfo server : serverList)
            packet.AddServerInfo(server, type);
    }

    /**
    * Adds server and channel information to packet.
    * 
    *  @param packet 
    *  @param server 
    *  @param type
    */
    public static void addServerInfo(/* parameter modifiers are not yet supported this */Packet packet, ServerInfo server, ServerInfoType type) throws Exception {
        packet.putString(server.getName());
        packet.PutShort(0);
        // Server type?
        packet.PutShort(0);
        packet.PutByte(1);
        // Channels
        packet.putInt(((int)(server.getChannels().size())));
        for (ChannelInfo channel : server.getChannels().values())
            packet.AddChannelInfo(channel, type);
    }

    /**
    * Adds channel information to packet.
    * 
    *  @param packet 
    *  @param channel 
    *  @param type
    */
    public static void addChannelInfo(/* parameter modifiers are not yet supported this */Packet packet, ChannelInfo channel, ServerInfoType type) throws Exception {
        packet.putString(channel.getName());
        packet.putInt(((Enum)channel.getState()).ordinal());
        packet.putInt(((Enum)channel.getEvents()).ordinal());
        packet.putInt(0);
        // 1 for Housing? Hidden?
        packet.putShort(channel.getStress());
        // Channels need more information
        if (type == ServerInfoType.Internal)
        {
            packet.putString(channel.getHost());
            packet.putInt(channel.getPort());
            packet.putInt(channel.getUsers());
        }
         
    }

}


