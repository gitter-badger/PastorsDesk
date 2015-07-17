using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PastorsDesk.Server.Database;

namespace PastorsDesk.Server.Network
{
    public class UserClient : DefaultClient
    {
        Account Account { get; set; }
    }
}
