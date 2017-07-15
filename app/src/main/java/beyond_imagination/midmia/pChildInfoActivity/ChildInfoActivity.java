package beyond_imagination.midmia.pChildInfoActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import beyond_imagination.midmia.Child;
import beyond_imagination.midmia.R;

public class ChildInfoActivity extends AppCompatActivity {

    public static final int PICK_FROM_CAMERA = 2000;
    public static final int PICK_FROM_ALBUM = 2001;
    public static final int CROP_THE_IMAGE = 2002;

    private ChildImageButton childImageButton;
    private NameEditText nameEditText;
    private AgeSpinner ageSpinner;
    private GenderRadioGroup genderRadioGroup;
    private SafeDistanceEditText safeDistanceEditText;
    private CommunicationCycleSpinner cycleSpinner;
    private SaveButton saveButton;
    private Button blueToothButton, cancelButton;

    private Child child;

    private Uri mImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childinfo);

        init();
    }
    private void init(){
        childImageButton = (ChildImageButton) findViewById(R.id.childImageButton);
        nameEditText = (NameEditText) findViewById(R.id.nameEditText);
        ageSpinner = (AgeSpinner) findViewById(R.id.ageSpinner);
        genderRadioGroup = (GenderRadioGroup) findViewById(R.id.genderRadioGroup);
        safeDistanceEditText = (SafeDistanceEditText) findViewById(R.id.safeDistanceEditText);
        cycleSpinner = (CommunicationCycleSpinner) findViewById(R.id.communicationCycleSpinner);
        blueToothButton = (Button) findViewById(R.id.connectingBluetoothButton);
        saveButton = (SaveButton) findViewById(R.id.saveButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag",1);
        if(flag == 1)
            child = new Child();
        else if(flag == 2) {
            child = intent.getParcelableExtra("child");
            childImageButton.setImageBitmap(child.getPhoto());
            nameEditText.setText(child.getName());
            ageSpinner.setChildAge(child.getAge());
            genderRadioGroup.setGender(child.getGender());
            safeDistanceEditText.setText(String.valueOf(child.getDistance()));
            cycleSpinner.setCycle(child.getCycle());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case PICK_FROM_ALBUM:
                mImageUri = data.getData();
                cropPickedImage(mImageUri);
                break;
            case PICK_FROM_CAMERA:
                mImageUri = childImageButton.getPhotoURI();
                cropPickedImage(mImageUri);
                break;
            case CROP_THE_IMAGE:
                Bundle extras = data.getExtras();

                if(extras != null){
                    Bitmap photo = extras.getParcelable("data");
                    childImageButton.setImageBitmap(photo);
                    child.setPhoto(photo);
                }
        }
    }

    private void cropPickedImage(Uri imageUri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");

        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_THE_IMAGE);
    }

    Child getChild(){
        return child;
    }
}