package beyond_imagination.midmia;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import beyond_imagination.midmia.pMainFragment.MainFragment;
import beyond_imagination.midmia.pMapFragment.MapFragment;
import beyond_imagination.midmia.pSettingFragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("main").setIndicator("Simple"), MainFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("map").setIndicator("Contacts"), MapFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("setting").setIndicator("Custom"), SettingFragment.class, null);
    }
}
