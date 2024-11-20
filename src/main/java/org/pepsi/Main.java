package org.pepsi;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;



public class Main {
    public static void main(String[] args){
        JDA api = JDABuilder.createDefault("token")
                .build();
        CommandManager cc = new CommandManager();

        api.addEventListener(cc);

    }
}
