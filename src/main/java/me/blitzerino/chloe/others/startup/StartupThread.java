package me.blitzerino.chloe.others.startup;

import me.blitzerino.chloe.api.API;

import static me.blitzerino.chloe.Chloe.*;

/**
 * Created by Blitz on 8/21/2016.
 */
public class StartupThread extends java.lang.Thread {
    public void run() {API.getAPI().delay();
        API.getAPI().infoPrint("Registering images.");
        API.getAPI().delay();
        API.getAPI().parserPrint("Parsing classes...");
        API.getAPI().delay();
        for (String s : classes) {
            API.getAPI().parserPrint(s + ".java has been successfully parsed.");
            API.getAPI().delay();
        }
        API.getAPI().delay();
        API.getAPI().parserPrint("Done! Found " + classes.size() + " classes!");
        API.getAPI().delay();
        API.getAPI().parserPrint("Adding admins...");
        API.getAPI().delay();
        API.getAPI().parserPrint("Done! Found " + admins.size() + " admins!");
        API.getAPI().delay();
        API.getAPI().parserPrint("All done! Starting up! Found " + fincommands.size() + " commands.");
        API.getAPI().delay();
        API.getAPI().infoPrint("I've successfully been started! Type " + API.getAPI().getCmdStart() + " help ingame to start!");
        return;

    }
}
