package org.kristallpojken.tangram1;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 2016-04-04.
 * Huvudklass för att hantera tangrampussel
 * Innehåller två instanser av pussel: ett att lägga och ett med lösningen
 */
public class Tangram {
    /*---- Variabler ------------------------------------------------------------*/
    static PlayField pf,solvpf;
    PlayFieldView pfv;
    SolutionView solvpfv;
    /*---- Konstruktorer ------------------------------------------------------------*/
    Tangram(Context context)
    {
        /*
        int puzzle[]={1,2,3,4,
                5,0,5,4,
                3,2,2,1,
                5,2,3,4};
        */
        int puzzle[]={1,2,3,0,
                2,5,5,3,
                1,5,5,4,
                0,1,4,0};
        int solution[]={0,2,3,0,
                2,5,5,3,
                1,5,5,4,
                0,1,4,0};
        pf=new PlayField(puzzle);
        solvpf=new PlayField(solution);

        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TileView.context=context;              // Lägg in vår kontext i TileView-klassen
        RelativeLayout mainLayout=(RelativeLayout)((MainActivity)context).findViewById(R.id.main_layout);
        pfv=new PlayFieldView(context, mainLayout, pf);
        ((MainActivity)context).addContentView(pfv, lp);
        // Kanske ska lösningen läggas i ett Fragment?
        solvpfv=new SolutionView(context,mainLayout,solvpf);
        ((MainActivity)context).addContentView(solvpfv, lp);
        solvpfv.setVisibility(ViewGroup.GONE);

    }
}
