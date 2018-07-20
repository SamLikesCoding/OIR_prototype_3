package radio.sa.com.oir_dev_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Edit_Area extends AppCompatActivity {

    EditText stn_name,stn_url;
    ImageView add_channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__area);

        stn_name = findViewById(R.id.station_name);
        stn_url = findViewById(R.id.station_url);
        add_channel = findViewById(R.id.add_trigger);
        final DB_Interface DB = new DB_Interface(this);

        add_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean job;
                String Ch_name = stn_name.getText().toString();
                String Ch_URL = stn_url.getText().toString();
                if (Ch_name.length() != 0 && Ch_URL.length() != 0){
                    job = DB.add_channel(Ch_name,Ch_URL);
                    if (job){
                        toaster(Ch_name+" has added to Channel List ");
                        Intent go_home = new Intent(Edit_Area.this, MainActivity.class);
                        startActivity(go_home);
                    }
                    else {
                        toaster(" Could not add to list");
                    }
                }
                else{
                    toaster(" The field seems to be empty : Fill the field ");
                }

            }
        });

    }

    private void toaster(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}
