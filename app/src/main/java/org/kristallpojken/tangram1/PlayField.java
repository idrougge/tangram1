package org.kristallpojken.tangram1;

import android.util.Log;

import java.util.Random;

/**
 * Spelfältet är ett rutnät om åtm. 3 x 3 rutor
 */
public class PlayField {
    static final private int MINROWS=3;
    static final private int MINCOLS=3;
    static final private int MINTILES=MINROWS*MINCOLS;
    public int rows=MINROWS;
    public int cols=MINROWS;
    static private Random random=new Random();
    Tiles field[];
    Tiles solution[];

    PlayField(int[] tiles)          // Initierar med slumpade pusselbitar i det synliga pusslet
    {
        if(tiles.length<MINTILES)
        {
            Log.e("PlayField","Fel vid init: Antalet rutor får inte vara mindre än "+MINTILES+"!");
            return;
        }
        if(tiles.length % Math.sqrt(tiles.length) != 0)
        {
            Log.e("PlayField","Fel vid init: Antalet rutor måste bilda en kvadrat");
            Log.e("PlayField","√"+tiles.length+"="+tiles.length%Math.sqrt(tiles.length));
            return;
        }
        Log.i("PlayField", "Skapar spelfält med " + tiles.length + " rutor.");
        field=new Tiles[tiles.length];
        solution=new Tiles[tiles.length];
        for (int i=0;i<tiles.length;i++)
        {
            solution[i]=Tiles.withNr(tiles[i]);
            field[i]=Tiles.withNr(random.nextInt(Tiles.values().length));
        }
        cols=rows=(int)Math.sqrt(tiles.length);
    }
    PlayField(int[] solution, int[] puzzle)         // Initierar med ett fördefinierat pussel
    {
        this(solution);
        if(solution.length!=puzzle.length)
            Log.e("PlayField","Båda matriserna måste ha samma längd!");
        else
        {
            for (int i = 0; i < puzzle.length; i++)
            {
                field[i]=Tiles.withNr(puzzle[i]);
            }
        }
    }

    public boolean completed()          // Kollar om pusslet har lösts
    {
        for (int i = 0; i < field.length; i++) {
            if(field[i]!=solution[i])
                return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof PlayField))                     // Jämförelse mot annan typ av objekt
            return false;
        if(field.length!=((PlayField) obj).field.length)    // Objekten är olika stora
            return false;
        PlayField that=(PlayField) obj;
        for (int i = 0; i < field.length-1; i++)
            if(field[i]!=that.field[i])
                return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < field.length; i++)
        {
            if (i % cols==0)
                str.append("\t");
            str.append(field[i].text());
        }
        return str.toString();
    }
}
