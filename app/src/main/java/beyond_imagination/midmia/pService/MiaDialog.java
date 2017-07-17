package beyond_imagination.midmia.pService;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import beyond_imagination.midmia.Child;
import beyond_imagination.midmia.R;

/**
 * Created by laggu on 2017-07-17.
 */

class MiaDialog extends Dialog {
    ImageView imageView;
    TextView textView;
    Button noButton, yesButton;
    Child child;
    boolean lost;

    MiaDialog(@NonNull Context context, Child child) {
        super(context);
        this.child = child;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = (ImageView) findViewById(R.id.imageView_miaDialog);
        textView = (TextView)findViewById(R.id.textView_miaDialog);
        noButton = (Button) findViewById(R.id.noButton_miaDialog);
        yesButton = (Button) findViewById(R.id.yesButton_miaDialog);

        imageView.setImageBitmap(child.getPhoto());

        textView.setText("아이와 연결이 끊어졌습니다." +
                "\n아이의 위치를 알고 있나요?");

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Server.uploadData(1.0,1.0);
                lost = true;
                dismiss();
            }
        });
    }

    boolean isLost(){
        return lost;
    }
}
