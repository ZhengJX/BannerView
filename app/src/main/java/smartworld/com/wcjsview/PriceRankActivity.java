package smartworld.com.wcjsview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${charles}     on 2017/11/22.
 *
 * @desc ${TODO}
 */

public class PriceRankActivity extends AppCompatActivity
{
    private LinearLayout ll_addview;

    int mPosX,mPosY,mCurPosX,mCurPosY;
    public static final int PERVIOUS = 1;
    public static final int MIDDLEN = 2;
    public static final int NEXT = 3;

    private int mCurrentPosiotn = PERVIOUS;


    int margin;
    int width;
    private int first,second;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        ll_addview = (LinearLayout) findViewById(R.id.ll_addview);
        margin = DensityUtils.dp2px(PriceRankActivity.this,50);
        width = ScreenUtils.getScreenWidth(PriceRankActivity.this);

        first = 0;
        second = (width - margin);




        ll_addview.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {

                int margin = DensityUtils.dp2px(PriceRankActivity.this,50);
                int width = ScreenUtils.getScreenWidth(PriceRankActivity.this);

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosX = (int) motionEvent.getX();
                        mPosY = (int) motionEvent.getY();

                        Log.e("Main"," mPosX " + mPosX + "  mPosY " + mPosY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = (int) motionEvent.getX();
                        mCurPosY = (int) motionEvent.getY();

                        Log.e("Main"," mCurPosX " + mCurPosX + "  mCurPosY " + mCurPosY);
                        break;
                    case MotionEvent.ACTION_UP:
//                        if (mCurPosY - mPosY > 0
//                                && (Math.abs(mCurPosY - mPosY) > 25)) {
//                            //向下滑動
//
//                        } else if (mCurPosY - mPosY < 0
//                                && (Math.abs(mCurPosY - mPosY) > 25)) {
//                            //向上滑动
//
//                        }

                        if (mCurPosX - mPosX < 0 &&  (Math.abs(mCurPosX - mPosX) > 25)){

                            //Toast.makeText(PriceRankActivity.this,"向左滑动",Toast.LENGTH_SHORT).show();


                            if (isAnimalPaly){
                                switch (mCurrentPosiotn){

                                    case PERVIOUS:

                                        initAnimation();

                                        mCurrentPosiotn = MIDDLEN;
                                        break;

                                    case MIDDLEN:
                                        first = (width - margin);
                                        second = 2*(width - margin);

                                        initAnimation();
                                        mCurrentPosiotn = NEXT;


                                        break;

                                    case NEXT:

                                        first = 2*(width - margin);
                                        second =  2*(width - margin);

                                        initAnimation();

                                        //ll_addview.scrollTo(2*(width-margin),0);
                                        break;
                                }
                            }


                        }

                        if (mCurPosX - mPosX > 0 &&  (Math.abs(mCurPosX - mPosX) > 25)){

                           // Toast.makeText(PriceRankActivity.this,"向右滑动",Toast.LENGTH_SHORT).show();
                            if (isAnimalPaly){
                                switch (mCurrentPosiotn){

                                    case PERVIOUS:
//                                    first = 0;
//                                    second = (width - margin);


                                        break;

                                    case MIDDLEN:
                                        first = (width - margin);
                                        second =  0;

                                        initAnimation();
                                        mCurrentPosiotn = PERVIOUS;
                                        first = 0;
                                        second = (width - margin);
                                        break;

                                    case NEXT:
                                        first = 2*(width - margin);
                                        second =  (width - margin);
                                        initAnimation();

                                        mCurrentPosiotn = MIDDLEN;

                                        break;
                                }
                            }

                        }


                        break;
                }
                return true;
            }
        });
        initData();

        addItemView();
    }

    private boolean isAnimalPaly = true;
    private void initAnimation(){
        ValueAnimator valueAnimator;valueAnimator = ValueAnimator.ofFloat(first,second);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                float mAnimatorValue = (float) valueAnimator.getAnimatedValue();

                ll_addview.scrollTo((int)mAnimatorValue,0);

            }
        });
        valueAnimator.setDuration(500);

        valueAnimator.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animator)
            {
                isAnimalPaly = false;
            }

            @Override
            public void onAnimationEnd(Animator animator)
            {
                Log.e(" animator ","动画结束");
                isAnimalPaly = true;
            }

            @Override
            public void onAnimationCancel(Animator animator)
            {

            }

            @Override
            public void onAnimationRepeat(Animator animator)
            {

            }
        });

        valueAnimator.start();
    }


    String[] str = {"本田","丰田","三菱"};
    String[] str2 = {"1","2","3"};
    private void addItemView(){
        int width = ScreenUtils.getScreenWidth(this);


            for (int z = 0; z < listList.size(); z++ ){

                View view = View.inflate(this,R.layout.item_price_rank,null);
                LinearLayout ll_root = (LinearLayout) view.findViewById(R.id.ll_root);

                List<PriceBean> priceBeanList = listList.get(z);
                for (int i=0; i < priceBeanList.size(); i++){

                    View childView =  View.inflate(this,R.layout.item_child,null);
                    TextView tv_title = (TextView) childView.findViewById(R.id.tv_title);
                    TextView tv_num = (TextView) childView.findViewById(R.id.tv_num);


                    tv_title.setText(priceBeanList.get(i).getTitle());
                    tv_num.setText(priceBeanList.get(i).getNum());

                    ll_root.addView(childView);
                }

                if (z == 2){

                    ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams((width), ViewGroup.LayoutParams.WRAP_CONTENT);
                    ll_addview.addView(view,lp);
                }else {
                    int margin = DensityUtils.dp2px(this,50);
                    ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams((width - margin), ViewGroup.LayoutParams.WRAP_CONTENT);
                    ll_addview.addView(view,lp);
                }

            }

    }

    private List<List<PriceBean>> listList = new ArrayList<>();
    private void initData(){
        List<PriceBean> list1 = new ArrayList<>();
        list1.add(new PriceBean("1","本田"));
        list1.add(new PriceBean("2","本田"));
        list1.add(new PriceBean("3","本田"));

        List<PriceBean> list2 = new ArrayList<>();
        list2.add(new PriceBean("1","宝马"));
        list2.add(new PriceBean("2","宝马"));
        list2.add(new PriceBean("3","宝马"));

        List<PriceBean> list3 = new ArrayList<>();
        list3.add(new PriceBean("1","奔驰"));
        list3.add(new PriceBean("2","奔驰"));
        list3.add(new PriceBean("3","奔驰"));

        listList.add(list1);
        listList.add(list2);
        listList.add(list3);
    }

    class PriceBean{

        String num;
        String title;

        public PriceBean(String num, String title)
        {
            this.num = num;
            this.title = title;
        }

        public String getNum()
        {
            return num;
        }

        public void setNum(String num)
        {
            this.num = num;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }
    }
}
