using System;
using PastorsDesk.Server.Database;
using PastorsDesk.Shared.Util;

namespace PastorsDesk.Server
{
    /// <summary>
    /// General methods needed by all servers.
    /// </summary>
    public abstract class ServerMain
    {
        /// <summary>
        /// Tries to call conf's load method, exits on error.
        /// </summary>
        public void LoadConf(BaseConf conf)
        {
            using (var t = new ChangingOutput("Reading Configuration..."))
            {
                try
                {
                    Log.ToFile("Reading Configuration...", LogLevel.Info);
                    conf.Load();
                    t.PrintResult(true);
                }
                catch (Exception ex)
                {
                    t.PrintResult(false);
                    Log.Exception(ex, "Unable to read configuration. ({0})", ex.Message);
                    CliUtil.Exit(1);
                }
            }
        }

        /// <summary>
        /// Tries to initialize database with the information from conf,
        /// exits on error.
        /// </summary>
        public virtual void InitDatabase(PastorsDeskDb db, BaseConf conf)
        {
            using (var t = new ChangingOutput("Initializing database..."))
            {
                try
                {
                    Log.ToFile("Initializing database...", LogLevel.Info);
                    db.Init(conf.Database.Host, conf.Database.User, conf.Database.Pass, conf.Database.Db);
                    t.PrintResult(true);
                }
                catch (Exception ex)
                {
                    t.PrintResult(false);
                    Log.Error("Unable to open database connection. ({0})", ex.Message);
                    CliUtil.Exit(1);
                }
            }           
        }
    }
}
