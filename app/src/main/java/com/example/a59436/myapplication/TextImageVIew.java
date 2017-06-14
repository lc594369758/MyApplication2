package com.example.a59436.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

/**
 * Created by 59436 on 2017/3/7.
 */

public class TextImageVIew extends View
{
    private  Context context;
    private int msrc;
    private String mtext;

    public TextImageVIew(Context context) {
        super(context);
    }

    public TextImageVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        int textId = attrs.getAttributeResourceValue(null, "Text",0);
        int srcId = attrs.getAttributeResourceValue(null, "Src", 0);
        mtext = context.getResources().getText(textId).toString();
        msrc = srcId;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        InputStream inputStream = getResources().openRawResource(msrc);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.drawText(mtext,width/2,30,paint);
        paint.set(paint);

    }
}
