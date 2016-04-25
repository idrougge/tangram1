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
    PlayField pf,solvpf;
    PlayFieldView pfv;
    SolutionView solvpfv;
    Button showButton;
    AssetManager assets;
    InputStream stream;
    ArrayList<int[]> solutions=new ArrayList<>();
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
        /*
        int puzzle[]=  {1,2,3,0,
                        2,5,5,3,
                        1,5,5,4,
                        0,1,4,0};
        int solution[]={0,2,3,0,
                        2,5,5,3,
                        1,5,5,4,
                        0,1,4,0};
        */
        int puzzle[]=  {4,5,3,
                        5,5,5,
                        1,5,4};
        int solution[]={2,5,3,
                        5,5,5,
                        1,5,4};
        solutions.add(solution);
        solutions.add(new int[]{0,2,3,0,
                2,5,5,3,
                1,5,5,4,
                0,1,4,0});
        //pf=new PlayField(solution,puzzle);
        //pf=new PlayField(solution);
        //pf=new PlayField(solutions.remove(1));
        //int[] nextPuzzle;
        //solvpf=new PlayField(solution,solution);
        //loadPuzzle();loadPuzzle();loadPuzzle();
        loadPuzzle();
        nextPuzzle();
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout mainLayout=(RelativeLayout)parent.findViewById(R.id.main_layout);
    }
    boolean nextPuzzle()
    {
        int[] nextPuzzle;
        //if(!(solutions.isEmpty())) {
        if( (nextPuzzle=loadPuzzle()) !=null) {
            //nextPuzzle=solutions.remove(0);
            pf=new PlayField(nextPuzzle);
            solvpf=new PlayField(nextPuzzle,nextPuzzle);
            return true;
        }
        else return false;
    }
    int[] loadPuzzle()
    {
        int puzzle[];

        try {
            int b=0;
            int cols, rows;

            while( (cols=rows=(stream.read()-48)) <0 || cols>9 )
            {
                Log.e("Tangram.loadPuzzle","Ogiltig storlek på pussel: "+b+"="+cols);
            }
            //Log.i("Tangram.loadPuzzle","Pusslets mått: "+cols+"x"+rows);
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