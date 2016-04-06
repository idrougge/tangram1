package org.kristallpojken.tangram1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Aktivitet för själva tangrampusslet
 */
public class TangramActivity extends AppCompatActivity {
    PlayFieldView pfv;
    SolutionView solvpfv;
    private Tangram tangram;
    private String className=getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Anropas alltid när aktiviteten byggs upp eller om
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangram);
        /*---------------------------------------------*/
        Log.i("TangramActivity", "onCreate()");
        Intent intent = getIntent();
        String action = intent.getAction();
        TextView tv=new TextView(this);
        tv.setText("Action: " + action);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //LinearLayout tangramLayout=(LinearLayout)findViewById(R.id.tangram_layout);
        RelativeLayout tangramLayout=(RelativeLayout)findViewById(R.id.tangram_layout);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        //lp.addRule(RelativeLayout.ALIGN_END,R.id.score_indicator);
        tangramLayout.addView(tv);
        if(savedInstanceState==null)
            tangram = new Tangram(this, this);
        else
            tangram=(Tangram)savedInstanceState.getSerializable("tangram");
        pfv=new PlayFieldView(this, tangramLayout, tangram, R.color.colorPuzzle, tangram.pf);
        tangramLayout.addView(pfv,lp);
        solvpfv=new SolutionView(this,tangramLayout,tangram,R.color.colorSolution,tangram.solvpf);
        solvpfv.setVisibility(ViewGroup.GONE);

    }
    @Override
    public void onStop()    // Körs när telefonen vrids
    {
        super.onStop();
        Log.i(className,"onStop()");
    }

    @Override
    public void onSaveInstanceState (Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.i(className, "onSaveInstanceState()");
        outState.putString("pf", tangram.pf.toString());
        outState.putSerializable("tangram",tangram);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(className,"onResume()");
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        Log.i(className, "onRestart()");
    }

    @Override
    public void onRestoreInstanceState (Bundle savedInstanceState)
    {
        // Hanterar återställning utifrån det som sparats i onSaveInstanceState
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(className+".onRestoreInstanceState", savedInstanceState==null?"tomt argument":"sparad instans");
        if(savedInstanceState!=null)
        {
            String pfstr=savedInstanceState.getString("pf");
            Log.i(className+".onRestoreInstanceState","Hittade sparad sträng: "+pfstr);
        }
        // Efter detta anropas onResume()
    }
}
