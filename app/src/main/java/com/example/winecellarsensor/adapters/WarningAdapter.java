package com.example.winecellarsensor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winecellarsensor.R;
import com.example.winecellarsensor.model.Warning;
import com.example.winecellarsensor.viewModel.CellarViewModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.ViewHolder> {

    private List<Warning> warnings;
    private CellarViewModel cellarViewModel;

    public WarningAdapter(){

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.warning_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.roomName.setText(warnings.get(position).getRoomName());
        holder.dateTimeValue.setText((warnings.get(position).getDate().toString()));
        holder.sensorIdValue.setText(warnings.get(position).getSensorName());
        holder.value.setText(Double.toString(warnings.get(position).getActualValue()));
        holder.minValue.setText(Double.toString(warnings.get(position).getMinValue()));
        holder.maxValue.setText(Double.toString(warnings.get(position).getMaxValue()));
    }

    @Override
    public int getItemCount() {
        if(warnings == null)
        {
            return 0;
        }
        return warnings.size();
    }

    public Warning getWarningAt(int i){
        return warnings.get(i);
    }

    public void setWarnings(List<Warning> warnings)
    {
        this.warnings = warnings;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView roomName, dateTime, dateTimeValue, sensorId, sensorIdValue,
                valueTV, value, minValueTV, minValue, maxValueTV, maxValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.warning_room_name);
            dateTime = itemView.findViewById(R.id.date_time_tv);
            dateTimeValue = itemView.findViewById(R.id.date_time_value);
            sensorId = itemView.findViewById(R.id.sensor_id_tv);
            sensorIdValue = itemView.findViewById(R.id.sensor_id_value);
            valueTV = itemView.findViewById(R.id.value_tv);
            value = itemView.findViewById(R.id.value_value);
            minValueTV = itemView.findViewById(R.id.min_value_tv);
            minValue = itemView.findViewById(R.id.min_value_value);
            maxValueTV = itemView.findViewById(R.id.max_value_tv);
            maxValue = itemView.findViewById(R.id.max_value_value);
        }
    }
}
