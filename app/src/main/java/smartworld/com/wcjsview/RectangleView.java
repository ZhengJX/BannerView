package smartworld.com.wcjsview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ${charles}     on 2017/9/19.
 *
 * @desc ${TODO}
 */

public class RectangleView extends View
{

    private Paint mPaint;
    private Path path;

    private int mWidth;
    private Bitmap closeBitmap;

    private int bitmapWidth;
    private int bitmapHeight;
    public RectangleView(Context context)
    {
        super(context);
    }

    public RectangleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        path = new Path();

        closeBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.close);
    }

    public RectangleView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        path.moveTo(mWidth,0);
        path.lineTo(mWidth,90);
        path.lineTo(mWidth-90,0);
        path.close();

        canvas.drawPath(path,mPaint);

        bitmapWidth = closeBitmap.getWidth();
        bitmapHeight = closeBitmap.getHeight();

        canvas.drawBitmap(closeBitmap,mWidth-40,15,mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.e(" w ",w + "");
        mWidth = w;
    }
}
