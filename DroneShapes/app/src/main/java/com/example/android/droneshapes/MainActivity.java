package com.example.android.droneshapes;

import android.os.Bundle;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); // Inflate view hierarchy.
        iv = (ImageView) findViewByID(R.id.MainRight);
        img[0]=R.drawable.YellowTriangle;
        img[1]=R.drawable.RedCircle;
        img[2]=R.drawable.YellowTriangle;
        img[3]=R.drawable.RedCircle;
        img[4]=R.drawable.YellowTriangle;
        img[5]=R.drawable.RedCircle;
        img[6]=R.drawable.YellowTriangle;
        img[7]=R.drawable.RedCircle;
        img[8]=R.drawable.YellowTriangle;
        img[9]=R.drawable.RedCircle;
        img[10]=R.drawable.YellowTriangle;
        img[11]=R.drawable.RedCircle;
    }
    int[] img = new int[12];
    String uri = "@drawable/myresources";
    final ImageView iv = new ImageView(this);
    Random rand = new Random();

public void Randomize()
{
    iv.setImageDrawable(getResources().getDrawable(img[rand.nextInt(6)]));
}





}
