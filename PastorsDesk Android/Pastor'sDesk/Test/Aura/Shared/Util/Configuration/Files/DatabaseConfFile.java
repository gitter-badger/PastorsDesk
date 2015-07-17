//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Configuration.Files;

import Aura.Shared.Util.Configuration.ConfFile;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Represents database.conf
*/
public class DatabaseConfFile  extends ConfFile 
{
    private String __Host;
    public String getHost() {
        return __Host;
    }

    public void setHost(String value) {
        __Host = value;
    }

    private String __User;
    public String getUser() {
        return __User;
    }

    public void setUser(String value) {
        __User = value;
    }

    private String __Pass;
    public String getPass() {
        return __Pass;
    }

    public void setPass(String value) {
        __Pass = value;
    }

    private String __Db;
    public String getDb() {
        return __Db;
    }

    public void setDb(String value) {
        __Db = value;
    }

    public void load() throws Exception {
        this.Require("system/conf/database.conf");
        this.setHost(this.GetString("host", "127.0.0.1"));
        this.setUser(this.GetString("user", "root"));
        this.setPass(this.GetString("pass", ""));
        this.setDb(this.GetString("database", "aura"));
    }

}


