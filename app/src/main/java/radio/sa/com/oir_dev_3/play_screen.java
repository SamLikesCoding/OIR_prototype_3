package radio.sa.com.oir_dev_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static radio.sa.com.oir_dev_3.R.mipmap.pause_white;

public class play_screen extends AppCompatActivity {

    ImageView to_list,next_stn,prev_stn,play_trigger;
    TextView radio_now;
    Boolean is_playing;
    Radio oir;
    int pos,tmp;
    String stn_name,stn_url;
    private static final String TAG = "Playing..." ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        to_list = findViewById(R.id.to_list);
        next_stn = findViewById(R.id.next_stn);
        prev_stn = findViewById(R.id.prev_stn);
        play_trigger = findViewById(R.id.play_trigger);
        radio_now = findViewById(R.id.radio_now);
        final Intent play_elements = getIntent();
        Bundle unpack = play_elements.getBundleExtra("class_list");
        final ArrayList<Station> seek_list;
        seek_list = (ArrayList<Station>) unpack.getSerializable("ARRAYLIST");
        pos = play_elements.getIntExtra("position",0);
        stn_name = seek_list.get(pos).getStation_name();
        stn_url =  seek_list.get(pos).getStation_url();
        tmp = pos;
        oir = new Radio(stn_url);
        is_playing = oir.get_state();
        if(is_playing){
            oir.stop_stream();
            oir.play_stream();
        }
        else{
            oir.play_stream();
        }

        radio_now.setText(stn_name);

        to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(play_screen.this,Player_activity.class);
                startActivity(intent);
            }
        });

        play_trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (is_playing){
                    oir.pause_stream();
                    play_trigger.setImageResource(R.mipmap.pause_white);
                    Log.d(TAG, "onClick: pause");
                }
                else {
                    oir.play_stream();
                    play_trigger.setImageResource(R.mipmap.play_white);
                    Log.d(TAG, "onClick: play");
                }
            }
        });

        next_stn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp = tmp + 1;
                oir.stop_stream();
                stn_name = seek_list.get(tmp).getStation_name();
                stn_url = seek_list.get(tmp).getStation_url();
                radio_now.setText(stn_name);
                oir.reset_url(stn_url);
                oir.play_stream();
            }
        });

        prev_stn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp = tmp - 1;
                oir.stop_stream();
                stn_name = seek_list.get(tmp).getStation_name();
                stn_url = seek_list.get(tmp).getStation_url();
                radio_now.setText(stn_name);
                oir.reset_url(stn_url);
                oir.play_stream();
            }
        });



    }
}
