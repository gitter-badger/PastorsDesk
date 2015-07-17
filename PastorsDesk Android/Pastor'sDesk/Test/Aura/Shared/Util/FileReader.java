//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import Aura.Shared.Util.FileReader;
import Aura.Shared.Util.FileReaderLine;
import CS2JNet.JavaSupport.Collections.Generic.IteratorSupport;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.Collections.LCC.IEnumerator;
import CS2JNet.System.IO.FileNotFoundException;
import CS2JNet.System.IO.StreamReader;
import CS2JNet.System.LCC.Disposable;
import CS2JNet.System.LCC.IDisposable;
import CS2JNet.System.StringSupport;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* General use class to read files line for line.
* 
* Ignores empty lines and lines prefixed with '!', ';', '#', '//', or '--'.
* Supports including of files via 'include {file}' and 'require {file}'.
* Require raises an exception if the file couldn't be found.
* 
*  {@code 
* using (var fr = new FileReader(filePath))
* {
* foreach (string line in fr)
* {
* // Do something with line
* }
* }
* }
*/
public class FileReader   implements IEnumerable<FileReaderLine>, IDisposable
{
    private String _filePath;
    private String _relativePath;
    private BufferedReader _streamReader;
    private int __CurrentLine;
    public int getCurrentLine() {
        return __CurrentLine;
    }

    public void setCurrentLine(int value) {
        __CurrentLine = value;
    }

    public FileReader(String filePath) throws Exception {
        if (!(new File(filePath)).exists())
            throw new FileNotFoundException("File '" + filePath + "' not found.");
         
        _filePath = filePath;
        _relativePath = (new File((new File(filePath)).getCanonicalPath())).getParent();
        _streamReader = new BufferedReader(StreamReader.make(new BufferedInputStream(new FileInputStream(filePath))));
    }

    public IEnumerator<Aura.Shared.Util.FileReaderLine> getEnumerator() throws Exception {
        String line;
        while ((line = _streamReader.readLine()) != null)
        {
            // Until EOF
            this.setCurrentLine(this.getCurrentLine() + 1);
            line = StringSupport.Trim(line);
            if (String.IsNullOrWhiteSpace(line))
                continue;
             
            // Ignore very short or commented lines
            if (line.length() < 2 || line.charAt(0) == '!' || line.charAt(0) == ';' || line.charAt(0) == '#' || line.startsWith("//") || line.startsWith("--"))
                continue;
             
            // Include files
            boolean require = false, divert = false;
            if (line.startsWith("include ") || (require = line.startsWith("require ")) || (divert = line.startsWith("divert ")))
            {
                String fileName = StringSupport.Trim(line.substring(line.indexOf(' ')), new char[] {' ', '"'});
                String includeFilePath = (new File((!fileName.startsWith("/") ? _relativePath : ""), StringSupport.TrimStart(fileName, new char[] {'/'}))).toString();
                // Prevent rekursive including
                if (!StringSupport.equals(includeFilePath, _filePath))
                {
                    // Silently ignore failed includes, only raise an
                    // exception on require.
                    if ((new File(includeFilePath)).exists())
                    {
                        FileReader fr = new FileReader(includeFilePath);
                        try
                        {
                            {
                                for (FileReaderLine incLine : fr)
                            
                            }
                        }
                        finally
                        {
                            if (fr != null)
                                Disposable.mkDisposable(fr).dispose();
                             
                        }
                        // Stop reading current file if divert was successful
                        if (divert)
                            /* [UNSUPPORTED] yield statements are not supported "yield return break;" */
                         
                    }
                    else if (require)
                    {
                        throw new FileNotFoundException("Required file '" + includeFilePath + "' not found.");
                    }
                      
                }
                 
                continue;
            }
             
        
        }
    }

    IEnumerator iEnumerable___GetEnumerator() throws Exception {
        return this.getEnumerator();
    }

    public void dispose() throws Exception {
        _streamReader.close();
    }

    public Iterator<Aura.Shared.Util.FileReaderLine> iterator() {
        Iterator<Aura.Shared.Util.FileReaderLine> ret = null;
        try
        {
            ret = IteratorSupport.mk(this.getEnumerator());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ret;
    }

}


