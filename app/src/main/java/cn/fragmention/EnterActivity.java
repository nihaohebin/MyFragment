package cn.fragmention;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fragmention.demo_flow.Flow_MainActivity;
import cn.fragmention.demo_wechat.WeChat_MainActivity;
import cn.fragmention.demo_zhihu.ZhiHu_MainActivity;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class EnterActivity extends AppCompatActivity {

    private static final int REQUEST_SETTING = 10;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_flow)
    Button btnFlow;
    @BindView(R.id.btn_wechat)
    Button btnWechat;
    @BindView(R.id.btn_zhihu)
    Button btnZhihu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkMyPermission();
        } else {
            init();
        }
    }

    private void checkMyPermission() {

        AndPermission.with(this)
                .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        init();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        StringBuilder sb = new StringBuilder();
                        for (String p : permissions) {
                            Logger.i("onDenied :" + p);
                            switch (p) {
                                case Permission.CAMERA:
                                    sb.append("《相机》");
                                    break;
                                case Permission.READ_EXTERNAL_STORAGE:
                                    sb.append("《存储》");
                                    break;
                            }
                        }
                        AlertDialog.Builder dlg = new AlertDialog.Builder(EnterActivity.this);
                        dlg.setTitle("提示")
                                .setMessage("请在权限管理中开启" + sb.toString() + "权限!")
                                .setCancelable(false)
                                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.parse("package:" + getPackageName());
                                        intent.setData(uri);
                                        startActivityForResult(intent, REQUEST_SETTING);
                                    }
                                }).create().show();
                    }
                }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.i(requestCode + "," + resultCode);
        checkMyPermission();
    }

    private void init() {

    }


    @OnClick({R.id.btn_flow, R.id.btn_wechat, R.id.btn_zhihu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_flow:
                startActivity(new Intent(EnterActivity.this, Flow_MainActivity.class));
                break;
            case R.id.btn_wechat:
                startActivity(new Intent(EnterActivity.this, WeChat_MainActivity.class));
                break;
            case R.id.btn_zhihu:
                startActivity(new Intent(EnterActivity.this, ZhiHu_MainActivity.class));
                break;
        }
    }
}
