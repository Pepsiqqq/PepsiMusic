package org.pepsi.lava;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.lavalink.youtube.YoutubeAudioSourceManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.AudioManager;

public class Player {
    AudioPlayerManager playerManager = new DefaultAudioPlayerManager();

    public Player (){
        YoutubeAudioSourceManager ytSourceManager = new dev.lavalink.youtube.YoutubeAudioSourceManager();
        //ytSourceManager.useOauth2(,false);

        playerManager.registerSourceManager(ytSourceManager);
        AudioSourceManagers.registerRemoteSources(playerManager,
                com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager.class);
        YoutubeAudioSourceManager source = new YoutubeAudioSourceManager();

    }
    public MusicManager getMusicManager(Guild guild){
        MusicManager mm = new MusicManager(playerManager);
        guild.getAudioManager().setSendingHandler(mm.getAudioForwarder());
        return mm;
    }

    public void play(Guild guild, String trackURL){
        MusicManager mm = getMusicManager(guild);
        playerManager.loadItemOrdered(mm, trackURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                mm.getTrackScheduler().queue(audioTrack);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {

            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException e) {

            }
        });
    }
}
