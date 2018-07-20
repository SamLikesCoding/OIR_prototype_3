package radio.sa.com.oir_dev_3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Player_activity extends AppCompatActivity {


    RecyclerView rv;
    DB_Interface SPLAY;
    private Cursor channels;
    private ArrayList<Station> CH_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_activity);
        SPLAY = new DB_Interface(this);
        channels = SPLAY.get_the_point();
        CH_LIST = new ArrayList<>();
        while (channels.moveToNext()){
            CH_LIST.add(new Station(channels.getString(1),channels.getString(2)));
        }
        start_rv();


    }

    private void start_rv(){
        rv = findViewById(R.id.Station_PlayList);
        RV_ADAPTER adapter = new RV_ADAPTER(CH_LIST,this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
