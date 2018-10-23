package cn.fragmention.demo_flow.ui.fragment.shop;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_flow.base.BaseSupportFragment;
import cn.fragmention.demo_flow.ui.fragment.CycleFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by YoKeyword on 16/2/9.
 */
public class ContentFragment extends BaseSupportFragment {
    private static final String ARG_MENU = "arg_menu";
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    Unbinder unbinder;

    private String mMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mMenu = args.getString(ARG_MENU);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static ContentFragment newInstance(String menu) {

        Bundle args = new Bundle();
        args.putString(ARG_MENU, menu);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        mTvContent.setText("Content:\n" + mMenu);

    }

    @Override
    public boolean onBackPressedSupport() {
        // ContentFragment是ShopFragment的栈顶子Fragment,可以在此处理返回按键事件
        return super.onBackPressedSupport();
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        // 和MsgFragment同级别的跳转 交给MsgFragment处理
        if (getParentFragment() instanceof ShopFragment) {
            ((ShopFragment) getParentFragment()).start(CycleFragment.newInstance(1));
        }
    }
}
