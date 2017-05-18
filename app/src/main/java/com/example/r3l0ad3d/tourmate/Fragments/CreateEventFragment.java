package com.example.r3l0ad3d.tourmate.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.r3l0ad3d.tourmate.DatabseConnectionCheck;
import com.example.r3l0ad3d.tourmate.HomeActivity;
import com.example.r3l0ad3d.tourmate.ModelClass.Event;
import com.example.r3l0ad3d.tourmate.ModelClass.UserInformation;
import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.databinding.FragmentCreateEventBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment {

    private FragmentCreateEventBinding binding;
    private Event eventObj;
    private String toDate="";
    private String fromDate="";
    private String currentDate="";


    private DatabseConnectionCheck getUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private static String userID;


    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_create_event, container, false);
        binding = DataBindingUtil.bind(view);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
        currentDate = formater.format(new Date(calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR),calendar.get(Calendar.DAY_OF_MONTH)));

        getUser = new HomeActivity();
        userID = getUser.getUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("Event").child(userID);

        btnClick();
        return view;
    }

   public void btnClick(){
       binding.btnToDateCE.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setDate("to");
           }
       });
       binding.btnFromDateCE.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setDate("from");
           }
       });
       binding.btnCreateEventCE.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String place = binding.etEventPlaceCE.getText().toString();
               String budget = binding.etEstimatedBudgetCE.getText().toString();
               if(currentDate.equals("")||toDate.equals("")||fromDate.equals("")||place.equals("")||budget.equals("")){
                   Toast.makeText(getContext(),"Empty field found",Toast.LENGTH_LONG).show();
               }else {
                   eventObj = new Event(currentDate,place,toDate,fromDate,budget);

                   //Toast.makeText(getContext(),userID,Toast.LENGTH_LONG).show();
                   String key=databaseReference.push().getKey();
                   eventObj.setEventId(key);
                   eventObj.setUserId(userID);

                   databaseReference.child(key).setValue(eventObj);
                   FragmentManager fm = getFragmentManager();
                   FragmentTransaction ft = fm.beginTransaction();
                   Fragment fragment = new EventListFragment();
                   ft.replace(R.id.replaceLayout,fragment).commit();
                   ft.addToBackStack(null);
                   Toast.makeText(getContext(),"Event create success",Toast.LENGTH_LONG).show();
               }
           }
       });
   }

    public void setDate(final String callFrom){
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yy");
        DatePickerDialog datePick = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String str = formate.format(new Date(year,month,dayOfMonth));
                if(callFrom.equals("from")){
                    binding.btnFromDateCE.setText(str);
                    fromDate=str;
                }else{
                    binding.btnToDateCE.setText(str);
                    toDate = str;
                }
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePick.show();
    }
}
