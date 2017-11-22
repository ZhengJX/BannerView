package smartworld.com.wcjsview;

/**
 * Created by ${charles}     on 2017/7/31.
 *
 * @desc ${TODO}
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

public class SyncHorizontalScrollView extends HorizontalScrollView{
    private View mView;

    private ScrollViewListener scrollViewListener = null;
    public SyncHorizontalScrollView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mView!=null){
            mView.scrollTo(l, t);
        }

        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setScrollView(View view){
        mView = view;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public interface ScrollViewListener {
        void onScrollChanged(SyncHorizontalScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        // 弹性滑动关键则是maxOverScrollX， 以及maxOverScrollY，
        // 一般默认值都是0，需要弹性时，更改其值即可
        // 即就是，为零则不会发生弹性，不为零（>0,负数未测试）则会滑动到其值的位置
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, 0, 0, isTouchEvent);
    }

}

