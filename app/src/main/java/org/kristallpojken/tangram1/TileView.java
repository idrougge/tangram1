package org.kristallpojken.tangram1;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Klass för att rita en tangram-ruta på skärmen. Bygger på ImageView.
 */
public class TileView extends ImageView {
    public static Context context;
    public Tiles tile;              // Kanske behövs ingen Tiles-referens här?
    public int nr;
    private ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    TileView()
    {
        super(context, null);
        this.setLayoutParams(lp);
        //this.tile=Tiles._270DEG;
        //this.setImageDrawable(context.getDrawable(R.drawable.ruta270grd));
    }
    TileView(Tiles tile)
    {
        this();
        this.tile=tile;
        //this.setImageDrawable(context.getDrawable(R.drawable.rutahel));
        this.setImageDrawable(tile.getDrawable(context));
    }
    TileView(Tiles tile, int nr)    // Konstruktor med nummer
    {
        this();
        this.tile=tile;
        this.nr=nr;
        this.setImageDrawable(tile.getDrawable(context));
        this.setPadding(2,2,2,2);
    }
}
