package me.blitzerino.chloe.registration;

import me.blitzerino.chloe.Chloe;

/**
 * Created by Blitz on 8/5/2016.
 */
public class Setcommands {
    public static void setCommands(){
        addCmd("~ ~ <Misc> ~", " ~");
        addCmd("test [%null%]", "Baby's first command :)");
        addCmd("help [%null%]", "The help command for this bot!");
        addCmd("google [%user%]", "Search Google either for yourself or another");
        addCmd("youtube [%null%]", "Search Youtube either for yourself or another");
        addCmd("runtime [%null%]", "Get my current runtime");
        addCmd("info [%null%]", "Get some information about me");
        addCmd("user [%user%]", "Get some information about a specific user");
        addCmd("share [%Img%]", "You can either get my image list or share a img");
        addCmd("~ ~ <Music> ~", " ~");
        addCmd("play [%null%]", "Resume a played song");
        addCmd("play [%AudioLink%]", "Play a song through the bot");
        addCmd("join [%VC%]", "Make the bot join a Voice Channel");
        addCmd("leave [%null%]", "Make the bot leave any active VC channels");
        addCmd("play [%null%]", "Resume a played song");
        addCmd("shuffle [%null%]", "Shuffle the active playlist's");
        addCmd("stop [%null%]", "Stop all music the bot is playing");
        addCmd("volume [%Integer%]", "Set the bot's music volume");
        addCmd("list [%null%]", "Get a list of all songs in the queue");
        addCmd("playing [%null%]", "Get the name of the current song playing");
        addCmd("skip [%null%]", "Skip the current playing song");
        addCmd("repeat [%null%]", "Repeat the last played song");
        addCmd("~ ~ <Administration> ~", " ~");
        addCmd("addimg <%link%> <%Ext%> <%Name%>[%null%]", "Add a image to my meme supply");
        addCmd("changelog [<add/reset>]", "Get something from my changelog or add something to it");
        addCmd("addadmin [<%user%>]", "Add a administrator");
        addCmd("todo [%text%]", "Either set my todo get my todo list");
        addCmd("execute [%Java Code%]", "Execute Java code from within a server");
        addCmd("set [%game%]", "Set my current game");
        addCmd("set [%nickname%]", "Set my current nickname");
        addCmd("mute [%user%]", "Mute a user");
        addCmd("shutdown [%null%]", "Shutdown the bot");
    }
    static void addCmd(String cmd, String desc){
        if(!cmd.contains("~ ~ ")) {
            Chloe.fincommands.add("~ " + cmd + "(" + desc + ")");
        }else{
            Chloe.fincommands.add(cmd + desc);
        }
    }
}
