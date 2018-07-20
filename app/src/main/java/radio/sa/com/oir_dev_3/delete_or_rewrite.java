package radio.sa.com.oir_dev_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class delete_or_rewrite extends AppCompatActivity {

    EditText edit_name,edit_url;
    ImageView delete_trigger,update_trigger;
    DB_Interface DEDB;

    private String c_name,c_url;
    private int c_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_or_rewrite);

        edit_name = findViewById(R.id.station_name_edit);
        edit_url = findViewById(R.id.station_url_edit);
        delete_trigger = findViewById(R.id.delete_this);
        update_trigger = findViewById(R.id.apply_edit);
        DEDB = new DB_Interface(this);

        Intent from_past = getIntent();
        c_id = from_past.getIntExtra("ID",-1);
        c_name = from_past.getStringExtra("C_NAME");
        c_url = from_past.getStringExtra("C_URL");

        edit_name.setText(c_name);
        edit_url.setText(c_url);

        delete_trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DEDB.delete_this(c_id);
                toaster(" Station URL is deleted from the list ");
                Intent go_home = new Intent(delete_or_rewrite.this, MainActivity.class);
                startActivity(go_home);
            }
        });

        update_trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updated_name = edit_name.getText().toString();
                String updated_url = edit_url.getText().toString();

                if (updated_name.length() != 0 && updated_url.length() != 0){
                    if (!updated_name.equals(c_name)){
                        DEDB.edit_this(updated_name,c_id,c_name,0);
                        Intent go_home = new Intent(delete_or_rewrite.this, MainActivity.class);
                        startActivity(go_home);
                    }
                    if (!updated_url.equals(c_url)){
                        DEDB.edit_this(updated_url,c_id,c_url,1);
                        Intent go_home = new Intent(delete_or_rewrite.this, MainActivity.class);
                        startActivity(go_home);
                    }
                }
                else{
                    toaster(" The field should'nt be empty for edit");
                }
            }
        });
    }

    private void toaster(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }


}
