package com.mobileappsco.training.surprise;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TakePhotoActivity extends AppCompatActivity {

    PersonInfo myParcel;
    ImageView imgView1;
    TextView txtViewFirstName;
    TextView txtViewLastName;
    TextView txtViewEmail;
    TextView txtViewPhone;
    TextView txtViewSex;
    Bitmap btmPhoto;
    static final int TAKE_PHOTO = 567;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        imgView1 = (ImageView) findViewById(R.id.imageView1);
        txtViewFirstName = (TextView) findViewById(R.id.txtViewFirstName);
        txtViewLastName = (TextView) findViewById(R.id.txtViewLastName);
        txtViewEmail = (TextView) findViewById(R.id.txtViewEmail);
        txtViewPhone = (TextView) findViewById(R.id.txtViewPhone);
        txtViewSex = (TextView) findViewById(R.id.txtViewSex);

        if (getIntent().hasExtra("myParcel")) {
            myParcel = getIntent().getExtras().getParcelable("myParcel");
            Helpers.displayToastAndLog(this, Log.DEBUG, "TakePhotoActivity >> hasExtra('myParcel') >> started");
            txtViewFirstName.setText(myParcel.getFirstName());
            txtViewLastName.setText(myParcel.getLastName());
            txtViewEmail.setText(myParcel.getEmail());
            txtViewPhone.setText(myParcel.getPhone());
            txtViewSex.setText(getResources().getStringArray(R.array.sexes)[myParcel.getSex()]);
            Helpers.displayToastAndLog(this, Log.DEBUG, "TakePhotoActivity >> hasExtra('myParcel') >> finished");
        }

    }

    public void butnSubmit2Clicked(View view) {
        // create intent with ACTION_IMAGE_CAPTURE action
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // start camera activity
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && requestCode == TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                Helpers.displayToastAndLog(this, Log.DEBUG, "TakePhotoActivity >> onActivityResult >>  Photo taken");
                try {
                    Bitmap imgreceived = (Bitmap) data.getExtras().get("data");
                    imgView1.setImageBitmap(imgreceived);
                    myParcel.setPhoto(imgreceived);
                    btmPhoto = imgreceived;
                } catch (Exception e) {
                    Helpers.displayToastAndLog(this, Log.ERROR, "TakePhotoActivity >> onActivityResult >> Error setting image to ImageView "+e.getMessage());
                }

            } else {
                Helpers.displayToastAndLog(this, Log.ERROR, "TakePhotoActivity >> onActivityResult >> PHOTO NOT TAKEN");
            }
        }
    }

    public void butnSubmit3Clicked(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("myParcel", myParcel);
        if (btmPhoto != null)
            i.putExtra("photo", btmPhoto);
        startActivity(i);
    }
}
