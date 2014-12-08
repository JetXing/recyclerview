package com.example.jet.github.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.jet.github.R;
import com.example.jet.github.base.adapter.BaseAdapter;
import com.example.jet.github.base.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by xingguangyao on 14/12/5.
 */
public class RecyclerFragment extends BaseFragment implements BaseAdapter.RecyclerViewItemClickListener {

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;

    private List<String> mStrings;

    public static RecyclerFragment newInstance(int sectionNumber) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.fragment_main_recycler;
    }

    @Override
    protected void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        mStrings = new ArrayList<String>();
        mStrings.add("headerview");
        mStrings.add("itemview");
        mStrings.add("itemview");
        mStrings.add("itemview");
        mStrings.add("footerview");
        mAdapter = new RecyclerAdapter(mStrings, this);
        mAdapter.addHeaderView(View.inflate(getActivity(), R.layout.fragment_main_recycler_header, null));
        mAdapter.addFooterView(View.inflate(getActivity(), R.layout.fragment_main_recycler_footer, null));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View itemView, int position) {

        Toast.makeText(getActivity(), mStrings.get(position), Toast.LENGTH_SHORT).show();
    }
}
