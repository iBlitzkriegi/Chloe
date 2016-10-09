package me.blitzerino.chloe.commands.administration;

import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.io.*;

import static me.blitzerino.chloe.api.API.getAPI;
import static me.blitzerino.chloe.api.FileManager.getFileManager;

/**
 * Created by Blitz on 10/3/2016.
 */
public class Changelog extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("changelog")) {
                if(getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    if (args.length > 1) {
                        if(args[1].equalsIgnoreCase("add")) {
                            String toadd = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart() + " changelog add ", "");
                            getFileManager().addChangeLog(toadd, "changelog.txt");
                            getAPI().sendmsg(e.getChannel(), "I have successfully attempted to add `" + toadd + "` to my changelog for you, " + e.getAuthor().getAsMention() + ".", true);
                        }else if(args[1].equalsIgnoreCase("reset")){
                            getFileManager().resetChangelog("changelog.txt");
                            getAPI().sendmsg(e.getChannel(), "I have successfully reset the changelog for you " + e.getAuthor().getAsMention() + ".", true);
                        }else{
                            getAPI().sendmsg(e.getChannel(), "Incorrect usage " + e.getAuthor().getAsMention() + ", syntax: `" + getAPI().getCmdStart() + " changelog [<add/reset>]`", true);
                        }
                    }else if(args.length == 1){
                        MessageBuilder builder = new MessageBuilder();
                        builder.appendString("Here is my changelog " + e.getAuthor().getAsMention() + "!").appendString("\n");
                        builder.appendString("```xl").appendString("\n").appendString("-=Current Changelog for Chloe version " + getAPI().getVersion() + "=-").appendString("\n");
                        String line;
                        String fileName = "changelog.txt";
                        File changelog = new File("changelog.txt");
                        try {
                            if(!changelog.createNewFile()){
                                changelog.createNewFile();
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            FileReader fileReader = new FileReader(fileName);
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            while ((line = bufferedReader.readLine()) != null) {
                                builder.appendString("* " + line).appendString("\n");
                            }
                            fileReader.close();
                            bufferedReader.close();
                        } catch (FileNotFoundException p) {
                            p.printStackTrace();
                        } catch (IOException r) {
                            r.printStackTrace();
                        }
                        builder.appendString("```");
                        getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
                    }
                }else{
                    getAPI().sendmsg(e.getChannel(), "You must have the rank `" + getAPI().getAdminrank() + "` to execute this command " + e.getAuthor().getAsMention() + ".", true);
                }
            }
        }
    }
}