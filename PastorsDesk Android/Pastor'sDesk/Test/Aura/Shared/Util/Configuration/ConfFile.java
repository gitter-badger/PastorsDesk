//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util.Configuration;

import Aura.Shared.Util.FileReader;
import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.System.DoubleSupport;
import CS2JNet.System.LCC.Disposable;
import CS2JNet.System.StringSupport;
import CS2JNet.System.TimeSpan;
import java.io.File;
import java.util.Date;
import java.util.HashMap;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Configuration options manager.
* 
* Uses 
*  {@link #FileReader}
*  to read conf files, that are parsed in key:value pairs.
* Separating character is a colon ':'.
*/
public class ConfFile   
{
    protected final HashMap<String,String> _options;
    protected ConfFile() throws Exception {
        _options = new HashMap<String,String>();
    }

    /**
    * Loads all options in the file, and included files.
    * Does nothing if file doesn't exist.
    * 
    *  @param filePath
    */
    public void include(String filePath) throws Exception {
        if (!(new File(filePath)).exists())
            return ;
         
        this.loadFile(filePath);
    }

    /**
    * Loads all options in the file, and included files.
    * Throws FileNotFoundException if file couldn't be found.
    * 
    *  @param filePath
    */
    protected void require(String filePath) throws Exception {
        this.loadFile(filePath);
    }

    /**
    * Loads all options in the file, and included files.
    * 
    *  @param filePath
    */
    private void loadFile(String filePath) throws Exception {
        FileReader fr = new FileReader(filePath);
        try
        {
            {
                for (FileReaderLine line : fr)
                {
                    int pos = -1;
                    // Check for seperator
                    if ((pos = line.getValue().indexOf(':')) < 0)
                        return ;
                     
                    _options.put(StringSupport.Trim(line.getValue().substring(0, (0) + (pos))), StringSupport.Trim(line.getValue().substring(pos + 1)));
                }
            }
        }
        finally
        {
            if (fr != null)
                Disposable.mkDisposable(fr).dispose();
             
        }
    }

    /**
    * Returns the option as bool, or the default value, if the option
    * doesn't exist.
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected boolean getBool(String option, boolean defaultValue) throws Exception {
        String value;
        boolean boolVar___0 = !_options.TryGetValue(option, refVar___0);
        if (boolVar___0)
        {
            RefSupport<String> refVar___0 = new RefSupport<String>();
            Boolean resVar___1 = defaultValue;
            value = refVar___0.getValue();
            return resVar___1;
        }
         
        value = StringSupport.Trim(value.toLowerCase());
        return (StringSupport.equals(value, "1") || StringSupport.equals(value, "yes") || StringSupport.equals(value, "true"));
    }

    /**
    * Returns the option as byte, or the default value, if the option
    * doesn't exist.
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected byte getByte(String option, byte defaultValue) throws Exception {
        String value;
        boolean boolVar___2 = !_options.TryGetValue(option, refVar___1);
        if (boolVar___2)
        {
            RefSupport<String> refVar___1 = new RefSupport<String>();
            Byte resVar___3 = defaultValue;
            value = refVar___1.getValue();
            return resVar___3;
        }
         
        byte ret;
        boolean boolVar___4 = byte.TryParse(value, refVar___2);
        if (boolVar___4)
        {
            RefSupport<Byte> refVar___2 = new RefSupport<Byte>();
            Byte resVar___5 = ret;
            ret = refVar___2.getValue();
            return resVar___5;
        }
         
        Aura.Shared.Util.Log.warning("Invalid value for '{0}', defaulting to '{1}'",option,defaultValue);
        return defaultValue;
    }

    /**
    * Returns the option as short, or the default value, if the option
    * doesn't exist.
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected short getShort(String option, short defaultValue) throws Exception {
        String value;
        boolean boolVar___6 = !_options.TryGetValue(option, refVar___3);
        if (boolVar___6)
        {
            RefSupport<String> refVar___3 = new RefSupport<String>();
            Short resVar___7 = defaultValue;
            value = refVar___3.getValue();
            return resVar___7;
        }
         
        short ret;
        boolean boolVar___8 = short.TryParse(value, refVar___4);
        if (boolVar___8)
        {
            RefSupport<Short> refVar___4 = new RefSupport<Short>();
            Short resVar___9 = ret;
            ret = refVar___4.getValue();
            return resVar___9;
        }
         
        Aura.Shared.Util.Log.warning("Invalid value for '{0}', defaulting to '{1}'",option,defaultValue);
        return defaultValue;
    }

    /**
    * Returns the option as int, or the default value, if the option
    * doesn't exist.
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected int getInt(String option, int defaultValue) throws Exception {
        String value;
        boolean boolVar___10 = !_options.TryGetValue(option, refVar___5);
        if (boolVar___10)
        {
            RefSupport<String> refVar___5 = new RefSupport<String>();
            Integer resVar___11 = defaultValue;
            value = refVar___5.getValue();
            return resVar___11;
        }
         
        int ret;
        boolean boolVar___12 = DoubleSupport.tryParse(value, refVar___6);
        if (boolVar___12)
        {
            RefSupport<Integer> refVar___6 = new RefSupport<Integer>();
            Integer resVar___13 = ret;
            ret = refVar___6.getValue();
            return resVar___13;
        }
         
        Aura.Shared.Util.Log.warning("Invalid value for '{0}', defaulting to '{1}'",option,defaultValue);
        return defaultValue;
    }

    /**
    * Returns the option as long, or the default value, if the option
    * doesn't exist.
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected long getLong(String option, long defaultValue) throws Exception {
        String value;
        boolean boolVar___14 = !_options.TryGetValue(option, refVar___7);
        if (boolVar___14)
        {
            RefSupport<String> refVar___7 = new RefSupport<String>();
            Long resVar___15 = defaultValue;
            value = refVar___7.getValue();
            return resVar___15;
        }
         
        long ret;
        boolean boolVar___16 = long.TryParse(value, refVar___8);
        if (boolVar___16)
        {
            RefSupport<Long> refVar___8 = new RefSupport<Long>();
            Long resVar___17 = ret;
            ret = refVar___8.getValue();
            return resVar___17;
        }
         
        Aura.Shared.Util.Log.warning("Invalid value for '{0}', defaulting to '{1}'",option,defaultValue);
        return defaultValue;
    }

    /**
    * Returns the option as string, or the default value, if the option
    * doesn't exist.
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected String getString(String option, String defaultValue) throws Exception {
        String value;
        RefSupport<String> refVar___9 = new RefSupport<String>();
        String resVar___18 = !_options.TryGetValue(option, refVar___9) ? defaultValue : value;
        value = refVar___9.getValue();
        return resVar___18;
    }

    /**
    * Returns the option as float, or the default value, if the option
    * doesn't exist.
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected float getFloat(String option, float defaultValue) throws Exception {
        String value;
        boolean boolVar___19 = !_options.TryGetValue(option, refVar___10);
        if (boolVar___19)
        {
            RefSupport<String> refVar___10 = new RefSupport<String>();
            Float resVar___20 = defaultValue;
            value = refVar___10.getValue();
            return resVar___20;
        }
         
        float ret;
        boolean boolVar___21 = float.TryParse(value, refVar___11);
        if (boolVar___21)
        {
            RefSupport<Float> refVar___11 = new RefSupport<Float>();
            Float resVar___22 = ret;
            ret = refVar___11.getValue();
            return resVar___22;
        }
         
        Aura.Shared.Util.Log.warning("Invalid value for '{0}', defaulting to '{1}'",option,defaultValue);
        return defaultValue;
    }

    /**
    * Returns the option as a DateTime, or the default value, if the
    * option doesn't exist.
    * 
    * For acceptable value formatting, see 
    *  {@link #}
    * .
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected Date getDateTime(String option, Date defaultValue) throws Exception {
        String value;
        boolean boolVar___23 = !_options.TryGetValue(option, refVar___12);
        if (boolVar___23)
        {
            RefSupport<String> refVar___12 = new RefSupport<String>();
            Date resVar___24 = defaultValue;
            value = refVar___12.getValue();
            return resVar___24;
        }
         
        Date ret;
        boolean boolVar___25 = Date.TryParse(value, refVar___13);
        if (boolVar___25)
        {
            RefSupport<Date> refVar___13 = new RefSupport<Date>();
            Date resVar___26 = ret;
            ret = refVar___13.getValue();
            return resVar___26;
        }
         
        Aura.Shared.Util.Log.warning("Invalid value for '{0}', defaulting to '{1}'",option,defaultValue);
        return defaultValue;
    }

    /**
    * Returns the option as a TimeSpan, or the default value, if the
    * option doesn't exist.
    * 
    * Value must be formatted as [-]{ d | [d.]hh:mm[:ss[.ff]] }
    * 
    * For more details, see 
    *  {@link #}
    * .
    * 
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected TimeSpan getTimeSpan(String option, TimeSpan defaultValue) throws Exception {
        String value;
        boolean boolVar___27 = !_options.TryGetValue(option, refVar___14);
        if (boolVar___27)
        {
            RefSupport<String> refVar___14 = new RefSupport<String>();
            TimeSpan resVar___28 = defaultValue;
            value = refVar___14.getValue();
            return resVar___28;
        }
         
        TimeSpan ret;
        boolean boolVar___29 = TimeSpan.TryParse(value, refVar___15);
        if (boolVar___29)
        {
            RefSupport<TimeSpan> refVar___15 = new RefSupport<TimeSpan>();
            TimeSpan resVar___30 = ret;
            ret = refVar___15.getValue();
            return resVar___30;
        }
         
        Aura.Shared.Util.Log.warning("Invalid value for '{0}', defaulting to '{1}'",option,defaultValue);
        return defaultValue;
    }

    /**
    * Returns the option as an enum, or the default value, if the option
    * doesn't exist.
    * The type of the enum
    *  @param option 
    *  @param defaultValue 
    *  @return
    */
    protected <T>T getEnum(String option, T defaultValue) throws Exception {
        if (!T.class.isEnum())
            throw new NotSupportedException("Type " + T.class + " is not an enum.");
         
        String value;
        boolean boolVar___31 = !_options.TryGetValue(option, refVar___16);
        if (boolVar___31)
        {
            RefSupport<String> refVar___16 = new RefSupport<String>();
            T resVar___32 = defaultValue;
            value = refVar___16.getValue();
            return resVar___32;
        }
         
        T ret = new T();
        boolean boolVar___33 = Enum.<T>TryParse(value, true, refVar___17);
        if (boolVar___33)
        {
            RefSupport<T> refVar___17 = new RefSupport<T>();
            T resVar___34 = ret;
            ret = refVar___17.getValue();
            return resVar___34;
        }
         
        Aura.Shared.Util.Log.warning("Invalid value for '{0}', defaulting to '{1}'",option,defaultValue);
        return defaultValue;
    }

}


