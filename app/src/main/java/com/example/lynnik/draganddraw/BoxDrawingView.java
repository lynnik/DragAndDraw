package com.example.lynnik.draganddraw;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View {

  public static final String TAG = "BoxDrawingView";

  private Box mCurrentBox;
  private List<Box> mBoxen = new ArrayList<>();

  public BoxDrawingView(Context context) {
    super(context);
  }

  public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    PointF current = new PointF(event.getX(), event.getY());
    String action = "";

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        action = "ACTION_DOWN";
        mCurrentBox = new Box(current);
        mBoxen.add(mCurrentBox);
        break;
      case MotionEvent.ACTION_MOVE:
        action = "ACTION_MOVE";
        if (mCurrentBox != null) {
          mCurrentBox.setCurrent(current);
          invalidate();
        }
        break;
      case MotionEvent.ACTION_UP:
        action = "ACTION_UP";
        mCurrentBox = null;
        break;
      case MotionEvent.ACTION_CANCEL:
        action = "ACTION_CANCEL";
        mCurrentBox = null;
        break;
    }

    Log.i(TAG, action + " at x=" + current.x + ", y=" + current.y);

    return true;
  }
}