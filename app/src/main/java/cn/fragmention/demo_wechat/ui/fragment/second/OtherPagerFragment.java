package cn.fragmention.demo_wechat.ui.fragment.second;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import cn.fragmention.R;

/**
 * Created by YoKeyword on 16/6/30.
 */
public class OtherPagerFragment extends SupportFragment {

    public static OtherPagerFragment newInstance() {

        Bundle args = new Bundle();
        OtherPagerFragment fragment = new OtherPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.wechat_fragment_tab_second_pager_other, container, false);
    }
}
