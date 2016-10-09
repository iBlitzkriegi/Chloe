package me.blitzerino.chloe.commands.misc;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/6/2016.
 */
public class Help extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("help")) {
                MessageBuilder builder = new MessageBuilder();
                getAPI().sendmsg(e.getChannel(),"I shall send you my command list to your Pm's in a few seconds, " + e.getAuthor().getAsMention() + "." , true);
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
