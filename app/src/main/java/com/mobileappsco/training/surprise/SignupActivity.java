package com.mobileappsco.training.surprise;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignupActivity extends AppCompatActivity {

    EditText edtxtFName;
    EditText edtxtLName;
    EditText edtxtEmail;
    EditText edtxtPhone;
    RadioButton radioSex1;
    RadioButton radioSex2;
    RadioGroup radiosSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtxtFName = (EditText) findViewById(R.id.edtxtFName);
        edtxtLName = (EditText) findViewById(R.id.edtxtLName);
        edtxtEmail = (EditText) findViewById(R.id.edtxtEmail);
        edtxtPhone = (EditText) findViewById(R.id.edtxtPhone);
        radioSex1 = (RadioButton) findViewById(R.id.radioSex1);
        radioSex2 = (RadioButton) findViewById(R.id.radioSex2);
        radiosSex = (RadioGroup) findViewById(R.id.radiosSex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Helpers.displayToastAndLog(this, Log.DEBUG, "SignupActivity >> onRestoreInstanceState >> started");

        try {
            edtxtFName.setText(savedInstanceState.getString("edtxtFName"));
            edtxtLName.setText(savedInstanceState.getString("edtxtLName"));
            edtxtEmail.setText(savedInstanceState.getString("edtxtEmail"));
            edtxtPhone.setText(savedInstanceState.getString("edtxtPhone"));
            radiosSex.check(savedInstanceState.getInt("radiosSex"));
        } catch (Exception e) {
            Helpers.displayToastAndLog(this, Log.ERROR, "SignupActivity >> onRestoreInstanceState >> "+e.getMessage());
        }

        Helpers.displayToastAndLog(this, Log.DEBUG, "SignupActivity >> onRestoreInstanceState >> finished");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Helpers.displayToastAndLog(this, Log.DEBUG, "SignupActivity >> onSaveInstanceState >> started");
        try {
            outState.putString("edtxtFName", edtxtFName.getText().toString());
            outState.putString("edtxtLName", edtxtLName.getText().toString());
            outState.putString("edtxtEmail", edtxtEmail.getText().toString());
            outState.putString("edtxtPhone", edtxtPhone.getText().toString());
            outState.putInt("radiosSex", radiosSex.getCheckedRadioButtonId());
            outState.putBoolean("radioSex1", radioSex1.isChecked());
            outState.putBoolean("radioSex2", radioSex2.isChecked());
        } catch (Exception e) {
            Helpers.displayToastAndLog(this, Log.ERROR, "SignupActivity >> onSaveInstanceState >> "+e.getMessage());
        }

        Helpers.displayToastAndLog(this, Log.DEBUG, "SignupActivity >> onSaveInstanceState >> finished");
    }

    public void btnSubmit1Clicked(View view) {
        Helpers.displayToastAndLog(this, Log.DEBUG, "SignupActivity >> btnSubmit1Clicked >> started");
        Intent i = new Intent(this, TakePhotoActivity.class);
        PersonInfo myParcel = new PersonInfo();
            myParcel.setFirstName(edtxtFName.getText().toString());
            myParcel.setLastName(edtxtLName.getText().toString());
            myParcel.setEmail(edtxtEmail.getText().toString());
            myParcel.setPhone(edtxtPhone.getText().toString());
            int sex =0 ;
            if (radioSex2.isChecked())
                sex = 1;
            myParcel.setSex(sex);
        i.putExtra("myParcel", myParcel);
        //Parcelable parcel = new Parcelable();
        startActivity(i);
        Helpers.displayToastAndLog(this, Log.DEBUG, "SignupActivity >> btnSubmit1Clicked >> finished");
    }
}
