package me.blitzerino.chloe.commands.administration;

import me.blitzerino.chloe.Chloe;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.api.API.getAPI;


/**
 * Created by Blitz on 8/6/2016.
 */
public class Shutdown extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("shutdown")) {
                if(Chloe.admins.contains(e.getAuthor().getId())){
                    getAPI().sendmsg(e.getChannel(), "Yes master..." + getAPI().getName() + " going down..", true);
                    getAPI().delay();
                    System.exit(-1);
                }else{
                    getAPI().sendmsg(e.getChannel(), getAPI().getAdminMsg(), true);

                }
            }
        }
    }
}
