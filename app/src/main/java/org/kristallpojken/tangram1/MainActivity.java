package org.kristallpojken.tangram1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        TileView.context=this;              // Lägg in vår kontext i TileView-klassen
        TileView urk=new TileView();
        this.addContentView(urk,new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
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
