// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder

using System;
using System.Threading;

namespace PastorsDesk.Shared.Util
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
}
