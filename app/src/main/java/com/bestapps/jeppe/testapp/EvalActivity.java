package com.bestapps.jeppe.testapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class EvalActivity extends Activity {
    ImageView stats1,stats2,stats3,indicator;
    static int active = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eval);
        stats1 = (ImageView)findViewById(R.id.stats1);
        stats2 = (ImageView)findViewById(R.id.stats2);
        stats3 = (ImageView)findViewById(R.id.stats3);
        indicator = (ImageView)findViewById(R.id.indicator);
        OnSwipeTouchListener ostl = new OnSwipeTouchListener(this) {
            public void setAllGone(){
                if(stats1.getVisibility()!=View.GONE)stats1.setVisibility(View.GONE);
                if(stats2.getVisibility()!=View.GONE)stats2.setVisibility(View.GONE);
                if(stats3.getVisibility()!=View.GONE)stats3.setVisibility(View.GONE);
            }
            public void changeToScr(int scr){
                setAllGone();
                switch(scr){
                    case 1:
                        stats1.setVisibility(View.VISIBLE);
                        indicator.setImageResource(R.drawable.so2);
                        active = 1;
                        break;
                    case 2:
                        stats2.setVisibility(View.VISIBLE);
                        indicator.setImageResource(R.drawable.so3);
                        active = 2;
                        break;
                    case 3:
                        stats3.setVisibility(View.VISIBLE);
                        indicator.setImageResource(R.drawable.so1);
                        active = 3;
                        break;

                }
            }

            @Override
            public void onSwipeLeft() {
                switch(active){
                    case 1:
                        changeToScr(3);
                        break;
                    case 2:
                        changeToScr(1);
                        break;
                    case 3:
                        break;
                }

            }
            @Override
            public void onSwipeRight() {
                switch(active){
                    case 1:
                        changeToScr(2);
                        break;
                    case 2:
                        break;
                    case 3:
                        changeToScr(1);
                        break;
                }
            }
        };
        stats1.setOnTouchListener(ostl);
        stats2.setOnTouchListener(ostl);
        stats3.setOnTouchListener(ostl);
        //changeToScr(active.stats3);
    }

    //borrowed an easy to use swipe listener from http://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
    //<3 Thank you Mirek Rusin and Prince!
    private class OnSwipeTouchListener implements View.OnTouchListener {

        public final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                        }
                        result = true;
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                    }
                    result = true;

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }
    }
}
