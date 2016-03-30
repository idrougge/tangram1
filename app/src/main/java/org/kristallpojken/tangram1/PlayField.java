package org.kristallpojken.tangram1;

import android.util.Log;

/**
 * Spelfältet är ett rutnät om åtm. 3 x 3 rutor
 */
public class PlayField {
    static final private int MINTILES=9;
    TileTypes field[];
    PlayField(TileTypes[] tiles)
    {
        Log.i("PlayField","Skapar spelfält med "+tiles.length+" rutor.");
        field=tiles.clone();
    }
    PlayField(int[] tiles)
    {
        if(tiles.length<MINTILES)
        {
            Log.e("PlayField","Fel vid init: Antalet rutor får inte vara mindre än "+MINTILES+"!");
            return;
        }

    }
}
