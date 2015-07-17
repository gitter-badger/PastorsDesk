//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Database;

import Aura.Shared.Database.SimpleCommand;
import CS2JNet.JavaSupport.Collections.Generic.LCC.CollectionSupport;
import CS2JNet.System.StringSupport;

/**
* Wrapper around MySqlCommand, for easier, cleaner updating.
* 
* Include one {0} where the set statements normally would be.
* It's automatically inserted, based on what was passed to "Set".
* 
*  {@code 
* using (var conn = AuraDb.Instance.Connection)
* using (var cmd = new UpdateCommand("UPDATE `accounts` SET {0} WHERE `accountId` = @accountId", conn))
* {
* cmd.AddParameter("@accountId", account.Id);
* cmd.Set("authority", (byte)account.Authority);
* cmd.Set("lastlogin", account.LastLogin);
* cmd.Set("banReason", account.BanReason);
* cmd.Set("banExpiration", account.BanExpiration);
* 
* cmd.Execute();
* }
* }
*/
public class UpdateCommand  extends SimpleCommand 
{
    public UpdateCommand(String command, MySqlConnection conn, MySqlTransaction trans) throws Exception {
        super(command, conn, trans);
    }

    /**
    * Runs MySqlCommand.ExecuteNonQuery
    * 
    *  @return
    */
    public int execute() throws Exception {
        // Build setting part
        StringBuilder sb = new StringBuilder();
        for (String parameter : CollectionSupport.mk(_set.keySet()))
            sb.append(String.format(StringSupport.CSFmtStrToJFmtStr("`{0}` = @{0}, "),parameter));
        // Add setting part
        _mc.CommandText = String.Format(_mc.CommandText, StringSupport.Trim(sb.toString(), new char[] {' ', ','}));
        for (KeyValuePair parameter : _set.entrySet())
            // Add parameters
            _mc.Parameters.AddWithValue("@" + parameter.Key, parameter.Value);
        return _mc.ExecuteNonQuery();
    }

}


// Run