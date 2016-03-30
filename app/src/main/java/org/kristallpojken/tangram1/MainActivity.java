package org.kristallpojken.tangram1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        Log.i("Main","fält: \n"+fält);
    }
}
