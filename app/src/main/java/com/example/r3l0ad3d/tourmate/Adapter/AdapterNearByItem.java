package com.example.r3l0ad3d.tourmate.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.databinding.NearbyItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r3l0ad3d on 5/15/17.
 */

public class AdapterNearByItem extends RecyclerView.Adapter<AdapterNearByItem.ViewHolder> {

    private Context context;
    private List<String> nearByItemList = new ArrayList<>();

    public AdapterNearByItem(Context context, List<String> nearByItemList) {
        this.context = context;
        this.nearByItemList = nearByItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.nearby_item_layout,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"WOrk",Toast.LENGTH_LONG).show();
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.tvNearByItem.setText(nearByItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return nearByItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NearbyItemLayoutBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
