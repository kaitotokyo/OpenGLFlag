package medical.app;

import android.app.Application;

/**
 * Created by hemi on 2016/4/4.
 */
public class GlobalContext extends Application {
    //单例模式
private static GlobalContext globalContext;



public static GlobalContext getInstance(){
    return globalContext;
}

    @Override
    public void onCreate() {
        super.onCreate();
        globalContext=this;

    }



}
