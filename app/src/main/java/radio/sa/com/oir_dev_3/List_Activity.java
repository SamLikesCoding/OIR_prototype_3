package radio.sa.com.oir_dev_3;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List_Activity extends AppCompatActivity {

    Button to_add;
    DB_Interface db;
    ListView the_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);

        the_list = findViewById(R.id.ch_list);
        to_add = findViewById(R.id.to_add_page);
        db = new DB_Interface(this);
        to_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_edit_page = new Intent(List_Activity.this, Edit_Area.class);
                startActivity(to_edit_page);
            }
        });

        fill_the_list();
    }


    private void fill_the_list(){
        Cursor channels = db.get_the_point();
        ArrayList<Station> Ch_LIST = new ArrayList<>();
        while (channels.moveToNext()){
            Ch_LIST.add(new Station(channels.getString(1),channels.getString(2)));
        }
        PListAdapter listAdapter = new PListAdapter(this, R.layout.channel_node, Ch_LIST);
        the_list.setAdapter(listAdapter);
        the_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station channel = (Station) parent.getItemAtPosition(position);
                String c_name = channel.getStation_name();
                String c_url = channel.getStation_url();
                Cursor data = db.get_channel_id(c_name);
                int Ch_ID = -1;
                while (data.moveToNext()){
                    Ch_ID = data.getInt(0);
                    if (Ch_ID > -1){
                        Intent to_drw = new Intent(List_Activity.this,delete_or_rewrite.class);
                        to_drw.putExtra("ID",Ch_ID);
                        to_drw.putExtra("C_NAME",c_name);
                        to_drw.putExtra("C_URL",c_url);
                        startActivity(to_drw);
                    }
                    else{
                        toaster("Did not find anything ");
                    }
                }
            }
        });
    }

    private void toaster(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}
