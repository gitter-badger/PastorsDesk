using System;
using System.Globalization;
using System.Reflection;
using System.Security.Principal;

namespace PastorsDesk.Shared.Util
{
    public class ChangingOutput : IDisposable
    {
        private static readonly int ResultLen = 10;

        private static readonly string ClearString = "\r" + new string(' ', Console.WindowWidth - 1) + "\r";
        private static readonly int MaxDescLength = Console.WindowWidth - ResultLen - 4; // [1 - 1 - 2], or bufferExtent - space - brackets
        private readonly string _desc;

        public ChangingOutput(string format, params object[] args)
            : this(string.Format(format, args))
        {

        }

        public ChangingOutput(string test)
        {
            _desc = test.Truncate(MaxDescLength).PadRight(MaxDescLength);
            Print();
        }

        public void Clear()
        {
            Console.Write(ClearString);
        }

        public void Print()
        {
            Clear();
            Console.Write(_desc);
        }

        public void PrintResult(bool passed)
        {
            Print();
            Console.Write(" [");

            var c = Console.ForegroundColor;
            Console.ForegroundColor = passed ? ConsoleColor.Green : ConsoleColor.Red;
            Console.Write((passed ? "Okay" : "FAILED").Center(ResultLen));
            Console.ForegroundColor = c;

            Console.Write("]");
        }

        public void PrintProgress(double progress)
        {
            var pAmount = (int)(progress * 100) / ResultLen;

            var str = "";
            if (pAmount > 0)
            {
                str = (pAmount == 1 ? "" : new string('=', pAmount - 1)) + ">";
            }

            Print();
            Console.Write(" [{0}]", str.PadRight(ResultLen));
        }

        public void PrintNumber(int num)
        {
            Print();
            Console.Write(" [{0}]", num.ToString(CultureInfo.InvariantCulture).Truncate(ResultLen).Center(ResultLen));
        }

        public void FinishLine()
        {
            Console.Write("\r");
            Console.WriteLine();
        }

        public void Dispose()
        {
            FinishLine();
        }
    }

    public class CliUtil
    {
        public static void WriteHeader(string title)
        {
            Console.Title = title;

            Console.ForegroundColor = ConsoleColor.White;
            Console.Write(@"Pastor's Desk Server Client".Center(Console.WindowWidth));
            Console.Write(string.Format("Version : {0}", Assembly.GetExecutingAssembly().GetName().Version).Center(Console.WindowWidth));
            Console.Write(@"Mabiavalon Software Solutions Limited".Center(Console.WindowWidth));

            Console.ForegroundColor = ConsoleColor.DarkGray;
            Console.Write(@"________________________________________________________________________________");
        }

        public static void LoadingTitle()
        {
            if (!Console.Title.StartsWith("* "))
                Console.Title = "* " + Console.Title;
        }

        public static void RunningTitle()
        {
            Console.Title = Console.Title.TrimStart('*', ' ');
        }

        public static void Exit(int exitCode, bool wait = true)
        {
            if (wait)
            {
                Log.Info("Press Enter to exit.");
                Console.ReadLine();
            }
            Log.Info("Exiting...");
            Environment.Exit(exitCode);
        }

        public static bool CheckAdmin()
        {
            var id = WindowsIdentity.GetCurrent();
            var principal = new WindowsPrincipal(id);

            return principal.IsInRole(WindowsBuiltInRole.Administrator);
        }
    }
}
