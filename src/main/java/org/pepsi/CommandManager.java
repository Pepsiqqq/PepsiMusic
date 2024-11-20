package org.pepsi;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class CommandManager extends ListenerAdapter {
    private Play p = new Play();
    @Override
    public  void onReady(@NotNull ReadyEvent event){
        Guild guild = event.getJDA().getGuildById(1298247104057184287L);
        guild.upsertCommand(p.getName(),p.getDescription()).addOptions(p.getOptions()).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(p.getName().equals(event.getName())){
            p.execute(event);
            return;
        }
    }
}
