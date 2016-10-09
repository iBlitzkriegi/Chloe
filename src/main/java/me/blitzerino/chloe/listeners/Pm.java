package me.blitzerino.chloe.listeners;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import net.dv8tion.jda.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by Blitz on 8/12/2016.
 */
public class Pm extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
        if (!e.getAuthor().getId().equals(e.getJDA().getSelfInfo().getId())) {
            ChatterBotFactory factory = new ChatterBotFactory();
            ChatterBot bot1 = null;
            try {
                bot1 = factory.create(ChatterBotType.CLEVERBOT);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            ChatterBotSession bot1session = bot1.createSession();
            String msg = e.getMessage().getContent();
            try {
                msg = bot1session.think(msg);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.getChannel().sendMessage(msg);
        }
    }

}
