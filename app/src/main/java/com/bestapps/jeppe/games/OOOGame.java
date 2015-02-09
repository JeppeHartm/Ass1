package com.bestapps.jeppe.games;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Jeppe on 09-02-2015.
 */
public class OOOGame {
    enum geom {round,square; public static geom getRandom(){return values()[new Random().nextInt(2)];}}
    private int[] colors = {Color.rgb(0x84, 0xc1, 0xff),Color.rgb(0x88,0xaa,0x00),Color.rgb(0xff,0xcc,0x00)};
    private Piece[] board;

    public class Piece{

        geom shape;
        int color;
        public Piece(geom s, int c){
            shape = s;
            color = c;
        }

    }
    private Piece getPiece(geom s, int c, boolean b){
        if(b){
            return new OOOGame.Piece(s,c);
        }else{
            geom ss;
            int cc;
            do{
                ss = geom.getRandom();
                cc = colors[new Random().nextInt(colors.length)];
            }while(s == ss || c == cc);
            return new OOOGame.Piece(ss,cc);
        }
    }
}
