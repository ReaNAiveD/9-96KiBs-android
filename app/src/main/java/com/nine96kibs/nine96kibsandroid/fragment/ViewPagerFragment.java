package com.nine96kibs.nine96kibsandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nine96kibs.nine96kibsandroid.R;
import com.nine96kibs.nine96kibsandroid.collect.Collection;
import com.nine96kibs.nine96kibsandroid.collect.CollectionAdapter;
import com.nine96kibs.nine96kibsandroid.collect.CollectionChild;
import com.nine96kibs.nine96kibsandroid.collect.CollectionParent;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragment extends Fragment {

    RecyclerView.Adapter secondAdapter;
    RecyclerView.Adapter thirdAdapter;

    public ViewPagerFragment() {
        List<Collection> collections = new ArrayList<>();
        collections.add(new CollectionParent(R.id.parent_type, 0, 0, "兄长"));
        collections.add(new CollectionChild(R.id.child_type, 0, 1, "谢沛东"));
        collections.add(new CollectionChild(R.id.child_type, 0, 2, "吴秦恺"));
        collections.add(new CollectionChild(R.id.child_type, 0, 3, "张雨奇"));
        collections.add(new CollectionParent(R.id.parent_type, 1, 0, "弟弟"));
        collections.add(new CollectionChild(R.id.child_type, 1, 1, "蔡徐坤"));
        collections.add(new CollectionChild(R.id.child_type, 1, 2, "卢本伟"));
        collections.add(new CollectionParent(R.id.parent_type, 2, 0, "弟中弟"));
        collections.add(new CollectionChild(R.id.child_type, 2, 1, "蒋钰涛"));
        List<Integer> shownPositionList = new ArrayList<>();
        shownPositionList.add(0);
        shownPositionList.add(1);
        shownPositionList.add(2);
        shownPositionList.add(3);
        shownPositionList.add(4);
        shownPositionList.add(5);
        shownPositionList.add(6);
        shownPositionList.add(7);
        shownPositionList.add(8);
        List<Integer> parentPositionList = new ArrayList<>();
        parentPositionList.add(0);
        parentPositionList.add(4);
        parentPositionList.add(7);
        List<Integer> parentShownPositionList = new ArrayList<>();
        parentShownPositionList.add(0);
        parentShownPositionList.add(4);
        parentShownPositionList.add(7);
        thirdAdapter = new CollectionAdapter(collections, shownPositionList, parentPositionList, parentShownPositionList);

    }

    public static ViewPagerFragment newInstance(int sectionNumber) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        RecyclerView recyclerView;
        if (getArguments()!= null)
            switch (getArguments().getInt("section_number")) {
                case 0:
                    rootView = inflater.inflate(R.layout.activity_main_body_view_view_pager_first_layout, container, false);
                    break;
                case 1:
                    rootView = inflater.inflate(R.layout.activity_main_body_view_view_pager_second_layout, container, false);
                    recyclerView = rootView.findViewById(R.id.second_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    recyclerView.setAdapter(secondAdapter);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.activity_main_body_view_view_pager_third_layout, container, false);
                    recyclerView = rootView.findViewById(R.id.third_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                    recyclerView.setAdapter(thirdAdapter);
                    break;
            }
        return rootView;
    }

}
