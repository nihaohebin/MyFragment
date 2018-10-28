package cn.fragmention.demo_zhihu.ui.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.fragmention.R;
import cn.fragmention.demo_zhihu.base.BaseBackFragment;
import cn.fragmention.demo_zhihu.entity.Article;
import cn.fragmention.demo_zhihu.ui.fragment.CycleFragment;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstDetailFragment extends BaseBackFragment {
    private static final String ARG_ITEM = "arg_item";

    @BindView(R.id.img_detail)
    ImageView mImgDetail;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_content)
    TextView mTvTitle;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    Unbinder unbinder;

    private Article mArticle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mArticle = getArguments().getParcelable(ARG_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_first_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static FirstDetailFragment newInstance(Article article) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, article);
        FirstDetailFragment fragment = new FirstDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        mToolbar.setTitle("");
        initToolbarNav(mToolbar);
        mImgDetail.setImageResource(mArticle.getImgRes());
        mTvTitle.setText(mArticle.getTitle());

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(1));
            }
        });
    }

}
