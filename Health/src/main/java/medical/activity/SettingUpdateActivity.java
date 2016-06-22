package medical.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SettingUpdateActivity extends BaseActivity {
    TextView tv_update_server_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting_update);
        setToolbar();
        tv_update_server_ip = $(R.id.tv_update_server_ip);

    }
    void setToolbar(){
        Toolbar toolbar = getToolBar();
        toolbar.setTitle("系统升级");
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
