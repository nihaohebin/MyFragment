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
public class RegisterFragment extends BaseBackFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_password_confirm)
    EditText mEtPasswordConfirm;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    Unbinder unbinder;

    private LoginFragment.OnLoginSuccessListener mOnLoginSuccessListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.OnLoginSuccessListener) {
            mOnLoginSuccessListener = (LoginFragment.OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLoginSuccessListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
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

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {

        showSoftInput(mEtAccount);
        toolbar.setTitle(R.string.register);
        initToolbarNav(toolbar);

    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String strAccount = mEtAccount.getText().toString();
        String strPassword = mEtPassword.getText().toString();
        String strPasswordConfirm = mEtPasswordConfirm.getText().toString();
        if (TextUtils.isEmpty(strAccount.trim())) {
            Toast.makeText(_mActivity, R.string.error_username, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strPassword.trim()) || TextUtils.isEmpty(strPasswordConfirm.trim())) {
            Toast.makeText(_mActivity, R.string.error_pwd, Toast.LENGTH_SHORT).show();
            return;
        }

        // 注册成功
        mOnLoginSuccessListener.onLoginSuccess(strAccount);
        popTo(LoginFragment.class, true);

    }
}
