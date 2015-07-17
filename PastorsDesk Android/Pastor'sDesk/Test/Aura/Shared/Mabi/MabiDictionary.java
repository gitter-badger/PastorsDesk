//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi;

import Aura.Shared.Mabi.MabiDictionary;
import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.JavaSupport.util.LocaleSupport;
import CS2JNet.System.StringSupport;
import CS2JNet.System.Text.RegularExpressions.GroupCollection;
import CS2JNet.System.Text.RegularExpressions.Match;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* "Generic" dictionary wrapper that can hold various var types and
* serializes to this format: SOMEINT:4:1234;SOMESTR:s:test;
* which is primarily used in items and quests.
*/
public class MabiDictionary   
{
    // Stored as object so we can put anything in
    private HashMap<String,Object> _values = new HashMap<String,Object>();
    private String _cache = null;
    public MabiDictionary() throws Exception {
    }

    public MabiDictionary(String toParse) throws Exception {
        this.parse(toParse);
    }

    public static MabiDictionary getEmpty() throws Exception {
        return new MabiDictionary();
    }

    private void set(String key, Object val) throws Exception {
        _values.put(key, val);
        _cache = null;
    }

    public void setByte(String key, byte val) throws Exception {
        this.set(key,val);
    }

    public void setShort(String key, short val) throws Exception {
        this.set(key,val);
    }

    public void setUShort(String key, ushort val) throws Exception {
        this.set(key,(short)val);
    }

    public void setInt(String key, int val) throws Exception {
        this.set(key,val);
    }

    public void setUInt(String key, uint val) throws Exception {
        this.set(key,(int)val);
    }

    public void setLong(String key, long val) throws Exception {
        this.set(key,val);
    }

    public void setULong(String key, ulong val) throws Exception {
        this.set(key,(long)val);
    }

    public void setFloat(String key, float val) throws Exception {
        this.set(key,val);
    }

    public void setString(String key, String val) throws Exception {
        this.set(key,val);
    }

    public void setString(String key, String format, Object... args) throws Exception {
        this.set(key,String.format(StringSupport.CSFmtStrToJFmtStr(format),args));
    }

    public void setBool(String key, boolean val) throws Exception {
        this.set(key,val);
    }

    /**
    * Returns the value with the given key, or null it wasn't found.
    * 
    *  @param key 
    *  @return
    */
    public Object get(String key) throws Exception {
        Object result;
        RefSupport<Object> refVar___0 = new RefSupport<Object>();
        _values.TryGetValue(key, refVar___0);
        result = refVar___0.getValue();
        return result;
    }

    /**
    * Returns value for key as T, or its default value if the key
    * doesn't exist, or is of different type.
    * 
    *  @param key 
    *  @return
    */
    private <T>T get(String key) throws Exception {
        Object result;
        RefSupport<Object> refVar___1 = new RefSupport<Object>();
        _values.TryGetValue(key, refVar___1);
        result = refVar___1.getValue();
        if (result != null && result instanceof T)
            return (T)result;
         
        return /* [UNSUPPORTED] default expressions are not yet supported "default T" */;
    }

    public byte getByte(String key) throws Exception {
        return this.get(key);
    }

    public short getShort(String key) throws Exception {
        return this.get(key);
    }

    public ushort getUShort(String key) throws Exception {
        return (ushort)this.get(key);
    }

    public int getInt(String key) throws Exception {
        return this.get(key);
    }

    public uint getUInt(String key) throws Exception {
        return ((Long) this.get(key));
    }

    public long getLong(String key) throws Exception {
        return this.get(key);
    }

    public ulong getULong(String key) throws Exception {
        return (ulong)this.get(key);
    }

    public float getFloat(String key) throws Exception {
        return this.get(key);
    }

    public String getString(String key) throws Exception {
        return this.get(key);
    }

    public boolean getBool(String key) throws Exception {
        return this.get(key);
    }

    /**
    * Removes the value with the given key.
    * 
    *  @param key
    */
    public void remove(String key) throws Exception {
        _values.remove(key);
        _cache = null;
    }

    /**
    * Removes all values.
    */
    public void clear() throws Exception {
        _values.clear();
        _cache = null;
    }

    /**
    * Returns number of values.
    */
    public int getCount() throws Exception {
        return _values.size();
    }

    /**
    * Returns whether a value exists for the given key.
    * 
    *  @param key 
    *  @return
    */
    public boolean has(String key) throws Exception {
        return _values.containsKey(key);
    }

    /**
    * Returns string type identifier for the object.
    * 
    *  @param val 
    *  @return
    */
    private String valToTypeStr(Object val) throws Exception {
        if (val instanceof Byte || val instanceof Byte)
            return "1";
        else if (val instanceof ushort || val instanceof Short)
            return "2";
        else if (val instanceof Long || val instanceof Integer)
            return "4";
        else if (val instanceof ulong || val instanceof Long)
            return "8";
        else if (val instanceof Float)
            return "f";
        else if (val instanceof String)
            return "s";
        else if (val instanceof Boolean)
            return "b";
        else
            throw new Exception("Unsupported type '" + val.getClass().toString() + "'");       
    }

    /**
    * Returns dictionary in the format "key:varType:value;...".
    * 
    *  @return
    */
    public String toString() {
        try
        {
            if (_values.size() < 1)
                return "";
             
            if (_cache != null)
                return _cache;
             
            StringBuilder sb = new StringBuilder();
            for (KeyValuePair tag : _values.entrySet())
            {
                String sType = this.valToTypeStr(tag.Value);
                if (StringSupport.equals(sType, "b"))
                    sb.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0}:{1}:{2};"),tag.Key,sType,(boolean)tag.Value ? "1" : "0"));
                else if (StringSupport.equals(sType, "s"))
                    sb.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0}:{1}:{2};"),tag.Key,sType,((String)tag.Value).replace(";", "%S").replace(":", "%C")));
                else
                    sb.append(String.format(StringSupport.CSFmtStrToJFmtStr("{0}:{1}:{2};"),tag.Key,sType,tag.Value));  
            }
            return (_cache = sb.toString());
        }
        catch (RuntimeException __dummyCatchVar0)
        {
            throw __dummyCatchVar0;
        }
        catch (Exception __dummyCatchVar0)
        {
            throw new RuntimeException(__dummyCatchVar0);
        }
    
    }

    /**
    * Reads a string in the format "key:varType:value;..." and adds
    * the values to this tag collection.
    * 
    *  @param str
    */
    public void parse(String str) throws Exception {
        if (String.IsNullOrWhiteSpace(str))
            return ;
         
        for (Object __dummyForeachVar1 : Pattern.Matches(str, "([^:]+):([^:]+):([^;]*);"))
        {
            Match match = (Match)__dummyForeachVar1;
            String key = GroupCollection.mk(match).get(1).getValue();
            String val = GroupCollection.mk(match).get(3).getValue();
            String __dummyScrutVar0 = GroupCollection.mk(match).get(2).getValue();
            if (__dummyScrutVar0.equals("1"))
            {
                this.set(key,Convert.ToByte(val));
            }
            else if (__dummyScrutVar0.equals("2"))
            {
                this.set(key,Convert.ToInt16(val));
            }
            else if (__dummyScrutVar0.equals("4"))
            {
                this.set(key,Integer.valueOf(val));
            }
            else if (__dummyScrutVar0.equals("8"))
            {
                this.set(key,Long.valueOf(val));
            }
            else if (__dummyScrutVar0.equals("f"))
            {
                this.set(key,Convert.ToSingle(val, LocaleSupport.INVARIANT));
            }
            else if (__dummyScrutVar0.equals("s"))
            {
                this.set(key,val.replace("%S", ";").replace("%C", ":"));
            }
            else if (__dummyScrutVar0.equals("b"))
            {
                this.set(key,StringSupport.equals(val, "1"));
            }
                   
        }
    }

    /**
    * Parses string and tries to return the value.
    * Returns T's default if the key can't be found or the type is incorrect.
    * 
    *  @param key 
    *  @param from 
    *  @return
    */
    public static <T>T fetch(String key, String from) throws Exception {
        MabiDictionary dict = new MabiDictionary(from);
        return dict.get(key);
    }

}


