//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Commands;

import Aura.Shared.Util.Commands.Command;
import Aura.Shared.Util.Log;
import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.StringSupport;
import java.util.HashMap;
import java.util.List;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Generalized command manager
*/
public abstract class CommandManager <TCommand extends Command<TFunc>, TFunc>  
{
    protected HashMap<String,TCommand> _commands;
    protected CommandManager() throws Exception {
        _commands = new HashMap<String,TCommand>();
    }

    /**
    * Adds command to list of command handlers.
    * 
    *  @param command
    */
    protected void add(TCommand command) throws Exception {
        if (_commands.ContainsKey(command.Name))
            Log.warning("Command '{0}' is being overwritten.",command.Name);
         
        _commands.put(command.Name, command);
    }

    /**
    * Returns arguments parsed from line.
    * 
    * Matches words and multiple words in quotation.
    * 
    * arg0 arg1 arg2 -- 3 args: "arg0", "arg1", and "arg2"
    * arg0 arg1 "arg2 arg3" -- 3 args: "arg0", "arg1", and "arg2 arg3"
    */
    protected List<String> parseLine(String line) throws Exception {
        List<String> args = new CSList<String>();
        Boolean quote = false;
        for (int i = 0, n = 0;i <= line.length();++i)
        {
            if ((i == line.length() || line.charAt(i) == ' ') && !quote)
            {
                if (i - n > 0)
                    args.add(StringSupport.Trim(line.substring(n, (n) + (i - n)), new char[] {' ', '"'}));
                 
                n = i + 1;
                continue;
            }
             
            if (line.charAt(i) == '"')
                quote = !quote;
             
        }
        return args;
    }

    /**
    * Returns command or null, if the command doesn't exist.
    * 
    *  @param name 
    *  @return
    */
    public TCommand getCommand(String name) throws Exception {
        TCommand command;
        RefSupport<TCommand> refVar___0 = new RefSupport<TCommand>();
        _commands.TryGetValue(name, refVar___0);
        command = refVar___0.getValue();
        return command;
    }

}


