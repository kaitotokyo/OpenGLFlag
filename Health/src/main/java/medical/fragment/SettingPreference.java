package medical.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import cn.hbnu.strong.medical.activity.R;

/**
 * Created by hemi on 2016/4/4.
 */
public class SettingPreference extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        initView();
    }

    private void initView() {
//        findPreference()
    }
}
