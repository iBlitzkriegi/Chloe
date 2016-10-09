package me.blitzerino.chloe.commands.misc;

import me.blitzerino.chloe.Chloe;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.chloe.api.API.getAPI;
import static me.blitzerino.chloe.commands.misc.Runtime.getTimeRunning;

/**
 * Created by Blitz on 8/6/2016.
 */
public class Info extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("info")) {
                long seconds = getTimeRunning() / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                String time = days + " day(s), " + hours % 24 + " hour(s), " + minutes % 60 + " minute(s) and " + seconds % 60 + " second(s)";
                MessageBuilder builder = new MessageBuilder();
                builder.appendString(getAPI().getMsgStart() + "Here is all the information I can give about myself " + e.getAuthor().getAsMention() + ".").appendString("\n");
                builder.appendString("```md").appendString("\n");
                builder.appendString("~ ~ <Info> ~ ~").appendString("\n");
                builder.appendString("~ ID[->](" + e.getJDA().getSelfInfo().getId() + ")").appendString("\n");
                builder.appendString("~ Username[->](" + e.getJDA().getSelfInfo().getUsername() + ")").appendString("\n");
                builder.appendString("~ MentionTag[->](" + e.getJDA().getSelfInfo().getAsMention() + ")").appendString("\n");
                builder.appendString("~ Playing[->](" + e.getJDA().getSelfInfo().getCurrentGame() + ")").appendString("\n");
                builder.appendString("~ FriendTag[->](" + e.getJDA().getSelfInfo().getUsername() + "#" + e.getJDA().getSelfInfo().getDiscriminator() + ")").appendString("\n");
                builder.appendString("~ IsBot[->](" + e.getJDA().getSelfInfo().isBot() + ")").appendString("\n");
                builder.appendString("~ IsVerified[->](" + e.getJDA().getSelfInfo().isVerified() + ")").appendString("\n");
                builder.appendString("").appendString("\n");
                builder.appendString("~ ~ <Statistics> ~ ~").appendString("\n");
                builder.appendString("~ # Of Msg's Sent[->](" + getAPI().getBotMsgs(e.getJDA().getSelfInfo().getId()).shortValue() + ")").appendString("\n");
                builder.appendString("~ # Of Commands Executed[->](" + String.valueOf(getAPI().binfo.get("commands").shortValue() + ")")).appendString("\n");
                builder.appendString("~ # Of Guilds [->](" + e.getJDA().getGuilds().size() + ")").appendString("\n");
                builder.appendString("~ # Of Users [->](" + e.getJDA().getUsers().size() + ")").appendString("\n");
                builder.appendString("~ Runtime[->](" + time + ")").appendString("\n");
                builder.appendString("").appendString("\n");
                builder.appendString("~ ~ <Extras> ~ ~").appendString("\n");
                builder.appendString("~ # Of Commands[->](" + Chloe.fincommands.size() + ")").appendString("\n");
                builder.appendString("~ # Of Classes[->](" + Chloe.classes.size() + ")").appendString("\n");
                builder.appendString("~ # Of Admins[->](" + Chloe.admins.size() + ")").appendString("\n");
                builder.appendString("~ Owner[->](EdDaDiscord#7234)").appendString("\n");
                builder.appendString("~ Developer[->](Blitz#3273)").appendString("\n");
                builder.appendString("~ Server[->](https://discord.gg/ujkgmvb)").appendString("\n");
                builder.appendString("~ Source[->](https://www.github.com/iblitzkriegi/Chloe)").appendString("\n");
                builder.appendString("~ Host[->](Hosted by iBlitzkriegi)").appendString("\n");
                builder.appendString("~ Java[->](Java 8)").appendString("\n");
                builder.appendString("~ Library[->](JDA)").appendString("\n");
                builder.appendString("```");
                getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);

            }
        }
    }
}
