using System;
using System.Threading;

namespace PastorsDesk.Util
{
    public static class RandomProvider
    {
        private static readonly Random Seed = new Random();

        private static readonly ThreadLocal<Random> RandomWrapper = new ThreadLocal<Random>(() =>
        {
            lock (Seed)
                return new Random(Seed.Next());
        });

        /// <summary>
        /// Returns an instance of Random for the calling thread.
        /// </summary>
        /// <returns></returns>
        public static Random Get()
        {
            return RandomWrapper.Value;
        }
    }

    /// <summary>
    /// Extensions for Random.
    /// </summary>
    public static class RandomExtension
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
    }
}
