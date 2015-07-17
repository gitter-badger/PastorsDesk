//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Commands;

import Aura.Shared.Util.CliUtil;
import Aura.Shared.Util.Commands.CommandManager;
import Aura.Shared.Util.Commands.CommandResult;
import Aura.Shared.Util.Commands.ConsoleCommand;
import Aura.Shared.Util.Commands.ConsoleCommandFunc;
import Aura.Shared.Util.Log;
import CS2JNet.JavaSupport.util.ListSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Console command manager
*/
public class ConsoleCommands  extends CommandManager<ConsoleCommand,ConsoleCommandFunc> 
{
    public ConsoleCommands() throws Exception {
        _commands = new HashMap<String,ConsoleCommand>();
        this.Add("help", "Displays this help", HandleHelp);
        this.Add("exit", "Closes application/server", HandleExit);
        this.Add("status", "Displays application status", HandleStatus);
    }

    /**
    * Adds new command handler.
    * 
    *  @param name 
    *  @param description 
    *  @param handler
    */
    public void add(String name, String description, ConsoleCommandFunc handler) throws Exception {
        this.add(name,"",description,handler);
    }

    /**
    * Adds new command handler.
    * 
    *  @param name 
    *  @param usage 
    *  @param description 
    *  @param handler
    */
    public void add(String name, String usage, String description, ConsoleCommandFunc handler) throws Exception {
        if (_commands.containsKey(name))
            Log.warning("Console command '{0}' is being overwritten.",name);
         
        _commands.put(name, new ConsoleCommand(name,usage,description,handler));
    }

    /**
    * Waits and parses commands till "exit" is entered.
    */
    public void Wait() throws Exception {
        Log.info("Type 'help' for a list of console commands.");
        while (true)
        {
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ line = Console.ReadLine();
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ args = this.ParseLine(line);
            if (args.Count == 0)
                continue;
             
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ command = this.GetCommand(args[0]);
            if (command == null)
            {
                Log.info("Unknown command '{0}'",args[0]);
                continue;
            }
             
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ result = command.Func(line, args);
            if (result == CommandResult.Break)
            {
                break;
            }
            else if (result == CommandResult.Fail)
            {
                Log.error("Failed to run command '{0}'.",command.Name);
            }
            else if (result == CommandResult.InvalidArgument)
            {
                Log.info("Usage: {0} {1}",command.Name,command.Usage);
            }
               
        }
    }

    private CommandResult handleHelp(String command, List<String> args) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ maxLength = _commands.values().Max(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(a) => {
            return a.Name.Length;
        }" */);
        Log.info("Available commands");
        for (/* [UNSUPPORTED] 'var' as type is unsupported "var" */ cmd : _commands.values().OrderBy(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(a) => {
            return a.Name;
        }" */))
            Log.info("  {0,-" + (maxLength + 2) + "}{1}",cmd.Name,cmd.Description);
        return CommandResult.Okay;
    }

    private CommandResult handleStatus(String command, List<String> args) throws Exception {
        Log.status("Memory Usage: {0:N0} KB",Math.Round(GC.GetTotalMemory(false) / 1024f));
        return CommandResult.Okay;
    }

    protected CommandResult handleExit(String command, List<String> args) throws Exception {
        CliUtil.exit(0,false);
        return CommandResult.Okay;
    }

}


