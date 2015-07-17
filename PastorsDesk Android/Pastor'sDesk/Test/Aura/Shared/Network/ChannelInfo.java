//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.ChannelEvent;
import Aura.Shared.Network.ChannelState;
import java.util.Date;

public class ChannelInfo   
{
    private String __Name;
    public String getName() {
        return __Name;
    }

    public void setName(String value) {
        __Name = value;
    }

    private String __ServerName;
    public String getServerName() {
        return __ServerName;
    }

    public void setServerName(String value) {
        __ServerName = value;
    }

    private String __FullName;
    public String getFullName() {
        return __FullName;
    }

    public void setFullName(String value) {
        __FullName = value;
    }

    private String __Host;
    public String getHost() {
        return __Host;
    }

    public void setHost(String value) {
        __Host = value;
    }

    private int __Port;
    public int getPort() {
        return __Port;
    }

    public void setPort(int value) {
        __Port = value;
    }

    private Date __LastUpdate;
    public Date getLastUpdate() {
        return __LastUpdate;
    }

    public void setLastUpdate(Date value) {
        __LastUpdate = value;
    }

    private ChannelState __State = ChannelState.Maintenance;
    public ChannelState getState() {
        return __State;
    }

    public void setState(ChannelState value) {
        __State = value;
    }

    private ChannelEvent __Events = ChannelEvent.None;
    public ChannelEvent getEvents() {
        return __Events;
    }

    public void setEvents(ChannelEvent value) {
        __Events = value;
    }

    /**
    * 0-100%, in increments of 5%.
    */
    public short getStress() throws Exception {
        Int32 val = (this.getUsers() * 100) / this.getMaxUsers();
        return (short)(val - val % 5);
    }

    /**
    * Current users
    */
    private int __Users;
    public int getUsers() {
        return __Users;
    }

    public void setUsers(int value) {
        __Users = value;
    }

    /**
    * Max users able to connect
    */
    private int __MaxUsers;
    public int getMaxUsers() {
        return __MaxUsers;
    }

    public void setMaxUsers(int value) {
        __MaxUsers = value;
    }

    public ChannelInfo(String name, String server, String ip, int port) throws Exception {
        this.setName(name);
        this.setServerName(server);
        this.setFullName(name + "@" + server);
        this.setHost(ip);
        this.setPort(port);
        this.setState(ChannelState.Normal);
        this.setEvents(ChannelEvent.None);
        this.setLastUpdate(Date.MinValue);
    }

}


