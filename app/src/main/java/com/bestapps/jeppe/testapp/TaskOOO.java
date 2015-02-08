package com.bestapps.jeppe.testapp;

import com.bestapps.jeppe.testapp.util.SystemUiHider;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TaskOOO extends Activity {
    ImageView cone,ctwo,cthree;
    ImageView task;
    ImageView fback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_ooo);
        cone = (ImageView)findViewById(R.id.cone);
        ctwo = (ImageView)findViewById(R.id.ctwo);
        cthree = (ImageView)findViewById(R.id.cthree);
        task = (ImageView) findViewById(R.id.task);
        fback = (ImageView)findViewById(R.id.feedback);
        anim();
    }
    private void anim(){

        inflateAndVanish(cthree, new Runnable() {
            @Override
            public void run() {
                cthree.setVisibility(View.GONE);
                inflateAndVanish(ctwo, new Runnable() {
                    @Override
                    public void run() {
                        ctwo.setVisibility(View.GONE);
                        inflateAndVanish(cone, new Runnable() {
                            @Override
                            public void run() {
                                cone.setVisibility(View.GONE);
                                task.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
            }
        });

    }
    private ViewPropertyAnimator inflateAndVanish(final ImageView iv,Runnable endAction) {
        return iv.animate()
                .setStartDelay(200)
                .setDuration(1000)
                .scaleXBy(-0.5f)
                .scaleYBy(-0.5f)
                .setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.decelerate_cubic))
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        iv.setVisibility(View.VISIBLE);
                    }
                })
                .withEndAction(endAction);

    }

    public void gotoFeedback(View view) {
        view.setVisibility(View.GONE);
        fback.setVisibility(View.VISIBLE);
    }

    public void gotoMain(View view) {
        this.finish();
    }
}




