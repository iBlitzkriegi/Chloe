package me.blitzerino.chloe.commands.misc;

import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by Blitz on 8/10/2016.
 */
public class Rolecheck extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            net.dv8tion.jda.entities.User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+s");
            if (args[0].equalsIgnoreCase("rolecheck")) {
                if(API.getAPI().hasRank(e.getChannel(), e.getAuthor())){
                    API.getAPI().sendmsg(e.getChannel(), "You have the rank, so here is a potato: :sweet_potato:", true);
                }else{
                    API.getAPI().sendmsg(e.getChannel(), "Error! You must have the rank `" + API.getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
