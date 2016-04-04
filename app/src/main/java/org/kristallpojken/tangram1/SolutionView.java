package org.kristallpojken.tangram1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Visar pusslet men låter en inte manipulera det genom att skriva över onTouch()
 */
public class SolutionView extends PlayFieldView implements View.OnClickListener {

    public SolutionView(Context context) {
        super(context);
    }

    public SolutionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SolutionView(Context context, View parent, PlayField pf) {
        super(context, parent, pf);
    }

    @Override
    public void onClick(View v) {
        Log.i("SolutionView.onClick", "Gömmer lösningen");
        this.setVisibility(GONE);
    }
}
