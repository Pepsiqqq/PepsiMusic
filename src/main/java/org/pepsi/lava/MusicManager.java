package org.pepsi.lava;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class MusicManager {
    private TrackScheduler trackScheduler;
    private AudioPlayerSendHandler audioPlayerSendHandler;

    public MusicManager(AudioPlayerManager manager) {
        AudioPlayer player = manager.createPlayer();
        trackScheduler= new TrackScheduler(player);
        player.addListener(trackScheduler);
        audioPlayerSendHandler =new AudioPlayerSendHandler(player);
    }
    public TrackScheduler getTrackScheduler(){
        return trackScheduler;
    }
    public  AudioPlayerSendHandler getAudioForwarder(){
        return audioPlayerSendHandler;
    }
}
