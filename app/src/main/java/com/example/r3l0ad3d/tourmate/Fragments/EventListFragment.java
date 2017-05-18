package com.example.r3l0ad3d.tourmate.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.r3l0ad3d.tourmate.Adapter.AdapterEventList;
import com.example.r3l0ad3d.tourmate.DatabseConnectionCheck;
import com.example.r3l0ad3d.tourmate.HomeActivity;
import com.example.r3l0ad3d.tourmate.ModelClass.Event;
import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.databinding.FragmentEventListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {

    private FragmentEventListBinding binding;
    private AdapterEventList adapter;
    private List<Event> eventList;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private DatabseConnectionCheck getUserId;

    private static  String userId;

    public EventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_event_list, container, false);
        binding = DataBindingUtil.bind(view);

        getUserId = new HomeActivity();
        userId = getUserId.getUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("Event").child(userId);
        eventList = new ArrayList<>();


        adapter = new AdapterEventList(getContext(),eventList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.rvEventList.setHasFixedSize(true);
        binding.rvEventList.setLayoutManager(layoutManager);
        binding.rvEventList.setAdapter(adapter);

        populateListFromDatabse();

        binding.fabCreateEventEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.replaceLayout,new CreateEventFragment()).commit();
                ft.addToBackStack(null);
            }
        });

        return view;
    }

    private void populateListFromDatabse() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    eventList.add(ds.getValue(Event.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
