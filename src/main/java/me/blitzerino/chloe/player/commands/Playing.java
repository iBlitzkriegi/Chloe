package me.blitzerino.chloe.player.commands;

import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.managers.AudioManager;
import net.dv8tion.jda.player.MusicPlayer;
import net.dv8tion.jda.player.source.AudioInfo;
import net.dv8tion.jda.player.source.AudioTimestamp;

import static me.blitzerino.chloe.api.API.getAPI;
import static me.blitzerino.chloe.registration.SetupPlayer.player;
import static net.dv8tion.jda.player.Bot.DEFAULT_VOLUME;

/**
 * Created by Blitz on 10/8/2016.
 */
public class Playing extends ListenerAdapter {
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
            if (args[0].equalsIgnoreCase("playing")) {
                if (getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    if (player.isPlaying()) {
                        AudioTimestamp currentTime = player.getCurrentTimestamp();
                        AudioInfo info = player.getCurrentAudioSource().getInfo();
                        if (info.getError() == null) {
                            getAPI().sendmsg(e.getChannel(),
                                    "**Playing:** " + info.getTitle() + "\n" +
                                            "**Time:**    [" + currentTime.getTimestamp() + " / " + info.getDuration().getTimestamp() + "]", true);
                        } else {
                            getAPI().sendmsg(e.getChannel(),
                                    "**Playing:** Info Error. Known source: " + player.getCurrentAudioSource().getSource() + "\n" +
                                            "**Time:**    [" + currentTime.getTimestamp() + " / (N/A)]", true);
                        }
                    } else {
                        getAPI().sendmsg(e.getChannel(), "The player is not currently playing anything!", true);
                    }
                }else{
                    getAPI().sendmsg(e.getChannel(), "Sorry " + e.getAuthor().getAsMention() + ". You must have the rank: `" + getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
