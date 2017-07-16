package beyond_imagination.midmia.pSettingFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import beyond_imagination.midmia.Child;
import beyond_imagination.midmia.Database;
import beyond_imagination.midmia.MainActivity;
import beyond_imagination.midmia.R;
import beyond_imagination.midmia.pChildInfoActivity.ChildInfoActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by laggu on 2017-07-15.
 */

public class SettingFragment extends ListFragment {
    public static final int CHILD_INFO_ACTIVITY = 3001;

    private MainActivity activity;
    private ChildAdapter adapter;
    private ArrayList<Child> children;

    private int lastClickedPosition;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity)getActivity();
        children = activity.getChildren();
        adapter = new ChildAdapter(children);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Child child = children.get(position);
        lastClickedPosition = position;

        Intent intent = new Intent(getActivity(), ChildInfoActivity.class);
        intent.putExtra("flag",2);
        intent.putExtra("child",child);

        startActivityForResult(intent, CHILD_INFO_ACTIVITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CHILD_INFO_ACTIVITY:
                Bundle bundle = data.getExtras();
                Child child = bundle.getParcelable("child");

                children.set(lastClickedPosition, child);
                Database.updateRecord(child);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
        }
    }
}