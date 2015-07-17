﻿using PastorsDesk.Shared.Util.Configuration;

namespace PastorsDesk.Server.Util.Configuration.Files
{
    /// <summary>
    /// Represents database.conf
    /// </summary>
    public class DatabaseConfFile : ConfFile
    {
        public string Host { get; protected set; }
        public string User { get; protected set; }
        public string Pass { get; protected set; }
        public string Db { get; protected set; }

        public void Load()
        {
            this.Require("conf/database.conf");

            this.Host = this.GetString("host", "127.0.0.1");
            this.User = this.GetString("user", "root");
            this.Pass = this.GetString("pass", "");
            this.Db = this.GetString("database", "pastorsdesk");
        }
    }
}
