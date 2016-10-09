package me.blitzerino.chloe.commands.misc;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.util.Date;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/5/2016.
 */
public class Runtime extends ListenerAdapter{
    public static long getTimeRunning(){
        Date date = new Date();
        return (date.getTime() - Chloe.startupTime);
    }
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("runtime")) {
                long seconds = getTimeRunning() / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
                getAPI().sendmsg(e.getChannel(),"My current runtime is " + time + ".. Kill me.. " + u.getAsMention() + "!" , true);
            }
        }
    }
}
