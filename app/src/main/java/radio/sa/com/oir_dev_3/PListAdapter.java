package radio.sa.com.oir_dev_3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class PListAdapter extends ArrayAdapter<Station>{

    private Context cxt;
    int res;


    public PListAdapter(List_Activity list_activity, int channel_node, ArrayList<Station> ch_list) {
        super(list_activity,channel_node,ch_list);
        this.cxt = list_activity;
        this.res = channel_node;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getStation_name();
        String url_link = getItem(position).getStation_url();
        LayoutInflater inflater = LayoutInflater.from(cxt);
        convertView = inflater.inflate(res, parent, false);

        TextView s_name = (TextView) convertView.findViewById(R.id.STN_NAME);
        TextView s_url = (TextView) convertView.findViewById(R.id.STN_URL);

        s_name.setText(name);
        s_url.setText(url_link);

        return convertView;
    }
}
