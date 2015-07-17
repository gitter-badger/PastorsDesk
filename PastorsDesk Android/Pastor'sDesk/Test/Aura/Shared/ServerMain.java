//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:44 AM
//

package Aura.Shared;

import Aura.Shared.Database.AuraDb;
import Aura.Shared.DataLoad;
import Aura.Shared.Util.CliUtil;
import Aura.Shared.Util.Configuration.BaseConf;
import Aura.Shared.Util.Localization;
import Aura.Shared.Util.Log;
import CS2JNet.System.IO.FileNotFoundException;
import java.io.File;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* General methods needed by all servers.
*/
public abstract class ServerMain   
{
    /**
    * Tries to find aura root folder and changes the working directory to it.
    * Exits if not successful.
    */
    public void navigateToRoot() throws Exception {
        for (int i = 0;i < 3;++i)
        {
            // Go back max 2 folders, the bins should be in [aura]/bin/(Debug|Release)
            if ((new File("system")).exists())
                return ;
             
            File.SetCurrentDirectory("..");
        }
        Log.error("Unable to find root directory.");
        CliUtil.Exit(1);
    }

    /**
    * Tries to call conf's load method, exits on error.
    */
    public void loadConf(BaseConf conf) throws Exception {
        Log.info("Reading configuration...");
        try
        {
            conf.load();
        }
        catch (Exception ex)
        {
            Log.exception(ex,"Unable to read configuration. ({0})",ex.getMessage());
            CliUtil.Exit(1);
        }
    
    }

    /**
    * Tries to initialize database with the information from conf,
    * exits on error.
    */
    public void initDatabase(AuraDb db, BaseConf conf) throws Exception {
        Log.info("Initializing database...");
        try
        {
            db.init(conf.getDatabase().getHost(),conf.getDatabase().getUser(),conf.getDatabase().getPass(),conf.getDatabase().getDb());
        }
        catch (Exception ex)
        {
            Log.error("Unable to open database connection. ({0})",ex.getMessage());
            CliUtil.Exit(1);
        }
    
    }

    /**
    * (Re-)Loads data files (db), exits on error.
    * 
    * Called on server start and with some reload commands.
    * Should only load required data, e.g. Msgr Server doesn't
    * need race data.
    */
    public void loadData(DataLoad toLoad, boolean reload) throws Exception {
        Log.info("Loading data...");
        try
        {
            if ((toLoad & DataLoad.Races) != 0)
            {
                this.LoadDb(AuraData.AncientDropDb, "db/ancient_drops.txt", reload);
                this.LoadDb(AuraData.SpeedDb, "db/speed.txt", reload, false);
                this.LoadDb(AuraData.RaceDb, "db/races.txt", reload);
            }
             
            if ((toLoad & DataLoad.StatsBase) != 0)
            {
                this.LoadDb(AuraData.StatsBaseDb, "db/stats_base.txt", reload);
            }
             
            if ((toLoad & DataLoad.StatsLevel) != 0)
            {
                this.LoadDb(AuraData.StatsLevelUpDb, "db/stats_levelup.txt", reload);
            }
             
            if ((toLoad & DataLoad.StatsAge) != 0)
            {
                this.LoadDb(AuraData.StatsAgeUpDb, "db/stats_ageup.txt", reload);
            }
             
            if ((toLoad & DataLoad.Motions) != 0)
            {
                this.LoadDb(AuraData.MotionDb, "db/motions.txt", reload);
            }
             
            if ((toLoad & DataLoad.Cards) != 0)
            {
                this.LoadDb(AuraData.CharCardSetDb, "db/charcardsets.txt", reload, false);
                this.LoadDb(AuraData.CharCardDb, "db/charcards.txt", reload);
            }
             
            if ((toLoad & DataLoad.Colors) != 0)
            {
                this.LoadDb(AuraData.ColorMapDb, "db/colormap.dat", reload);
            }
             
            if ((toLoad & DataLoad.Items) != 0)
            {
                this.LoadDb(AuraData.ItemDb, "db/items.txt", reload);
                this.LoadDb(AuraData.ChairDb, "db/chairs.txt", reload);
            }
             
            if ((toLoad & DataLoad.Skills) != 0)
            {
                this.LoadDb(AuraData.SkillDb, "db/skills.txt", reload);
            }
             
            if ((toLoad & DataLoad.Regions) != 0)
            {
                this.LoadDb(AuraData.RegionDb, "db/regions.txt", reload);
                this.LoadDb(AuraData.RegionInfoDb, "db/regioninfo.dat", reload);
            }
             
            if ((toLoad & DataLoad.Shamala) != 0)
            {
                this.LoadDb(AuraData.ShamalaDb, "db/shamala.txt", reload);
            }
             
            if ((toLoad & DataLoad.PropDrops) != 0)
            {
                this.LoadDb(AuraData.PropDropDb, "db/prop_drops.txt", reload);
            }
             
            if ((toLoad & DataLoad.Exp) != 0)
            {
                this.LoadDb(AuraData.ExpDb, "db/exp.txt", reload);
            }
             
            if ((toLoad & DataLoad.Pets) != 0)
            {
                this.LoadDb(AuraData.PetDb, "db/pets.txt", reload);
            }
             
            if ((toLoad & DataLoad.Weather) != 0)
            {
                this.LoadDb(AuraData.WeatherTableDb, "db/weathertables.txt", reload);
                this.LoadDb(AuraData.WeatherDb, "db/weather.txt", reload);
            }
             
            if ((toLoad & DataLoad.Keywords) != 0)
            {
                this.LoadDb(AuraData.KeywordDb, "db/keywords.txt", reload);
            }
             
            if ((toLoad & DataLoad.Titles) != 0)
            {
                this.LoadDb(AuraData.TitleDb, "db/titles.txt", reload);
            }
             
            if ((toLoad & DataLoad.ItemUpgrades) != 0)
            {
                this.LoadDb(AuraData.ItemUpgradesDb, "db/itemupgrades.txt", reload);
            }
             
            if ((toLoad & DataLoad.Props) != 0)
            {
                this.LoadDb(AuraData.PropsDb, "db/props.txt", reload);
            }
             
            if ((toLoad & DataLoad.Collecting) != 0)
            {
                this.LoadDb(AuraData.CollectingDb, "db/collecting.txt", reload);
            }
             
        }
        catch (DatabaseErrorException ex)
        {
            Log.error("{0}",ex.toString());
            CliUtil.Exit(1);
        }
        catch (FileNotFoundException ex)
        {
            Log.error(ex.getMessage());
            CliUtil.Exit(1);
        }
        catch (Exception ex)
        {
            Log.exception(ex,"Error while loading data.");
            CliUtil.Exit(1);
        }
    
    }

    /**
    * Loads db, first from system, then from user.
    * Logs problems as warnings.
    */
    private void loadDb(IDatabase db, String path, boolean reload, boolean log) throws Exception {
        String systemPath = (new File("system", path)).toString().replace('\\', '/');
        String userPath = (new File("user", path)).toString().replace('\\', '/');
        String cachePath = (new File("cache", path)).toString().replace('\\', '/');
        cachePath = Path.ChangeExtension(cachePath, "mpk");
        String cacheDir = (new File(cachePath)).getParent();
        if (!(new File(cacheDir)).exists())
            (new File(cacheDir)).mkdirs();
         
        if (!(new File(systemPath)).exists())
            throw new FileNotFoundException("Data file '" + systemPath + "' couldn't be found.", systemPath);
         
        db.Load(new String[]{ systemPath, userPath }, cachePath, reload);
        for (/* [UNSUPPORTED] 'var' as type is unsupported "var" */ ex : db.Warnings)
            Log.warning("{0}",ex.ToString());
        if (log)
            Log.info("  done loading {0} entries from {1}",db.Count,(new File(path)).getName());
         
    }

    /**
    * Loads system and user localization files.
    */
    public void loadLocalization(BaseConf conf) throws Exception {
        Log.info("Loading localization ({0})...",conf.getLocalization().getLanguage());
        try
        {
            // System
            Localization.parse("system/localization/" + conf.getLocalization().getLanguage());
        }
        catch (FileNotFoundException ex)
        {
            Log.warning("Unable to load localization: " + ex.getMessage());
        }

        try
        {
            // User
            Localization.parse("user/localization/" + conf.getLocalization().getLanguage());
        }
        catch (FileNotFoundException __dummyCatchVar0)
        {
        }
    
    }

}


