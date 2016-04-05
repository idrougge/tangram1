package org.kristallpojken.tangram1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Aktivitet för själva tangrampusslet
 */
public class TangramActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangram);
        /*---------------------------------------------*/
        Log.i("MainActivity", "onCreate()");
        Intent intent = getIntent();
        String action = intent.getAction();
        TextView tv=new TextView(this);
        tv.setText("Action: "+action);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout tangramLayout=(LinearLayout)findViewById(R.id.tangram_layout);
        tangramLayout.addView(tv);
        Tangram tangram=new Tangram(this, this);
    }
}
