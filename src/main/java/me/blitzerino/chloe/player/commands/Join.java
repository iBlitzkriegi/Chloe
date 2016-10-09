package me.blitzerino.chloe.player.commands;

import net.dv8tion.jda.entities.VoiceChannel;
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
public class Join extends ListenerAdapter {
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
            if (args[0].equalsIgnoreCase("join")) {
                if (getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    String chanName = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart() + " join ", "");
                    VoiceChannel channel = e.getGuild().getVoiceChannels().stream().filter(
                            vChan -> vChan.getName().equalsIgnoreCase(chanName))
                            .findFirst().orElse(null);  //If there isn't a matching name, return null.
                    if (channel == null) {
                        getAPI().sendmsg(e.getChannel(), "There isn't a VoiceChannel in this Guild with the name: '" + chanName + "'", true);
                        return;
                    }
                    manager.openAudioConnection(channel);
                    getAPI().sendmsg(e.getChannel(), "Successfully joined VoiceChannel `" + chanName + "`", true);
                }else{
                    getAPI().sendmsg(e.getChannel(), "Sorry " + e.getAuthor().getAsMention() + ". You must have the rank: `" + getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
