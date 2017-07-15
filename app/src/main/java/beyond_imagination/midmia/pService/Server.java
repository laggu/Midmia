package beyond_imagination.midmia.pService;

import android.os.StrictMode;
import android.util.Log;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by laggu on 2017-07-14.
 */

class Server {
    private static final String ADDRESS = "http://163.152.219.172/midmia/insert_mia_location.php";

    static{
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    static void uploadData(final double latitude, final double longitude){
        URL url = null;
        try{
             url = new URL(ADDRESS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            String postData = "latitude=" + latitude + "&" + "longitude=" + longitude;
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            conn.disconnect();
        }
        catch (Exception e) {
            Log.i("PHPRequest", "request was failed.");
        }
    }
}
