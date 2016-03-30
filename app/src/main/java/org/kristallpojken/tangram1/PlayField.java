package org.kristallpojken.tangram1;

import android.util.Log;

/**
 * Spelfältet är ett rutnät om åtm. 3 x 3 rutor
 */
public class PlayField {
    static final private int MINROWS=3;
    static final private int MINCOLS=3;
    static final private int MINTILES=MINROWS*MINCOLS;
    Tiles field[];
    PlayField(Tiles[] tiles)
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
        Log.i("PlayField","Skapar spelfält med "+tiles.length+" rutor.");
        field=new Tiles[tiles.length];
        for (int i=0;i<tiles.length;i++)
        {
            field[i]=Tiles.withNr(tiles[i]);
            Log.i("PlayField","Hämtade ruta "+i+": "+field[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < field.length; i++)
        {
            if (i % MINCOLS==0)
                str.append("\t");
            str.append(field[i].text());
        }
        return str.toString();
    }
}
