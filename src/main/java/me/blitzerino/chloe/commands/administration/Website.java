package me.blitzerino.chloe.commands.administration;

import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/11/2016.
 */
public class Website extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("website")) {
                if(args.length == 2){
                    String url = args[1];
                    HttpURLConnection connection = null;
                    try {
                        connection = (HttpURLConnection) new URL(url).openConnection();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        connection.setRequestMethod("HEAD");
                    } catch (ProtocolException e1) {
                        e1.printStackTrace();
                    }
                    int responseCode = 0;
                    try {
                        responseCode = connection.getResponseCode();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if (responseCode != 200) {
                        getAPI().sendmsg(e.getChannel(),"The url `" + url + "` does not seem to be up right now! Response code: " + responseCode , true);
                    }else{
                        getAPI().sendmsg(e.getChannel(), "Connection was successful! Website is up!", true);
                    }
                }
            }

        }
    }
}
