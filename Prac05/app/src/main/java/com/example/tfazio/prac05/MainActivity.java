package com.example.tfazio.prac05;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Intent callIntent;
    private Intent mapIntent;
    private Intent cameraIntent;
    Button phoneButton;
    Button locationButton;
    Button cameraButton;
    EditText phoneEditText;
    EditText latEditText;
    EditText longEditText;
    ImageView cameraImageView;

    private static final int REQUEST_THUMBNAIL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //get UI elements

        phoneButton = (Button)findViewById(R.id.phoneButton);
        locationButton = (Button)findViewById(R.id.locationButton);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        latEditText = (EditText) findViewById(R.id.latEditText);
        longEditText = (EditText)findViewById(R.id.longEditText);
        cameraButton = (Button)findViewById(R.id.cameraButton);
        cameraImageView = (ImageView)findViewById(R.id.cameraImageView);
        //click listeners

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //get UI data
                String phoneNo = phoneEditText.getText().toString();
                //build intent
                callIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + phoneNo));
                //start activity
                startActivity(callIntent);
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //get UI data
                String lat = latEditText.getText().toString();
                String lon = longEditText.getText().toString();
                //build intent
                mapIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:"+lat+","+lon));
                //start activity
                startActivity(mapIntent);
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent,REQUEST_THUMBNAIL);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent ret)
    {
        if(resultCode== Activity.RESULT_OK &&
                requestCode == REQUEST_THUMBNAIL)
        {
            Bitmap thumbnail = (Bitmap)ret.getExtras().get("data");
            cameraImageView.setImageBitmap(thumbnail);
        }
    }
}
