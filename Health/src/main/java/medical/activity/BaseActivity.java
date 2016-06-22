package medical.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
/**
 * Created by hemi on 2016/3/19.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public  String TAG=this.getClass().getName();

    protected <T extends View> T $(int viewId) {
        return (T) findViewById(viewId);
    }

    Toolbar getToolBar(){return $(R.id.toolbar);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected abstract void initView();
}
