using System;
using PastorsDesk.Server.Database;
using PastorsDesk.Shared.Util;

namespace PastorsDesk.Server
{
    public class Program
    {
        [STAThread]
        public static void Main(string[] args)
        {
            CliUtil.WriteHeader(Properties.Resources.ConsoleTitle);

            Console.WriteLine("Test of database connection");
            var db = new PastorsDeskDb();
            try
            {
                db.Init("127.0.0.1", "root", "dwilliams378007!", "pastorsdesk");
            }
            catch (Exception)
            {
                System.Windows.MessageBox.Show("Error with database!");     
            }

            Console.ReadLine();
        }
    }
}
