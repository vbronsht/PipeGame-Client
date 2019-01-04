package view;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PlaySound
{

    public PlaySound()
    {
        // open the sound file as a Java input stream
        String sound = "./resources/sound.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(sound);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // create an audiostream from the inputstream
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // play the audio clip with the audioplayer class

        AudioData audiodata = null;
        try {
            audiodata = audioStream.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContinuousAudioDataStream loop = null;
        loop = new ContinuousAudioDataStream(audiodata);

        AudioPlayer.player.start(loop);
        //AudioPlayer.player.start(audioStream);
    }
}
