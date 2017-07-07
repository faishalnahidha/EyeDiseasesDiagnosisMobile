package com.izzan.eyediseasesdiagnosismobile.others;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Aizen on 7 Jul 2017.
 */

public class ScrollAwareFabBehaviour extends FloatingActionButton.Behavior {

    public ScrollAwareFabBehaviour(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild,
                                       View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);

        if (dyConsumed > 10 && child.getVisibility() != View.VISIBLE) {
            /**
             * Scrolling down
             */
            child.show();
            child.setVisibility(View.VISIBLE);
        } else if (dyConsumed < 0 && child.getVisibility() == View.VISIBLE) {
            /**
             * Scrolling up
             */
            child.hide(new FloatingActionButton.OnVisibilityChangedListener(){
                /**
                 * Called when a FloatingActionButton has been hidden
                 *
                 * @param fab the FloatingActionButton that was hidden.
                 */
                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onShown(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
            //child.setVisibility(View.INVISIBLE);
        }
    }
}
