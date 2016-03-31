package org.kristallpojken.tangram1;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Skapar layout för rutnätet där pusselbitarna läggs ut
 */
public class PlayFieldView extends ViewGroup{
    int tileWidth;
    int tileHeight;
    int layoutWidth;
    int layoutHeight;
    private int cols=3;
    private int rows=3;
    PlayFieldView(Context context)
    {
        super(context);
    }
    /*
    PlayFieldView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context,attrs,defStyleAttr);
    }
    PlayFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context,attrs,defStyleAttr,defStyleRes);
    }
    */
    PlayFieldView(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }
    PlayFieldView(Context context,View parent,PlayField pf)
    {
        super(context);
        cols=pf.cols;
        rows=pf.rows;
        Log.i("PlayFieldView","Skapar spelplan med storlek "+cols+"x"+rows+" rutor");
        this.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        layoutWidth=getWidth();
        layoutHeight=getHeight();
        int specWidth=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        int specHeight=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        parent.measure(specWidth, specHeight);
        //parent.measure(layoutWidth,layoutHeight);
        Log.i("PlayFieldView","Skapar spelplan med storlek "+layoutWidth+"x"+layoutHeight);
        Log.i("PlayFieldView","MeasureSpec har storlek "+specWidth+"x"+specHeight);
        Log.i("PlayFieldView","MainLayout har storlek "+parent.getMeasuredWidth()+"x"+parent.getMeasuredHeight());
        Log.i("PlayFieldView", "Kör measure()");
        this.measure(0, 0);
        Log.i("PlayFieldView", "Skapar spelplan med storlek " + layoutWidth + "x" + layoutHeight);
        Log.i("PlayFieldView","MeasureSpec har storlek "+specWidth+"x"+specHeight);
        Log.i("PlayFieldView","MainLayout har storlek "+parent.getMeasuredWidth()+"x"+parent.getMeasuredHeight());
        for(int i=0;i<pf.field.length;i++)
        {
            addView(new TileView(pf.field[i]));
        }
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        Log.i("onLayout","onLayout anropades med argument "+changed+","+left+","+top+","+right+","+bottom);
        layoutWidth=this.getMeasuredWidth();
        layoutHeight=this.getMeasuredHeight();
        Log.i("onLayout","PlayFieldView har storlek "+layoutWidth+"x"+layoutHeight);
        int childCount=getChildCount();
        tileWidth=layoutWidth/cols;
        int tileLeft=left+this.getPaddingLeft();
        Log.i("onLayout","Hittade "+childCount+" barn");
        for(int i=0; i<childCount; i++) {
            if( (i>0) && (i % cols == 0) )
            {
                tileLeft=this.getPaddingLeft();
                top+=tileWidth;
            }
            TileView child=(TileView)getChildAt(i);
            Log.i("onLayout", "Bearbetar barn nr " + i + " med stl " + tileWidth + " på pos " + tileLeft+"x"+top);
            child.layout(tileLeft,top,tileLeft+=tileWidth,top+tileWidth);

        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layoutWidth=this.getMeasuredWidth();
        layoutHeight=this.getMeasuredHeight();
        Log.i("onMeasure","MainLayout har storlek "+layoutWidth+"x"+layoutHeight);
        tileWidth=layoutWidth/cols;
        tileHeight=tileWidth;
        Log.i("onMeasure","Det finns "+cols+" rutor per rad och deras storlek är "+tileWidth);
    }
}
