package cn.fragmention.demo_flow.ui.fragment.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import cn.fragmention.demo_flow.base.BaseSupportFragment;
import cn.fragmention.demo_flow.listener.OnItemClickListener;
import cn.fragmention.demo_flow.ui.fragment.CycleFragment;


public class PagerChildFragment extends BaseSupportFragment {
    private static final String ARG_FROM = "arg_from";

    @BindView(R.id.recy)
    RecyclerView mRecy;
    Unbinder unbinder;

    private int mFrom;
    private FlowPagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mFrom = args.getInt(ARG_FROM);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static PagerChildFragment newInstance(int from) {
        Bundle args = new Bundle();
        args.putInt(ARG_FROM, from);

        PagerChildFragment fragment = new PagerChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {

        mAdapter = new FlowPagerAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (getParentFragment() instanceof DiscoverFragment) {
                    ((DiscoverFragment) getParentFragment()).start(CycleFragment.newInstance(1));
                }
            }
        });

        mRecy.post(new Runnable() {
            @Override
            public void run() {
                // Init Datas
                List<String> items = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    String item;
                    if (mFrom == 0) {
                        item = getString(R.string.recommend) + " " + i;
                    } else if (mFrom == 1) {
                        item = getString(R.string.hot) + " " + i;
                    } else {
                        item = getString(R.string.favorite) + " " + i;
                    }
                    items.add(item);
                }
                mAdapter.setDatas(items);
            }
        });
    }

}
