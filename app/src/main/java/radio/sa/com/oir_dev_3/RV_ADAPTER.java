package radio.sa.com.oir_dev_3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RV_ADAPTER extends RecyclerView.Adapter<RV_ADAPTER.ViewContainer> {

    private ArrayList<Station> PlayList = new ArrayList<>();
    private Context context;

    public RV_ADAPTER(ArrayList<Station> playList, Context context) {
        PlayList = playList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewContainer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_node,parent,false);
        ViewContainer container = new ViewContainer(v);
        return container;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewContainer holder, final int position) {
        final Station current_station = PlayList.get(position);
        final String current_station_name = current_station.getStation_name();
        holder.Radio_Station_Name.setText(current_station_name);
        holder.play_node.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context,play_screen.class);
               Bundle st_obj = new Bundle();
               st_obj.putSerializable("ARRAYLIST",PlayList);
               intent.putExtra("class_list",st_obj);
               int pos = position;
               intent.putExtra("position",pos);
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PlayList.size();
    }

    public class ViewContainer extends RecyclerView.ViewHolder{

        TextView Radio_Station_Name,Radio_status;
        CardView play_node;

        public ViewContainer(View itemView) {
            super(itemView);
            Radio_Station_Name = itemView.findViewById(R.id.ChannelName);
            play_node = itemView.findViewById(R.id.play_node);
        }
    }

    private void toaster(String content){
        Toast.makeText(this.context,content,Toast.LENGTH_SHORT).show();
    }
}
