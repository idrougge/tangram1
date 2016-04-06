package org.kristallpojken.tangram1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.Serializable;

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
    /*---- Konstruktorer ------------------------------------------------------------*/
    Tangram(Context context, AppCompatActivity parent)
    {
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
        pf=new PlayField(solution,puzzle);
        solvpf=new PlayField(solution,solution);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout mainLayout=(RelativeLayout)parent.findViewById(R.id.main_layout);
        //pfv=new PlayFieldView(context, mainLayout, this, R.color.colorPuzzle, pf);
        //parent.addContentView(pfv, lp);
        //LinearLayout tangramLayout=(LinearLayout)parent.findViewById(R.id.tangram_layout);
        //tangramLayout.addView(pfv);
        // Kanske ska lösningen läggas i ett Fragment?
        //solvpfv=new SolutionView(context,mainLayout,this,R.color.colorSolution,solvpf);
        //solvpfv.setVisibility(ViewGroup.GONE);
        //parent.addContentView(solvpfv, lp);
        //showButton=(Button)parent.findViewById(R.id.show_solution_button);
    }
}
