package medical.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.hbnu.strong.medical.util.SettingUtil;

public class SettingServerActivity extends BaseActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting);
        setToolbar();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ServerSettingFragment serverSettingFragment = new ServerSettingFragment();

        fragmentTransaction.replace(R.id.fl_setting,serverSettingFragment).commit();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    void setToolbar(){
        Toolbar toolbar = getToolBar();
        toolbar.setTitle("云服务器管理");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(SettingServerActivity.this).
                        unregisterOnSharedPreferenceChangeListener(SettingServerActivity.this);
                finish();
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    public class ServerSettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_server);
        }

        @Override
        public void onDetach() {
            super.onDetach();
            PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                String value=sharedPreferences.getString(key,"");

            if (key.equals(SettingActivity.SERVER_IP)){
                //在global中设置
                SettingUtil.setServerIP(value);
            }

            if (key.equals(SettingActivity.SERVER_PORT)){
//                String value=sharedPreferences.getString(key,"");
                //在global中设置
                SettingUtil.setServerPort(value);
//                getPreferenceScreen().findPreference(key).setSummary(value);
            }

            if (key.equals(SettingActivity.SERVER_PROTOCAL)){
//                String value=sharedPreferences.getString(key,"");
                //在global中设置
                SettingUtil.setServerIP(value);
//                getPreferenceScreen().findPreference(key).setSummary(value);
            }
            getPreferenceScreen().findPreference(key).setSummary(value);
        }

    }
}
