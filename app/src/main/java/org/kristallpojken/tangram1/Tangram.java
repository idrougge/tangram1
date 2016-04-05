package org.kristallpojken.tangram1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 2016-04-04.
 * Huvudklass för att hantera tangrampussel
 * Innehåller två instanser av pussel: ett att lägga och ett med lösningen
 */
public class Tangram {
    /*---- Variabler ------------------------------------------------------------*/
    PlayField pf,solvpf;
    PlayFieldView pfv;
    SolutionView solvpfv;
    Button showButton;
    /*---- Konstruktorer ------------------------------------------------------------*/
    Tangram(Context context, AppCompatActivity parent)
    {
        /*
        int puzzle[]={1,2,3,4,
                5,0,5,4,
                3,2,2,1,
                5,2,3,4};
        */
        int puzzle[]=  {1,2,3,0,
                        2,5,5,3,
                        1,5,5,4,
                        0,1,4,0};
        int solution[]={0,2,3,0,
                        2,5,5,3,
                        1,5,5,4,
                        0,1,4,0};
        pf=new PlayField(solution,puzzle);
        solvpf=new PlayField(solution,solution);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout mainLayout=(RelativeLayout)parent.findViewById(R.id.main_layout);
        //pfv=new PlayFieldView(context, mainLayout, this, R.color.colorPuzzle, pf);
        //parent.addContentView(pfv, lp);
        LinearLayout tangramLayout=(LinearLayout)parent.findViewById(R.id.tangram_layout);
        //tangramLayout.addView(pfv);
        // Kanske ska lösningen läggas i ett Fragment?
        //solvpfv=new SolutionView(context,mainLayout,this,R.color.colorSolution,solvpf);
        //solvpfv.setVisibility(ViewGroup.GONE);
        //parent.addContentView(solvpfv, lp);
        showButton=(Button)parent.findViewById(R.id.showButton);
    }
    public void showSolution(View v)
    {
        switch(pfv.getVisibility())
        {
            case ViewGroup.VISIBLE:
                Log.i("showSolution","Visar lösning");
                pfv.setVisibility(ViewGroup.INVISIBLE);
                solvpfv.setVisibility(ViewGroup.VISIBLE);
                showButton.setText(R.string.hide_solution);
                break;
            case ViewGroup.INVISIBLE:
                Log.i("showSolution","Gömmer lösning");
                solvpfv.setVisibility(ViewGroup.GONE);
                pfv.setVisibility(ViewGroup.VISIBLE);
                showButton.setText(R.string.show_solution);
                break;
        }
    }
}
