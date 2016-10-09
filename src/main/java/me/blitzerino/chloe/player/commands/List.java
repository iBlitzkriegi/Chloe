package me.blitzerino.chloe.player.commands;

import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.managers.AudioManager;
import net.dv8tion.jda.player.MusicPlayer;
import net.dv8tion.jda.player.source.AudioInfo;
import net.dv8tion.jda.player.source.AudioSource;
import net.dv8tion.jda.player.source.AudioTimestamp;

import static me.blitzerino.chloe.api.API.getAPI;
import static me.blitzerino.chloe.registration.SetupPlayer.player;
import static net.dv8tion.jda.player.Bot.DEFAULT_VOLUME;

/**
 * Created by Blitz on 10/8/2016.
 */
public class List extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            AudioManager manager = e.getGuild().getAudioManager();
            String message = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart(), "").trim();
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (manager.getSendingHandler() == null) {
                player = new MusicPlayer();
                player.setVolume(DEFAULT_VOLUME);
                manager.setSendingHandler(player);
            } else {
                player = (MusicPlayer) manager.getSendingHandler();
            }
            if (args[0].equalsIgnoreCase("list")) {
                if (getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    java.util.List<AudioSource> queue = player.getAudioQueue();
                    if (queue.isEmpty()) {
                        getAPI().sendmsg(e.getChannel(), "The queue is currently empty!", true);
                        return;
                    }
                    MessageBuilder builder = new MessageBuilder();
                    builder.appendString("__Current Queue.  Entries: " + queue.size() + "__\n");
                    for (int i = 0; i < queue.size() && i < 10; i++) {
                        AudioInfo info = queue.get(i).getInfo();
                        if (info == null)
                            builder.appendString("*Could not get info for this song.*");
                        else {
                            AudioTimestamp duration = info.getDuration();
                            builder.appendString("`[");
                            if (duration == null)
                                builder.appendString("N/A");
                            else
                                builder.appendString(duration.getTimestamp());
                            builder.appendString("]` " + info.getTitle() + "\n");
                        }
                    }

                    boolean error = false;
                    int totalSeconds = 0;
                    for (AudioSource source : queue) {
                        AudioInfo info = source.getInfo();
                        if (info == null || info.getDuration() == null) {
                            error = true;
                            continue;
                        }
                        totalSeconds += info.getDuration().getTotalSeconds();
                    }

                    builder.appendString("\nTotal Queue Time Length: " + AudioTimestamp.fromSeconds(totalSeconds).getTimestamp());
                    if (error)
                        builder.appendString("`An error occured calculating total time. Might not be completely valid.");
                    e.getChannel().sendMessage(builder.build());
                }else{
                    getAPI().sendmsg(e.getChannel(), "Sorry " + e.getAuthor().getAsMention() + ". You must have the rank: `" + getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
