package cn.fragmention.demo_wechat.ui.fragment.second;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_wechat.base.BaseBackFragment;
import cn.fragmention.demo_wechat.ui.fragment.CycleFragment;

/**
 * 该类是展示 1.0 版本新特性：拓展事务 extraTransaction()
 * <p>
 * Created by YoKey on 16/11/25.
 */
public class NewFeatureFragment extends BaseBackFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_start_dont_hide)
    Button btnStartDontHide;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_fragment_new_feature, container, false);
        unbinder = ButterKnife.bind(this, attachToSwipeBack(view));

        initToolbarNav(toolbar);
        toolbar.setTitle("NewFeatures");
        return attachToSwipeBack(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static NewFeatureFragment newInstance() {
        return new NewFeatureFragment();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 懒加载
        // 同级Fragment场景、ViewPager场景均适用
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        // 当对用户可见时 回调
        // 不管是 父Fragment还是子Fragment 都有效！
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        // 当对用户不可见时 回调
        // 不管是 父Fragment还是子Fragment 都有效！
    }

    @OnClick({R.id.btn_start, R.id.btn_start_dont_hide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 自定义动画启动一个Fragment，并且不隐藏自己
            case R.id.btn_start:
                extraTransaction()
                        //                        .setTag("CustomTag")
                        //                        . ...
                        .setCustomAnimations(R.anim.v_fragment_enter, R.anim.v_fragment_pop_exit,
                                R.anim.v_fragment_pop_enter, R.anim.v_fragment_exit)
                        .start(CycleFragment.newInstance(0));
                break;
            // 自定义动画启动一个Fragment
            case R.id.btn_start_dont_hide:
                extraTransaction()
                        .setCustomAnimations(R.anim.v_fragment_enter, 0, 0, R.anim.v_fragment_exit)
                        .startDontHideSelf(ViewFragment.newInstance());
                break;
        }
    }
}
