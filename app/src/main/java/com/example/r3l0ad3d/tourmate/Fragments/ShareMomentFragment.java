package com.example.r3l0ad3d.tourmate.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3l0ad3d.tourmate.Adapter.AdapterShareMoment;
import com.example.r3l0ad3d.tourmate.ModelClass.ShareMoment;
import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.databinding.FragmentShareMomentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareMomentFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterShareMoment adapter;
    private List<ShareMoment> shareMomentList;

    private FragmentShareMomentBinding binding;

    public ShareMomentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_moment, container, false);

        binding = DataBindingUtil.bind(view);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvShareMoment);
        shareMomentList = new ArrayList<>();
        adapter = new AdapterShareMoment(getContext(),shareMomentList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
