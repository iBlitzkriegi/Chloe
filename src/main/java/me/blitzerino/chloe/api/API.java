package me.blitzerino.chloe.api;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.others.startup.StartupThread;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.entities.Channel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.utils.SimpleLog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static me.blitzerino.chloe.Chloe.s;

/**
 * Created by Blitz on 8/5/2016.
 */
public class API{
    private String cmdStart = ".ch";
    private String msgStart = "Ӝ ";
    private String name = "Chloe";
    private String adminmsg = "You are not a admin therefore you may not execute this command!";
    private String game = cmdStart + " help";
    private String adminrank = "Chloe Commander";
    private String version = "Beta 1.0.5";
    private Thread sendmsgthread;
    // Bot information methods \\
    public String getCmdStart(){return cmdStart;}
    public String getVersion(){return version;}
    public String getMsgStart(){return msgStart;}
    public String getAdminrank(){return adminrank;}
    public String getName(){return name;}
    public String getAdminMsg(){return adminmsg;}
    public String getGame(){return game;}
    // Map's \\
    public HashMap<String, Double> binfo = new HashMap<>(); // Playername, Messages sent
    public ArrayList<String> muted = new ArrayList<>();
    // This shit
    private static API instance;
    private API() {
        instance = this;
    }
    // Botinfo statistics \\
    @Deprecated
    public static void setBotMsgs(String user, double amount) {
        API.getAPI().binfo.put(user, amount);
    }
    public static Double getBotMsgs(String id) {
        return API.getAPI().binfo.get(id);
    }
    // Useful method's \\
    public void sendmsg(Channel s, String msg, Boolean usePrefix) {
        s.getGuild().getTextChannels().get(s.getPosition()).sendTyping();
        if(sendmsgthread != null) {
            try {
                sendmsgthread.sleep(new Random().nextInt(((700 - 400) + 1) + 400));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            sendmsgthread = new Thread();
            sendmsgthread.start();
            try{
                sendmsgthread.sleep(new Random().nextInt(((700 - 400) + 1) + 400));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(usePrefix) {
            s.getGuild().getTextChannels().get(s.getPosition()).sendMessage(getMsgStart() + msg);
        }else if(!usePrefix){
            s.getGuild().getTextChannels().get(s.getPosition()).sendMessage(msg);
        }
    }
    // Get JDA instance \\
    public JDA getJDA(){
        return s;
    }
    // Startup Thread stuff \\
    public void delay(){
        try {
            StartupThread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void infoPrint(String s){
        SimpleLog log = SimpleLog.getLog("Info");
        log.info(s);

    }
    public void parserPrint(String s){
        SimpleLog log = SimpleLog.getLog("Parser");

        log.info(s);
    }
    // Admin checks \\
    public boolean hasRank(Channel s, User u) {
        boolean hasRank;
        String urroles = String.valueOf(s.getGuild().getRolesForUser(u));
        if (urroles.contains(API.getAPI().adminrank)) {
            hasRank = true;
        } else {
            if(Chloe.admins.contains(u.getId())){
                hasRank = true;
            }else{
                hasRank = false;
            }
        }
        return hasRank;
    }
    // API Instance \\
    public static API getAPI(){
        if(instance == null){
            return new API();
        }else{
            return instance;
        }
    }
    // Adder methods \\
    public void addAdmin(User u) {
        File file = new File("admins.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
        } catch (IOException r) {
            r.printStackTrace();
        }

        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write(u.getId());
            Chloe.admins.add(u.getId());
        } catch (IOException b) {
            b.printStackTrace();
        }
        try {
            bw.newLine();
        } catch (IOException t) {
            t.printStackTrace();
        }
        try {
            bw.flush();
        } catch (IOException m) {
            m.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException p) {
            p.printStackTrace();
        }
    }
}
