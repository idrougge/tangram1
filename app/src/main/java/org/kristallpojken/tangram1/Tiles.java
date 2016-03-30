package org.kristallpojken.tangram1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Spelet har sex rutor: Tom, ifylld och halvifylld roterad i 90-graderssteg
 */
public enum Tiles {
    _empty(0,"︎◻︎",R.drawable.rutatom),_90deg(1,"◥",R.drawable.ruta90grd),
    _180deg(2,"◢",R.drawable.ruta180grd),_270deg(3,"◣",R.drawable.ruta270grd),
    _360deg(4,"◤",R.drawable.ruta360grd),_full(5,"█",R.drawable.rutahel);
    private final int nr;
    private final String text;
    private final int drawable;
    public static Tiles[] tiles = new Tiles[Tiles.values().length];
    static{
        int i=0;
        for (Tiles tile: Tiles.values())
        {
            tiles[i++]=tile;
        }
    }
    Tiles(int nr, String text,int drawable)
    {
        this.nr=nr;
        this.text=text;
        this.drawable=drawable;
    }
    public int nr()
    {
        return nr;
    }
    public String text()
    {
        return text;
    }
    public static Tiles withNr(int nr)
    {
        if(nr<=Tiles.values().length)
            return tiles[nr];
        else
            Log.e("Tiles","Ogiltigt tal vid hämtning av ruta!");
        return null;
    }
    public Drawable getDrawable(Context context)
    {
        return context.getDrawable(drawable);
    }
    @Override
    public String toString()
    {
        return "Ruta med nr "+nr+": "+text;
    }
}
