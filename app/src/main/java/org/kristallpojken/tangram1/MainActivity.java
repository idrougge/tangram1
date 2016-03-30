package org.kristallpojken.tangram1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*---------------------------------------------*/
        Tiles bla=Tiles._180deg;
        Log.i("Main","Tile 5: "+Tiles.tiles[5]);
        int värden[]={1,2,3,4,5,0,5,4,3,2,1,0};
        PlayField fält=new PlayField(värden);
        //Log.i("Main","fält: \n"+fält);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TileView.context=this;              // Lägg in vår kontext i TileView-klassen
        TileView urk=new TileView();
        this.addContentView(urk, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        TileView hick=new TileView(fält.field[3]);
        Log.i("Main","Hick: "+hick.getWidth()+"x"+hick.getHeight());
        Log.i("Main", "Hick: " + hick.getMeasuredWidth() + "x" + hick.getMeasuredHeight());
        addContentView(hick, lp);
        Log.i("Main", "Hick: " + hick.getWidth() + "x" + hick.getHeight());
        Log.i("Main", "Hick: " + hick.getMeasuredWidth() + "x" + hick.getMeasuredHeight());

        RelativeLayout mainLayout=(RelativeLayout)findViewById(R.id.main_layout);
        PlayFieldView pfv=new PlayFieldView(this,mainLayout);
        Log.i("Main","Storlek: "+mainLayout.getWidth()+"x"+mainLayout.getHeight());
        Log.i("Main","Storlek: "+mainLayout.getMeasuredWidth()+"x"+mainLayout.getMeasuredHeight());
        Log.i("Main", "Hick: " + hick.getWidth() + "x" + hick.getHeight());
        Log.i("Main", "Hick: " + hick.getMeasuredWidth() + "x" + hick.getMeasuredHeight());
        WindowManager wm = (WindowManager)getSystemService(this.WINDOW_SERVICE);
        Display display=wm.getDefaultDisplay();
        Log.i("Main", "display: " +display.getWidth() + "x" + display.getHeight());
        addContentView(pfv,new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        /*
        for (Tiles tile:fält.field)
        {
            ImageView tileView=new ImageView(this);
            tileView.setImageDrawable(getDrawable(R.drawable.ruta180grd));
            ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            this.addContentView(tileView,lp);
        }
        */
    }
}
