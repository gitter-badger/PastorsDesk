//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Configuration.Files;

import Aura.Shared.Util.Configuration.ConfFile;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Represents localization.conf
*/
public class LocalizationConfFile  extends ConfFile 
{
    private String __Language;
    public String getLanguage() {
        return __Language;
    }

    public void setLanguage(String value) {
        __Language = value;
    }

    public void load() throws Exception {
        this.Require("system/conf/localization.conf");
        this.setLanguage(this.GetString("language", "us"));
    }

}


