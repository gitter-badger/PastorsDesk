// Copyright (c) ImaBrokeDude - Licensed under GNU GPL
// For more information, see license file in the main folder

using System;
using System.Collections.Generic;

namespace PastorsDesk.Database
{
    public class Account
    {
        public string Username { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string Fullname { get; set; }

        public int Authority { get; set; }

        public long SessionKey { get; set; }

        public DateTime Creation { get; set; }
        public DateTime LastLogin { get; set; }

        public bool LoggedIn { get; set; }

        public List<Appointment> Appointments { get; set; } 

        public Account()
        {
            Creation = DateTime.Now;
            LastLogin = DateTime.Now;

            Appointments = new List<Appointment>();
        }
    }
}
