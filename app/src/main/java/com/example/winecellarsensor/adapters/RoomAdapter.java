package com.example.winecellarsensor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.winecellarsensor.R;
import com.example.winecellarsensor.model.Room;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    public interface OnListItemClickListener {
        void onListItemClick(Room room);
    }

    private List<Room> rooms;
    final private OnListItemClickListener mOnListItemClickListener;
    SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm");

    public RoomAdapter(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.room_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.roomName.setText(rooms.get(position).getRoomName());
        viewHolder.co2.setText(rooms.get(position).getCo2().getValue().toString());
        viewHolder.temperature.setText(rooms.get(position).getTemperature().getValue().toString());
        viewHolder.humidity.setText(rooms.get(position).getHumidity().getValue().toString());
        String lastSync = format.format(new Date());
        viewHolder.lastSync.setText("Last sync: " + lastSync);
    }


    public int getItemCount() {
        if(rooms == null)
        {
            return 0;
        }
        return rooms.size();
    }

    public void setRooms(List<Room> rooms)
    {
        this.rooms = rooms;
        notifyDataSetChanged();
    }


    public String getRoomAt(int position)
    {

        return rooms.get(position).getRoomName();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView roomName, co2, temperature, humidity, lastSync;

        ViewHolder(View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.roomName);
            co2 = itemView.findViewById(R.id.co2);
            temperature = itemView.findViewById(R.id.temperature);
            humidity = itemView.findViewById(R.id.humidity);
            lastSync = itemView.findViewById(R.id.lastSync);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           mOnListItemClickListener.onListItemClick(rooms.get(getAdapterPosition()));

        }
    }
}
