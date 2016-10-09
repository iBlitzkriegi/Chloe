package me.blitzerino.chloe.commands.misc;

import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.OnlineStatus;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.entities.VoiceChannel;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.util.List;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/12/2016.
 */
public class GuildInfo extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("guild")) {
                int online = 0;
                int away = 0;
                int offline = 0;
                int bots = 0;
                for(User s : e.getGuild().getUsers()){
                    if(s.getOnlineStatus().equals(OnlineStatus.ONLINE)){
                        online++;
                    }else if(s.getOnlineStatus().equals(OnlineStatus.AWAY)){
                        away++;
                    }else if(s.getOnlineStatus().equals(OnlineStatus.OFFLINE)){
                        offline++;
                    }
                }
                for(User s : e.getGuild().getUsers()){
                    if(s.isBot()){
                        bots++;
                    }
                }
                boolean islarge;
                if(e.getGuild().getUsers().size() > 50){
                    islarge = true;
                }else{
                    islarge = false;
                }
                net.dv8tion.jda.entities.Guild r = e.getGuild();
                MessageBuilder builder = new MessageBuilder();
                builder.appendString(getAPI().getMsgStart() + "Here is all the information I could find about this server " + u.getAsMention() + ".").appendString("\n");
                builder.appendString("```md").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "~ ~ <Basics> ~ ~").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Name [->](" + r.getName() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "ID [->](" + r.getId() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Members [->](" + r.getUsers().size() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Online [->](" + online + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Away [->](" + away + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Offline [->](" + offline + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "isLarge [->](" + islarge + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Bots [->](" + bots + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Verification [->](" + r.getVerificationLevel() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "TextChannels [->](" + r.getTextChannels().size() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "VoiceChannels [->](" + r.getVoiceChannels().size() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "AfkChannelID [->](" + r.getAfkChannelId() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "~ ~ <Info> ~ ~").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Owner [->](" + r.getOwner().getUsername() + "#" + r.getOwner().getDiscriminator() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "OwnerID [->](" + r.getOwnerId() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "OwnerMention [->](" + r.getOwner().getAsMention() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "TextChannels[->](");
                List<TextChannel> txtchannels = e.getGuild().getTextChannels();
                for(int i = 0; i < txtchannels.size(); i++){
                    builder.appendString(txtchannels.get(i).getName());
                    if(i != txtchannels.size() - 1){
                        builder.appendString(", ");
                    }else{
                        builder.appendString(")");
                    }
                }
                builder.appendString("\n").appendString(getAPI().getMsgStart() + "VoiceChannels[->](");
                List<VoiceChannel> vcchannels = e.getGuild().getVoiceChannels();
                for(int i = 0; i < vcchannels.size(); i++){
                    builder.appendString(vcchannels.get(i).getName().toLowerCase());
                    if(i != vcchannels.size() - 1){
                        builder.appendString(", ");
                    }else{
                        builder.appendString(")");
                    }
                }
                builder.appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "Region [->](" + r.getRegion() + ")").appendString("\n");
                builder.appendString(getAPI().getMsgStart() + "AfkTimeout [->](" + r.getAfkTimeout() + ")").appendString("\n");
                builder.appendString("```");
                getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
            }
        }
    }
}
