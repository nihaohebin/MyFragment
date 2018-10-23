package cn.fragmention;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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
