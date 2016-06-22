package medical.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.hbnu.strong.medical.fragment.SettingPreference;

/**
 * Created by hemi on 2016/4/4.
 */
public class SettingBluetoothActivity extends  BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting);
        setToolbar();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SettingPreference  settingPreference = new SettingPreference();

        fragmentTransaction.replace(R.id.fl_setting,settingPreference).commit();



    }

    void setToolbar(){
        Toolbar toolbar = getToolBar();
        toolbar.setTitle("蓝牙配置");
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
