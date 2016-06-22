package medical.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hbnu.strong.medical.activity.R;
import cn.hbnu.strong.medical.util.BitmapUtil;

/**
 * Created by hemi on 2016/3/23.
 */
public class MainAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private static final int[]icons={
    R.drawable.ic_icon1,
    R.drawable.ic_icon2,
    R.drawable.ic_icon3,
    R.drawable.ic_icon4,
    R.drawable.ic_icon5,
    R.drawable.ic_icon6,
    R.drawable.ic_icon7,
    R.drawable.ic_icon8,
    R.drawable.ic_icon9,
    R.drawable.ic_icon10,
    R.drawable.ic_icon11,
    R.drawable.ic_icon12
    };
    private static final String[] names={
    "心电测量","血氧测量","血压测量",
    "血糖测量","尿酸测量","胆固醇测量",
    "尿常规测量","健康指数测量","体温测量",
    "用户测量档案","打印记录","系统设置",

    };
    int width;
    LinearLayout.LayoutParams params;
    public MainAdapter(Context context) {
        this.context = context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //获取屏幕尺寸
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width= wm.getDefaultDisplay().getWidth()-50;
        params= new LinearLayout.LayoutParams(width/3,width/3);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder ;
        if (convertView==null){
        holder= new ViewHolder();
            convertView = inflater.inflate(R.layout.item_main,null);
            holder.iv_main_item_icon= (ImageView) convertView.findViewById(R.id.iv_main_item_icon);
            holder.tv_main_item_name= (TextView) convertView.findViewById(R.id.tv_main_item_name);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
//        holder.iv_main_item_icon.setImageResource(icons[position]);

        holder.iv_main_item_icon.setImageBitmap(BitmapUtil.decodeSampledBitmapFromResource(
                context.getResources(),icons[position],width/3,width/3
        ));
        holder.iv_main_item_icon.setLayoutParams(params);
        holder.tv_main_item_name.setText(names[position]);
        return convertView;
    }
    class ViewHolder{
        TextView tv_main_item_name;
        ImageView iv_main_item_icon;
    }
}
