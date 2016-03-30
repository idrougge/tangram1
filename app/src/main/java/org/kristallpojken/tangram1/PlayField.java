package org.kristallpojken.tangram1;

import android.util.Log;

/**
 * Spelfältet är ett rutnät om åtm. 3 x 3 rutor
 */
public class PlayField {
    PlayField(TileTypes[] tiles)
    {
        Log.i("PlayField","Skapar spelfält med "+tiles.length+" rutor.");

    }
}
