package radio.sa.com.oir_dev_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView player;
    CardView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = findViewById(R.id.Player);
        list = findViewById(R.id.List);

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_player = new Intent(MainActivity.this, Player_activity.class);
                startActivity(to_player);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_list = new Intent(MainActivity.this, List_Activity.class);
                startActivity(to_list);
            }
        });
    }
}
