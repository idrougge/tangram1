package org.kristallpojken.tangram1;

import android.util.Log;

import java.util.ArrayList;

/**
 * Spelet har sex rutor: Tom, ifylld och halvifylld roterad i 90-graderssteg
 */
public enum Tiles {
    _empty(0,"︎◻︎"),_90deg(1,"◥"),_180deg(2,"◢"),_270deg(3,"◣"),_360deg(4,"◤"),_full(5,"█");
    private final int nr;
    private final String text;
    public static Tiles[] tiles = new Tiles[Tiles.values().length];
    static{
        int i=0;
        for (Tiles tile: Tiles.values())
        {
            tiles[i++]=tile;
        }
    }
    Tiles(int nr, String text)
    {
        this.nr=nr;
        this.text=text;
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
    @Override
    public String toString()
    {
        return "Ruta med nr "+nr+": "+text;
    }
}
