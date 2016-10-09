package me.blitzerino.chloe.commands.memes;

import me.blitzerino.chloe.api.API;
import me.blitzerino.chloe.others.memes.Memecatch;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static me.blitzerino.chloe.Chloe.admins;
import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/4/2016.
 */
public class Addimg extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("addimg")) {
                if (admins.contains(e.getAuthor().getId())) {
                    if (args.length > 1) {
                        if (args[1].contains("http")) {
                            if (args[2].contains("jpg") || args[2].contains("png")) {
                                e.getChannel().sendMessage(API.getAPI().getMsgStart() + " Attempting to add new image..");
                                File f = new File("memes.txt");
                                BufferedWriter br = null;
                                try {
                                    br = new BufferedWriter(new FileWriter(f, true));
                                } catch (Exception r) {
                                    r.printStackTrace();
                                }
                                if (br != null) {
                                    try {
                                        br.write("\n" + args[1] + "!" + args[2] + "!" + args[3]);
                                    } catch (IOException g) {
                                        g.printStackTrace();
                                    }
                                    try {
                                        br.close();
                                        Memecatch.cacheImage(args[1], args[2], args[3]);
                                        Memecatch.images.add(args[3] + "." + args[2]);
                                        getAPI().sendmsg(e.getChannel(),API.getAPI().getMsgStart() + "Image successfully added " + e.getAuthor().getAsMention() + "!" , true);
                                    } catch (IOException p) {
                                        p.printStackTrace();
                                    }
                                }
                            } else {
                                getAPI().sendmsg(e.getChannel(), "Incorrect usage " + e.getAuthor().getAsMention() + "! Syntax is: `" + API.getAPI().getCmdStart() + " addimg %directLink% %ImageExtension% %ImgName%`", true);
                            }
                        } else {
                            getAPI().sendmsg(e.getChannel(), "Incorrect usage " + e.getAuthor().getAsMention() + "! Syntax is: `" + API.getAPI().getCmdStart() + " addimg %directLink% %ImageExtension% %ImgName%`", true);
                        }
                    } else {
                        getAPI().sendmsg(e.getChannel(),"Incorrect usage " + e.getAuthor().getAsMention() + "! Syntax is: `" + API.getAPI().getCmdStart() + " addimg %directLink% %ImageExtension% %ImgName%`" , true);
                    }
                } else {
                    getAPI().sendmsg(e.getChannel(),API.getAPI().getCmdStart() + e.getAuthor().getAsMention() + API.getAPI().getAdminMsg() , true);
                }
            }
        }
    }
}
