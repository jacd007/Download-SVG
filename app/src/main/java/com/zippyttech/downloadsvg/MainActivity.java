package com.zippyttech.downloadsvg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Drawable svgDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv_image);
        // Create a new ImageView
         ImageView imageView = new ImageView(this);
         // Set the background color to white
        imageView.setBackgroundColor(Color.WHITE);
        // Parse the SVG file from the resource
        SVG svg = SVGParser.getSVGFromResource(getResources(), R.raw.android);
        // Get a drawable from the parsed SVG and set it as the drawable for the ImageView
        imageView.setImageDrawable(svg.createPictureDrawable());
        svgDrawable = svg.createPictureDrawable();
        // Set the ImageView as the content view for the Activity setContentView(imageView);

        //iv.setImageDrawable(svgDrawable);

        try {
            HttpImageRequestTask task = new HttpImageRequestTask(this, "https://restcountries.eu/data/afg.svg");
            task.setTaskComplete(resp -> {
                Bitmap bitmap = ImageUtils.base64ToBitmap(resp);
                iv.setImageBitmap(bitmap);
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void getData(View view) {

    }
}