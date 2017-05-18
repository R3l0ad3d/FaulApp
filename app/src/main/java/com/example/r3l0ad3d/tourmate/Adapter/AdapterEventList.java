package com.example.r3l0ad3d.tourmate.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.r3l0ad3d.tourmate.Fragments.EventListItemShowFragment;
import com.example.r3l0ad3d.tourmate.ModelClass.Event;
import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.databinding.EventListItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r3l0ad3d on 5/14/17.
 */

public class AdapterEventList extends RecyclerView.Adapter<AdapterEventList.ViewHolder> {

    private Context context;
    private List<Event> eventList = new ArrayList<>();

    public AdapterEventList(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public AdapterEventList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.event_list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterEventList.ViewHolder holder, int position) {

        holder.binding.tvEventPlaceEL.setText(eventList.get(position).getEventPlace());
        holder.binding.tvToDateEL.setText(eventList.get(position).getToDate());
        holder.binding.tvFromDateEL.setText(eventList.get(position).getFromDate());
        holder.binding.tvEstimatedBudgetEL.setText(eventList.get(position).getEstimatedBudget());
        setItemClick(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    private void setItemClick(View view,final int pos){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"On Click "+pos,Toast.LENGTH_LONG).show();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle bundle = new Bundle();
                bundle.putSerializable("event",eventList.get(pos));
                Fragment fragment = new EventListItemShowFragment();
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.replaceLayout,fragment).addToBackStack(null).commit();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EventListItemBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
