package medical.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    EditText et_username,et_password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        getToolBar().setTitle("用户登录");
        et_username = $(R.id.username);
        et_password = $(R.id.password);
    }

    public void login(View v){
        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}

