package cn.fragmention.demo_wechat.ui.fragment.third;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_wechat.base.BaseBackFragment;
import cn.fragmention.demo_wechat.ui.fragment.CycleFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class ModifyDetailFragment extends BaseBackFragment {
    private static final String ARG_TITLE = "arg_title";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_modify_title)
    EditText mEtModiyTitle;
    @BindView(R.id.btn_modify)
    Button mBtnModify;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    Unbinder unbinder;

    private String mTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mTitle = args.getString(ARG_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_detail, container, false);
        unbinder = ButterKnife.bind(this, attachToSwipeBack(view));
        initView();
        return attachToSwipeBack(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static ModifyDetailFragment newInstance(String title) {
        Bundle args = new Bundle();
        ModifyDetailFragment fragment = new ModifyDetailFragment();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        mToolbar.setTitle(R.string.start_result_test);
        initToolbarNav(mToolbar);
        mEtModiyTitle.setText(mTitle);

        // 显示 软键盘
        //        showSoftInput(mEtModiyTitle);

    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @OnClick({R.id.btn_modify, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_modify:
                Bundle bundle = new Bundle();
                bundle.putString(DetailFragment.KEY_RESULT_TITLE, mEtModiyTitle.getText().toString());
                setFragmentResult(RESULT_OK, bundle);

                Toast.makeText(_mActivity, R.string.modify_success, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_next:
                start(CycleFragment.newInstance(1));
                break;
        }
    }
}
