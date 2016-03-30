package org.kristallpojken.tangram1;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Klass för att rita en tangram-ruta på skärmen. Bygger på ImageView.
 */
public class TileView extends ImageView {
    public static Context context;
    private ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    TileView()
    {
        super(context, null);
        this.setLayoutParams(lp);
        this.setImageDrawable(context.getDrawable(R.drawable.ruta270grd));
    }
    TileView(Tiles tile)
    {
        this();
        //this.setImageDrawable(context.getDrawable(R.drawable.rutahel));
        this.setImageDrawable(tile.getDrawable(context));
    }
}
