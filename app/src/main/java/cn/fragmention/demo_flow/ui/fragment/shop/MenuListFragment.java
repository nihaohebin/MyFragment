package cn.fragmention.demo_flow.ui.fragment.shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_flow.adapter.MenuAdapter;
import cn.fragmention.demo_flow.base.BaseSupportFragment;
import cn.fragmention.demo_flow.listener.OnItemClickListener;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by YoKeyword on 16/2/9.
 */
public class MenuListFragment extends BaseSupportFragment {
    private static final String ARG_MENUS = "arg_menus";
    private static final String SAVE_STATE_POSITION = "save_state_position";
    @BindView(R.id.recy)
    RecyclerView mRecy;
    Unbinder unbinder;

    private MenuAdapter mAdapter;
    private ArrayList<String> mMenus;
    private int mCurrentPosition = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mMenus = args.getStringArrayList(ARG_MENUS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_menu, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mAdapter = new MenuAdapter(_mActivity);
        mRecy.setAdapter(mAdapter);
        mAdapter.setDatas(mMenus);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                showContent(position);
            }
        });

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(SAVE_STATE_POSITION);
            mAdapter.setItemChecked(mCurrentPosition);
        } else {
            mCurrentPosition = 0;
            mAdapter.setItemChecked(0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static MenuListFragment newInstance(ArrayList<String> menus) {

        Bundle args = new Bundle();
        args.putStringArrayList(ARG_MENUS, menus);

        MenuListFragment fragment = new MenuListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }


    private void showContent(int position) {
        if (position == mCurrentPosition) {
            return;
        }

        mCurrentPosition = position;

        mAdapter.setItemChecked(position);

        ContentFragment fragment = ContentFragment.newInstance(mMenus.get(position));

        assert getParentFragment() != null;
        ((ShopFragment) getParentFragment()).switchContentFragment(fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_POSITION, mCurrentPosition);
    }

}
