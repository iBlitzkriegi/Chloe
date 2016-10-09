package me.blitzerino.chloe.commands.administration;

import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;


/**
 * Created by Blitz on 8/11/2016.
 */
public class Spam extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("spam")) {
                if(args.length > 3){
                    int r = Integer.parseInt(args[1]);
                    User tospam = e.getMessage().getMentionedUsers().get(0);
                    String spammsg = e.getMessage().getContent().replaceFirst(API.getAPI().getCmdStart(), "").replaceFirst("spam", "").replaceFirst(String.valueOf(r), "").replaceFirst(String.valueOf(tospam.getUsername()), "").replaceFirst("@", "");
                    for(int i = 0; i < r; i++){
                        tospam.getPrivateChannel().sendMessage(spammsg);
                        System.out.println("Message: " + spammsg.trim() + " has been sent to " + tospam.getUsername());
                    }
                }
            }
        }
    }
}
