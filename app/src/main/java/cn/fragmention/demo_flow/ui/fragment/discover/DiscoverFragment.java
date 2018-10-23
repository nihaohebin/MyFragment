package cn.fragmention.demo_flow.ui.fragment.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_flow.adapter.DiscoverFragmentAdapter;
import cn.fragmention.demo_flow.base.BaseMainFragment;

/**
 * Created by YoKeyword on 16/2/3.
 */
public class DiscoverFragment extends BaseMainFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    private void initView() {

        mToolbar.setTitle(R.string.discover);
        initToolbarNav(mToolbar);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.recommend));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.hot));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.favorite));
        mViewPager.setAdapter(new DiscoverFragmentAdapter(getChildFragmentManager(), getString(R.string.recommend), getString(R.string.hot), getString(R.string.favorite)));
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
