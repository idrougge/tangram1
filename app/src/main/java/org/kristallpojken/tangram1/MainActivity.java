package org.kristallpojken.tangram1;

import android.content.Intent;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button showButton;
    Tangram tangram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*---------------------------------------------*/
        Log.i("MainActivity","onCreate()");
        Intent intent=new Intent(this,TangramActivity.class);
        intent.setAction("RUN");
        //onActivityResult();
        startActivity(intent);
        showButton=(Button)findViewById(R.id.showButton_old);
    }

    public void showSolution(View v) {
        //tangram.showSolution(v);
    }

    public void play(View v) {
        Intent intent=new Intent(this,TangramActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }
}
