//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import Aura.Shared.Util.Log;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
public class CliUtil   
{
    /**
    * Prints Aura's ASCII art.
    * 
    *  @param title Name of this server (for the console's title)
    *  @param color Color of the header
    */
    public static void writeHeader(String title, ConsoleColor color) throws Exception {
        if (title != null)
            Console.Title = "Aura : " + title;
         
        Console.ForegroundColor = color;
        System.out.print("                          __     __  __  _ __    __                             ");
        System.out.print("                        /\'__`\\  /\\ \\/\\ \\/\\`\'__\\/\'__`\\                           ");
        System.out.print("                       /\\ \\L\\.\\_\\ \\ \\_\\ \\ \\ \\//\\ \\L\\.\\_                         ");
        System.out.print("                       \\ \\__/.\\_\\\\ \\____/\\ \\_\\\\ \\__/.\\_\\                        ");
        System.out.print("                        \\/__/\\/_/ \\/___/  \\/_/ \\/__/\\/_/                        ");
        System.out.print("                                                                                ");
        Console.ForegroundColor = ConsoleColor.White;
        System.out.print("                         by the Aura development team                           ");
        Console.ForegroundColor = ConsoleColor.DarkGray;
        System.out.print("________________________________________________________________________________");
        System.out.println("");
    }

    public static void loadingTitle() throws Exception {
        if (!Console.Title.StartsWith("* "))
            Console.Title = "* " + Console.Title;
         
    }

    public static void runningTitle() throws Exception {
        Console.Title = Console.Title.TrimStart('*', ' ');
    }

    /**
    * Waits for the return key, and closes the application afterwards.
    * 
    *  @param exitCode 
    *  @param wait
    */
    public static void exit(int exitCode, boolean wait) throws Exception {
        if (wait)
        {
            Log.info("Press Enter to exit.");
            Console.ReadLine();
        }
         
        Log.info("Exiting...");
        Environment.Exit(exitCode);
    }

    /**
    * Returns whether the application runs with admin rights or not.
    */
    public static boolean checkAdmin() throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ id = WindowsIdentity.GetCurrent();
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ principal = new WindowsPrincipal(id);
        return principal.IsInRole(WindowsBuiltInRole.Administrator);
    }

}


