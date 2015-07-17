//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Database;

import Aura.Shared.Database.SimpleCommand;
import CS2JNet.JavaSupport.Collections.Generic.LCC.CollectionSupport;
import CS2JNet.System.StringSupport;

/**
* Wrapper around MySqlCommand, for easier, cleaner inserting.
* 
* Include one {0} where the "(...) VALUES (...) part normally would be.
* It's automatically inserted, based on what was passed to "Set".
* 
*  {@code 
* using (var cmd = new InsertCommand("INSERT INTO `keywords` {0}", conn, transaction))
* {
* cmd.Set("creatureId", creature.CreatureId);
* cmd.Set("keywordId", keywordId);
* 
* cmd.Execute();
* }
* }
*/
public class InsertCommand  extends SimpleCommand 
{
    /**
    * Returns last insert id.
    */
    public long getLastId() throws Exception {
        return _mc.LastInsertedId;
    }

    public InsertCommand(String command, MySqlConnection conn, MySqlTransaction transaction) throws Exception {
        super(command, conn, transaction);
    }

    /**
    * Runs MySqlCommand.ExecuteNonQuery
    * 
    *  @return
    */
    public int execute() throws Exception {
        // Build values part
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String parameter : CollectionSupport.mk(_set.keySet()))
        {
            sb1.append(String.format(StringSupport.CSFmtStrToJFmtStr("`{0}`, "),parameter));
            sb2.append(String.format(StringSupport.CSFmtStrToJFmtStr("@{0}, "),parameter));
        }
        // Add values part
        String values = "(" + (StringSupport.Trim(sb1.toString(), new char[] {' ', ','})) + ") VALUES (" + (StringSupport.Trim(sb2.toString(), new char[] {' ', ','})) + ")";
        _mc.CommandText = String.Format(_mc.CommandText, values);
        for (KeyValuePair parameter : _set.entrySet())
            // Add parameters
            _mc.Parameters.AddWithValue("@" + parameter.Key, parameter.Value);
        return _mc.ExecuteNonQuery();
    }

}


// Run