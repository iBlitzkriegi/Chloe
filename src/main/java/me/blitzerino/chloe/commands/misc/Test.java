package me.blitzerino.chloe.commands.misc;

import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/3/2016.
 */
public class Test extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e)
    {
        if(e.getMessage().getContent().startsWith(".ping"))
        {
            User u = e.getAuthor();
            String[] args = e.getMessage().getContent().split("\\s+");
            args[0] = args[0].replaceFirst(getAPI().getCmdStart(), "");
            if(args[0].equalsIgnoreCase("test"))
            {
                getAPI().sendmsg(e.getChannel(), "I'll test your mom's bed out tonight, faggot.", true);
            }
        }
    }
}
