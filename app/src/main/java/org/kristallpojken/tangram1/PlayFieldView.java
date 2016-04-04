package org.kristallpojken.tangram1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

/**
 * Skapar layout för rutnätet där pusselbitarna läggs ut
 */
public class PlayFieldView extends ViewGroup implements View.OnClickListener{
    /*---- Variabler ------------------------------------------------------------*/
    int tileWidth;
    int tileHeight;
    int layoutWidth;
    int layoutHeight;
    private int cols=3;
    private int rows=3;
    private PlayField playField;
    public TileView[] tileViews;
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
    PlayFieldView(Context context,View parent,PlayField pf)
    {
        super(context);
        playField=pf;
        tileViews=new TileView[pf.field.length];
        cols=pf.cols;
        rows=pf.rows;
        Log.i("PlayFieldView","Skapar spelplan med storlek "+cols+"x"+rows+" rutor");
        this.setLayoutParams(layoutParams);
        this.setPadding(20,20,20,20);
        for(int i=0;i<playField.field.length;i++)
        {
            //addView(new TileView(pf.field[i]));
            tileViews[i]=new TileView(playField.field[i],i);
            tileViews[i].setOnClickListener(this);
            addView(tileViews[i]);
        }
    }
    /*---- Metoder ------------------------------------------------------------*/
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        Log.i("onLayout","onLayout anropades med argument "+changed+","+left+","+top+","+right+","+bottom);
        layoutWidth=this.getMeasuredWidth()-this.getPaddingLeft()-this.getPaddingRight();
        layoutHeight=this.getMeasuredHeight()-this.getPaddingTop()-this.getPaddingBottom();
        top+=getPaddingTop();
        Log.i("onLayout","PlayFieldView har storlek "+layoutWidth+"x"+layoutHeight);
        int childCount=getChildCount();
        tileHeight=tileWidth=layoutWidth/cols;
        int tileLeft=left+getPaddingLeft();
        Log.i("onLayout", "Hittade " + childCount + " barn");
        for(int i=0; i<childCount; i++) {
            if( (i>0) && (i % cols == 0) )
            {
                tileLeft=this.getPaddingLeft();
                top+=tileWidth;
            }
            TileView child=(TileView)getChildAt(i);
            /*
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    TileView tile=(TileView)v;
                    Log.i("PlayFieldView", "Mottog ett klick");
                    PlayFieldView pfv=(PlayFieldView)tile.getParent();
                    Log.i("PlayFieldView", "Identifierade modern med "+pfv.getChildCount()+" barn");
                    int tilenr=-1;
                    for(int i=0;i<pfv.getChildCount();i++)
                    {
                        Log.i("PlayFieldView", "Letar efter barnet... "+i);
                        if(pfv.getChildAt(i)==tile)
                        {
                            Log.i("PlayFieldView", "Hittade barnet på nr "+i);
                            tilenr=i;
                            break;
                        }
                        //Log.e("PlayFieldView", "Hittade inte barnet!");
                    }
                    Log.i("PlayFieldView", "Hittade barnet på nr " + tilenr);
                    //tile.setImageDrawable(playField.field[tilenr+1].getDrawable(getContext()));
                    Tiles newTile=playField.field[tilenr];
                    Log.i("PlayFieldView", "Barnet är "+newTile);
                    //newTile=Tiles.withNr(newTile.nr()+1);
                    newTile=newTile.next();
                    Log.i("PlayFieldView", "Barnet blev "+newTile);
                    //tile.setImageDrawable(newTile.getDrawable(getContext()));
                    tile.tile.cycle(); // Det här räcker nog inte.
                                                // Det här pekar bara om Tiles-pekaren i denna TileView
                                                // och ändrar inte värdet i den ursprungliga Tiles-instansen
                                                // som delades av TileView och PlayField
                    //playField.field[tilenr]=newTile;
                    //tile.setImageDrawable(playField.field[tilenr].getDrawable())
                    //tile.setImageDrawable(getContext().getDrawable(R.drawable.rutahel));
                }
            });
            */
            Log.i("onLayout", "Bearbetar barn nr " + i + " med stl " + tileWidth + " på pos " + tileLeft+"x"+top);
            child.layout(tileLeft, top, tileLeft += tileWidth, top + tileHeight);
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

    @Override
    public void onClick(View v) {
        if(v instanceof TileView)
        {
            TileView tv=(TileView) v;
            Log.i("PlayFieldView.onClick","Hittade en TileView med nr "+tv.nr+": "+tv.tile);
            tv.tile.next()
        }
    }
}
