package com.example.jason.myvideoplayer.mainPage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.mainPage.adapter.TTAdapter;

import java.util.ArrayList;
import java.util.List;


public class Tab3Fragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {


    private RecyclerView rvTest;
    private TTAdapter mAdapter;
    private List<TTAdapter.MuEntity> datas;
    private TextView hearView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        hearView = new TextView(getContext());
        hearView.setText("头部");
        rvTest =(RecyclerView)view. findViewById(R.id.rvTest);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new TTAdapter(null);
        rvTest.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTest.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.addHeaderView(hearView);
        mAdapter.setNewData(getDatas());
    }


    public List<TTAdapter.MuEntity> getDatas() {
        List<TTAdapter.MuEntity> datas=new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if(i%2==0){
                datas.add(new TTAdapter.MuEntity("标题"+i));
            }else {
                datas.add(new TTAdapter.MuEntity(R.mipmap.ic_launcher, TTAdapter.MuEntity.TYPE2));
            }
        }
        return datas;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TTAdapter.MuEntity item= (TTAdapter.MuEntity) adapter.getItem(position);
        switch (item.getItemType()){
            case TTAdapter.MuEntity.TYPE1:
                Toast.makeText(getContext(), ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case TTAdapter.MuEntity.TYPE2:

                break;
        }
    }
}
