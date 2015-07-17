using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PastorsDesk.Shared.Network;

namespace PastorsDesk.Server.Network.Handlers
{
    public class PastorsDeskServerHandlers : PacketHandlerManager<UserClient>
    {
        [PacketHandler(Op.ClientIdent)]
        public void CLientIdent(UserClient client, Packet packet)
        {
            
        }
    }
}
