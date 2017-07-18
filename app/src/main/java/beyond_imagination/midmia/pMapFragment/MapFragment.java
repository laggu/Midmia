package beyond_imagination.midmia.pMapFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import beyond_imagination.midmia.Child;
import beyond_imagination.midmia.R;
import beyond_imagination.midmia.pService.MiaDialog;
//import beyond_imagination.midmia.pService.Server;

/**
 * Created by laggu on 2017-07-15.
 */

public class MapFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_map, container, false);

        Button button = (Button)(rootView.findViewById(R.id.buttonTest));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiaDialog dialog = new MiaDialog(getContext(), new Child());

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        MiaDialog miaDialog =  (MiaDialog)dialog;
                        Toast.makeText(getContext(), ""+miaDialog.isLost(), Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });
        return rootView;
    }
}