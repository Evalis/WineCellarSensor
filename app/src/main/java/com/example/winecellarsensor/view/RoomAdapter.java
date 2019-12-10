package com.example.winecellarsensor.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.winecellarsensor.R;
import com.example.winecellarsensor.model.Sensor;
import com.example.winecellarsensor.model.WineCellar;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    public interface OnListItemClickListener {
        void onListItemClick(String room);
    }

    private WineCellar cellar;
    private List<String> rooms;
    final private OnListItemClickListener mOnListItemClickListener;

    public RoomAdapter(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
        rooms = new ArrayList<String>();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.room_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.roomName.setText(rooms.get(position));
        viewHolder.co2.setText(getMeasurementValue(rooms.get(position),"CO2"));
       viewHolder.temperature.setText(getMeasurementValue(rooms.get(position),"Temperature"));
        viewHolder.humidity.setText(getMeasurementValue(rooms.get(position),"Humidity"));
    }

    private String getMeasurementValue(String roomLocation, String type)
    {
        String measurement = "";

        List<Sensor> sensorList = this.cellar.getSensorList();
        for (Sensor sensor: sensorList) {
              if (sensor.getRoomLocation().equals(roomLocation) && sensor.getSensorname().equals(type))
              {
                  measurement = sensor.getListOfMeasure().get(0).getDataValue();
              }
        }
        return measurement;

    }


    public int getItemCount() {
        if(rooms == null)
        {
            return 0;
        }
        return rooms.size();
    }

    public void setCellar(WineCellar cellar)
    {
        this.cellar = cellar;
        // extract rooms for cellar
        extractRooms();
        notifyDataSetChanged();
    }

    private void extractRooms()
    {
        List<Sensor> sensorList = this.cellar.getSensorList();
        for (Sensor sensor: sensorList) {
            String roomLocation =sensor.getRoomLocation();
            if(!rooms.contains(roomLocation))
            {
                rooms.add(roomLocation);
            }
        }
    }

    public String getRoomAt(int position)
    {
        return rooms.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView roomName, co2, temperature, humidity;

        ViewHolder(View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.roomName);
            co2 = itemView.findViewById(R.id.co2);
            temperature = itemView.findViewById(R.id.temperature);
            humidity = itemView.findViewById(R.id.humidity);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           mOnListItemClickListener.onListItemClick(rooms.get(getAdapterPosition()));

        }
    }
}
