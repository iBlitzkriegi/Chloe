package me.blitzerino.chloe.commands.misc;

import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.utils.MiscUtil;

import java.util.List;
import java.util.stream.Collectors;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/8/2016.
 */
public class UserInfo extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        Boolean founduser = false;
        if (e.getMessage().getContent().startsWith(getAPI().getCmdStart())) {
            String content = e.getMessage().getContent().substring(getAPI().getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("user")) {
                if (args.length == 2) {
                    if (e.getMessage().getMentionedUsers().size() == 1) {
                        net.dv8tion.jda.entities.User u = e.getMessage().getMentionedUsers().get(0);
                        String joindate = e.getGuild().getJoinDateForUser(u).getMonthValue() + "-" + e.getGuild().getJoinDateForUser(u).getDayOfMonth() + "-" + e.getGuild().getJoinDateForUser(u).getYear() + " at " + e.getGuild().getJoinDateForUser(u).getHour() + ":" + e.getGuild().getJoinDateForUser(u).getMinute();
                        String dcjoindate = String.valueOf(MiscUtil.getCreationTime(u.getId()).getMonthValue()) + "-" + String.valueOf(MiscUtil.getCreationTime(u.getId()).getDayOfMonth()) + "-" + String.valueOf(MiscUtil.getCreationTime(u.getId()).getYear());
                        MessageBuilder builder = new MessageBuilder();
                        builder.appendString(getAPI().getMsgStart() + "Here is all the information I could find on " + u.getUsername() + ", " + e.getAuthor().getAsMention() + ".").appendString("\n");
                        builder.appendString("```md").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "Username [->](" + u.getUsername() + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "Nickname [->](" + e.getGuild().getNicknameForUser(u) + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "ID [->](" + u.getId() + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "Playing [->](" + u.getCurrentGame() + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "FriendTag [->](" + u.getUsername() + "#" + u.getDiscriminator() + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "MentionTag [->](" + u.getAsMention() + ")").appendString("\n");
                        if(!u.getOnlineStatus().name().equals("UNKNOWN")) {
                            builder.appendString(getAPI().getMsgStart() + "Status [->](" + u.getOnlineStatus() + ")").appendString("\n");
                        }else{
                            builder.appendString(getAPI().getMsgStart() + "Status [->](Do Not Disturb)").appendString("\n");
                        }
                        builder.appendString(getAPI().getMsgStart() + "JoinedGuild [->](" + joindate + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "JoinedDiscord [->](" + dcjoindate + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "IsBot [->](" + u.isBot() + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "Discriminator [->](" + u.getDiscriminator() + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "AvatarUrl [->](" + u.getAvatarUrl() + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "Hashcode [->](" + u.hashCode() + ")").appendString("\n");
                        builder.appendString(getAPI().getMsgStart() + "Roles [->](");
                        List<Role> roles = e.getGuild().getRolesForUser(u);
                        for (int i = 0; i < roles.size(); i++) {
                            builder.appendString(roles.get(i).getName().toLowerCase());
                            if (i != roles.size() - 1) {
                                builder.appendString(", ");
                            } else {
                                builder.appendString(")");
                            }
                        }
                        builder.appendString("\n");
                        builder.appendString("```");
                        e.getChannel().sendMessage(builder.build());
                    } else {
                        String name = e.getMessage().getContent().replaceFirst(getAPI().getCmdStart() + " user", "").trim();
                        List<User> lauser = e.getGuild().getUsers().stream().filter(user -> user.getUsername().startsWith(name)).collect(Collectors.toList());
                        if (!lauser.isEmpty()) {
                            if (lauser.size() == 1) {
                                User u = lauser.get(0);
                                String joindate = e.getGuild().getJoinDateForUser(u).getMonthValue() + "-" + e.getGuild().getJoinDateForUser(u).getDayOfMonth() + "-" + e.getGuild().getJoinDateForUser(u).getYear() + " at " + e.getGuild().getJoinDateForUser(u).getHour() + ":" + e.getGuild().getJoinDateForUser(u).getMinute();
                                String dcjoindate = String.valueOf(MiscUtil.getCreationTime(u.getId()).getMonthValue()) + "-" + String.valueOf(MiscUtil.getCreationTime(u.getId()).getDayOfMonth()) + "-" + String.valueOf(MiscUtil.getCreationTime(u.getId()).getYear());
                                MessageBuilder builder = new MessageBuilder();
                                builder.appendString(getAPI().getMsgStart() + "Here is all the information I could find on " + u.getUsername() + ", " + e.getAuthor().getAsMention() + ".").appendString("\n");
                                builder.appendString("```md").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "Username [->](" + u.getUsername() + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "Nickname [->](" + e.getGuild().getNicknameForUser(u) + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "ID [->](" + u.getId() + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "Playing [->](" + u.getCurrentGame() + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "FriendTag [->](" + u.getUsername() + "#" + u.getDiscriminator() + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "MentionTag [->](" + u.getAsMention() + ")").appendString("\n");
                                if(!u.getOnlineStatus().name().equals("UNKNOWN")) {
                                    builder.appendString(getAPI().getMsgStart() + "Status [->](" + u.getOnlineStatus() + ")").appendString("\n");
                                }else{
                                    builder.appendString(getAPI().getMsgStart() + "Status [->](Do Not Disturb)").appendString("\n");
                                }
                                builder.appendString(getAPI().getMsgStart() + "JoinedGuild [->](" + joindate + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "JoinedDiscord [->](" + dcjoindate + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "IsBot [->](" + u.isBot() + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "Discriminator [->](" + u.getDiscriminator() + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "AvatarUrl [->](" + u.getAvatarUrl() + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "Hashcode [->](" + u.hashCode() + ")").appendString("\n");
                                builder.appendString(getAPI().getMsgStart() + "Roles [->](");
                                List<Role> roles = e.getGuild().getRolesForUser(u);
                                for (int i = 0; i < roles.size(); i++) {
                                    builder.appendString(roles.get(i).getName().toLowerCase());
                                    if (i != roles.size() - 1) {
                                        builder.appendString(", ");
                                    } else {
                                        builder.appendString(")");
                                    }
                                }
                                builder.appendString("\n");
                                builder.appendString("```");
                                e.getChannel().sendMessage(builder.build());
                                return;
                            } else if (lauser.size() > 1) {
                                MessageBuilder builder = new MessageBuilder();
                                builder.appendString("Hmm. It seems I found multiple users with that name " + e.getAuthor().getAsMention() + ". Here they are:").appendString("\n");
                                builder.appendString("```").appendString("\n");
                                builder.appendString("Potential User's").appendString("\n");
                                for (User s : lauser) {
                                    builder.appendString(s.getUsername() + "#" + s.getDiscriminator()).appendString("\n");
                                }
                                builder.appendString("```").appendString("\n");
                                getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
                            }
                        } else {
                            for (User u : e.getGuild().getUsers()) {
                                if (e.getGuild().getNicknameForUser(u) != null) {
                                    if(e.getGuild().getNicknameForUser(u).startsWith(name) || e.getGuild().getNicknameForUser(u).equalsIgnoreCase(name)) {
                                        String joindate = e.getGuild().getJoinDateForUser(u).getMonthValue() + "-" + e.getGuild().getJoinDateForUser(u).getDayOfMonth() + "-" + e.getGuild().getJoinDateForUser(u).getYear() + " at " + e.getGuild().getJoinDateForUser(u).getHour() + ":" + e.getGuild().getJoinDateForUser(u).getMinute();
                                        String dcjoindate = String.valueOf(MiscUtil.getCreationTime(u.getId()).getMonthValue()) + "-" + String.valueOf(MiscUtil.getCreationTime(u.getId()).getDayOfMonth()) + "-" + String.valueOf(MiscUtil.getCreationTime(u.getId()).getYear());
                                        MessageBuilder builder = new MessageBuilder();
                                        builder.appendString(getAPI().getMsgStart() + "Here is all the information I could find on " + u.getUsername() + ", " + e.getAuthor().getAsMention() + ".").appendString("\n");
                                        builder.appendString("```md").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "Username [->](" + u.getUsername() + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "Nickname [->](" + e.getGuild().getNicknameForUser(u) + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "ID [->](" + u.getId() + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "Playing [->](" + u.getCurrentGame() + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "FriendTag [->](" + u.getUsername() + "#" + u.getDiscriminator() + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "MentionTag [->](" + u.getAsMention() + ")").appendString("\n");
                                        if(!u.getOnlineStatus().name().equals("UNKNOWN")) {
                                            builder.appendString(getAPI().getMsgStart() + "Status [->](" + u.getOnlineStatus() + ")").appendString("\n");
                                        }else{
                                            builder.appendString(getAPI().getMsgStart() + "Status [->](Do Not Disturb)").appendString("\n");
                                        }
                                        builder.appendString(getAPI().getMsgStart() + "JoinedGuild [->](" + joindate + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "JoinedDiscord [->](" + dcjoindate + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "IsBot [->](" + u.isBot() + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "Discriminator [->](" + u.getDiscriminator() + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "AvatarUrl [->](" + u.getAvatarUrl() + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "Hashcode [->](" + u.hashCode() + ")").appendString("\n");
                                        builder.appendString(getAPI().getMsgStart() + "Roles [->](");
                                        List<Role> roles = e.getGuild().getRolesForUser(u);
                                        for (int i = 0; i < roles.size(); i++) {
                                            builder.appendString(roles.get(i).getName().toLowerCase());
                                            if (i != roles.size() - 1) {
                                                builder.appendString(", ");
                                            } else {
                                                builder.appendString(")");
                                            }
                                        }
                                        builder.appendString("\n");
                                        builder.appendString("```");
                                        getAPI().sendmsg(e.getChannel(), builder.build().getRawContent(), false);
                                        return;
                                    }
                                }
                            }
                            getAPI().sendmsg(e.getChannel(), "It seems you didn't tag a user so I searched this guild for a user by the name `" + name + "` but it also came up empty. Try tagging the user.", true);
                        }
                    }
                } else {
                    getAPI().sendmsg(e.getChannel(), "Incorrect usage! Syntax `.ch user <%user%>`", false);
                }
            }


        }
    }
}
