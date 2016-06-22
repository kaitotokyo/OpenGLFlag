package medical.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.apkfuns.logutils.LogUtils;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import cn.hbnu.strong.medical.adapter.MainAdapter;

public class MainActivity extends BaseActivity {
    private  Toolbar.OnMenuItemClickListener onMenuItemClickListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar =getToolBar();
        toolbar.setTitle("姓名:测试账号");
        toolbar.setSubtitle("登录方式:[身份证登录]");//需设置到Application里面
//        toolbar.setMenu(new MenuBuilder(MainActivity.this), new ActionMenuPresenter(this))
        setSupportActionBar(toolbar);
        initexit();
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);

        GridView gv=$(R.id.gl_main);
        gv.setAdapter(new MainAdapter(this));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 1:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 2:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 3:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 4:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 5:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 6:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 7:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 8:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 9:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 10:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 11:{startActivity(new Intent(MainActivity.this,SettingActivity.class));break; }
                    case 12:{startActivity(new Intent(MainActivity.this,SettingActivity.class));finish();break; }
                }
            }
        });
    }

    private  void jump2login(){
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void initexit(){
        onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LogUtils.d(item.getItemId());
                if (item.getItemId()==R.id.action_exit){
                    final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(MainActivity.this);
                    dialogBuilder
                            .withTitle("提示")                                  //.withTitle(null)  no title
                            .withTitleColor("#FFFFFF")                                  //def
                            .withDividerColor("#11000000")                              //def
                            .withMessage("是否要退出当前用户")                     //.withMessage(null)  no Msg
                            .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                            .withDialogColor("#303F9F")                               //def  | withDialogColor(int resid)
                            .withDuration(400)                                          //def
                            .withEffect(Effectstype.SlideBottom)
                            .withButton1Text("确定")                                      //def gone
                            .withButton2Text("取消")                                  //def gone
                            .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
//                            .setCustomView(R.layout.custom_view,v.getContext())         //.setCustomView(View or ResId,context)
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialogBuilder.dismiss();
                                    jump2login();
                                }
                            })
                            .setButton2Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogBuilder.dismiss();
                                }
                            })
                            .show();
                }

                return false;
            }
        };
    }
}
