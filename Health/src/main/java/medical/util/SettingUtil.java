package medical.util;

import android.content.Context;

import cn.hbnu.strong.medical.activity.SettingActivity;
import cn.hbnu.strong.medical.app.GlobalContext;

/**
 * Created by hemi on 2016/4/22.
 */
public class SettingUtil {

    private static Context getContext() {
        return GlobalContext.getInstance();
    }
    public static void setServerIP(String ip){
        SettingHelper.setEditor(getContext(), SettingActivity.SERVER_IP,ip);
    }
    public  static String getServerIP(){
        return SettingHelper.getSharedPreferences(getContext(),SettingActivity.SERVER_IP,"");
    }
    public static void setServerPort(String ip){
        SettingHelper.setEditor(getContext(), SettingActivity.SERVER_PORT,ip);
    }
    public  static String getServerPort(){
        return SettingHelper.getSharedPreferences(getContext(),SettingActivity.SERVER_PORT,"");
    }
}
