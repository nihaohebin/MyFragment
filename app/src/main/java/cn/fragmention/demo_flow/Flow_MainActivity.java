package cn.fragmention.demo_flow;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fragmention.R;
import cn.fragmention.demo_flow.base.BaseMainFragment;
import cn.fragmention.demo_flow.base.BaseSupportActivity;
import cn.fragmention.demo_flow.base.BaseSupportFragment;
import cn.fragmention.demo_flow.ui.fragment.account.LoginFragment;
import cn.fragmention.demo_flow.ui.fragment.discover.DiscoverFragment;
import cn.fragmention.demo_flow.ui.fragment.home.HomeFragment;
import cn.fragmention.demo_flow.ui.fragment.shop.ShopFragment;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 流程式demo  tip: 多使用右上角的"查看栈视图"
 * Created by YoKeyword on 16/1/29.
 */
public class Flow_MainActivity extends BaseSupportActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        BaseMainFragment.OnFragmentOpenDrawerListener,
        LoginFragment.OnLoginSuccessListener {

    public static final String TAG = Flow_MainActivity.class.getSimpleName();
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    private long TOUCH_TIME = 0;
    private TextView mTvName;   // NavigationView上的名字
    private ImageView mImgNav;  // NavigationView上的头像
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BaseSupportFragment fragment = findFragment(HomeFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
        initView();
    }

    /**
     * 设置动画，也可以使用setFragmentAnimator()设置
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
        //        return new DefaultHorizontalAnimator();
        // 设置自定义动画
        //        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void initView() {
        toggle = new ActionBarDrawerToggle(this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //        mDrawer.setDrawerListener(toggle);

        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);

        LinearLayout llNavHeader = (LinearLayout) mNavigationView.getHeaderView(0);
        mTvName = llNavHeader.findViewById(R.id.tv_name);
        mImgNav = llNavHeader.findViewById(R.id.img_nav);
        llNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(GravityCompat.START);
                mDrawer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goLogin();
                    }
                }, 300);
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            ISupportFragment topFragment = getTopFragment();

            // 主页的Fragment
            if (topFragment instanceof BaseMainFragment) {
                mNavigationView.setCheckedItem(R.id.nav_home);
            }

            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                pop();
            } else {
                if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                    finish();
                } else {
                    TOUCH_TIME = System.currentTimeMillis();
                    Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 打开抽屉
     */
    @Override
    public void onOpenDrawer() {
        if (!mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        mDrawer.closeDrawer(GravityCompat.START);

        mDrawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();

                final ISupportFragment topFragment = getTopFragment();
                BaseSupportFragment myHome = (BaseSupportFragment) topFragment;

                if (id == R.id.nav_home) {

                    HomeFragment fragment = findFragment(HomeFragment.class);
                    Bundle newBundle = new Bundle();
                    newBundle.putString("from", "From:" + topFragment.getClass().getSimpleName());
                    fragment.putNewBundle(newBundle);

                    myHome.start(fragment, SupportFragment.SINGLETASK);
                } else if (id == R.id.nav_discover) {
                    DiscoverFragment fragment = findFragment(DiscoverFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(DiscoverFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        // 如果已经在栈内,则以SingleTask模式start
                        myHome.start(fragment, SupportFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_shop) {
                    ShopFragment fragment = findFragment(ShopFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(ShopFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        // 如果已经在栈内,则以SingleTask模式start,也可以用popTo
                        //                        start(fragment, SupportFragment.SINGLETASK);
                        myHome.popTo(ShopFragment.class, false);
                    }
                } else if (id == R.id.nav_login) {
                    goLogin();
                } else if (id == R.id.nav_swipe_back) {
                    startActivity(new Intent(Flow_MainActivity.this, SwipeBackSampleActivity.class));
                }
            }
        }, 300);

        return true;
    }

    private void goLogin() {
        start(LoginFragment.newInstance());
    }

    @Override
    public void onLoginSuccess(String account) {
        mTvName.setText(account);
        mImgNav.setImageResource(R.drawable.ic_account_circle_white_48dp);
        Toast.makeText(this, R.string.sign_in_success, Toast.LENGTH_SHORT).show();
    }
}
