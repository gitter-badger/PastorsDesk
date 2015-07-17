//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import Aura.Shared.Util.LogLevel;
import CS2JNet.JavaSupport.util.LocaleSupport;
import CS2JNet.System.LCC.Disposable;
import CS2JNet.System.StringSupport;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
* Logs messages to command line and file.
*/
public class Log   
{
    private static String _logFile;
    /**
    * Specifies the log levels that shouldn't be displayed
    * on the command line.
    */
    private static LogLevel __Hide = LogLevel.Info;
    public static LogLevel getHide() {
        return __Hide;
    }

    public static void setHide(LogLevel value) {
        __Hide = value;
    }

    /**
    * Sets or returns the directory in which the logs are archived.
    * If no archive is set, log files will simply be overwritten.
    */
    private static String __Archive;
    public static String getArchive() {
        return __Archive;
    }

    public static void setArchive(String value) {
        __Archive = value;
    }

    /**
    * Sets or returns the file to log to. Upon setting, the file will
    * be deleted. If Archive is set, it will be moved to safety first.
    */
    public static String getLogFile() throws Exception {
        return _logFile;
    }

    public static void setLogFile(String value) throws Exception {
        if (value != null)
        {
            String pathToFile = (new File(value)).getParent();
            if (!(new File(pathToFile)).exists())
                (new File(pathToFile)).mkdirs();
             
            if ((new File(value)).exists())
            {
                if (getArchive() != null)
                {
                    if (!(new File(getArchive())).exists())
                        (new File(getArchive())).mkdirs();
                     
                    /* [UNSUPPORTED] 'var' as type is unsupported "var" */ time = File.GetLastWriteTime(value);
                    /* [UNSUPPORTED] 'var' as type is unsupported "var" */ archive = Path.Combine(getArchive(), time.ToString("yyyy-MM-dd_hh-mm"));
                    /* [UNSUPPORTED] 'var' as type is unsupported "var" */ archiveFilePath = Path.Combine(archive, (new File(value)).getName());
                    if (!File.Exists(archive))
                        File.CreateDirectory(archive);
                     
                    if (File.Exists(archiveFilePath))
                        File.Delete(archiveFilePath);
                     
                    File.Move(value, archiveFilePath);
                }
                 
                (new File(value)).delete();
            }
             
        }
         
        _logFile = value;
    }

    public static void info(String format, Object... args) throws Exception {
        writeLine(LogLevel.Info,format,args);
    }

    public static void warning(String format, Object... args) throws Exception {
        writeLine(LogLevel.Warning,format,args);
    }

    public static void error(String format, Object... args) throws Exception {
        writeLine(LogLevel.Error,format,args);
    }

    public static void debug(String format, Object... args) throws Exception {
        writeLine(LogLevel.Debug,format,args);
    }

    public static void debug(Object obj) throws Exception {
        writeLine(LogLevel.Debug,obj.toString());
    }

    public static void status(String format, Object... args) throws Exception {
        writeLine(LogLevel.Status,format,args);
    }

    public static void exception(Exception ex, String description, Object... args) throws Exception {
        if (description != null)
        {
            if (getHide().HasFlag(LogLevel.Exception))
                description += " See log file for more details.";
             
            writeLine(LogLevel.Error,description,args);
        }
         
        writeLine(LogLevel.Exception,ex.toString());
    }

    public static void unimplemented(String format, Object... args) throws Exception {
        writeLine(LogLevel.Unimplemented,format,args);
    }

    public static void progress(int current, int max) throws Exception {
        Double donePerc = (100f / max * current);
        Int32 done = (int)Math.Ceiling(20f / max * current);
        write(LogLevel.Info,false,"[" + (StringSupport.PadRight("", done, '#') + StringSupport.PadLeft("", 20 - done, '.')) + "] {0,5}%\r",donePerc.ToString("0.0", LocaleSupport.INVARIANT));
    }

    public static void writeLine(LogLevel level, String format, Object... args) throws Exception {
        write(level,format + System.getProperty("line.separator"),args);
    }

    public static void writeLine() throws Exception {
        writeLine(LogLevel.None,"");
    }

    public static void write(LogLevel level, String format, Object... args) throws Exception {
        write(level,true,format,args);
    }

    private static void write(LogLevel level, boolean toFile, String format, Object... args) throws Exception {
        synchronized (System.out)
        {
            {
                if (!getHide().HasFlag(level))
                {
                    switch(level)
                    {
                        case Info: 
                            Console.ForegroundColor = ConsoleColor.White;
                            break;
                        case Warning: 
                            Console.ForegroundColor = ConsoleColor.Yellow;
                            break;
                        case Error: 
                            Console.ForegroundColor = ConsoleColor.Red;
                            break;
                        case Debug: 
                            Console.ForegroundColor = ConsoleColor.Cyan;
                            break;
                        case Status: 
                            Console.ForegroundColor = ConsoleColor.Green;
                            break;
                        case Exception: 
                            Console.ForegroundColor = ConsoleColor.DarkRed;
                            break;
                        case Unimplemented: 
                            Console.ForegroundColor = ConsoleColor.DarkGray;
                            break;
                    
                    }
                    if (level != LogLevel.None)
                        System.out.printf(StringSupport.CSFmtStrToJFmtStr("[{0}]"),level);
                     
                    Console.ForegroundColor = ConsoleColor.Gray;
                    if (level != LogLevel.None)
                        System.out.print(" - ");
                     
                    System.out.printf(StringSupport.CSFmtStrToJFmtStr(format),args);
                }
                 
                if (_logFile != null && toFile)
                {
                    StreamWriter file = new PrintWriter(new FileWriter(_logFile,true),true);
                    try
                    {
                        {
                            file.print(Calendar.getInstance().getTime() + " ");
                            if (level != LogLevel.None)
                                System.out.printf(StringSupport.CSFmtStrToJFmtStr("[{0}] - "),level);
                             
                            System.out.printf(StringSupport.CSFmtStrToJFmtStr(format),args);
                            file.Flush();
                        }
                    }
                    finally
                    {
                        if (file != null)
                            Disposable.mkDisposable(file).dispose();
                         
                    }
                }
                 
            }
        }
    }

}


