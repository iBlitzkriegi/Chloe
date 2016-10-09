package me.blitzerino.chloe.commands.administration;

import me.blitzerino.chloe.Chloe;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.api.API.getAPI;


/**
 * Created by Blitz on 8/6/2016.
 */
public class Set extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("set")) {
                if (Chloe.admins.contains(e.getAuthor().getId())) {
                    if(args.length >= 2) {
                        if (args[1].equalsIgnoreCase("game")) {
                            String game = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart(), "").replaceFirst("set game", "");
                            e.getJDA().getAccountManager().setGame(game);
                            getAPI().delay();
                            getAPI().sendmsg(e.getChannel(), "Successfully set game to `" + game + "`!", true);
                        } else if (args[1].equalsIgnoreCase("nickname")) {
                            if (args.length == 2) {
                                String name = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart(), "").replaceFirst("set nickname", "");
                                e.getJDA().getAccountManager().setNickname(e.getGuild(), name);
                                getAPI().delay();
                                getAPI().sendmsg(e.getChannel(), "Successfully reset my nickname " + e.getAuthor().getAsMention() + ".", true);
                            } else {
                                String name = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart(), "").replaceFirst("set nickname", "");
                                e.getJDA().getAccountManager().setNickname(e.getGuild(), name);
                                getAPI().delay();
                                getAPI().sendmsg(e.getChannel(), "Successfully set nickname to `" + name + "`, " + e.getAuthor().getAsMention() + ".", true);
                            }
                        }else if(args[1].equalsIgnoreCase("username")){
                            getAPI().sendmsg(e.getChannel(), String.valueOf(args.length), true);
                            if(args.length == 3){
                                String name = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart(), "").replaceFirst("set username", "");
                                getAPI().getJDA().getAccountManager().setUsername(name).update();
                                getAPI().delay();
                                getAPI().sendmsg(e.getChannel(), "Successfully set my username to `" + name + "`, " + e.getAuthor().getAsMention() + ".", true);
                            }
                        }
                    }else{
                        getAPI().sendmsg(e.getChannel(), String.valueOf(args.length), true);
                    }
                } else {
                    getAPI().sendmsg(e.getChannel(), getAPI().getAdminMsg(), true);
                }
            }
        }
    }

}
