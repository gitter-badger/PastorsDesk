//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Configuration.Files;

import Aura.Shared.Util.Configuration.ConfFile;
import Aura.Shared.Util.LogLevel;
import CS2JNet.System.StringSupport;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Represents log.conf
*/
public class LogConfFile  extends ConfFile 
{
    private boolean __Archive;
    public boolean getArchive() {
        return __Archive;
    }

    public void setArchive(boolean value) {
        __Archive = value;
    }

    private LogLevel __Hide = LogLevel.Info;
    public LogLevel getHide() {
        return __Hide;
    }

    public void setHide(LogLevel value) {
        __Hide = value;
    }

    public void load() throws Exception {
        this.Require("system/conf/log.conf");
        this.setArchive(this.GetBool("archive", true));
        this.setHide((LogLevel)this.GetInt("cmd_hide", ((Enum)(LogLevel.Debug)).ordinal()));
        if (this.getArchive())
            Aura.Shared.Util.Log.setArchive("log/archive/");
         
        Aura.Shared.Util.Log.setLogFile(String.format(StringSupport.CSFmtStrToJFmtStr("log/{0}.txt"),System.AppDomain.CurrentDomain.FriendlyName.Replace(".exe", "").Replace(".vshost", "")));
        Aura.Shared.Util.Log.setHide(Aura.Shared.Util.Log.getHide() | this.getHide());
    }

}


