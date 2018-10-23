package cn.fragmention.demo_flow.ui.fragment.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import cn.fragmention.demo_flow.base.BaseBackFragment;

/**
 * Created by YoKeyword on 16/2/14.
 */
public class LoginFragment extends BaseBackFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_register)
    Button mBtnLogin;
    @BindView(R.id.btn_login)
    Button mBtnRegister;
    Unbinder unbinder;

    private OnLoginSuccessListener mOnLoginSuccessListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginSuccessListener) {
            mOnLoginSuccessListener = (OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLoginSuccessListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnLoginSuccessListener = null;
    }

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {

        toolbar.setTitle(R.string.login);
        initToolbarNav(toolbar);
    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                start(RegisterFragment.newInstance());
                break;
            case R.id.btn_login:
                String strAccount = mEtAccount.getText().toString();
                String strPassword = mEtPassword.getText().toString();
                if (TextUtils.isEmpty(strAccount.trim())) {
                    Toast.makeText(_mActivity, R.string.error_username, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(strPassword.trim())) {
                    Toast.makeText(_mActivity, R.string.error_pwd, Toast.LENGTH_SHORT).show();
                    return;
                }

                // 登录成功
                mOnLoginSuccessListener.onLoginSuccess(strAccount);
                pop();
                break;
        }
    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess(String account);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }
}
