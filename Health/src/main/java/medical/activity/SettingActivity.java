package medical.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.apkfuns.logutils.LogUtils;

import cn.hbnu.strong.medical.adapter.SettingAdapter;
import cn.hbnu.strong.medical.ui.DividerItemDecoretion;

public class SettingActivity extends BaseActivity {

    //蓝牙板块
    public final static String BLUETOOTH_NIAOCHANGGUI="bluetooth_niaochanggui";
    public final static String BLUETOOTH_TIZHONGCHENG="bluetooth_tizhongcheng";

    //云服务器
    public final static String SERVER="server";
    public final static String SERVER_IP="server_ip";
    public final static String SERVER_PORT="server_port";
    public final static String SERVER_PROTOCAL="server_protocal";





    RecyclerView lv_setting;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting_main);
        lv_setting=$(R.id.lv_setting);
        layoutManager=new LinearLayoutManager(this);
        lv_setting.setLayoutManager(layoutManager);
        SettingAdapter settingAdapter= new SettingAdapter(this);
        lv_setting.setAdapter(settingAdapter);
        settingAdapter.setItemClickListener(new SettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int postion) {
                LogUtils.d("onclickitem" + postion);
               switch (postion){
                   case 0:startActivity(new Intent(SettingActivity.this,SettingBluetoothActivity.class));break;
                   case 1:startActivity(new Intent(SettingActivity.this,SettingTimeActivity.class));break;
                   case 2:startActivity(new Intent(SettingActivity.this,SettingTimeActivity.class));break;
                   case 3:startActivity(new Intent(SettingActivity.this,SettingServerActivity.class));break;
                   case 4:startActivity(new Intent(SettingActivity.this,SettingSystemActivity.class));break;
                   case 5:startActivity(new Intent(SettingActivity.this,SettingUpdateActivity.class));break;
               }
            }
        });

        lv_setting.addItemDecoration(new DividerItemDecoretion(this, DividerItemDecoretion.VERTICAL_LIST));


        //设置toolbar
        Toolbar toolbar = getToolBar();
        toolbar.setTitle("系统设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}