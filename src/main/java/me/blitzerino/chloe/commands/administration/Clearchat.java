package me.blitzerino.chloe.commands.administration;

import net.dv8tion.jda.MessageHistory;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.util.List;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/12/2016.
 */
public class Clearchat extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("clearchat")) {
                if (getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    if (args.length == 1) {
                        getAPI().sendmsg(e.getChannel(), "There is a insufficient amount of arguments, " + e.getAuthor().getAsMention() + ". Syntax `" + getAPI().getCmdStart() + " clearchat <integer>`", true);
                        return;
                    }
                    MessageHistory messageHistory = e.getChannel().getHistory();

                    int amount = (int) Math.round(Double.parseDouble(args[1]));
                    amount = Math.min(amount, 100);

                    if (amount < 2) {
                        getAPI().sendmsg(e.getChannel(), "You need to delete 2 or more messages to use this command.", true);
                        return;

                    }
                    List<Message> messages = messageHistory.retrieve(amount);
                    e.getChannel().deleteMessages(messages);
                    getAPI().sendmsg(e.getChannel(), e.getAuthor().getAsMention() + " ➜ Attempted to delete `" + messages.size() + "` messages.", true);
                }else{
                    getAPI().sendmsg(e.getChannel(), "You need the rank `" + getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
