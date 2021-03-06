package org.kristallpojken.tangram1;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Skapar layout för rutnätet där pusselbitarna läggs ut
 */
public class PlayFieldView extends ViewGroup implements View.OnClickListener{
    /*---- Variabler ------------------------------------------------------------*/
    private int tileWidth;
    private int tileHeight;
    private int layoutWidth;
    private int layoutHeight;
    private int cols=3;
    private int rows=3;
    private Tangram tangram;
    private Context context;
    private PlayField playField;
    private TileView[] tileViews;
    private String className=getClass().getSimpleName();
    final private LayoutParams layoutParams=new LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    /*---- Konstruktorer ------------------------------------------------------------*/
    PlayFieldView(Context context)                      // Meningslös konstruktor
    {
        super(context);
    }
    PlayFieldView(Context context, AttributeSet attrs)  // s o
    {
        super(context,attrs);
    }
    PlayFieldView(Context context,View parent,Tangram t,int colour,PlayField pf)
    {
        super(context);
        tangram=t;
        playField=pf;
        this.context=context;
        TileView.context=context;                  // Lägg in vår kontext i TileView-klassen
        TileView.colour=context.getColor(colour);  // Liksom vår färg
        tileViews=new TileView[playField.field.length];
        cols=playField.cols;
        rows=playField.rows;
        Log.i(className, "Skapar spelplan med storlek " + cols + "x" + rows + " rutor");
        this.setLayoutParams(layoutParams);
        this.setPadding(20,20,20,20);
        //this.setBackgroundColor(context.getColor(R.color.colorPrimaryDark));
        for(int i=0;i<playField.field.length;i++)
        {
            tileViews[i]=new TileView(playField.field[i],i);
            tileViews[i].setOnClickListener(this);
            addView(tileViews[i]);
        }
    }
    /*---- Metoder ------------------------------------------------------------*/
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        Log.i(className + ".onLayout", "onLayout anropades med argument " + changed + "," + left + "," + top + "," + right + "," + bottom);
        layoutWidth=this.getMeasuredWidth()-this.getPaddingLeft()-this.getPaddingRight();
        layoutHeight=this.getMeasuredHeight()-this.getPaddingTop()-this.getPaddingBottom()-top;
        top+=getPaddingTop();
        Log.i(className+".onLayout","PlayFieldView har storlek "+layoutWidth+"x"+layoutHeight);
        if (layoutWidth<layoutHeight)
            tileHeight=tileWidth=layoutWidth/cols;
        else
            tileWidth=tileHeight=layoutHeight/rows;
        int tileLeft=left+getPaddingLeft();
        int childCount=getChildCount();
        for(int i=0; i<childCount; i++) {
            if( (i>0) && (i % cols == 0) )
            {
                tileLeft=this.getPaddingLeft();
                top+=tileWidth;
            }
            TileView child=(TileView)getChildAt(i);
            child.layout(tileLeft, top, tileLeft += tileWidth, top + tileHeight);
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layoutWidth=this.getMeasuredWidth();
        layoutHeight=this.getMeasuredHeight();
        Log.i(className+".onMeasure","MainLayout har storlek "+layoutWidth+"x"+layoutHeight);
        if(layoutWidth<layoutHeight)
            tileHeight=tileWidth=layoutWidth/cols;
        else
            tileWidth=tileHeight=layoutHeight/rows;
        Log.i(className+".onMeasure","Det finns "+cols+" rutor per rad och deras storlek är "+tileWidth);
    }

    @Override
    public void onClick(View v) {
        if(v instanceof TileView)
        {
            TileView tv=(TileView) v;
            Log.i(className+".onClick","Hittade en TileView med nr "+tv.nr+": "+playField.field[tv.nr]);
            playField.field[tv.nr]=playField.field[tv.nr].next();
            tv.setImageDrawable(playField.field[tv.nr].getDrawable(tv.context));
            if(tangram.pf.completed())
            {
                ((TangramActivity)context).onCompletion();
            }
        }
    }
    public void onCompletion() {
        final TangramActivity activity=(TangramActivity)context;
        Log.i(className + ".onClick", "Du vann!");
        Toast.makeText(context,R.string.congratulation,Toast.LENGTH_LONG).show();
        //activity.finish();
        //activity.timer.cancel();
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.nextPuzzle();
            }
        }, 5000);

    }
}
