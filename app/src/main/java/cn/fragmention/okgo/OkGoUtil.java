package cn.fragmention.okgo;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * 作者: Joker
 * 时间: 2018/11/9. 17:06
 */

public class OkGoUtil {


    public static void doGet(String url){

        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

            }
        });
    }


}
