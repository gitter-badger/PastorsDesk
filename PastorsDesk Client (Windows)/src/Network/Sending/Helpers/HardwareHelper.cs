using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.NetworkInformation;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using PastorsDesk.Shared.Network.Crypto;

namespace PastorsDesk.Network.Sending.Helpers
{
    public static class HardwareHelper
    {
        public static string GetMacAddress()
        {
            return
                NetworkInterface.GetAllNetworkInterfaces()
                    .Where(nic => nic.OperationalStatus == OperationalStatus.Up)
                    .Select(nic => nic.GetPhysicalAddress().ToString())
                    .FirstOrDefault();
        }
    }
}
