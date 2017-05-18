package com.example.r3l0ad3d.tourmate.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.r3l0ad3d.tourmate.ModelClass.ShareMoment;
import com.example.r3l0ad3d.tourmate.R;;

import java.util.List;

/**
 * Created by r3l0ad3d on 5/2/17.
 */

public class AdapterShareMoment extends RecyclerView.Adapter<AdapterShareMoment.ViewHolder> {

    private Context context;
    private List<ShareMoment> shareMomentList ;

    public AdapterShareMoment(Context context, List<ShareMoment> shareMomentList) {
        this.context = context;
        this.shareMomentList = shareMomentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.share_moment_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvUserName.setText(shareMomentList.get(position).getUserName());
        holder.tvDiscription.setText(shareMomentList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return shareMomentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private TextView tvDiscription;
        private ImageView ivShareImage;
        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserNameShareMomentItem);
            tvDiscription = (TextView) itemView.findViewById(R.id.tvDescriptionShareMomentItem);
            ivShareImage = (ImageView) itemView.findViewById(R.id.ivImageShareMomentItem);
        }
    }
}
