package org.kristallpojken.tangram1;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Nedräknare till tangrampussel
 */
public class GameTimer extends CountDownTimer {
    private Context context;
    private TextView window;
    public long time;

    GameTimer(Context c, View v, long duration, long interval)
    {
        super(duration*1000,interval*1000);
        context=c;
        //window=(TextView)v.findViewById(R.id.score_text);
        window=(TextView) v;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        time=millisUntilFinished;
        window.setText(""+millisUntilFinished/1000);
    }

    @Override
    public void onFinish() {
        onTick(0L);
        Toast.makeText(context, R.string.time_is_out, Toast.LENGTH_LONG).show();
        ((TangramActivity)context).finish();
    }
}
