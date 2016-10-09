package me.blitzerino.chloe.player.events;

import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.player.hooks.PlayerListenerAdapter;
import net.dv8tion.jda.player.hooks.events.SkipEvent;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 9/29/2016.
 */
public class EvntSkip extends PlayerListenerAdapter{
    @Override
    public void onSkip(SkipEvent e){
        for(Guild s: getAPI().getJDA().getGuilds()){
            s.getJDA().getAccountManager().setGame(e.getPlayer().getCurrentAudioSource().getInfo().getTitle());
        }
        getAPI().infoPrint("SkipEvent fired.");

    }
}
