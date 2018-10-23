package cn.fragmention.demo_flow.ui.fragment_swipe_back;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_flow.adapter.FlowPagerAdapter;
import cn.fragmention.demo_flow.listener.OnItemClickListener;


public class RecyclerSwipeBackFragment extends BaseSwipeBackFragment {
    private static final String ARG_FROM = "arg_from";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recy)
    RecyclerView mRecy;
    Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_back_recy, container, false);
        unbinder = ButterKnife.bind(this, attachToSwipeBack(view));
        initView();
        return attachToSwipeBack(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static RecyclerSwipeBackFragment newInstance() {
        return new RecyclerSwipeBackFragment();
    }

    private void initView() {
        _initToolbar(mToolbar);

        FlowPagerAdapter adapter = new FlowPagerAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                start(FirstSwipeBackFragment.newInstance());
            }
        });

        // Init Datas
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String item;
            item = getString(R.string.favorite) + " " + i;
            items.add(item);
        }
        adapter.setDatas(items);
    }

}
