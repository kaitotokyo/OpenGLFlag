package medical.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.hbnu.strong.medical.activity.R;
import cn.hbnu.strong.medical.util.BitmapUtil;

/**
 * Created by hemi on 2016/3/25.
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private static final int[]icons={
            R.drawable.ic_setting_01,
            R.drawable.ic_setting_02,
            R.drawable.ic_setting_03,
            R.drawable.ic_setting_04,
            R.drawable.ic_setting_05,
            R.drawable.ic_setting_06
    };
    private static final String[] names={
            "蓝牙配置","日期/时间","网络配置",
            "云服务配置","系统管理","系统更新"

    };
    public OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int postion);
    }

    public SettingAdapter(Context context) {
        this.context = context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.iv_setting_item_icon.setImageResource(icons[position]);
        holder.iv_setting_item_icon.setImageBitmap(BitmapUtil.decodeSampledBitmapFromResource(context.getResources(),
                icons[position],130,130));
        holder.tv_setting_item_name.setText(names[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView iv_setting_item_icon;
        TextView tv_setting_item_name;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_setting_item_icon= (ImageView) itemView.findViewById(R.id.iv_setting_item_icon);
            tv_setting_item_name= (TextView) itemView.findViewById(R.id.tv_setting_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener!=null)
            {
             itemClickListener.onItemClick(v,getPosition());
            }
        }
    }
}
