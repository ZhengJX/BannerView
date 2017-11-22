package smartworld.com.wcjsview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smartworld.com.wcjsview.model.LeftModel;

/**
 * Created by ${charles}     on 2017/7/31.
 *
 * @desc ${TODO}
 */

public class ConfigurationActivity extends AppCompatActivity
{

    private LinearLayout ll_top;
    private ListViewForScrollView list_left;
    private LeftAdapter leftAdapter;
    private List<LeftModel> strings;

    private LinearLayout ll_container;

    private StickyScrollView sv;

    private SyncHorizontalScrollView sy_top,sy_contain;

    private TextView tv_canshu;

    private List<Integer> list = new ArrayList<>();
    private List<String> strs = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con);

        list.add(0);
        list.add(672 - 22);
        strs.add("基本参数");
        strs.add("车身");

        ll_top = (LinearLayout) findViewById(R.id.ll_top);
        list_left = (ListViewForScrollView) findViewById(R.id.list_left);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);

        tv_canshu = (TextView) findViewById(R.id.tv_canshu);

        sy_top = (SyncHorizontalScrollView) findViewById(R.id.sy_top);
        sy_contain = (SyncHorizontalScrollView) findViewById(R.id.sy_contain);
        sy_top.setScrollView(sy_contain);
        sy_contain.setScrollView(sy_top);

        strings = new ArrayList<>();

        initData();
        leftAdapter = new LeftAdapter(strings,this);
        list_left.setAdapter(leftAdapter);

        list_left.setDivider(null);//去除listview的下划线



        for (int i=0; i < 6; i++){

            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    LinearLayout title = (LinearLayout) LayoutInflater.from(ConfigurationActivity.this).inflate(R.layout.item_top,null);
                    ll_top.addView(title);

                }
            },100);

        }



        for (int j=0; j < 20 ; j++){
            final LinearLayout  ll_right_contain = (LinearLayout) LayoutInflater.from(ConfigurationActivity.this).inflate(R.layout.item_contain,null);
            final View right_title =  LayoutInflater.from(ConfigurationActivity.this).inflate(R.layout.item_contain_title,null);
            LinearLayout ll_title = (LinearLayout) right_title.findViewById(R.id.ll_title);
            right_title.setX(300);

            if (j == 6){

                ll_container.addView(right_title);
                continue;
            }


            for (int i=0; i < 6; i++){


                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        TextView tv_contain = (TextView) LayoutInflater.from(ConfigurationActivity.this).inflate(R.layout.item_contain_text,null);
                        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dp2px(ConfigurationActivity.this,100),DensityUtils.dp2px(ConfigurationActivity.this,50));
                        tv_contain.setGravity(Gravity.CENTER);

                        ll_right_contain.addView(tv_contain,params);

                    }
                },100);

            }

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtils.dp2px(ConfigurationActivity.this,50));
            ll_right_contain.setGravity(Gravity.CENTER);
            ll_container.addView(ll_right_contain,params);
        }

        sv = (StickyScrollView) findViewById(R.id.act_solution_4_sv);
        sv.smoothScrollTo(0, 0);


        sy_contain.setScrollViewListener(new SyncHorizontalScrollView.ScrollViewListener() {//滑动监听,获取图片
            @Override
            public void onScrollChanged(SyncHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {

                Log.e("==","滑动回调" + " x " + x + " oldx " + oldx);
               // ll_container.getChildAt(0).setX(300 + x );

                ll_container.getChildAt(6).setX(300 + x );
                sy_contain.invalidate();


            }
        });


        sv.setScrollViewListener(new StickyScrollView.ScrollViewListener()
        {
            @Override
            public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy)
            {
                Log.e( " y ",  y + " ");

                for (int i=0; i < list.size(); i++){

                    if (list.get(i) <= y){

                        tv_canshu.setText(strs.get(i));

                    }

                }


            }
        });


        Log.e("dptopx",DensityUtils.dp2px(this,56) + "");
    }

    private void initData(){
        LeftModel leftModel0 = new LeftModel(1,"基本参数");
        LeftModel leftMode1 = new LeftModel(1,"基本参数");
        LeftModel leftMode2 = new LeftModel(1,"基本参数");
        LeftModel leftMode3 = new LeftModel(1,"基本参数");
        LeftModel leftMode4 = new LeftModel(1,"基本参数");
        LeftModel leftMode5= new LeftModel(1,"基本参数");
        LeftModel leftMode6 = new LeftModel(0,"车身");
        LeftModel leftMode7 = new LeftModel(1,"基本参数");
        LeftModel leftMode8 = new LeftModel(1,"基本参数");
        LeftModel leftMode9 = new LeftModel(1,"基本参数");
        LeftModel leftModel10 = new LeftModel(1,"车身");
        LeftModel leftModel11 = new LeftModel(1,"基本参数");
        LeftModel leftModel12 = new LeftModel(1,"基本参数");
        LeftModel leftModel13 = new LeftModel(1,"基本参数");
        LeftModel leftModel14 = new LeftModel(1,"基本参数");
        LeftModel leftModel15 = new LeftModel(1,"基本参数");

        strings.add(leftModel0);
        strings.add(leftMode1);
        strings.add(leftMode2);
        strings.add(leftMode3);
        strings.add(leftMode4);
        strings.add(leftMode5);
        strings.add(leftMode6);
        strings.add(leftMode7);
        strings.add(leftMode8);
        strings.add(leftMode9);
        strings.add(leftModel10);
        strings.add(leftModel11);
        strings.add(leftModel12);
        strings.add(leftModel13);
        strings.add(leftModel14);
        strings.add(leftModel15);


    }
}
