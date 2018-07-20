package radio.sa.com.oir_dev_3;

import java.io.Serializable;

public class Station implements Serializable{

    private String station_name,station_url;

    public Station(String station_name, String station_url) {
        this.station_name = station_name;
        this.station_url = station_url;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getStation_url() {
        return station_url;
    }
}
