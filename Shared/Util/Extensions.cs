using System;
using System.Collections.Generic;
using System.Globalization;
using MySql.Data.MySqlClient;

namespace PastorsDesk.Shared.Util
{
    public static class FloatExtensions
    {
        public static string ToInvariant(this float f, string format = "g")
        {
            return f.ToString(format, CultureInfo.InvariantCulture);
        }

        public static string ToInvariant(this double f, string format = "g")
        {
            return f.ToString(format, CultureInfo.InvariantCulture);
        }
    }

    public static class RandomExtensions
    {
        /// <summary>
        /// Returns random long.
        /// </summary>
        /// <param name="rnd"></param>
        /// <returns></returns>
        public static long NextInt64(this Random rnd)
        {
            return (((long)rnd.Next() << 8 * 4 - 1) + rnd.Next());
        }

        public static float Between(this Random rnd, float min, float max)
        {
            return (float)(min + (rnd.NextDouble() * (max - min)));
        }
    }

    public static class MySqlDataReaderExtensions
    {
        /// <summary>
        /// Returns true if value at index is null.
        /// </summary>
        /// <param name="reader"></param>
        /// <param name="index"></param>
        /// <returns></returns>
        private static bool IsDbNull(this MySqlDataReader reader, string index)
        {
            return reader.IsDBNull(reader.GetOrdinal(index));
        }

        /// <summary>
        /// Same as GetString, except for a is null check. Returns null if NULL.
        /// </summary>
        /// <param name="reader"></param>
        /// <param name="index"></param>
        /// <returns></returns>
        public static string GetStringSafe(this MySqlDataReader reader, string index)
        {
            return IsDbNull(reader, index) ? null : reader.GetString(index);
        }

        /// <summary>
        /// Returns DateTime of the index, or DateTime.MinValue, if value is null.
        /// </summary>
        /// <param name="reader"></param>
        /// <param name="index"></param>
        /// <returns></returns>
        public static DateTime GetDateTimeSafe(this MySqlDataReader reader, string index)
        {
            return reader[index] as DateTime? ?? DateTime.MinValue;
        }
    }

    public static class ListExtensions
    {
        /// <summary>
        /// Returns a random item from the given IList
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="list">The list.</param>
        /// <returns></returns>
        public static T Random<T>(this IList<T> list)
        {
            return list[RandomProvider.Get().Next(list.Count)];
        }
    }

    public static class StringExtensions
    {
        /// <summary>
        /// Calculates differences between 2 strings.
        /// </summary>
        /// <remarks>
        /// http://en.wikipedia.org/wiki/Levenshtein_distance
        /// </remarks>
        /// <example>
        /// <code>
        /// "test".LevenshteinDistance("test")       // == 0
        /// "test1".LevenshteinDistance("test2")     // == 1
        /// "test1".LevenshteinDistance("test1 asd") // == 4
        /// </code>
        /// </example>
        public static int LevenshteinDistance(this string str, string compare, bool caseSensitive = true)
        {
            if (!caseSensitive)
            {
                str = str.ToLower();
                compare = compare.ToLower();
            }

            var sLen = str.Length;
            var cLen = compare.Length;
            var result = new int[sLen + 1, cLen + 1];

            if (sLen == 0)
                return cLen;

            if (cLen == 0)
                return sLen;

            for (var i = 0; i <= sLen; result[i, 0] = i++) { }
            for (var i = 0; i <= cLen; result[0, i] = i++) { }

            for (var i = 1; i <= sLen; i++)
            {
                for (var j = 1; j <= cLen; j++)
                {
                    var cost = (compare[j - 1] == str[i - 1]) ? 0 : 1;
                    result[i, j] = Math.Min(Math.Min(result[i - 1, j] + 1, result[i, j - 1] + 1), result[i - 1, j - 1] + cost);
                }
            }

            return result[sLen, cLen];
        }

        public static string Center(this string stringToCenter, int totalLength)
        {
            return stringToCenter
                .PadLeft(((totalLength - stringToCenter.Length) / 2) + stringToCenter.Length)
                .PadRight(totalLength);
        }
        public static string Truncate(this string value, int maxLength)
        {
            if (string.IsNullOrEmpty(value))
                return value;
            return value.Length <= maxLength ? value : value.Substring(0, maxLength);
        }
    }

    public static class MathExtensions
    {
        /// <summary>
        /// Returns min, if val is lower than min, max, if val is
        /// greater than max, or simply val.
        /// </summary>
        /// <param name="min"></param>
        /// <param name="max"></param>
        /// <param name="val"></param>
        /// <returns></returns>
        public static int Clamp(int min, int max, int val)
        {
            if (val < min)
                return min;
            return val > max ? max : val;
        }

        /// <summary>
        /// Returns min, if val is lower than min, max, if val is
        /// greater than max, or simply val.
        /// </summary>
        /// <param name="min"></param>
        /// <param name="max"></param>
        /// <param name="val"></param>
        /// <returns></returns>
        public static float Clamp(float min, float max, float val)
        {
            if (val < min)
                return min;
            return val > max ? max : val;
        }

        /// <summary>
        /// Returns min, if val is lower than min, max, if val is
        /// greater than max, or simply val.
        /// </summary>
        /// <param name="min"></param>
        /// <param name="max"></param>
        /// <param name="val"></param>
        /// <returns></returns>
        public static long Clamp(long min, long max, long val)
        {
            if (val < min)
                return min;
            return val > max ? max : val;
        }

        /// <summary>
        /// Returns true if val is between min and max (incl).
        /// </summary>
        /// <param name="val"></param>
        /// <param name="min"></param>
        /// <param name="max"></param>
        /// <returns></returns>
        public static bool Between(int val, int min, int max)
        {
            return (val >= min && val <= max);
        }
    }
}
