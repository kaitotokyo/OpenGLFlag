package medical.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hemi on 2016/3/20.
 */
public class ToastUtil {
    public  static  void longTime(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    } public  static  void shortTime(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
