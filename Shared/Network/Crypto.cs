using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PastorsDesk.Shared.Network
{
    public class PacketCrypto
    {
        public void EncodePacket(ref byte[] packet)
        {
            if (packet[5] == 1)
                BitConverter.GetBytes(BitConverter.ToUInt32(packet, 6) ^ 2050191920U).CopyTo(packet, 6);
            else
                packet[5] = 3;
        }
    }
}
