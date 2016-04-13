package com.akshaykhanna.actionbar_pizza;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

/**
 * Created by Akshay Khanna on 13-04-2016.
 */
public class AnimateObjects extends View
{
    Bitmap ball;
    private int noOfObjects;
    public AnimateObjects(Context context, int noOfObjects)
    {
        super(context);
        this.noOfObjects=noOfObjects;
        ball= BitmapFactory.decodeResource(getResources(),R.mipmap.numerals_object_ball);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        drawGivenNoOfObjects(canvas);


    }

    private void drawGivenNoOfObjects(Canvas canvas) {
       /* for(int i=0;i<noOfObjects;i++)
        {
            canvas.dr
        }*/
        int bW=ball.getWidth();
        int bH=ball.getHeight();
        int tW=bW*noOfObjects, tH=bH*noOfObjects;
        int cH=canvas.getHeight(),cW=canvas.getWidth();
        
        canvas.drawBitmap(ball,canvas.getWidth()/2,canvas.getHeight()/2,null);

    }
}
