package org.kristallpojken.tangram1;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 2016-04-04.
 * Huvudklass för att hantera tangrampussel
 * Innehåller två instanser av pussel: ett att lägga och ett med lösningen
 */
public class Tangram implements Serializable{
    /*---- Variabler ------------------------------------------------------------*/
    static final boolean DEBUG=true;
    PlayField pf,solvpf;
    PlayFieldView pfv;
    SolutionView solvpfv;
    Button showButton;
    AssetManager assets;
    InputStream stream;
    /*---- Konstruktorer ------------------------------------------------------------*/
    Tangram(Context context, AppCompatActivity parent)
    {
        assets=context.getAssets();
        try {
            stream=assets.open("puzzles.txt");
            Log.i("Tangram.init","Pusselfilen hittades");
        } catch (IOException e) {
            if(assets==null) Log.e("Tangram.init","Kunde inte öppna Assets!");
            Log.e("Tangram.init", "Fel vid öppning av fil");
            e.printStackTrace();
        }
        //loadPuzzle();
        nextPuzzle();
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout mainLayout=(RelativeLayout)parent.findViewById(R.id.main_layout);
    }
    /*-------------------------------------------------------------------------------*/
    boolean nextPuzzle()
    {
        int[] nextPuzzle;
        if( (nextPuzzle=loadPuzzle()) !=null) {
            if(DEBUG)
                pf=new PlayField(nextPuzzle,nextPuzzle);
            else
                pf=new PlayField(nextPuzzle);
            solvpf=new PlayField(nextPuzzle,nextPuzzle);
            return true;
        }
        else return false;
    }
    /*-------------------------------------------------------------------------------*/
    int[] loadPuzzle()
    {
        int puzzle[];

        try {
            int b=0;
            int cols, rows;

            while( (cols=rows=(stream.read()-48)) <0 || cols>9 )
            {
                Log.e("Tangram.loadPuzzle","Ogiltig storlek på pussel: "+b+"="+cols);
                if(stream.available()==0)
                    return null;
            }
            if(stream.available()<cols*rows)
            {
                Log.e("Tangram.loadPuzzle","Filen är för kort ("+stream.available()+" kvar)");
                return null;
            }
            Log.i("Tangram.loadPuzzle","Skapar pussel med mått "+cols+"x"+rows);
            puzzle=new int[cols*rows];
            Log.i("Tangram.loadPuzzle","Pusslet har mått "+cols+"x"+rows+" ("+puzzle.length+")");
            for (int tile = 0; tile < cols*rows; ) {
                b=stream.read();
                Log.i("Tangram.loadPuzzle","b="+b);
                if(b==-1)
                    return null;
                else
                {
                    b-=48;
                    if(b<0 || b>5)
                    {
                        Log.e("Tangram.loadPuzzle","Felaktig siffra: "+b);
                        continue;
                    }
                    Log.i("Tangram.loadPuzzle","Bit nr "+tile+": "+b+" ("+Tiles.withNr(b).text()+")");
                    puzzle[tile++]=b;
                }
            }
        } catch (IOException e) {
            Log.e("Tangram.loadPuzzle", "Något gick fel med inläsning av fil");
            e.printStackTrace();
            return null;
        }
        Log.i("Tangram.loadPuzzle","Pusslet inladdat.");
        return puzzle;
    }
}