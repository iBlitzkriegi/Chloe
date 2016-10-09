package me.blitzerino.chloe.listeners;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 9/19/2016.
 */
public class Gmre extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User u = e.getAuthor();
        String content = e.getMessage().getContent();
        String[] args = content.split("\\s+");
        if (args.length == 1) {
            if(args[0].equalsIgnoreCase(getAPI().getCmdStart())) {
                MessageBuilder builder = new MessageBuilder();
                e.getChannel().sendMessage("I shall send you my command list to your Pm's in a few seconds, " + e.getAuthor().getAsMention() + ".");
                builder.appendString("```md").appendString("\n");
                for (String s : Chloe.fincommands) {
                    builder.appendString(API.getAPI().getMsgStart() + s).appendString("\n");
                }
                builder.appendString("```");
                e.getAuthor().getPrivateChannel().sendMessage(builder.build());
            }
        }

    }
}