package me.blitzerino.chloe.player.commands;

import me.blitzerino.chloe.registration.SetupPlayer;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.managers.AudioManager;
import net.dv8tion.jda.player.MusicPlayer;
import net.dv8tion.jda.player.Playlist;
import net.dv8tion.jda.player.source.AudioInfo;
import net.dv8tion.jda.player.source.AudioSource;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static me.blitzerino.chloe.api.API.getAPI;
import static me.blitzerino.chloe.registration.SetupPlayer.player;
import static net.dv8tion.jda.player.Bot.DEFAULT_VOLUME;

/**
 * Created by Blitz on 8/21/2016.
 */
public class Play extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            AudioManager manager = e.getGuild().getAudioManager();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (manager.getSendingHandler() == null) {
                player = new MusicPlayer();
                player.setVolume(DEFAULT_VOLUME);
                manager.setSendingHandler(player);
            } else {
                player = (MusicPlayer) manager.getSendingHandler();
            }
            if (args[0].equalsIgnoreCase("play")) {
                if (getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    if (args.length == 1) {
                        if (player.isPaused()) {
                            player.play();
                            getAPI().sendmsg(e.getChannel(), "Successfully resumed the song, " + e.getAuthor().getAsMention() + ".", true);
                        } else {
                            getAPI().sendmsg(e.getChannel(), "There is nothing playing right now! Add something with `" + getAPI().getCmdStart() + " play <URL>` " + e.getAuthor().getAsMention() + ".", true);
                        }
                    } else {
                        getAPI().sendmsg(e.getChannel(), "Attempting to play audio...", true);
                        String msg = "";
                        String url = args[1];
                        Playlist playlist = Playlist.getPlaylist(url);
                        List<AudioSource> sources = new LinkedList(playlist.getSources());
                        if (sources.size() > 1) {
                            getAPI().sendmsg(e.getChannel(), "Found a playlist with **" + sources.size() + "** entries.\n" +
                                    "Proceeding to gather information and queue sources. This may take some time...", true);
                            final MusicPlayer fPlayer = player;
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    for (Iterator<AudioSource> it = sources.iterator(); it.hasNext(); ) {
                                        AudioSource source = it.next();
                                        AudioInfo info = source.getInfo();
                                        List<AudioSource> queue = fPlayer.getAudioQueue();
                                        if (info.getError() == null) {
                                            queue.add(source);
                                            if (fPlayer.isStopped())
                                                fPlayer.play();
                                        } else {
                                            getAPI().sendmsg(e.getChannel(), "Error detected, skipping source. Error:\n" + info.getError(), true);
                                            it.remove();
                                        }
                                    }
                                    getAPI().sendmsg(e.getChannel(), "Finished queuing provided playlist. Successfully queued **" + sources.size() + "** sources", true);
                                    getAPI().delay();
                                    getAPI().getJDA().getAccountManager().setGame(SetupPlayer.player.getCurrentAudioSource().getInfo().getTitle());
                                }
                            };


                            thread.start();
                        } else {
                            AudioSource source = sources.get(0);
                            AudioInfo info = source.getInfo();
                            if (info.getError() == null) {
                                player.getAudioQueue().add(source);
                                msg += "The provided URL has been added the to queue";
                                if (player.isStopped()) {
                                    player.play();
                                    msg += " and the player has started playing";
                                }
                                getAPI().sendmsg(e.getChannel(), msg + ".", true);
                            } else {
                                getAPI().sendmsg(e.getChannel(), "There was an error while loading the provided URL.\n" +
                                        "Error: " + info.getError(), true);
                            }
                        }
                    }
                }else{
                    getAPI().sendmsg(e.getChannel(), "Sorry " + e.getAuthor().getAsMention() + ". You must have the rank: `" + getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
