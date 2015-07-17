//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.ChannelInfo;
import CS2JNet.JavaSupport.language.RefSupport;
import java.util.HashMap;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public class ServerInfo   
{
    private String __Name;
    public String getName() {
        return __Name;
    }

    public void setName(String value) {
        __Name = value;
    }

    private HashMap<String,ChannelInfo> __Channels;
    public HashMap<String,ChannelInfo> getChannels() {
        return __Channels;
    }

    public void setChannels(HashMap<String,ChannelInfo> value) {
        __Channels = value;
    }

    public ServerInfo(String name) throws Exception {
        this.setName(name);
        this.setChannels(new HashMap<String,ChannelInfo>());
    }

    /**
    * Returns channel info or null, if it doesn't exist.
    * 
    *  @param channelName 
    *  @return
    */
    public ChannelInfo get(String channelName) throws Exception {
        ChannelInfo result;
        /**
        * Status of the channel
        * 
        * This needs to be update to reflect the stress value.
        * 
        * What events are going on
        */
        RefSupport<ChannelInfo> refVar___0 = new RefSupport<ChannelInfo>();
        this.getChannels().TryGetValue(channelName, refVar___0);
        result = refVar___0.getValue();
        return result;
    }

}


