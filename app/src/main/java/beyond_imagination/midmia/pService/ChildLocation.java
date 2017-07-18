package beyond_imagination.midmia.pService;

/**
 * Created by laggu on 2017-07-17.
 */

public class ChildLocation {
    double latitude, longitude;

    public ChildLocation(){

    }

    ChildLocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
