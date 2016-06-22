package medical.activity;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SettingTimeActivity extends BaseActivity {
    private TextView tv_setting_time_date,tv_setting_time_time,tv_setting_time_show,tv_setting_time_sync;
    @Override
    protected void initView() {
    setContentView(R.layout.activity_setting_time);
        Toolbar toolbar = getToolBar();
        toolbar.setTitle("日期/时间设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_setting_time_date=$(R.id.tv_setting_time_date);
        tv_setting_time_time=$(R.id.tv_setting_time_time);
        tv_setting_time_show=$(R.id.tv_setting_time_show);
        tv_setting_time_sync=$(R.id.tv_setting_time_sync);
    }
}
