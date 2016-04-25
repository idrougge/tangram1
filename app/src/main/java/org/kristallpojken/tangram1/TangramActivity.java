package org.kristallpojken.tangram1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

/**
 * Aktivitet för själva tangrampusslet
 */
public class TangramActivity extends AppCompatActivity {
    PlayFieldView pfv;
    SolutionView solvpfv;
    private Tangram tangram;
    private GameTimer timer;
    private String className=getClass().getSimpleName();
    private static RelativeLayout tangramLayout;
////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Anropas alltid när aktiviteten byggs upp eller om
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangram);
        /*---------------------------------------------*/
        Log.i("TangramActivity", "onCreate()");
        Intent intent = getIntent();
        String action = intent.getAction();
        //TextView tv=new TextView(this);
        //tv.setText("Action: " + action);
        TextView scoreView=(TextView)findViewById(R.id.score_indicator);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //final RelativeLayout tangramLayout=(RelativeLayout)findViewById(R.id.tangram_layout);
        tangramLayout=(RelativeLayout)findViewById(R.id.tangram_layout);
        FrameLayout scoreFrame;
        if((scoreFrame=(FrameLayout)findViewById(R.id.score_frame_top))!=null)
            lp.addRule(RelativeLayout.BELOW,scoreFrame.getId());
        //tangramLayout.addView(tv);
        if(savedInstanceState==null) {
            tangram = new Tangram(this, this);
            timer=new GameTimer(this,scoreView,60,1);
        }
        else {
            tangram = (Tangram) savedInstanceState.getSerializable("tangram");
            //tangram.nextPuzzle();
            timer=new GameTimer(this,scoreView,savedInstanceState.getLong("time")/1000,1);
        }
        pfv=new PlayFieldView(this, tangramLayout, tangram, R.color.colorPuzzle, tangram.pf);
        tangramLayout.addView(pfv,lp);
        solvpfv=new SolutionView(this,tangramLayout,tangram,R.color.colorSolution,tangram.solvpf);
        solvpfv.setVisibility(ViewGroup.GONE);
        tangramLayout.addView(solvpfv, lp);
        timer.start();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    public void nextPuzzle()
    {
        if(tangram.nextPuzzle())
        {
            RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            tangramLayout=(RelativeLayout)findViewById(R.id.tangram_layout);
            FrameLayout scoreFrame;
            if((scoreFrame=(FrameLayout)findViewById(R.id.score_frame_top))!=null)
                lp.addRule(RelativeLayout.BELOW,scoreFrame.getId());
            tangramLayout.removeView(solvpfv);
            tangramLayout.removeView(pfv);
            pfv=new PlayFieldView(this, tangramLayout, tangram, R.color.colorPuzzle, tangram.pf);
            tangramLayout.addView(pfv,lp);
            solvpfv=new SolutionView(this,tangramLayout,tangram,R.color.colorSolution,tangram.solvpf);
            solvpfv.setVisibility(ViewGroup.GONE);
            tangramLayout.addView(solvpfv, lp);
        }
        else
        {
            Toast.makeText(this, R.string.game_completed, Toast.LENGTH_LONG).show();
            finish();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    public void showSolution(View v)
    {
        switch(pfv.getVisibility())
        {
            case ViewGroup.VISIBLE:
                Log.i("showSolution","Visar lösning");
                pfv.setVisibility(ViewGroup.INVISIBLE);
                solvpfv.setVisibility(ViewGroup.VISIBLE);
                ((Button)v).setText(R.string.hide_solution);
                break;
            case ViewGroup.INVISIBLE:
                Log.i("showSolution", "Gömmer lösning");
                solvpfv.setVisibility(ViewGroup.GONE);
                pfv.setVisibility(ViewGroup.VISIBLE);
                ((Button)v).setText(R.string.show_solution);
                break;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onCompletion() {
        timer.cancel();
        Log.i(className + ".onCompletion", "Du vann!");
        Toast.makeText(this, R.string.congratulation, Toast.LENGTH_LONG).show();
        //activity.finish();
        pfv.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextPuzzle();
            }
        }, 5000);

    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Log.i(className, "onBackPressed()");
        timer.cancel();
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
        outState.putSerializable("tangram", tangram);
        outState.putLong("time", timer.time);
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
