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

public class LikeSeekBar extends View
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

    private float radius = 30;
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

    private Context mContext;
    public LikeSeekBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;

        mLeftPain = new Paint();
        mLeftPain.setAntiAlias(true);
        mLeftPain.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mRightPath = new Path();

        mLeftRect = new Path();
        mRightRect = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int defaultSize = 600;

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST )
        {
            setMeasuredDimension(defaultSize,defaultSize);
        } else if (widthMode == MeasureSpec.AT_MOST){

            setMeasuredDimension(defaultSize,height);
        }else if (heightMode == MeasureSpec.AT_MOST){

            setMeasuredDimension(width,defaultSize);
        }else {
            setMeasuredDimension(width,height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int radiusToPX = DensityUtils.dp2px(mContext,radius);
        int rounfHight = DensityUtils.dp2px(mContext,5);

        mLeftPain.setColor(Color.parseColor("#F9D201"));
        sum = leftNum + rightNum;

        RectF roundRecf = new RectF(rounfHight*2,0,2*radiusToPX - rounfHight*2,3 *rounfHight );
        canvas.drawRoundRect(roundRecf,6,6,mLeftPain);

        float triangleWidth = (2*radiusToPX - (rounfHight*2)*2 )/2;
        mLeftRect.moveTo(rounfHight*2+ triangleWidth- 10,3 *rounfHight );
        mLeftRect.lineTo(rounfHight*2+triangleWidth,3 *rounfHight + 10);
        mLeftRect.lineTo(rounfHight*2+ triangleWidth+ 10,3 *rounfHight);
        canvas.drawPath(mLeftRect,mLeftPain);

        mLeftPain.setTextSize(30);
        mLeftPain.setColor(Color.WHITE);
        int leftText = (int) leftNum;
        //橙色数量
        float leftTextSize = mLeftPain.measureText(String.valueOf(leftText));
        float leftTextX =  rounfHight*2+triangleWidth - leftTextSize / 2;
        float leftTextY =  3 *rounfHight /2 + 10 ;

        Log.e(" leftTextX",leftTextX + " ");
        Log.e(" leftTextY",leftTextY + " ");
        canvas.drawText(String.valueOf(leftText),leftTextX,leftTextY,mLeftPain);


        //橙色矩形
        mLeftPain.setColor(Color.parseColor("#F9D201"));
        mPath.addCircle( radiusToPX , radiusToPX + 3 * rounfHight + 10 + 25,radiusToPX, Path.Direction.CW);
        //构造一个区域对象，左闭右开的。
        RectF r=new RectF();
        //计算控制点的边界
        mPath.computeBounds(r, true);
        //设置区域路径和剪辑描述的区域
        re.setPath(mPath, new Region((int)r.left,(int)r.top,(int)r.right,(int)r.bottom));
        canvas.drawPath(mPath,mLeftPain);

        Bitmap likeBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.like);
        float bitmapWidth = likeBitmap.getWidth();
        float bitmapHeight = likeBitmap.getHeight();

        canvas.drawBitmap(likeBitmap,radiusToPX - bitmapWidth /2,radiusToPX + 3 * rounfHight + 10 + 25 - bitmapHeight / 2,mLeftPain);


        //红色矩形
        mLeftPain.setColor(Color.RED);
        mRightPath.addCircle(canvasWidth - radiusToPX, radiusToPX  + 3 * rounfHight + 10 + 25 ,radiusToPX, Path.Direction.CW);
        //构造一个区域对象，左闭右开的。
        RectF rightRF=new RectF();
        //计算控制点的边界
        mRightPath.computeBounds(rightRF, true);
        //设置区域路径和剪辑描述的区域
        rightRegion.setPath(mRightPath, new Region((int)rightRF.left,(int)rightRF.top,(int)rightRF.right,(int)rightRF.bottom));
        canvas.drawPath(mRightPath,mLeftPain);


        RectF rightRectF = new RectF(canvasWidth - 2*radiusToPX +rounfHight*2 ,0,canvasWidth - rounfHight*2,3 *rounfHight);
        canvas.drawRoundRect(rightRectF,6,6,mLeftPain);
        mRightRect.moveTo(canvasWidth - radiusToPX - 10,3 *rounfHight);
        mRightRect.lineTo(canvasWidth - radiusToPX,3 *rounfHight + 10);
        mRightRect.lineTo(canvasWidth - radiusToPX + 10,3 *rounfHight);
        canvas.drawPath(mRightRect,mLeftPain);

        int rightText = (int) rightNum;
        float rightTextSize = mLeftPain.measureText(String.valueOf(rightText));
        float rightTextX =  canvasWidth - radiusToPX - rightTextSize*1/2;
        float rightTextY =  3 *rounfHight /2 + 10 ;
        mLeftPain.setColor(Color.WHITE);
        mLeftPain.setTextSize(30);
        canvas.drawText(String.valueOf(rightText),rightTextX,rightTextY,mLeftPain);


        float cricleY = radiusToPX + 3 * rounfHight + 10 + 25;
        mLeftPain.setStrokeWidth(40);
        if (sum == 0){
            mLeftPain.setColor(Color.parseColor("#F9D201"));
            canvas.drawLine(radiusToPX * 2 - 2,cricleY,canvasWidth -radiusToPX * 2 ,cricleY,mLeftPain);

            mLeftPain.setColor(Color.RED);
            canvas.drawLine(canvasWidth - 2*radiusToPX + 2,cricleY,2*radiusToPX + (canvasWidth - 4*radiusToPX) / 2,cricleY,mLeftPain);
        }else{

            if (leftNum == 0){

                mLeftPain.setColor(Color.RED);
                canvas.drawLine(canvasWidth - 2*radiusToPX + 2,cricleY,2*radiusToPX ,cricleY,mLeftPain);
            }else if (rightNum == 0){

                mLeftPain.setColor(Color.parseColor("#F9D201"));
                canvas.drawLine(radiusToPX * 2 - 2,cricleY,canvasWidth -radiusToPX * 2 ,cricleY,mLeftPain);
            }else {

                float leftPercent = leftNum / sum  * (canvasWidth -radiusToPX * 2 - radiusToPX * 2 + 4);
                float rightPercent = rightNum / sum * (canvasWidth -radiusToPX * 2 - radiusToPX * 2  + 4);

                mLeftPain.setColor(Color.parseColor("#F9D201"));
                canvas.drawLine(radiusToPX * 2 - 2,cricleY,radiusToPX * 2 - 2 + leftPercent ,cricleY,mLeftPain);

                mLeftPain.setColor(Color.RED);
                canvas.drawLine(canvasWidth - 2*radiusToPX + 2 ,cricleY,canvasWidth - 2*radiusToPX+2 - rightPercent ,cricleY,mLeftPain);

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
