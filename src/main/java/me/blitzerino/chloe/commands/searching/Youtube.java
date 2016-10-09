package me.blitzerino.chloe.commands.searching;

import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/5/2016.
 */
public class Youtube extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            args[0] = args[0].replaceFirst(API.getAPI().getCmdStart(), "");
            if (args[0].equalsIgnoreCase("yt")) {
                if (args.length >= 2) {
                    if (e.getMessage().getMentionedUsers().size() == 0) {
                        String question = "";
                        args[0] = "";
                        for (String s : args) {
                            if (s != "") {
                                question = question + s + "%20";
                            }
                        }
                        String rawr = question.substring(0, question.length() - 3) + "";
                        String url = "https://www.youtube.com/results?search_query=" + rawr;
                        MessageBuilder builder = new MessageBuilder();
                        builder.appendString(API.getAPI().getMsgStart() + e.getMessage().getAuthor().getAsMention() + " here you go you lazy ass.. ").appendString("\n").appendString(url);
                        getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
                    }else if(e.getMessage().getMentionedUsers().size() == 1){
                        String question = "";
                        args[0] = "";
                        args[1] = "";
                        for (String s : args) {
                            if (s != "") {
                                question = question + s + "%20";
                            }
                        }
                        String rawr = question.substring(0, question.length() - 3) + "";
                        String url = "https://www.youtube.com/results?search_query=" + rawr;
                        MessageBuilder builder = new MessageBuilder();
                        builder.appendString(API.getAPI().getMsgStart() + e.getMessage().getMentionedUsers().get(0).getAsMention() + "! " + e.getAuthor().getAsMention() + " thinks your dumbass needs this!").appendString("\n").appendString(url);
                        getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
                    }else{
                        getAPI().sendmsg(e.getChannel(), "You can only include one user!", true);
                    }
                }else{
                    getAPI().sendmsg(e.getChannel(),"Error occured!" , false);
                }
            }
        }
    }
}
