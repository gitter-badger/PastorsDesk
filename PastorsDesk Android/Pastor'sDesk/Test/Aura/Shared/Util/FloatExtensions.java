//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import CS2JNet.JavaSupport.util.LocaleSupport;
import java.util.Locale;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public class FloatExtensions   
{
    public static String toInvariant(/* parameter modifiers are not yet supported this */float f, String format) throws Exception {
        return f.ToString(format, LocaleSupport.INVARIANT);
    }

    public static String toInvariant(/* parameter modifiers are not yet supported this */double f, String format) throws Exception {
        return f.ToString(format, LocaleSupport.INVARIANT);
    }

}


