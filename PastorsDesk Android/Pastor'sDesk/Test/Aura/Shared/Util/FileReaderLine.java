//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import CS2JNet.System.LCC.Disposable;
import java.io.File;

public class FileReaderLine   
{
    /**
    * Current line.
    */
    private String __Value;
    public String getValue() {
        return __Value;
    }

    public void setValue(String value) {
        __Value = value;
    }

    /**
    * Full path to the file the value was read from.
    */
    private String __File;
    public String getFile() {
        return __File;
    }

    public void setFile(String value) {
        __File = value;
    }

    /**
    * New FileReaderLine.
    * 
    *  @param line 
    *  @param file
    */
    public FileReaderLine(String line, String file) throws Exception {
        this.setValue(line);
        this.setFile((new File(file)).getCanonicalPath());
    }

}


