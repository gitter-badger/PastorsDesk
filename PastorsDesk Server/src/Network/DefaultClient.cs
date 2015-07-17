using System;
using PastorsDesk.Shared.Network;

namespace PastorsDesk.Server.Network
{
    /// <summary>
    /// Normal Mabi client (Login, Channel).
    /// </summary>
    public class DefaultClient : BaseClient
    {
        protected override void EncodeBuffer(byte[] buffer)
        {
            //this.Crypto.FromServer(buffer);

            // Set raw flag
            buffer[5] = 0x03;
        }

        protected override void DecodeBuffer()
        {
            
        }

        protected override byte[] BuildPacket(Packet packet)
        {
            var result = new byte[6 + packet.GetSize() + 4]; // header + packet + checksum
            result[0] = 0x88;
            System.Buffer.BlockCopy(BitConverter.GetBytes(result.Length), 0, result, 1, sizeof(int));
            packet.Build(ref result, 6);

            return result;
        }
    }
}
