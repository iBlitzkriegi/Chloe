package me.blitzerino.chloe.player.commands;

import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.entities.VoiceChannel;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.managers.AudioManager;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 10/8/2016.
 */
public class Summon extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            AudioManager manager = e.getGuild().getAudioManager();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("summon")) {
                if (getAPI().hasRank(e.getChannel(), e.getAuthor())) {
                    User r = e.getAuthor();
                    for (VoiceChannel s : e.getGuild().getVoiceChannels()) {
                        if (s.getUsers().contains(r)) {
                            manager.openAudioConnection(s);
                            getAPI().sendmsg(e.getChannel(), "You have successfully summoned me into your Voice Channel.", true);
                            return;
                        }
                    }
                    getAPI().sendmsg(e.getChannel(), "You are not currenty in a Voice Channel! Please join one first.", true);
                }else{
                    getAPI().sendmsg(e.getChannel(), "Sorry " + e.getAuthor().getAsMention() + ". You must have the rank: `" + getAPI().getAdminrank() + "` to execute this command.", true);
                }
            }
        }
    }
}
