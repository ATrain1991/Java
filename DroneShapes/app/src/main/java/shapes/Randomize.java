package shapes;
import android.widget.ImageView;
import com.example.android.droneshapes.R;

import java.util.Random;
public class Randomize {
    int[] img = new int[12];
    final ImageView iv = (ImageView) findViewByID(R.id.MainRight);


    public void random() {
        String uri = "@drawable/myresources";
        Random rand = new Random();
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


        iv.setImageDrawable(getResources().getDrawable(img[rand.nextInt(6)]));
        /*switch (rand.nextInt(6)+1)
        {
            case 1:
                //Yellow Triangle
                case 2:
                    //blue something
            case 3:
                //nother color
            case 4:
                //asdf
            case 5:
                //asdfasd
                case 6:
                    //asdf
    }
        switch (rand.nextInt(6)+1)
        {
            case 1:
                //Yellow Triangle
            case 2:
                //blue something
            case 3:
                //nother color
            case 4:
                //asdf
            case 5:
                //asdfasd
            case 6:
                //asdf
        }*/
        //set left picture to top switch case
        //set right picture to bottom switch case
    }
}
