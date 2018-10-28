package cn.fragmention.demo_wechat.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
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
import cn.fragmention.demo_wechat.base.BaseBackFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class CycleFragment extends BaseBackFragment {

    private static final String ARG_NUMBER = "arg_number";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.btn_next_with_finish)
    Button mBtnNextWithFinish;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    Unbinder unbinder;

    private int mNumber;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mNumber = args.getInt(ARG_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cycle, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return attachToSwipeBack(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static CycleFragment newInstance(int number) {
        CycleFragment fragment = new CycleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    private void initView() {

        String title = "CyclerFragment " + mNumber;
        mToolbar.setTitle(title);
        initToolbarNav(mToolbar);

        mTvName.setText(title + "\n" + getString(R.string.can_swipe));
    }

    @OnClick({R.id.btn_next_with_finish, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next_with_finish:
                startWithPop(CycleFragment.newInstance(mNumber + 1));
                break;
            case R.id.btn_next:
                start(CycleFragment.newInstance(mNumber + 1));
                break;
        }
    }
}
