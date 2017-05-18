package com.example.r3l0ad3d.tourmate.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.r3l0ad3d.tourmate.Adapter.AdapterExpance;
import com.example.r3l0ad3d.tourmate.ModelClass.Expences;
import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.databinding.AddExpanceBinding;
import com.example.r3l0ad3d.tourmate.databinding.FragmentExpanceRecordBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpanceRecordFragment extends Fragment {

    private FragmentExpanceRecordBinding binding;

    private static String eventId;
    private static String userId;
    private static String budget;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private List<Expences> expencesList;
    private AdapterExpance adapter;

    public ExpanceRecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_expance_record, container, false);
        binding = DataBindingUtil.bind(view);
        eventId = getArguments().getString("eventId");
        userId = getArguments().getString("userId");
        budget = getArguments().getString("budget");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("root").child("Expance").child(userId).child(eventId);

        expencesList = new ArrayList<>();
        adapter = new AdapterExpance(getContext(),expencesList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.rvExpanceHistory.hasFixedSize();
        binding.rvExpanceHistory.setLayoutManager(layoutManager);
        binding.rvExpanceHistory.setAdapter(adapter);

        binding.tvEstimatedBudgetExHistory.setText(budget);

        binding.btnAddExpHistoryExHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpances();
            }
        });

        populateRecyclList();

        return view;
    }

    private void populateRecyclList() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    expencesList.add(ds.getValue(Expences.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addExpances() {


        final String currentDate;

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Expance Add");
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.add_expance,null);
        final AddExpanceBinding binding = DataBindingUtil.bind(view);

        Calendar calender = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
        currentDate=formater.format(new Date(calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)));

        alert.setView(view);

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String amount =binding.etExpanceAmontAddExp.getText().toString();
                String reason =binding.etExpanceReasonAddExp.getText().toString();
                Expences ex = new Expences(reason,amount,currentDate);
                databaseReference.push().setValue(ex);
                Toast.makeText(getContext(),"Add Success",Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

}
