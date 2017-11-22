package smartworld.com.wcjsview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import smartworld.com.wcjsview.view.SelfDialog;

public class MainActivity extends AppCompatActivity
{

//    private LikeSeekBar customSeekBar;
//
//    private int x,y;

    private ExpandView mExpandView;
    private LinearLayout mLinearLayout;
    private TextView mTextView;
    private ImageView mImageView;

    private TextView tv_move,tv_zhuanjia,tv_cheyou;

    private ViewPager vp_page;
    private List<View> views = new ArrayList<>();
    private CommentAdapter adapter;

    private float rigthX;
    private float leftX;

    private Button btn_show;

    private RatingBar ratingbar = null;

    private LinearLayout ll_addview;

    public static final int PERVIOUS = 1;
    public static final int MIDDLEN = 2;
    public static final int NEXT = 3;

    private int mCurrentPosiotn = MIDDLEN;

    private ValueAnimator valueAnimator,valueAnimator2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_addview = (LinearLayout) findViewById(R.id.ll_addview);

        ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        ratingbar.setOnRatingBarChangeListener(new RatingBarListener());

        adapter = new CommentAdapter();

        btn_show = (Button) findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final SelfDialog selfDialog = new SelfDialog(view.getContext());
                selfDialog.show();

                selfDialog.setCloseListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        selfDialog.dismiss();
                    }
                });
            }
        });


        // Log.e("---",width + "");

//        customSeekBar = (LikeSeekBar) findViewById(R.id.customSeekBar);
//
//        customSeekBar.setLeftLikeListener(new LikeSeekBar.LeftLikeListener()
//        {
//            @Override
//            public void leftClick()
//            {
//                x++;
//
//                customSeekBar.setLeftNum(x);
//                customSeekBar.invalidate();
//
//                Toast.makeText(MainActivity.this,"黄色矩形", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        customSeekBar.setRightLikeListener(new LikeSeekBar.RightLikeListener()
//        {
//            @Override
//            public void rightClick()
//            {
//                y++;
//
//                customSeekBar.setRightNum(y);
//                customSeekBar.invalidate();
//
//                Toast.makeText(MainActivity.this,"红色矩形", Toast.LENGTH_SHORT).show();
//            }
//        });
        //initExpandView();
        vp_page = (ViewPager) findViewById(R.id.vp_page);
        tv_move = (TextView) findViewById(R.id.tv_move);
        tv_zhuanjia = (TextView) findViewById(R.id.tv_zhuanjia);
        tv_cheyou = (TextView) findViewById(R.id.tv_cheyou);

        for (int i = 0; i < 2; i++)
        {
            View view = LayoutInflater.from(this).inflate(R.layout.item_comment, null);
            views.add(view);
        }
        vp_page.setAdapter(adapter);

//        ObjectAnimator animator_img = ObjectAnimator.ofFloat(view, "translationX", 0, 0);
//        animator_img.setDuration(20);
//        animator_img.start();

        tv_zhuanjia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e(" view.getX()zhuanjia ", view.getX() + "");
//                ObjectAnimator animator_img = ObjectAnimator.ofFloat(tv_move, "translationX", tv_move.getX(), tv_zhuanjia.getX());
//                animator_img.setDuration(100);
//                animator_img.start();

                vp_page.setCurrentItem(0);
            }
        });

        tv_cheyou.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e(" view.getX() ", view.getX() + "");
//                ObjectAnimator animator_img = ObjectAnimator.ofFloat(tv_move, "translationX", tv_move.getX(), view.getX()-DensityUtils.dp2px(MainActivity.this,6));
//                animator_img.setDuration(100);
//                animator_img.start();

                vp_page.setCurrentItem(1);
            }
        });


        vp_page.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {

                Log.e(" ---- ", tv_cheyou.getX() + "");

                if (position == 1)
                {
                    ObjectAnimator animator_img = ObjectAnimator.ofFloat(tv_move, "translationX", tv_move.getX(), tv_cheyou.getX() - DensityUtils.dp2px(MainActivity.this, 6));
                    animator_img.setDuration(200);
                    animator_img.start();
                }
                else
                {
                    ObjectAnimator animator_img = ObjectAnimator.ofFloat(tv_move, "translationX", tv_move.getX(), tv_zhuanjia.getX());
                    animator_img.setDuration(200);
                    animator_img.start();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        addItemView();

        int margin = DensityUtils.dp2px(MainActivity.this,50);
        int width = ScreenUtils.getScreenWidth(MainActivity.this);

        valueAnimator = ValueAnimator.ofFloat(0,(width-margin));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                float mAnimatorValue = (float) valueAnimator.getAnimatedValue();

                ll_addview.scrollTo((int)mAnimatorValue,0);

            }
        });
        valueAnimator.setDuration(3000);


        //valueAnimator.start();


        ll_addview.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {

                int margin = DensityUtils.dp2px(MainActivity.this,50);
                int width = ScreenUtils.getScreenWidth(MainActivity.this);

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

                            Toast.makeText(MainActivity.this,"向左滑动",Toast.LENGTH_SHORT).show();




                            switch (mCurrentPosiotn){

                                case PERVIOUS:

                                    ll_addview.scrollTo(0,0);

                                    break;

                                case MIDDLEN:

                                    valueAnimator.start();
                                    mCurrentPosiotn = NEXT;
                                    break;

                                case NEXT:

                                    valueAnimator2.start();
                                    //ll_addview.scrollTo(2*(width-margin),0);
                                    break;
                            }
                        }

                        if (mCurPosX - mPosX > 0 &&  (Math.abs(mCurPosX - mPosX) > 25)){

                            Toast.makeText(MainActivity.this,"向右滑动",Toast.LENGTH_SHORT).show();
                            switch (mCurrentPosiotn){

                                case PERVIOUS:



                                    break;

                                case MIDDLEN:
                                    ll_addview.scrollTo(0,0);

                                    break;

                                case NEXT:

                                    ll_addview.scrollTo(width-margin,0);

                                    mCurrentPosiotn = MIDDLEN;
                                    break;
                            }
                        }


                        break;
                }
                return true;
            }
        });


    }

    int mPosX,mPosY,mCurPosX,mCurPosY;
    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener{

        public void onRatingChanged(RatingBar ratingBar, float rating,
                                    boolean fromUser) {
            System.out.println("等级：" + rating);
            System.out.println("星星：" + ratingBar.getNumStars());
        }
    }

    public void initExpandView(){
        mLinearLayout = (LinearLayout)findViewById(R.id.layout_title);
        mTextView = (TextView)findViewById(R.id.textview_title);
        mImageView = (ImageView)findViewById(R.id.imageview_state);
        mExpandView = (ExpandView) findViewById(R.id.expandView);
        mExpandView.setContentView();
        mLinearLayout.setClickable(true);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mExpandView.isExpand()){
                    mExpandView.collapse();
                    mTextView.setText("点击向下展开");
                    mImageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
                }else{
                    mExpandView.expand();
                    mTextView.setText("点击向上收叠");
                    mImageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
                }
            }
        });
        mTextView.setText("点击向上收叠");
    }


    class CommentAdapter extends PagerAdapter{

        @Override
        public int getCount()
        {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            container.addView(views.get(position), 0);
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView(views.get(position));
        }
    }

    String[] str = {"本田","丰田","三菱"};
    private void addItemView(){
        int width = ScreenUtils.getScreenWidth(this);

        for (int i=0 ; i < 3; i++){
            View view = View.inflate(this,R.layout.item_price_rank,null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);

            tv_title.setText(str[i]);
            if (i == 2){

                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams((width), ViewGroup.LayoutParams.WRAP_CONTENT);
                ll_addview.addView(view,lp);
            }else {
                int margin = DensityUtils.dp2px(this,50);
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams((width - margin), ViewGroup.LayoutParams.WRAP_CONTENT);
                ll_addview.addView(view,lp);
            }

        }

    }


}
