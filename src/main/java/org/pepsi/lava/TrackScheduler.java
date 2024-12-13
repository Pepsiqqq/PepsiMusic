package org.pepsi.lava;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer audioPlayer;
    private final BlockingQueue<AudioTrack> queue = new LinkedBlockingQueue<>();

    public TrackScheduler (AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        audioPlayer.startTrack(queue.poll(),false);
    }
    public void queue(AudioTrack track){
        if(!audioPlayer.startTrack(track,true)){
            queue.offer(track);
        }
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }
}