package cn.fragmention.demo_flow.ui.fragment_swipe_back;

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

/**
 * Created by YoKeyword on 16/4/19.
 */
public class FirstSwipeBackFragment extends BaseSwipeBackFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btn)
    Button btn;
    Unbinder unbinder;

    public static FirstSwipeBackFragment newInstance() {

        Bundle args = new Bundle();

        FirstSwipeBackFragment fragment = new FirstSwipeBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_back_first, container, false);
        unbinder = ButterKnife.bind(this, view);

        mToolbar.setTitle("SwipeBackActivity's Fragment");
        _initToolbar(mToolbar);
        return attachToSwipeBack(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        start(RecyclerSwipeBackFragment.newInstance());
    }
}
