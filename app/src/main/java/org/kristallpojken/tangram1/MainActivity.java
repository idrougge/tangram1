package org.kristallpojken.tangram1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    PlayField pf,solvpf;
    PlayFieldView pfv;
    SolutionView solvpfv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*---------------------------------------------*/
        int puzzle[]={1,2,3,4,
                      5,0,5,4,
                      3,2,2,1,
                      5,2,3,4};
        int solution[]={0,2,3,0,
                        2,5,5,3,
                        1,5,5,4,
                        0,1,4,0};
        pf=new PlayField(puzzle);
        solvpf=new PlayField(solution);

        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TileView.context=this;              // Lägg in vår kontext i TileView-klassen
        //LinearLayout mainLayout=(LinearLayout)findViewById(R.id.main_layout);
        RelativeLayout mainLayout=(RelativeLayout)findViewById(R.id.main_layout);
        pfv=new PlayFieldView(this, mainLayout, pf);
        addContentView(pfv,lp);
        // Kanske ska lösningen läggas i ett Fragment?
        solvpfv=new SolutionView(this,mainLayout,solvpf);
        addContentView(solvpfv, lp);
        //pfv.setVisibility(ViewGroup.GONE);
    }

    public void showSolution(View v)
    {
        Log.i("showSolution","Visar lösning");
        solvpfv.setVisibility(ViewGroup.VISIBLE);
    }
}
