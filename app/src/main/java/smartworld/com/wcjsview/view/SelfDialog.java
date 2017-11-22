package smartworld.com.wcjsview.view;


import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smartworld.com.wcjsview.DensityUtils;
import smartworld.com.wcjsview.R;

/**
 * 创建自定义的dialog，主要学习其实现原理
 * Created by chengguo on 2016/3/22.
 */
public class SelfDialog extends Dialog {


    private TextView tv_move,tv_zhuanjia,tv_cheyou,tv_zhuanjia_text,tv_cheyou_text;

    private ViewPager vp_page;
    private List<View> views = new ArrayList<>();
    private CommentAdapter adapter;

    private ImageView iv_close;


    public SelfDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_exercise_sure_dialog_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

        adapter = new CommentAdapter();
        vp_page = (ViewPager) findViewById(R.id.vp_page);
        tv_move = (TextView) findViewById(R.id.tv_move);
        tv_zhuanjia = (TextView) findViewById(R.id.tv_zhuanjia);
        tv_cheyou = (TextView) findViewById(R.id.tv_cheyou);
        tv_zhuanjia_text = (TextView) findViewById(R.id.tv_zhuanjia_text);
        tv_cheyou_text = (TextView) findViewById(R.id.tv_cheyou_text);

        for (int i=0; i < 2; i++){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_comment,null);
            views.add(view);
        }
        vp_page.setAdapter(adapter);



        tv_zhuanjia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.e(" view.getX()zhuanjia " ,view.getX() + "");
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
                Log.e(" view.getX() " ,view.getX() + "");
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

                Log.e(" ---- ",tv_cheyou.getX() + "");

                if (position == 1){
                    ObjectAnimator animator_img = ObjectAnimator.ofFloat(tv_move, "translationX", tv_move.getX(), tv_cheyou.getX()- DensityUtils.dp2px(getContext(),6));
                    animator_img.setDuration(200);
                    animator_img.start();

                    setTextColor(true,false);
                }else {
                    ObjectAnimator animator_img = ObjectAnimator.ofFloat(tv_move, "translationX", tv_move.getX(), tv_zhuanjia.getX());
                    animator_img.setDuration(200);
                    animator_img.start();

                    setTextColor(false,true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        
    }

    public void setCloseListener(View.OnClickListener onClickListener){

        iv_close.setOnClickListener(onClickListener);
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message

    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        iv_close = (ImageView) findViewById(R.id.iv_close);
    }
   
   
    /**
     * 设置内容布局是否可见
     * **/
    public void setMessageVisible(boolean isVisiable){
    	

    }
    
    /**
     * 设置金额布局是否可见
     * **/
    public void setPriceLayoutVisible(boolean isVisiable){
    	

    }
    
    /**
     * 设置两个按钮布局是否可见
     * **/
    public void setTwoLayoutVisible(boolean isVisiable){
    	

    }
    
    /**
     * 设置单/个按钮布局是否可见
     * **/
    public void setSingleLayoutVisible(boolean isVisiable){
    	

    }

    class CommentAdapter extends PagerAdapter
    {

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

    private void setTextColor(boolean cheyou,boolean zhuanjia){
        tv_cheyou_text.setTextColor(cheyou ? Color.parseColor("#ffffff") : Color.parseColor("#999999"));
        tv_zhuanjia_text.setTextColor(zhuanjia ? Color.parseColor("#ffffff") : Color.parseColor("#999999"));

    }
}

