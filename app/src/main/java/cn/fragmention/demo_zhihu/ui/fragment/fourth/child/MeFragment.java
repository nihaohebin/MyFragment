package cn.fragmention.demo_zhihu.ui.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_zhihu.ui.fragment.fourth.ZhihuFourthFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/6/6.
 */
public class MeFragment extends SupportFragment {
    @BindView(R.id.tv_btn_settings)
    TextView mTvBtnSettings;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_fourth_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean onBackPressedSupport() {
        // 这里实际项目中推荐使用 EventBus接耦
        assert getParentFragment() != null;
        ((ZhihuFourthFragment) getParentFragment()).onBackToFirstFragment();
        return true;
    }

    @OnClick(R.id.tv_btn_settings)
    public void onViewClicked() {
        start(SettingsFragment.newInstance());
    }
}
