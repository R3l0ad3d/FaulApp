package com.example.r3l0ad3d.tourmate.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3l0ad3d.tourmate.Adapter.AdapterNearByItem;
import com.example.r3l0ad3d.tourmate.Adapter.PageAdapterELIS;
import com.example.r3l0ad3d.tourmate.DatabseConnectionCheck;
import com.example.r3l0ad3d.tourmate.HomeActivity;
import com.example.r3l0ad3d.tourmate.ModelClass.Event;
import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.Weather.Adapter.AdapterWeatherForeCast;
import com.example.r3l0ad3d.tourmate.Weather.Interface.WeatherApi;
import com.example.r3l0ad3d.tourmate.Weather.ModelClass.ForeCastReport;
import com.example.r3l0ad3d.tourmate.Weather.ModelClass.WeatherModelResponse;
import com.example.r3l0ad3d.tourmate.databinding.FragmentEventListItemShowBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListItemShowFragment extends Fragment {

    private FragmentEventListItemShowBinding binding ;


    private static String cityName="dhaka";
    private static  List<ForeCastReport> foreCastReportList;
    private static List<String> nearByItemList;

    private AdapterNearByItem adapterNearByItem;
    private AdapterWeatherForeCast adapterWeatherForeCast;
    private PageAdapterELIS pageAdapterELIS;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private DatabseConnectionCheck getUser;
    private static String userID;
    private static String eventID;

    private static Event event;

    public EventListItemShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list_item_show, container, false);

        binding = DataBindingUtil.bind(view);
        foreCastReportList = new ArrayList<>();
        nearByItemList = new ArrayList<>();

        getUser = new HomeActivity();
        userID = getUser.getUser();

        event = (Event) getArguments().getSerializable("event");
        eventID=event.getEventId();
        cityName = event.getEventPlace();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("root").child("Event").child(userID).child(eventID);


        //set data on event details
        binding.tvEventPlaceELIS.setText(event.getEventPlace());
        binding.tvFromDateELIS.setText(event.getFromDate());
        binding.tvToDateELIS.setText(event.getToDate());
        binding.tvEstimatedBudgetELIS.setText(event.getEstimatedBudget());

        //recycle view adapter initial
        adapterNearByItem = new AdapterNearByItem(getContext(),nearByItemList);
        adapterWeatherForeCast = new AdapterWeatherForeCast(getContext(),foreCastReportList);

        //layout manager set
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        binding.rvForeCastWeatherELIS.hasFixedSize();
        binding.rvNearByELIS.hasFixedSize();

        binding.rvForeCastWeatherELIS.setLayoutManager(layoutManager);
        binding.rvNearByELIS.setLayoutManager(layoutManager1);

        //adapter set on recycle view
        binding.rvForeCastWeatherELIS.setAdapter(adapterWeatherForeCast);
        binding.rvNearByELIS.setAdapter(adapterNearByItem);



        //populate recycle view
        getReport();
        setNearByData();

       //expance history
        binding.btnExpanceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ExpanceRecordFragment();

                Bundle bundle = new Bundle();
                bundle.putString("eventId",eventID);
                bundle.putString("budget",event.getEstimatedBudget());
                bundle.putString("userId",userID);

                fragment.setArguments(bundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.replaceLayout,fragment).commit();
                ft.addToBackStack(null);
            }
        });

        //Toast.makeText(getContext(),""+foreCastReportList.size(),Toast.LENGTH_LONG).show();

        return view;
    }


    private void getReport() {

        final String BASE_URL = "https://query.yahooapis.com/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        String url = "v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" +
                cityName+"%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        Call<WeatherModelResponse> weatherModelResponseCall = weatherApi.getWeatherResponse(url);

        weatherModelResponseCall.enqueue(new Callback<WeatherModelResponse>() {
            @Override
            public void onResponse(Call<WeatherModelResponse> call, Response<WeatherModelResponse> response) {
                if(response.code()==200){
                    WeatherModelResponse responsList = response.body();

                    final ForeCastReport reportList = new ForeCastReport(responsList);

                    foreCastReportList.addAll(reportList.getForeCastReportList());
                    adapterWeatherForeCast.notifyDataSetChanged();

                }else {
                    Log.d("Error","Connection Problem");
                }
            }

            @Override
            public void onFailure(Call<WeatherModelResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

    }

    private void setNearByData(){
        for(int i=0;i<10;i++){
            nearByItemList.add("Hospital");
        }
        adapterNearByItem.notifyDataSetChanged();
    }

}
