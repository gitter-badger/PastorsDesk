//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Commands;

import Aura.Shared.Util.Commands.CommandResult;
import Aura.Shared.Util.Commands.ConsoleCommandFunc;
import CS2JNet.JavaSupport.util.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface ConsoleCommandFunc   
{
    CommandResult invoke(String command, List<String> args) throws Exception ;

    List<ConsoleCommandFunc> getInvocationList() throws Exception ;

}


