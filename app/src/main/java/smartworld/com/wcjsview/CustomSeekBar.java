package smartworld.com.wcjsview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ${charles}     on 2017/7/27.
 *
 * @desc ${TODO}
 */

public class CustomSeekBar extends View
{
    private Paint mLeftPain;
    private Path mPath;
    private Path mRightPath;
    private Path mLeftRect;
    private Path mRightRect;

    Region re=new Region();
    Region rightRegion = new Region();

    private float leftNum = 0;
    private float rightNum = 0;
    private float sum = 0;

    public void setLeftNum(float leftNum)
    {
        this.leftNum = leftNum;
    }

    public void setRightNum(float rightNum)
    {
        this.rightNum = rightNum;
    }

    public float getLeftNum()
    {
        return leftNum;
    }

    public float getRightNum()
    {
        return rightNum;
    }

    public CustomSeekBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mLeftPain = new Paint();
        mLeftPain.setAntiAlias(true);
        mLeftPain.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mRightPath = new Path();

        mLeftRect = new Path();
        mRightRect = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        mLeftPain.setColor(Color.parseColor("#F9D201"));
        sum = leftNum + rightNum;



        RectF roundRecf = new RectF(50,10,150,40);
        canvas.drawRoundRect(roundRecf,6,6,mLeftPain);

        mLeftRect.moveTo(90,40);
        mLeftRect.lineTo(100,50);
        mLeftRect.lineTo(110,40);
        canvas.drawPath(mLeftRect,mLeftPain);


        float leftTextSize = mLeftPain.measureText(String.valueOf(leftNum));
        float leftTextX =  100 - leftTextSize*1/2;
        float leftTextY =  25 + leftTextSize / 4;
        mLeftPain.setColor(Color.WHITE);
        mLeftPain.setTextSize(22);

        Log.e(" leftTextX",leftTextX + " ");
        Log.e(" leftTextY",leftTextY + " ");
        canvas.drawText(String.valueOf(leftNum),leftTextX,leftTextY,mLeftPain);


        mLeftPain.setColor(Color.parseColor("#F9D201"));
        mPath.addCircle(100,150,50, Path.Direction.CW);
        //构造一个区域对象，左闭右开的。
        RectF r=new RectF();
        //计算控制点的边界
        mPath.computeBounds(r, true);
        //设置区域路径和剪辑描述的区域
        re.setPath(mPath, new Region((int)r.left,(int)r.top,(int)r.right,(int)r.bottom));
        canvas.drawPath(mPath,mLeftPain);

        mLeftPain.setStrokeWidth(20);

        Bitmap likeBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.like);
        float bitmapWidth = likeBitmap.getWidth();
        float bitmapHeight = likeBitmap.getHeight();

        canvas.drawBitmap(likeBitmap,100 - bitmapWidth /2,150 - bitmapHeight / 2,mLeftPain);


        //红色矩形
        mLeftPain.setColor(Color.RED);
        mRightPath.addCircle(550,150,50, Path.Direction.CW);
        //构造一个区域对象，左闭右开的。
        RectF rightRF=new RectF();
        //计算控制点的边界
        mRightPath.computeBounds(rightRF, true);
        //设置区域路径和剪辑描述的区域
        rightRegion.setPath(mRightPath, new Region((int)rightRF.left,(int)rightRF.top,(int)rightRF.right,(int)rightRF.bottom));
        canvas.drawPath(mRightPath,mLeftPain);


        RectF rightRectF = new RectF(502,10,600,40);
        canvas.drawRoundRect(rightRectF,6,6,mLeftPain);
        mRightRect.moveTo(540,40);
        mRightRect.lineTo(550,50);
        mRightRect.lineTo(560,40);
        canvas.drawPath(mRightRect,mLeftPain);


        float rightTextSize = mLeftPain.measureText(String.valueOf(rightNum));
        float rightTextX =  550 - rightTextSize*1/2;
        float rightTextY =  25 + rightTextSize / 4 ;
        mLeftPain.setColor(Color.WHITE);
        mLeftPain.setTextSize(22);
        canvas.drawText(String.valueOf(rightNum),rightTextX,rightTextY,mLeftPain);



        if (sum == 0){
            mLeftPain.setColor(Color.parseColor("#F9D201"));
            canvas.drawLine(148,150,325,150,mLeftPain);

            mLeftPain.setColor(Color.RED);
            canvas.drawLine(502,150,325,150,mLeftPain);
        }else{

            if (leftNum == 0){

                mLeftPain.setColor(Color.RED);
                canvas.drawLine(502,150,150,150,mLeftPain);
            }else if (rightNum == 0){

                mLeftPain.setColor(Color.parseColor("#F9D201"));
                canvas.drawLine(148,150,500,150,mLeftPain);
            }else {

                float leftPercent = leftNum / sum  * 354;
                float rightPercent = rightNum / sum * 354;

                mLeftPain.setColor(Color.parseColor("#F9D201"));
                canvas.drawLine(148,150,148 + leftPercent,150,mLeftPain);

                mLeftPain.setColor(Color.RED);
                canvas.drawLine(502,150,502 -rightPercent ,150,mLeftPain);

            }


        }

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        //判断所点击点（x,y）是否属于刚才绘制的图形区域。
        if(event.getAction()==MotionEvent.ACTION_DOWN){

            if (re.contains((int)event.getX(), (int)event.getY())){


                if (leftLikeListener != null)
                leftLikeListener.leftClick();

            }

            if (rightRegion.contains((int)event.getX(), (int)event.getY())){


                if (rightLikeListener != null)
                    rightLikeListener.rightClick();
            }

           // Toast.makeText(this.getContext(),String.valueOf(re.contains((int)event.getX(), (int)event.getY())), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private LeftLikeListener leftLikeListener;
    public void setLeftLikeListener(LeftLikeListener leftLikeListener)
    {
        this.leftLikeListener = leftLikeListener;
    }

    public interface LeftLikeListener{

        void leftClick();

    }


    private RightLikeListener rightLikeListener;
    public void setRightLikeListener(RightLikeListener rightLikeListener)
    {
        this.rightLikeListener = rightLikeListener;
    }

    public interface RightLikeListener{

        void rightClick();

    }
}
