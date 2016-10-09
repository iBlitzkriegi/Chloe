package me.blitzerino.chloe.player.events;

import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.player.hooks.PlayerListenerAdapter;
import net.dv8tion.jda.player.hooks.events.NextEvent;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 9/17/2016.
 */
public class EvntNext extends PlayerListenerAdapter {
    @Override
    public void onNext(NextEvent e) {
        getAPI().infoPrint("NextEvent fired.");
        for(Guild s: getAPI().getJDA().getGuilds()){
            s.getJDA().getAccountManager().setGame(e.getPlayer().getCurrentAudioSource().getInfo().getTitle());
        }

    }
}
