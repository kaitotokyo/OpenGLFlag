package medical.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daimajia.numberprogressbar.NumberProgressBar;

public class SplashActivity extends Activity {
    NumberProgressBar numberProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        initView();


    }

    private void initView() {
        numberProgressBar = $(R.id.number_progress_bar);
        numberProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump2login();
            }
        });


    }
private  void jump2login(){
    finish();
    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
}
    protected <T extends View> T $(int viewId) {
        return (T) findViewById(viewId);
    }
}
