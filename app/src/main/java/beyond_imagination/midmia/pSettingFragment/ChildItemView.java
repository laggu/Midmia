package beyond_imagination.midmia.pSettingFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import beyond_imagination.midmia.R;

/**
 * Created by laggu on 2017-07-16.
 */

public class ChildItemView extends LinearLayout {
    ImageView childImageView;
    TextView nameTextView, ageTextView, genderTextView;

    public ChildItemView(Context context) {
        super(context);
        init(context);
    }

    public ChildItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.child_item, this, true);

        childImageView = (ImageView)findViewById(R.id.childImageView_childItem);
        nameTextView = (TextView) findViewById(R.id.childNameTextView_childItem);
        ageTextView = (TextView) findViewById(R.id.childAgeTextView_childItem);
        genderTextView = (TextView) findViewById(R.id.childGenderTextView_childItem);
    }

    void setChildImageView(Bitmap bitmap){
        childImageView.setImageBitmap(bitmap);
    }

    void setNameTextView(String name){
        nameTextView.setText(name);
    }

    void setAgeTextView(String age){
        ageTextView.setText(age+"세");
    }

    void setGenderTextView(String gender){
        genderTextView.setText(gender);
    }
}
