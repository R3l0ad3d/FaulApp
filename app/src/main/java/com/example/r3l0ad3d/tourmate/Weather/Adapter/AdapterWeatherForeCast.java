package com.example.r3l0ad3d.tourmate.Weather.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.r3l0ad3d.tourmate.R;
import com.example.r3l0ad3d.tourmate.Weather.ModelClass.ForeCastReport;

import java.util.List;

/**
 * Created by r3l0ad3d on 5/1/17.
 */

public class AdapterWeatherForeCast extends RecyclerView.Adapter<AdapterWeatherForeCast.ViewHolder>{

    private Context context;
    private List<ForeCastReport> foreCastReportList;

    public AdapterWeatherForeCast(Context context, List<ForeCastReport> foreCastReportList) {
        this.context = context;
        this.foreCastReportList = foreCastReportList;
    }

    @Override
    public AdapterWeatherForeCast.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.weather_report_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterWeatherForeCast.ViewHolder holder, int position) {
        ForeCastReport report = foreCastReportList.get(position);
        holder.tvDate.setText(report.getDate());
        holder.tvReport.setText(report.getReport());
        holder.tvMaxTemp.setText(report.getMaxTemperature()+"F");
        holder.tvMinTemp.setText(report.getMinTemperature()+"F");

        Uri imgURI = Uri.parse(report.getImageURI());

        Glide.with(context).load(imgURI).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return foreCastReportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate;
        private TextView tvReport;
        private TextView tvMaxTemp;
        private TextView tvMinTemp;
        private ImageView ivImage;
        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tvWeatherDate);
            tvReport = (TextView) itemView.findViewById(R.id.tvWeatherReport);
            tvMaxTemp = (TextView) itemView.findViewById(R.id.tvWeatherTemperatureMax);
            tvMinTemp = (TextView) itemView.findViewById(R.id.tvWeatherTemperatureMin);
            ivImage = (ImageView) itemView.findViewById(R.id.ivWeatherIcon);
        }
    }
}
