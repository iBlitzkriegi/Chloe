package me.blitzerino.chloe.registration;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.others.startup.StartupThread;
import net.dv8tion.jda.utils.AvatarUtil;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static me.blitzerino.chloe.Chloe.sendAdminMsg;
import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 10/8/2016.
 */
public class SetupBot {
    public static void SetupBot(){
        Date date = new Date();
        Chloe.startupTime = date.getTime();
        SetupPlayer.SetupPlayer();
        Setclasses.setClasses();
        Setadmins.setAdmins();
        Setcommands.setCommands();
        try {
            getAPI().getJDA().getAccountManager().setAvatar(AvatarUtil.getAvatar(ImageIO.read(new File("pfp.jpg")))).update();
        } catch (IOException e) {
            e.printStackTrace();
        }
        (new StartupThread()).start();
        getAPI().getJDA().getTextChannels().stream().filter(s -> s.getId().equalsIgnoreCase("212393303159734274")).forEach(s -> Chloe.adminchannel = s);
        sendAdminMsg(getAPI().getName() + " version `" + getAPI().getVersion() + "` has been successfully loaded. Type " + getAPI().getCmdStart() + " help to get started.");
    }
}
