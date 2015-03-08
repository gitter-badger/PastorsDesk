// Copyright (c) ImaBrokeDude - Licensed under GNU GPL
// For more information, see license file in the main folder

using System;
using System.Collections.Generic;

namespace PastorsDeskServer.Database
{
    public class Appointment
    {
        public string Creator { get; set; }
        public string Description { get; set; }
        public string Location { get; set; }

        public int Id { get; set; }
            
        public DateTime Start { get; set; }
        public DateTime End { get; set; }
        public DateTime Creation { get; set; }

        public List<string> Participants { get; set; }

        public Appointment()
        {
            Creation = DateTime.Now;

            Participants = new List<string>();
        }
    }
}
