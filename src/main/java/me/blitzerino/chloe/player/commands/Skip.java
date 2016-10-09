package me.blitzerino.chloe.player.commands;

import me.blitzerino.chloe.player.events.EvntNext;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.managers.AudioManager;
import net.dv8tion.jda.player.MusicPlayer;

import static me.blitzerino.chloe.api.API.getAPI;
import static me.blitzerino.chloe.registration.SetupPlayer.player;
import static net.dv8tion.jda.player.Bot.DEFAULT_VOLUME;

/**
 * Created by Blitz on 10/8/2016.
 */
public class Skip extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            AudioManager manager = e.getGuild().getAudioManager();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (manager.getSendingHandler() == null) {
                player = new MusicPlayer();
                player.addEventListener(new EvntNext());
                player.setVolume(DEFAULT_VOLUME);
                manager.setSendingHandler(player);
            } else {
                player = (MusicPlayer) manager.getSendingHandler();
            }
            if (args[0].equalsIgnoreCase("skip")) {
                if(getAPI().hasRank(e.getChannel(),e.getAuthor())) {
                    if (player.isPlaying()) {
                        player.skipToNext();
                        getAPI().sendmsg(e.getChannel(), "Skipped the current song.", true);
                        getAPI().delay();
                        getAPI().getJDA().getAccountManager().setGame(player.getCurrentAudioSource().getInfo().getTitle());
                    } else {
                        getAPI().sendmsg(e.getChannel(), "There was no songs playing! None to skip..", true);
                    }
                }else{
                    getAPI().sendmsg(e.getChannel(), "Sorry " + e.getAuthor().getAsMention() + ". You must have the rank: `" + getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
