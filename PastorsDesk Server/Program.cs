using System;
using PastorsDeskServer.Database;

namespace PastorsDeskServer
{
    public class Program
    {
        [STAThread]
        public static void Main(string[] args)
        {
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
             
        }
    }
}
