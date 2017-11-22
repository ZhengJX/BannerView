package smartworld.com.wcjsview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import smartworld.com.wcjsview.model.LeftModel;

/**
 * Created by ${charles}     on 2017/7/31.
 *
 * @desc ${TODO}
 */

public class LeftAdapter extends BaseAdapter
{
    private List<LeftModel> list;
    private Context mContext;

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_CONTENT = 1;


    public LeftAdapter(List<LeftModel> list,Context context)
    {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int i)
    {
        return list.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (list.get(position).getType() == 0){

            return TYPE_TITLE;
        }else {
            return TYPE_CONTENT;
        }

    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder holder;
        ViewHolderTitle viewHolderTitle;
        switch (getItemViewType(i)){
            case TYPE_TITLE:
                if (view == null){

                    viewHolderTitle = new ViewHolderTitle();
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_left_title,viewGroup,false);
                    viewHolderTitle.tv_left_title = (TextView) view.findViewById(R.id.tv_left_title);
                    view.setTag(viewHolderTitle);
                }else {
                    viewHolderTitle = (ViewHolderTitle) view.getTag();
                }

                viewHolderTitle.tv_left_title.setText(list.get(i).getData());
                break;

            case TYPE_CONTENT:


                if (view == null){

                    holder = new ViewHolder();
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_left,viewGroup,false);
                    holder.tv_left = (TextView) view.findViewById(R.id.tv_left);
                    view.setTag(holder);
                }else {
                    holder = (ViewHolder) view.getTag();
                }
                break;
        }

        return view;
    }

    class ViewHolder{
        TextView tv_left;
    }

    class ViewHolderTitle{
        TextView tv_left_title;
    }
}
