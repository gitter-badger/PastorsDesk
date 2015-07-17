//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.ChannelInfo;
import Aura.Shared.Network.ServerInfo;
import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.StringSupport;
import java.util.HashMap;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public class ServerInfoManager   
{
    private HashMap<String,ServerInfo> _servers;
    public ServerInfoManager() throws Exception {
        this._servers = new HashMap<String,ServerInfo>();
    }

    public CSList<ServerInfo> getList() throws Exception {
        synchronized (_servers)
        {
            return _servers.values().ToList();
        }
    }

    /**
    * Returns true if a server with that name exists.
    * 
    *  @param serverName 
    *  @return
    */
    public boolean has(String serverName) throws Exception {
        return _servers.containsKey(serverName);
    }

    /**
    * Returns server info or null, if it doesn't exist.
    * 
    *  @param serverName 
    *  @return
    */
    public ServerInfo getServer(String serverName) throws Exception {
        ServerInfo result;
        RefSupport<ServerInfo> refVar___0 = new RefSupport<ServerInfo>();
        _servers.TryGetValue(serverName, refVar___0);
        result = refVar___0.getValue();
        return result;
    }

    /**
    * Returns channel info from server or null, if server or channel
    * doesn't exist.
    * 
    *  @param serverName 
    *  @param channelName 
    *  @return
    */
    public ChannelInfo getChannel(String serverName, String channelName) throws Exception {
        ServerInfo server = this.getServer(serverName);
        if (server == null)
            return null;
         
        return server.get(channelName);
    }

    /**
    * Returns channel info or null, if channel doesn't exist.
    * 
    *  @param fullName 
    *  @return
    */
    public ChannelInfo getChannel(String fullName) throws Exception {
        synchronized (this.getList())
        {
            {
                for (ServerInfo server : this.getList())
                {
                    for (ChannelInfo channel : server.getChannels().values())
                    {
                        if (StringSupport.equals(channel.getFullName(), fullName))
                            return channel;
                         
                    }
                }
            }
        }
        return null;
    }

    /**
    * Adds server with this name, if it doesn't exist already,
    * and returns the new or existing server info for it.
    * 
    *  @param serverName 
    *  @return
    */
    public ServerInfo add(String serverName) throws Exception {
        if (!this.has(serverName))
        {
            ServerInfo result = new ServerInfo(serverName);
            _servers.put(serverName, result);
            return result;
        }
         
        return this.getServer(serverName);
    }

    /**
    * Adds channel and server, if it doesn't exist yet.
    * Replaces channel if it already exists.
    * 
    *  @param channel
    */
    public void add(ChannelInfo channel) throws Exception {
        ServerInfo server = this.add(channel.getServerName());
        server.getChannels().put(channel.getName(), channel);
    }

}


