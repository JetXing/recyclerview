package com.example.jet.github.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.jet.github.R;
import com.example.jet.github.model.User;
import com.example.jet.library.base.adapter.BaseAdapter;
import com.example.jet.library.base.ui.BaseFragment;

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

//    private List<String> mStrings;
    private List<User> mList;

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
        mList = getData();
        mAdapter = new RecyclerAdapter(mList, this);
        mAdapter.addHeaderView(new HeaderViewHelper(getActivity()).getHeadView());
        mAdapter.addHeaderView(new HeaderViewHelper(getActivity()).getHeadView());
        mAdapter.addFooterView(new FooterViewHelper(getActivity()).getFooterView());

        mRecyclerView.setAdapter(mAdapter);
    }

    private List<User> getData(){
        List<User> mList = new ArrayList<User>();
        mList.add(getUser("group", "groupName"));
        mList.add(getUser("child", "childName"));
        mList.add(getUser("child", "childName"));
        mList.add(getUser("child", "childName"));
        mList.add(getUser("group", "groupName"));
        mList.add(getUser("child", "childName"));
        mList.add(getUser("child", "childName"));
        mList.add(getUser("child", "childName"));
        return mList;
    }

    private User getUser(String type, String username){
        User mUser = new User();
        mUser.username = username;
        mUser.type = type;

        return mUser;
    }


    @Override
    public void onClick(View itemView, int position) {

//        Toast.makeText(getActivity(), mStrings.get(position) + position, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), mList.get(position).username + position, Toast.LENGTH_SHORT).show();
    }
}
