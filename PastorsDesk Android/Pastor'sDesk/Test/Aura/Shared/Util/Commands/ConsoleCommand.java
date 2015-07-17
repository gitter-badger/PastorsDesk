//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Commands;

import Aura.Shared.Util.Commands.Command;
import Aura.Shared.Util.Commands.ConsoleCommandFunc;
import CS2JNet.JavaSupport.util.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsoleCommand  extends Command<ConsoleCommandFunc> 
{
    public ConsoleCommand(String name, String usage, String description, ConsoleCommandFunc func) throws Exception {
        super(name, usage, description, func);
    }

}


