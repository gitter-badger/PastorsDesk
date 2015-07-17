//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Database;

import CS2JNet.System.LCC.IDisposable;
import java.util.HashMap;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public abstract class SimpleCommand   implements IDisposable
{
    protected MySqlCommand _mc = new MySqlCommand();
    protected HashMap<String,Object> _set;
    protected SimpleCommand(String command, MySqlConnection conn, MySqlTransaction trans) throws Exception {
        _mc = new MySqlCommand(command, conn, trans);
        _set = new HashMap<String,Object>();
    }

    /**
    * Adds a parameter that's not handled by Set.
    * 
    *  @param name 
    *  @param value
    */
    public void addParameter(String name, Object value) throws Exception {
        _mc.Parameters.AddWithValue(name, value);
    }

    /**
    * Sets value for field.
    * 
    *  @param field 
    *  @param value
    */
    public void set(String field, Object value) throws Exception {
        _set.put(field, value);
    }

    public abstract int execute() throws Exception ;

    public void dispose() throws Exception {
        _mc.Dispose();
    }

}


