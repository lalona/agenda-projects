package com.example.lalo10.agenda.Helpers;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.lalo10.agenda.List_Adapters.CalActOrgAdapter;

/**
 * Created by lalo10 on 11/8/17.
 */

public class AdjustmentHelper {

    private View viewAlargin;
    private int posViewPendingAdjustment;
    private int adjustmentSize;
    private Direction direction;
    private int posAlargin;
    private boolean alargeTouched;
    private int beginY;
    private int lastDiff;
    private static AdjustmentHelper onlyInstance;

    private AdjustmentHelper() {}

    public static AdjustmentHelper getOnlyInstance() {
        if(onlyInstance == null) {
            onlyInstance = new AdjustmentHelper();
            onlyInstance.setAlargeTouched(false);
        }
        return onlyInstance;
    }

    public void touchStart(int startY,View v,int pos, Direction d) {
        setBeginY(startY);
        setAlargeTouched(true);
        setViewAlargin(v);
        direction = d;
        setPosAlargin(pos);
    }

    public enum Direction {
        DECRESE,INCRESE
    }

    public void noAdjustment() {
        this.posViewPendingAdjustment = -1;
    }

    public void adjustIfNecessary(View v) {
        if(this.posViewPendingAdjustment >= 0) {
            v.getLayoutParams().height += getDiffForNewHeightForNeighbordView(getAdjustmentSize());
            v.requestLayout();
        }
    }

    public void setAdjustment(int pos, int adjustmentSize, Direction direction) {
        this.direction = direction;
        this.posViewPendingAdjustment = pos;
        this.adjustmentSize = pos;
    }

    public int getBeginY() {
        return beginY;
    }

    public void setBeginY(int beginY) {
        this.beginY = beginY;
        lastDiff = -1;
    }

    public int getPosViewPendingAdjustment() {return posViewPendingAdjustment;}

    public void setPosViewPendingAdjustment(int posViewPendingAdjustment) {this.posViewPendingAdjustment = posViewPendingAdjustment;}

    public int getAdjustmentSize() {return adjustmentSize;}

    public void setAdjustmentSize(int adjustmentSize) {this.adjustmentSize = adjustmentSize;}

    public View getViewAlargin() {return viewAlargin;}

    public void setViewAlargin(View viewAlargin) {this.viewAlargin = viewAlargin;}

    public int getPosAlargin() {return posAlargin;}

    public void setPosAlargin(int posAlargin) {this.posAlargin = posAlargin;}

    public boolean isAlargeTouched() {
        return alargeTouched;
    }

    public void setAlargeTouched(boolean alargeTouched) {
        this.alargeTouched = alargeTouched;
    }


    public boolean alargeView(int diff, RecyclerView.LayoutManager layoutManager) {
        if(viewAlargin == null)
            return false;
        View neighbordView;
        int neighPos;
        if(direction == Direction.DECRESE) {
            neighPos = getPosAlargin() - 1;

        } else {
            neighPos = getPosAlargin() + 1;
        }
        neighbordView = layoutManager.findViewByPosition(neighPos);
        viewAlargin.getLayoutParams().height += getDiffForNewHeightForView(diff - getBeginY());
        viewAlargin.requestLayout();
        if(neighbordView != null) {
            neighbordView.getLayoutParams().height += getDiffForNewHeightForNeighbordView(diff - getBeginY());
            neighbordView.requestLayout();
            setPosViewPendingAdjustment(-1);
        }
        else {
            setPosViewPendingAdjustment(neighPos);
            setAdjustmentSize(diff - getBeginY());
        }
        setBeginY(diff);
        return true;
    }

    private int getDiffForNewHeightForView(int diff) {
        return (direction == Direction.INCRESE) ? diff : -diff;
    }

    private int getDiffForNewHeightForNeighbordView(int diff) {
        return (direction == Direction.INCRESE) ? -diff : diff;
    }

}
