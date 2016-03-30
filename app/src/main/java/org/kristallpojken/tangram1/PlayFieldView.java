package org.kristallpojken.tangram1;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Skapar layout för rutnätet där pusselbitarna läggs ut
 */
public class PlayFieldView extends LinearLayout{
    int tileWidth;
    int tileHeight;
    int layoutWidth;
    int layoutHeight;
    PlayFieldView(Context context,View parent)
    {
        super(context);
        this.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        layoutWidth=getWidth();
        layoutHeight=getHeight();
        int specWidth=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        int specHeight=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        parent.measure(specWidth,specHeight);
        //parent.measure(layoutWidth,layoutHeight);
        Log.i("PlayFieldView","Skapar spelplan med storlek "+layoutWidth+"x"+layoutHeight);
        Log.i("PlayFieldView","MeasureSpec har storlek "+specWidth+"x"+specHeight);
        Log.i("PlayFieldView","MainLayout har storlek "+parent.getMeasuredWidth()+"x"+parent.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layoutWidth=this.getMeasuredWidth();
        layoutHeight=this.getMeasuredHeight();
        Log.i("onMeasure","MainLayout har storlek "+layoutWidth+"x"+layoutHeight);
    }
}
