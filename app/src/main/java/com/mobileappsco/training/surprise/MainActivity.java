package com.mobileappsco.training.surprise;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView aminfo;
    ImageView amphoto;
    PersonInfo myParcel;
    ShareActionProvider mShareActionProvider;
    Context mContext;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_share, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.share_action);

        // Fetch and store ShareActionProvider
        //mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        mShareActionProvider.setShareIntent(createShareIntent());

        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share_action:
                setShareIntent(createShareIntent());

                Toast.makeText(this, "click on share", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return true;
        }
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, aminfo.getText().toString());
        return shareIntent;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        aminfo = (TextView) findViewById(R.id.txtView1);
        amphoto = (ImageView) findViewById(R.id.amphoto);

        if (getIntent().hasExtra("myParcel")) {
            myParcel = getIntent().getExtras().getParcelable("myParcel");
            aminfo.setText(myParcel.getLastName().toUpperCase()+", "+myParcel.getFirstName()+"\n"+
                            getResources().getStringArray(R.array.sexes)[myParcel.getSex()]+"\n"+
                            myParcel.getEmail()+"\n"+
                            myParcel.getPhone()
                            );
            amphoto.setImageBitmap(myParcel.getPhoto());
            //amphoto.setImageBitmap((Bitmap) getIntent().getExtras().get("photo")); // this works
            amphoto.setImageBitmap((Bitmap) getIntent().getParcelableExtra("photo")); // this also works
        }
    }

    public void btn1Clicked(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }

}
