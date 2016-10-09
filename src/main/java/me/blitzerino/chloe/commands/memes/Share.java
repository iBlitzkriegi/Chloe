package me.blitzerino.chloe.commands.memes;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.api.API;
import me.blitzerino.chloe.others.memes.Memecatch;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.io.*;

import static me.blitzerino.chloe.api.API.getAPI;
import static me.blitzerino.chloe.others.memes.Memecatch.cacheImage;

/**
 * Created by Blitz on 8/4/2016.
 */
public class Share extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if(args[0].equalsIgnoreCase("share")) {
                if (args.length == 2) {
                    e.getChannel().sendFile(Memecatch.imageCache.get(args[1]), null);
                } else {
                    MessageBuilder builder = new MessageBuilder();
                    builder.appendString(getAPI().getMsgStart() + "Not sure " + e.getAuthor().getAsMention() + "? Here is a list of my images!").appendString("\n").appendString("```xl").appendString("\n").appendString("{*}---=CurrentImages=---{*}").appendString("\n");
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(new File("memes.txt")));
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            String[] another = line.split("!");
                            cacheImage(another[0], another[1], another[2]);
                            Chloe.images.add(another[2] + "." + another[1]);
                            builder.appendString(API.getAPI().getMsgStart() + another[2] + "." + another[1]).appendString("\n");
                        }
                        br.close();
                        builder.appendString("```");
                        getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
                        Chloe.images.clear();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
