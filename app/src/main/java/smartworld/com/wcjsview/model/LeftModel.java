package smartworld.com.wcjsview.model;

/**
 * Created by ${charles}     on 2017/7/31.
 *
 * @desc ${TODO}
 */

public class LeftModel
{
    private int type;
    private String data;

    public LeftModel(int type, String data)
    {
        this.type = type;
        this.data = data;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}
