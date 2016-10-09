package me.blitzerino.chloe.commands.administration;

import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.Chloe.admins;
import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 9/30/2016.
 */
public class AddAdmin extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("addadmin")) {
                if (getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    if (args.length == 1) {
                        MessageBuilder builder = new MessageBuilder();
                        builder.appendString("Not sure who you admins are " + e.getAuthor().getUsername() + "? Here they are!").appendString("\n");
                        builder.appendString("```xml").appendString("\n");
                        e.getJDA().getUsers().stream().filter(s -> admins.contains(s.getId())).forEach(s -> builder.appendString(s.getUsername() + "#" + s.getDiscriminator()).appendString("\n"));
                        builder.appendString("```").appendString("\n");
                        getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
                    } else if (args.length == 2) {
                        if (e.getMessage().getMentionedUsers().size() == 1) {
                            User target = e.getMessage().getMentionedUsers().get(0);
                            if (!admins.contains(target.getId())) {
                                getAPI().addAdmin(target);
                                getAPI().sendmsg(e.getChannel(), "I have successfully added " + target.getUsername() + " to my admin list. Welcome to my admin list, " + target.getUsername() + ".", true);
                            } else {
                                getAPI().sendmsg(e.getChannel(), target.getUsername() + " is already a admin, " + e.getAuthor().getUsername() + ".", true);
                            }
                        } else {
                            getAPI().sendmsg(e.getChannel(), "Incorrect usage! Syntax: `" + getAPI().getCmdStart() + " addadmin [%user%]`", true);
                        }
                    } else {
                        getAPI().sendmsg(e.getChannel(), "Incorrect usage! Syntax: `" + getAPI().getCmdStart() + " addadmin [%user%]`", true);
                    }
                }else{
                    getAPI().sendmsg(e.getChannel(), "Sorry " + e.getAuthor().getAsMention() + ", you must have the rank `" + getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
