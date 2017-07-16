package beyond_imagination.midmia.pMainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class MainFragment extends Fragment {
    public static final int CHILD_INFO_ACTIVITY = 1001;

    private ArrayList<Child> children;
    private Button addChildButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        children = ((MainActivity)getActivity()).getChildren();

        addChildButton = (Button) rootView.findViewById(R.id.addChildButton);
        addChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChildInfoActivity.class);
                intent.putExtra("flag",1);
                startActivityForResult(intent, CHILD_INFO_ACTIVITY);
            }
        });

        return rootView;
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
                children.add(child);
                Database.insertIntoDatabase(child);
        }
    }
}