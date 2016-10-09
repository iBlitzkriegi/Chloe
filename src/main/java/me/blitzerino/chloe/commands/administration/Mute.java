package me.blitzerino.chloe.commands.administration;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by Blitz on 8/10/2016.
 */
public class Mute extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("mute")) {
                if(API.getAPI().hasRank(e.getChannel(), e.getAuthor())){
                    if(args.length == 2){
                        if(e.getMessage().getMentionedUsers().size() == 1){
                            User u = e.getMessage().getMentionedUsers().get(0);
                            if(!API.getAPI().muted.contains(u.getId())) {
                                API.getAPI().muted.add(u.getId());
                                API.getAPI().sendmsg(e.getChannel(), "You have successfully muted " + u.getAsMention() + ".", true);
                                Chloe.sendAdminMsg(e.getAuthor().getAsMention() + " has muted " + u.getAsMention() + ".");
                            }else{
                                API.getAPI().muted.remove(u.getId());
                                API.getAPI().sendmsg(e.getChannel(), "You have successfully un-muted " + u.getAsMention() + ".", true);
                                Chloe.sendAdminMsg(e.getAuthor().getAsMention() + " has un-muted " + u.getAsMention() + ".");
                            }
                        }else{
                            API.getAPI().sendmsg(e.getChannel(), "Incorrect usage! Syntax: `.g mute <user>`", true);
                        }
                    }else{
                        API.getAPI().sendmsg(e.getChannel(), "Incorrect usage! Syntax: `.g mute <user>`", true);
                    }
                }else{
                    API.getAPI().sendmsg(e.getChannel(), "Error! You must have the rank `" + API.getAPI().getAdminrank() + "` to execute this command, " + e.getAuthor().getAsMention() + ".", true);
                }
            }
        }
    }
}
