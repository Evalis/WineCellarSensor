package com.example.winecellarsensor.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.winecellarsensor.R;
import com.example.winecellarsensor.model.Cellar;
import com.example.winecellarsensor.model.Room;
import androidx.recyclerview.widget.RecyclerView;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    public interface OnListItemClickListener {
        void onListItemClick(Room room);
    }

    private Cellar cellar;
    final private OnListItemClickListener mOnListItemClickListener;

    public RoomAdapter(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.room_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.roomName.setText(cellar.getRooms().get(position).getRoomName());
        viewHolder.co2.setText(cellar.getRooms().get(position).getMeasurements().getCo2().get(position).getValue().toString());
        viewHolder.temperature.setText(cellar.getRooms().get(position).getMeasurements().getTemperature().get(position).getValue().toString());
        viewHolder.humidity.setText(cellar.getRooms().get(position).getMeasurements().getHumidity().get(position).getValue().toString());
    }

    public int getItemCount() {
        if(cellar == null)
        {
            return 0;
        }
        return cellar.getRooms().size();
    }

    public void setCellar(Cellar cellar)
    {
        this.cellar = cellar;
        notifyDataSetChanged();
    }

    public Room getRoomAt(int position)
    {
        return cellar.getRooms().get(position);
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
           mOnListItemClickListener.onListItemClick(cellar.getRooms().get(getAdapterPosition()));
        }
    }
}
