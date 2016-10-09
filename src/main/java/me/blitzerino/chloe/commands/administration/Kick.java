package me.blitzerino.chloe.commands.administration;

import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.exceptions.PermissionException;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/24/2016.
 */
public class Kick extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("kick")) {
                if (getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    if (args.length != 2) {
                        getAPI().sendmsg(e.getChannel(), "Sorry! You have given an incorrect of arguments. Syntax: `" + getAPI().getCmdStart() + " Kick [%user%]`", true);
                    }else{
                        if(e.getMessage().getMentionedUsers().size() != 1){
                            getAPI().sendmsg(e.getChannel(), "Incorrect usage! Syntax: " + getAPI().getCmdStart() + " kick [%user%]", true);
                        }else{
                            User u = e.getMessage().getMentionedUsers().get(0);
                            try {
                                e.getGuild().getManager().kick(u);
                            }catch (PermissionException er){
                                getAPI().sendmsg(e.getChannel(), er.getLocalizedMessage(), true);
                                return;
                            }
                            TextChannel modchannel = null;
                            getAPI().sendmsg(e.getChannel(), "I have successfully kicked " + u.getUsername() + " for you " + e.getAuthor().getUsername() + ".", true);
                            for(TextChannel r: e.getGuild().getTextChannels()){
                                if(r.getName().equalsIgnoreCase("moderation")){
                                    modchannel = r;
                                }
                            }
                            if(modchannel != null){
                                modchannel.sendMessage(getAPI().getMsgStart() + e.getAuthor().getAsMention() + " has kicked " + u.getAsMention() + ".");
                            }
                        }
                    }
                }else{
                    getAPI().sendmsg(e.getChannel(), "You must have the role `" + getAPI().getAdminrank() + "` role to execute this command.", true);
                }
            }
        }
    }
}
