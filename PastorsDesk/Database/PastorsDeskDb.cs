using System;
using System.Collections.Generic;
using MySql.Data.MySqlClient;
using PastorsDesk.Util;

namespace PastorsDesk.Database
{
    public class PastorsDeskDb
    {
        private string _connectionString;

        /// <summary>
        /// Returns a valid connection
        /// </summary>
        public MySqlConnection Connection
        {
            get
            {
                if (_connectionString == null)
                    throw new Exception("PastorsDeskDb has not been initialized.");

                var result = new MySqlConnection(_connectionString);
                result.Open();
                return result;
            }
        }

        public void Init(string host, string user, string pass, string db)
        {
            _connectionString =
                string.Format(
                    "server={0}; database={1}; uid={2}; password={3}; pooling=true; min pool size=0; max pool size=100;",
                    host, db, user, pass);
            TestConnection();
        }

        public void TestConnection()
        {
            MySqlConnection conn = null;
            try
            {
                conn = Connection;
            }
            finally
            {
                if (conn != null)
                    conn.Close();
            }
        }

        //------------------------------------------------------------------------------------------------------------

        /// <summary>
        /// Returns whether the account exists.
        /// </summary>
        /// <param name="username"></param>
        /// <returns></returns>
        public bool AccountExists(string username)
        {
            using (var conn = Connection)
            {
                var mc = new MySqlCommand("SELECT `username` FROM `accounts` WHERE `username` = @username", conn);
                mc.Parameters.AddWithValue("username", username);

                using (var reader = mc.ExecuteReader())
                    return reader.HasRows;
            }
        }

        /// <summary>
        /// Adds a new account to the database.
        /// </summary>
        /// <param name="username"></param>
        /// <param name="password"></param>
        /// <param name="fullname"></param>
        /// <param name="email"></param>
        public void CreateAccount(string username, string password, string fullname, string email)
        {
            // Converts plain text password to MD5 hash
            // then to SHA256 then hashes and salts
            // password with BCrypt encrypted hash
            password = Password.HashRaw(password);

            using (var conn = Connection)
            using (var cmd = new InsertCommand("INSERT INTO `accounts` {0}", conn))
            {
                cmd.Set("username", username);
                cmd.Set("password", password);
                cmd.Set("email", email);
                cmd.Set("fullname", fullname);
                cmd.Set("lastLogin", DateTime.Now);
                cmd.Set("creation", DateTime.Now);

                cmd.Execute();
            }
        }

        /// <summary>
        /// Retrieves an account start the database if it exists.
        /// </summary>
        /// <param name="username"></param>
        /// <returns></returns>
        public Account GetAccount(string username)
        {
            var account = new Account();

            using (var conn = Connection)
            {
                using (var mc = new MySqlCommand("SELECT * FROM `accounts` WHERE `username` = @username", conn))
                {
                    mc.Parameters.AddWithValue("@username", username);

                    using (var reader = mc.ExecuteReader())
                    {
                        if (!reader.Read())
                            return null;

                        account.Username = reader.GetStringSafe("username");
                        account.Password = reader.GetStringSafe("password");
                        account.Email = reader.GetStringSafe("email");
                        account.Fullname = reader.GetStringSafe("fullname");
                        account.Authority = reader.GetByte("authority");
                        account.Creation = reader.GetDateTime("creation");    
                        account.LastLogin = reader.GetDateTime("lastLogin");
                        account.Appointments = GetAppointments(username);
                    }
                }
            }

            return account;
        }

        /// <summary>
        /// Creates and returns a new session key for the account
        /// </summary>
        /// <param name="username"></param>
        /// <returns></returns>
        public long CreateSession(string username)
        {
            using (var conn = Connection)
            using (
                var mc =
                    new MySqlCommand("UPDATE `accounts` SET `sessionKey` = @sessionKey WHERE `username` = @username",
                        conn))
            {
                var sessionKey = RandomProvider.Get().NextInt64();

                mc.Parameters.AddWithValue("@username", username);
                mc.Parameters.AddWithValue("@sessionKey", sessionKey);

                mc.ExecuteNonQuery();

                return sessionKey;
            }
        }

        public void SaveAccount(Account account)
        {
            using (var conn = Connection)
            using (var cmd = new UpdateCommand("UPDATE `accounts` SET {0} WHERE `username` = @username", conn))
            {
                cmd.AddParameter("@username", account.Username);
                cmd.Set("authority", account.Authority);
                cmd.Set("lastLogin", account.LastLogin);

                cmd.Execute();
            }
        }

        /// <summary>
        /// Adds a new appointment to the database
        /// </summary>
        /// <param name="appointmentId"></param>
        /// <param name="creator"></param>
        /// <param name="start"></param>
        /// <param name="end"></param>
        /// <param name="description"></param>
        /// <param name="participants"></param>
        public void CreateAppointment(int appointmentId, string creator, DateTime start, DateTime end, string description, List<string> participants)
        {
            using (var conn = Connection)
            using (var cmd = new InsertCommand("INSERT INTO `appointments` WHERE {0}", conn))
            {
                cmd.Set("appointmentId", appointmentId);
                cmd.Set("creator", creator);
                cmd.Set("description", description);
                cmd.Set("start", start);
                cmd.Set("end", end);
                
                cmd.Set("creation", DateTime.Now);

                cmd.Execute();
            }
            foreach (var participant in participants)
            {
                CreateParticipant(appointmentId, participant);
            }
        }

        /// <summary>
        /// Creates a participant record for an appointment
        /// </summary>
        /// <param name="appointmentId"></param>
        /// <param name="participant"></param>
        public void CreateParticipant(int appointmentId, string participant)
        {
            using (var conn = Connection)
            using (var cmd = new InsertCommand("INSERT INTO `participants` WHERE {0}", conn))
            {
                cmd.Set("appointmentId", appointmentId);
                cmd.Set("participant", participant);

                cmd.Execute();
            }
        }

        /// <summary>
        /// Gets all appointments for the account
        /// </summary>
        /// <param name="accountId"></param>
        /// <returns></returns>
        public List<Appointment> GetAppointments(string accountId)
        {
            var result = new List<Appointment>();

            using (var conn = Connection)
            {
                // Appointment
                // -----------------------------------------------
                using (var mc = new MySqlCommand("SELECT * FROM `appointments` WHERE `username` = @username", conn))
                {
                    mc.Parameters.AddWithValue("@username", accountId);

                    using (var reader = mc.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            var appointment = new Appointment
                            {
                                Id = reader.GetInt32("appointmentId"),
                                Creator = reader.GetStringSafe("creator"),
                                Start = reader.GetDateTimeSafe("start"),
                                End = reader.GetDateTimeSafe("end"),
                                Description = reader.GetStringSafe("description"),
                                Creation = reader.GetDateTime("creation")
                            };

                            appointment.Participants = GetParticipants(appointment.Id);

                            result.Add(appointment);
                        }
                    }
                }
                return result;
            }
        }

        /// <summary>
        /// Gets all the participants for the appointment
        /// </summary>
        /// <param name="appointmentId"></param>
        /// <returns></returns>
        private List<string> GetParticipants(int appointmentId)
        {
            var result = new List<string>();

            using (var conn = Connection)
            {
                using (
                    var mc = new MySqlCommand("SELECT * FROM `participants` WHERE `appointmentId` = @appointmentId",
                        conn))
                {
                    mc.Parameters.AddWithValue("@appointmentId", appointmentId);

                    using (var reader = mc.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            var participant = reader.GetStringSafe("participant");

                            result.Add(participant);
                        }
                     }
                }
            }

            return result;
        }
    }

    public static class MySqlDataReaderExtension
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
}
