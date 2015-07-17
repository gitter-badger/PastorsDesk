//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Commands;

import Aura.Shared.Util.Commands.__MultiConsoleCommandFunc;
import Aura.Shared.Util.Commands.CommandResult;
import Aura.Shared.Util.Commands.ConsoleCommandFunc;
import CS2JNet.JavaSupport.util.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class __MultiConsoleCommandFunc   implements ConsoleCommandFunc
{
    public CommandResult invoke(String command, List<String> args) throws Exception {
        List<ConsoleCommandFunc> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<ConsoleCommandFunc>(members);
        }
        ConsoleCommandFunc prev = null;
        for (ConsoleCommandFunc d : copy)
        {
            if (prev != null)
                prev.invoke(command, args);
             
            prev = d;
        }
        return prev.invoke(command, args);
    }

    private List<ConsoleCommandFunc> _invocationList = new ArrayList<ConsoleCommandFunc>();
    public static ConsoleCommandFunc combine(ConsoleCommandFunc a, ConsoleCommandFunc b) throws Exception {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiConsoleCommandFunc ret = new __MultiConsoleCommandFunc();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static ConsoleCommandFunc remove(ConsoleCommandFunc a, ConsoleCommandFunc b) throws Exception {
        if (a == null || b == null)
            return a;
         
        List<ConsoleCommandFunc> aInvList = a.getInvocationList();
        List<ConsoleCommandFunc> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiConsoleCommandFunc ret = new __MultiConsoleCommandFunc();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<ConsoleCommandFunc> getInvocationList() throws Exception {
        return _invocationList;
    }

}


