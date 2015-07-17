using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PastorsDesk.Network.Sending.Helpers;
using PastorsDesk.Shared.Network;
using PastorsDesk.Shared.Util.Security;

namespace PastorsDesk.Network.Sending
{
    public static partial class Send
    {
        public static void ClientIdent(PdClient client)
        {
            var packet = new Packet(Op.ClientIdent, 0);
            packet.PutString("PDC_USA-0.0.0.1-Dev[PRK-496d6142726f6b6544756465]");
            packet.PutBin(Encoding.UTF8.GetBytes(HardwareHelper.GetMacAddress()));
        }

        public static void Login(PdClient client, byte passBin, bool register = false)
        {
            var packet = new Packet(Op.Login, 0);
            packet.PutByte(register ? (byte) LoginType.Register : (byte) LoginType.Login);
        }
    }
}
