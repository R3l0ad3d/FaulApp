package com.example.r3l0ad3d.tourmate.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3l0ad3d.tourmate.ModelClass.Expences;
import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.databinding.ExpanceItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NiL-AkAsH on 5/18/2017.
 */

public class AdapterExpance extends RecyclerView.Adapter<AdapterExpance.ViewHolder>{

    private Context context;
    private List<Expences> expencesList = new ArrayList<>();

    public AdapterExpance(Context context, List<Expences> expencesList) {
        this.context = context;
        this.expencesList = expencesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.expance_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.tvExpanceDateLISEI.setText(expencesList.get(position).getExpanceDate());
        holder.binding.tvExpanceResonLISEI.setText(expencesList.get(position).getExpenceResone());
        holder.binding.tvExpanceAmountLISEI.setText(expencesList.get(position).getExpenceAmount());
    }

    @Override
    public int getItemCount() {
        return expencesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ExpanceItemBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
