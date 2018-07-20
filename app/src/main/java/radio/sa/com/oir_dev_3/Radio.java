package radio.sa.com.oir_dev_3;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

public class Radio extends Service{

    private MediaPlayer radio_obj;
    String inet_radio_url;

    public Radio(String inet_radio_url) {
        this.inet_radio_url = inet_radio_url;
        radio_obj = new MediaPlayer();
        radio_obj.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public Boolean get_state(){
        return radio_obj.isPlaying();
    }

    public void play_stream(){
        try {
            radio_obj.reset();
            radio_obj.setDataSource(inet_radio_url);
            radio_obj.prepare();
            radio_obj.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause_stream(){
        radio_obj.pause();
    }

    public void stop_stream(){
        radio_obj.stop();
        radio_obj.reset();
    }

    public void reset_url(String new_url){
        this.inet_radio_url = new_url;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
