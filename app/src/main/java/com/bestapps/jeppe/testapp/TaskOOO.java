package com.bestapps.jeppe.testapp;

import com.bestapps.jeppe.testapp.util.SystemUiHider;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Random;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TaskOOO extends Activity {
    enum mode {
        color,
        shape;
        public static mode getRandom(){
           return values()[(int)(Math.random()*values().length)];
        }
    }
    int answer = 0;
    ImageView cone,ctwo,cthree;
    GridView task;
    ImageView fback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_task_ooo);
        cone = (ImageView)findViewById(R.id.cone);
        ctwo = (ImageView)findViewById(R.id.ctwo);
        cthree = (ImageView)findViewById(R.id.cthree);
        task = (GridView) findViewById(R.id.task);
        task.setAdapter(new ImageAdapter(this));
        task.setOnItemClickListener(new TaskClickListener(this));
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

    private class ImageAdapter extends BaseAdapter {
        int[] shapeIds;
        int[] colors = new int[]{Color.rgb(0x84,0xc1,0xff),Color.rgb(0x88,0xaa,0x00),Color.rgb(0xff,0xcc,0x00)};
        private Context mContext;
        public ImageAdapter(Context c) {
            Random r = new Random();
            mContext = c;
            mThumbIds = new Drawable[9];
            shapeIds = new int[]{R.drawable.square,R.drawable.round};
            answer = r.nextInt(9);
            switch(mode.shape){
                case shape:
                    for(int i = 0; i < 9; i++){
                        if(i == answer){
                            Drawable d = getResources().getDrawable(R.drawable.square).getConstantState().newDrawable();
                            d.setColorFilter(colors[0], PorterDuff.Mode.MULTIPLY);
                            mThumbIds[i] = d;
                        }else{

                            Drawable d = getResources().getDrawable(R.drawable.round).getConstantState().newDrawable();
                            d.setColorFilter(colors[0], PorterDuff.Mode.MULTIPLY);
                            mThumbIds[i] = d;
                        }

                    }
                    break;
                case color:
                    for(int i = 0; i < 9; i++){
                        if(i == answer){
                            Drawable d = getResources().getDrawable(shapeIds[r.nextInt(2)]).getConstantState().newDrawable();
                            d.setColorFilter(colors[2], PorterDuff.Mode.MULTIPLY);
                            mThumbIds[i] = d;
                        }else{
                            Drawable d = getResources().getDrawable(shapeIds[r.nextInt(2)]).getConstantState().newDrawable();
                            d.setColorFilter(colors[0], PorterDuff.Mode.MULTIPLY);
                            mThumbIds[i] = d;
                        }

                    }
                    break;
            }

        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageDrawable(mThumbIds[position]);
            return imageView;
        }

        private Drawable[] mThumbIds;
    }

    private class TaskClickListener implements AdapterView.OnItemClickListener {
        Context con;
        public TaskClickListener(Context c){
            con = c;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(answer == position) gotoFeedback(parent);
            else ((GridView)parent).setAdapter(new ImageAdapter(con));
        }
    }
}




