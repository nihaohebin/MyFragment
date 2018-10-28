package cn.fragmention.demo_wechat.ui.fragment.first;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_wechat.adapter.MsgAdapter;
import cn.fragmention.demo_wechat.base.BaseBackFragment;
import cn.fragmention.demo_wechat.entity.Chat;
import cn.fragmention.demo_wechat.entity.Msg;

/**
 * Created by YoKeyword on 16/6/30.
 */
public class MsgFragment extends BaseBackFragment {

    private static final String ARG_MSG = "arg_msg";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recy)
    RecyclerView mRecy;
    @BindView(R.id.et_send)
    EditText mEtSend;
    @BindView(R.id.btn_send)
    Button mBtnSend;
    Unbinder unbinder;

    private Chat mChat;
    private MsgAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mChat = getArguments().getParcelable(ARG_MSG);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_fragment_tab_first_msg, container, false);
        unbinder = ButterKnife.bind(this, attachToSwipeBack(view));
        initView();
        return attachToSwipeBack(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecy = null;
        _mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        hideSoftInput();
        unbinder.unbind();
    }

    public static MsgFragment newInstance(Chat msg) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_MSG, msg);
        MsgFragment fragment = new MsgFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        mToolbar.setTitle(mChat.name);
        initToolbarNav(mToolbar);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        // 入场动画结束后执行  优化,防动画卡顿

        _mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mRecy.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecy.setHasFixedSize(true);
        mAdapter = new MsgAdapter(_mActivity);
        mRecy.setAdapter(mAdapter);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEtSend.getText().toString().trim();
                if (TextUtils.isEmpty(str))
                    return;

                mAdapter.addMsg(new Msg(str));
                mEtSend.setText("");
                mRecy.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });

        mAdapter.addMsg(new Msg(mChat.message));
    }

}
