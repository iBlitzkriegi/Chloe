package me.blitzerino.chloe.player.events;

import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.player.hooks.PlayerListenerAdapter;
import net.dv8tion.jda.player.hooks.events.FinishEvent;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 9/29/2016.
 */
public class EvntFinish extends PlayerListenerAdapter {
    @Override
    public void onFinish(FinishEvent e){
        for(Guild s: getAPI().getJDA().getGuilds()){
            s.getJDA().getAccountManager().setGame(getAPI().getGame());
        }
        getAPI().getJDA().getAccountManager().setGame(getAPI().getGame());
        getAPI().infoPrint("FinishEvent fired");

    }

}
