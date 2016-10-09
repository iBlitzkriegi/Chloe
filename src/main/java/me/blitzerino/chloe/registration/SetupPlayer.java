package me.blitzerino.chloe.registration;

import me.blitzerino.chloe.player.events.EvntFinish;
import me.blitzerino.chloe.player.events.EvntNext;
import me.blitzerino.chloe.player.events.EvntPlay;
import me.blitzerino.chloe.player.events.EvntSkip;
import net.dv8tion.jda.player.MusicPlayer;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 9/29/2016.
 */
public class SetupPlayer {
    public static MusicPlayer player = new MusicPlayer();
    public static void SetupPlayer(){
        player.addEventListener(new EvntPlay());
        player.addEventListener(new EvntNext());
        player.addEventListener(new EvntFinish());
        player.addEventListener(new EvntSkip());
        getAPI().infoPrint("Player successfully initialized.");
    }
}
