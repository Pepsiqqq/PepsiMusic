package org.pepsi;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.pepsi.lava.Player;

import java.util.ArrayList;
import java.util.List;

public class Play{
    public String getName(){
        return "петь";
    }
    public String getDescription(){
        return "Играет песню";
    }
    public List<OptionData> getOptions(){
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "url", "дай ссьілку", true));
        return options;
    }
    public void execute(SlashCommandInteractionEvent event){
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();
        if(!memberVoiceState.inAudioChannel()){
            event.reply("зайди в канал дура").queue();
            return;
        }
        event.getGuild().getAudioManager().openAudioConnection(memberVoiceState.getChannel());
        Player player = new Player();
        event.reply("играю").queue();
        player.play(event.getGuild(), event.getOption("url").getAsString());
    }


}
