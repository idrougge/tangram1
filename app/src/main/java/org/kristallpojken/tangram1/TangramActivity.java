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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangram);
        /*---------------------------------------------*/
        Log.i("MainActivity", "onCreate()");
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
        Tangram tangram=new Tangram(this, this);
        pfv=new PlayFieldView(this, tangramLayout, tangram, R.color.colorPuzzle, tangram.pf);
        tangramLayout.addView(pfv,lp);
        solvpfv=new SolutionView(this,tangramLayout,tangram,R.color.colorSolution,tangram.solvpf);
        solvpfv.setVisibility(ViewGroup.GONE);

    }
}
