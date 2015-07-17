//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Commands;

import CS2JNet.System.InvalidOperationException;

/**
* Generalized command holder
*/
public abstract class Command <TFunc>  
{
    private String __Name;
    public String getName() {
        return __Name;
    }

    public void setName(String value) {
        __Name = value;
    }

    private String __Usage;
    public String getUsage() {
        return __Usage;
    }

    public void setUsage(String value) {
        __Usage = value;
    }

    private String __Description;
    public String getDescription() {
        return __Description;
    }

    public void setDescription(String value) {
        __Description = value;
    }

    private TFunc __Func;
    public TFunc getFunc() {
        return __Func;
    }

    public void setFunc(TFunc value) {
        __Func = value;
    }

    protected Command(String name, String usage, String description, TFunc func) throws Exception {
        if (!TFunc.class.IsSubclassOf(Delegate.class))
            throw new InvalidOperationException(TFunc.class.getName() + " is not a delegate type");
         
        this.setName(name);
        this.setUsage(usage);
        this.setDescription(description);
        this.setFunc(func);
    }

}


