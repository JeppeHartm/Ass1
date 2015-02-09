package com.bestapps.jeppe.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(new ImageAdapter(this));
        gv.setOnItemClickListener(new MenuItemClickListener(this));
    }
    //This approach is "borrowed" from the developer.android.com reference for the API
    //  it can be found at http://developer.android.com/guide/topics/ui/layout/gridview.html
    private class MenuItemClickListener implements AdapterView.OnItemClickListener {
        private Context mContext;
        public MenuItemClickListener(Context c) {
            mContext = c;

        }
        @Override
        public void onItemClick(AdapterView<?> parent,final View view, int position, long id) {
            Intent i = null;
            switch (position) {
                case 3://OOO
                    i = new Intent(mContext,TaskOOO.class);
                    break;
                case 15://Eval
                    i = new Intent(mContext,EvalActivity.class);
                    break;
            }
            final Intent intent = i;
            view.animate()
                    .setDuration(300)
                    .rotationBy(360f)
                    .setInterpolator(AnimationUtils.loadInterpolator(mContext, android.R.interpolator.decelerate_cubic))
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            if(intent != null) startActivity(intent);
                        }
                    });


        }
    }
    private class ImageAdapter extends BaseAdapter {
        private Context mContext;
        public ImageAdapter(Context c) {
            mContext = c;
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

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        private Integer[] mThumbIds = {
                R.drawable.blank, R.drawable.blank,R.drawable.blank,
                R.drawable.ooobutton, R.drawable.blank, R.drawable.blank,
                R.drawable.blank, R.drawable.blank, R.drawable.blank,
                R.drawable.blank, R.drawable.blank, R.drawable.blank,
                R.drawable.blank, R.drawable.blank, R.drawable.blank,
                R.drawable.statbutton
        };
    }
}
