package me.blitzerino.chloe.api;

import net.dv8tion.jda.entities.Guild;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 9/29/2016.
 */
public class SelfInfo {
    // Instance \\
    private static SelfInfo instance;
    private SelfInfo() {
        instance = this;
    }
    public static SelfInfo getSelfInfo(){
        if(instance == null){
            return new SelfInfo();
        }else{
            return instance;
        }
    }
    // Getters \\
    public String getGame(){
        return getAPI().getJDA().getSelfInfo().getCurrentGame().getName();
    }
    public String getName(){
        return getAPI().getJDA().getSelfInfo().getUsername();
    }
    public String getID(){
        return getAPI().getJDA().getSelfInfo().getId();
    }
    public String getDiscriminator(){
        return getAPI().getJDA().getSelfInfo().getDiscriminator();
    }
    public String getAuthUrl(){
        return getAPI().getJDA().getSelfInfo().getAuthUrl();
    }
    // Setters \\
    public void setGame(String game){
        getAPI().getJDA().getAccountManager().setGame(game);
    }
    public void setName(String name){
        getAPI().getJDA().getAccountManager().setUsername(name).update();
    }
    public void setNickname(String nickname, Guild guild) {
        getAPI().getJDA().getAccountManager().setNickname(guild, nickname);
    }
    public void setStreaming(String Name, String url){
        getAPI().getJDA().getAccountManager().setStreaming(Name, url);

    }
    public void setIdle(Boolean isIdle){
        getAPI().getJDA().getAccountManager().setIdle(isIdle);
    }



}
