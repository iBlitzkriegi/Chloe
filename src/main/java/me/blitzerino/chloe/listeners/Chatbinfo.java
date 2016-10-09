package me.blitzerino.chloe.listeners;

import me.blitzerino.chloe.api.API;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by Blitz on 8/6/2016.
 */
public class Chatbinfo extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
//        if (e.getAuthor().getId().equals(e.getJDA().getSelfInfo().getId())) {
//            double amount = 0;
//            try {
//                amount = Double.parseDouble(String.valueOf(API.getAPI().binfo.get(e.getAuthor().getId())));
//            } catch (Exception r) {
//                r.printStackTrace();
//            }
//            API.getAPI().binfo.put(e.getAuthor().getId(), amount + 1);
//        }else
            if(e.getMessage().getContent().startsWith(API.getAPI().getCmdStart())){
            double amount = 0;
            try {
                amount = Double.parseDouble(String.valueOf(API.getAPI().binfo.get("commands").shortValue()));
            } catch (Exception r) {
                r.printStackTrace();
            }
            API.getAPI().binfo.put("commands", amount + 1);
        }
    }

}
