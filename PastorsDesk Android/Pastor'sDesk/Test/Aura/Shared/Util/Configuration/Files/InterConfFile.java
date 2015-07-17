//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Configuration.Files;

import Aura.Shared.Util.Configuration.ConfFile;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Represents inter.conf
*/
public class InterConfFile  extends ConfFile 
{
    private String __Password;
    public String getPassword() {
        return __Password;
    }

    public void setPassword(String value) {
        __Password = value;
    }

    public void load() throws Exception {
        this.Require("system/conf/inter.conf");
        this.setPassword(this.GetString("password", "change_me"));
    }

}


