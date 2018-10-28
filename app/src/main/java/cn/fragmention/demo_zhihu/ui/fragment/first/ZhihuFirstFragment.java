package cn.fragmention.demo_zhihu.ui.fragment.first;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.fragmention.R;
import cn.fragmention.demo_zhihu.base.BaseMainFragment;
import cn.fragmention.demo_zhihu.ui.fragment.first.child.FirstHomeFragment;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class ZhihuFirstFragment extends BaseMainFragment {

    public static ZhihuFirstFragment newInstance() {
        Bundle args = new Bundle();
        ZhihuFirstFragment fragment = new ZhihuFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.zhihu_fragment_first, container, false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(FirstHomeFragment.class) == null) {
            loadRootFragment( R.id.fl_first_container, FirstHomeFragment.newInstance());
        }
    }
}
