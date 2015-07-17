//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Configuration;

import Aura.Shared.Util.Configuration.ConfFile;
import Aura.Shared.Util.Configuration.Files.DatabaseConfFile;
import Aura.Shared.Util.Configuration.Files.InterConfFile;
import Aura.Shared.Util.Configuration.Files.LocalizationConfFile;
import Aura.Shared.Util.Configuration.Files.LogConfFile;
import CS2JNet.System.StringSupport;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public abstract class BaseConf  extends ConfFile 
{
    /**
    * log.conf
    */
    private LogConfFile __Log;
    public LogConfFile getLog() {
        return __Log;
    }

    public void setLog(LogConfFile value) {
        __Log = value;
    }

    /**
    * database.conf
    */
    private DatabaseConfFile __Database;
    public DatabaseConfFile getDatabase() {
        return __Database;
    }

    public void setDatabase(DatabaseConfFile value) {
        __Database = value;
    }

    /**
    * localization.conf
    */
    private LocalizationConfFile __Localization;
    public LocalizationConfFile getLocalization() {
        return __Localization;
    }

    public void setLocalization(LocalizationConfFile value) {
        __Localization = value;
    }

    /**
    * internal.conf
    */
    private InterConfFile __Internal;
    public InterConfFile getInternal() {
        return __Internal;
    }

    public void setInternal(InterConfFile value) {
        __Internal = value;
    }

    protected BaseConf() throws Exception {
        this.setLog(new LogConfFile());
        this.setDatabase(new DatabaseConfFile());
        this.setLocalization(new LocalizationConfFile());
        this.setInternal(new InterConfFile());
    }

    /**
    * Loads several conf files that are generally required,
    * like log, database, etc.
    */
    protected void loadDefault() throws Exception {
        this.getLog().load();
        this.getDatabase().load();
        this.getLocalization().load();
        this.getInternal().load();
        if (StringSupport.equals(this.getInternal().getPassword(), "change_me"))
            Aura.Shared.Util.Log.warning("Using the default inter server password is risky, please change it in 'inter.conf'.");
         
    }

    public abstract void load() throws Exception ;

}


