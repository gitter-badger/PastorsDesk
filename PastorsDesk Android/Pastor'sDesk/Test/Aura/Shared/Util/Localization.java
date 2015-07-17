//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.System.IO.FileNotFoundException;
import CS2JNet.System.IO.StreamReader;
import CS2JNet.System.LCC.Disposable;
import CS2JNet.System.StringSupport;
import CS2JNet.System.Text.EncodingSupport;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Manages localization strings.
*/
public class Localization   
{
    private static HashMap<String,String> _storage = new HashMap<String,String>();
    /**
    * Starts parsing on path.
    * 
    * If path is a file, it simply reads the file. If path is a directory,
    * it starts parsing all files recursively.
    * 
    *  @param path What to parse
    */
    public static void parse(String path) throws Exception {
        if ((new File(path)).exists())
        {
            loadFile(path);
        }
        else if ((new File(path)).exists())
        {
            DirectoryInfo di = new File(path);
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ files = File.EnumerateFiles(path);
            for (/* [UNSUPPORTED] 'var' as type is unsupported "var" */ file : files)
                LoadFile(file);
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ dirs = File.EnumerateDirectories(path);
            for (/* [UNSUPPORTED] 'var' as type is unsupported "var" */ dir : dirs)
                Parse(dir);
        }
        else
        {
            throw new FileNotFoundException(path + " not found.");
        }  
    }

    /**
    * Adds strings in file to collection.
    * 
    *  @param path
    */
    private static void loadFile(String path) throws Exception {
        StreamReader sr = new BufferedReader(StreamReader.make(new BufferedInputStream(new FileInputStream(path), EncodingSupport.GetEncoder("UTF-8"))));
        try
        {
            {
                if (!(new File(path)).exists())
                    return ;
                 
                while (!sr.EndOfStream)
                {
                    String line = StringSupport.Trim(sr.readLine());
                    if (line.length() < 3 || line.startsWith("//"))
                        continue;
                     
                    // Next line if not tab found
                    Int32 pos = line.indexOf('\t');
                    if (pos < 0)
                        continue;
                     
                    String key = StringSupport.Trim(line.substring(0, (0) + (pos)));
                    String val = StringSupport.Trim(line.substring(pos + 1));
                    // Replace \t and [\r]\n
                    val = val.replace("\\t", "\t");
                    val = val.replace("\\r\\n", "\n");
                    val = val.replace("\\n", "\n");
                    _storage.put(key, val);
                }
            }
        }
        finally
        {
            if (sr != null)
                Disposable.mkDisposable(sr).dispose();
             
        }
    }

    /**
    * Returns localization string, or the key, if it doesn't exist.
    * 
    *  @param key 
    *  @return
    */
    public static String get(String key) throws Exception {
        String val;
        RefSupport<String> refVar___0 = new RefSupport<String>();
        _storage.TryGetValue(key, refVar___0);
        val = refVar___0.getValue();
        if (val != null)
            return val;
         
        return key;
    }

}


