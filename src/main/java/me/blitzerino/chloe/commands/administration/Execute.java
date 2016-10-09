package me.blitzerino.chloe.commands.administration;

import me.blitzerino.chloe.Chloe;
import me.blitzerino.chloe.api.SelfInfo;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.Channel;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import org.apache.commons.lang3.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/13/2016.
 */
public class Execute extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            String content = event.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("execute")) {
                if (Chloe.admins.contains(event.getAuthor().getId())) {
                    args[0] = "";
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
                    engine.put("jda", event.getJDA());
                    Channel r = event.getChannel();
                    net.dv8tion.jda.entities.Guild t = event.getGuild();
                    engine.put("guild", event.getGuild());
                    engine.put("channel", event.getChannel());
                    engine.put("account", event.getJDA().getAccountManager());
                    engine.put("e", event);
                    engine.put("Runtime", Runtime.getRuntime());
                    engine.put("API", getAPI());
                    engine.put("Bot", SelfInfo.getSelfInfo());
                    String script = StringUtils.join(args, " ");
                    MessageBuilder builder = new MessageBuilder();
                    builder.appendString("```").appendString("\n");
                    builder.appendString("Executed!").appendString("\n");
                    builder.appendString("```");
                    getAPI().sendmsg(event.getChannel(), "Attempting to execute `" + script + "` for you " + event.getAuthor().getAsMention() + ".", true);
                    Object result;
                    try {
                        result = engine.eval(script);
                    } catch (ScriptException e) {
                        getAPI().sendmsg(event.getGuild().getTextChannels().get(r.getPosition()), "Error occured.. The error: ` " + e.toString() + "` while executing JavaScript statement.", true);
                        return;
                    }

                    if (result != null) {
                        if (result.getClass() == Integer.class || result.getClass() == Double.class || result.getClass() == String.class || result.getClass() == Boolean.class) {
                            getAPI().sendmsg(event.getChannel(), builder.build().getRawContent(), false);
                            getAPI().sendmsg(event.getGuild().getTextChannels().get(r.getPosition()), "The result is `" + result + "` " + event.getAuthor().getAsMention(), false);

                        }
                    }else{
                        getAPI().sendmsg(event.getChannel(), "There was no result so that probably means it was just a void response " + event.getAuthor().getAsMention() + ".", true);
                    }
                }else{
                    getAPI().sendmsg(event.getChannel(), "Sorry! This is a admin command only.", true);
                }
            }
        }
    }
}
