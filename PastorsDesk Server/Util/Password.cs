// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder

using System;
using System.Linq;
using System.Security.Cryptography;
using System.Text;

namespace PastorsDeskServer.Util
{
    /// <summary>
    /// Handles hashing of passwords
    /// </summary>
    /// <remarks>
    /// Hashed password with MD5 then SHA256 then BCrypt
    /// </remarks>
    public static class Password
    {
        private const int BCryptStrength = 12;

        private static readonly MD5 Md5 = MD5.Create();
        private static readonly SHA256Managed Sha256 = new SHA256Managed();

        public static string RawToMd5(byte[] passbin)
        {
            var password = passbin.TakeWhile(a => a != 0).Aggregate(string.Empty, (current, chr) => current + (char)chr);

            return RawToMd5(password);
        }

        public static string RawToMd5(string password)
        {
            return BitConverter.ToString(Md5.ComputeHash(Encoding.UTF8.GetBytes(password))).Replace("-", "");
        }

        public static string Md5ToSha256(string password)
        {
            return BitConverter.ToString(Sha256.ComputeHash(Encoding.UTF8.GetBytes(password))).Replace("-", "");
        }

        public static string RawToMd5Sha256(string password)
        {
            return Md5ToSha256(RawToMd5(password));
        }

        public static string Hash(string password)
        {
            return BCrypt.HashPassword(password, BCrypt.GenerateSalt(BCryptStrength));
        }

        public static string HashRaw(string password)
        {
            return BCrypt.HashPassword(RawToMd5Sha256(password), BCrypt.GenerateSalt(BCryptStrength));
        }

        public static bool Check(string password, string hashedPassword)
        {
            return BCrypt.CheckPassword(password, hashedPassword);
        }
    }
}
