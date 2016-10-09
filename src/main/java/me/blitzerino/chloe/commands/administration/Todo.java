package me.blitzerino.chloe.commands.administration;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.io.*;

import static me.blitzerino.chloe.api.API.getAPI;
import static me.blitzerino.chloe.api.FileManager.getFileManager;

/**
 * Created by Blitz on 8/5/2016.
 */
public class Todo extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(API.getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("todo")) {
                if (args.length == 1) {
                    MessageBuilder builder = new MessageBuilder();
                    builder.appendString("Here is my todo list " + e.getAuthor().getAsMention() + "!").appendString("\n");
                    builder.appendString("```xl").appendString("\n").appendString("-=Current Todo List=-").appendString("\n");
                    String line;
                    String fileName = "todo.txt";
                    int i = 1;
                    try {
                        FileReader fileReader = new FileReader(fileName);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        while ((line = bufferedReader.readLine()) != null) {
                            builder.appendString(i + ". " + line).appendString("\n");
                            i++;
                        }
                        fileReader.close();
                        bufferedReader.close();
                    } catch (FileNotFoundException p) {
                        p.printStackTrace();
                    } catch (IOException r) {
                        r.printStackTrace();
                    }
                    builder.appendString("```");
                    getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
                } else if (args.length > 2) {
                    if (args[1].equalsIgnoreCase("add")) {
                        if (Chloe.admins.contains(e.getAuthor().getId())) {
                            String rawr = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart() + " todo add", "").trim();
                            File file = new File("todo.txt");
                            FileWriter fw = null;
                            try {
                                fw = new FileWriter(file, true);
                            } catch (IOException r) {
                                r.printStackTrace();
                            }
                            BufferedWriter bw = new BufferedWriter(fw);
                            try {
                                bw.write(rawr);

                            } catch (IOException b) {
                                b.printStackTrace();
                            }
                            try {
                                bw.newLine();
                            } catch (IOException t) {
                                t.printStackTrace();
                            }
                            try {
                                bw.flush();
                            } catch (IOException m) {
                                m.printStackTrace();
                            }
                            try {
                                bw.close();
                            } catch (IOException p) {
                                p.printStackTrace();
                            }
                            getAPI().sendmsg(e.getChannel(), "Congratulations " + e.getAuthor().getAsMention() + ", You have successfully added `" + rawr.replaceFirst("- ", "") + "` to my todo list!", true);
                        } else {
                            getAPI().sendmsg(e.getChannel(), getAPI().getAdminMsg(), true);
                        }
                    }else if(args[1].equalsIgnoreCase("remove")){
                        String rawr = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart() + " todo remove", "").trim();
                        try {
                            getFileManager().removeTodo(rawr, "todo.txt");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        getAPI().sendmsg(e.getChannel(), "I have successfully attempted to remove `" + rawr + "` from my todo list.", true);
                    }else{
                        getAPI().sendmsg(e.getChannel(), "Incorrect usage! Syntax: `" + getAPI().getCmdStart() + " todo [add/remove} %text%`", true);
                    }
                }else{
                    getAPI().sendmsg(e.getChannel(), "Incorrect usage! Syntax: `" + getAPI().getCmdStart() + " todo [add/remove} %text%`", true);
                }
            }
        }
    }
}