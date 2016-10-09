package me.blitzerino.chloe;

import me.blitzerino.chloe.commands.administration.*;
import me.blitzerino.chloe.commands.administration.Shutdown;
import me.blitzerino.chloe.commands.memes.Addimg;
import me.blitzerino.chloe.commands.memes.Share;
import me.blitzerino.chloe.commands.misc.*;
import me.blitzerino.chloe.commands.misc.Runtime;
import me.blitzerino.chloe.commands.searching.Google;
import me.blitzerino.chloe.commands.searching.Youtube;
import me.blitzerino.chloe.listeners.Chatbinfo;
import me.blitzerino.chloe.listeners.Gmre;
import me.blitzerino.chloe.listeners.Pm;
import me.blitzerino.chloe.others.memes.Memecatch;
import me.blitzerino.chloe.player.commands.*;
import me.blitzerino.chloe.registration.SetupBot;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.entities.Channel;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import static me.blitzerino.chloe.api.API.getAPI;


/**
 * Created by Blitz on 8/3/2016.
 */
public class Chloe {
    public static String token;
    public static ArrayList<String> fincommands = new ArrayList<>();
    public static HashMap<String, String> commands = new HashMap<>();
    public static ArrayList<String> classes = new ArrayList<>();
    public static ArrayList<String> todo = new ArrayList<>();
    public static Channel adminchannel;
    public static ArrayList<String> images = new ArrayList<>();
    public static long startupTime;
    public static ArrayList<String> admins = new ArrayList<>();
    public static JDA s;
    public static void main(String[] args) throws LoginException, InterruptedException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("token.txt")));
            String line;
            while((line=br.readLine()) != null) {
                Chloe.token = line;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JDA api = new JDABuilder().setBotToken(token)
                .addListener(new Test())
                .addListener(new Addimg())
                .addListener(new Share())
                .addListener(new Help())
                .addListener(new Youtube())
                .addListener(new Set())
                .addListener(new Info())
                .addListener(new Execute())
                .addListener(new Todo())
                .addListener(new Shutdown())
                .addListener(new Spam())
                .addListener(new Pm())
                .addListener(new Rolecheck())
                .addListener(new Volume())
                .addListener(new Google())
                .addListener(new Mute())
                .addListener(new Clearchat())
                .addListener(new GuildInfo())
                .addListener(new UserInfo())
                .addListener(new Chatbinfo())
                .addListener(new Play())
                .addListener(new Runtime())
                .addListener(new Kick())
                .addListener(new AddAdmin())
                .addListener(new Repeat())
                .addListener(new Changelog())
                .addListener(new Reset())
                .addListener(new Playing())
                .addListener(new Join())
                .addListener(new Gmre())
                .addListener(new Leave())
                .addListener(new Pause())
                .addListener(new Stop())
                .addListener(new Summon())
                .addListener(new List())
                .setBulkDeleteSplittingEnabled(false)
                .buildBlocking();
        s = api;
        getAPI().binfo.put(getAPI().getJDA().getSelfInfo().getId(), 1D);
        getAPI().binfo.put("commands", 1D);
        SetupBot.SetupBot();
        Memecatch memecatch = new Memecatch();
        memecatch.cacheImages();
    }
    public static void sendAdminMsg(String s){
        adminchannel.getGuild().getTextChannels().get(adminchannel.getPosition()).sendMessage(getAPI().getMsgStart() + s);
    }

}
