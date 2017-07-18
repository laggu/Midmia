package beyond_imagination.midmia;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import beyond_imagination.midmia.pMainFragment.MainFragment;
import beyond_imagination.midmia.pMapFragment.MapFragment;
import beyond_imagination.midmia.pSettingFragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private ArrayList<Child> children = null;
    private OrientationSensing orientationSensing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database.setDatabase(this);
        children = Database.readFromDatabase();

        orientationSensing = new OrientationSensing(this);

        setUpTabHost();
    }

    private void setUpTabHost(){
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("main").setIndicator("main"), MainFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mapt").setIndicator("map"), MapFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("setting").setIndicator("setting"), SettingFragment.class, null);
    }

    public ArrayList getChildren(){
        return children;
    }

    @Override
    protected void onResume() {
        super.onResume();
        orientationSensing.registerListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        orientationSensing.unregisterListener();
    }
}
