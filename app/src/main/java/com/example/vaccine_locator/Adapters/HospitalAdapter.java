package com.example.vaccine_locator.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaccine_locator.HospitalDetails;
import com.example.vaccine_locator.Models.HospitalModel;
import com.example.vaccine_locator.R;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.viewHolder> {
    Context context;
    ArrayList<HospitalModel> list;

    public HospitalAdapter(Context context, ArrayList<HospitalModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_hospital, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        HospitalModel model = list.get(position);
        holder.hName.setText(model.gethName());
        holder.address.setText(model.gethAddress());
        holder.vaccineName.setText(model.getVaccineName());
        holder.feeType.setText(model.getFeeType());
        holder.dose1.setText("Dose1: " + model.getDose1());
        holder.dose2.setText("Dose2: " + model.getDose2());
        holder.rcvCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HospitalDetails.class);
                intent.putExtra("obj", model);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        TextView hName, address, vaccineName, feeType, dose1, dose2;
        CardView rcvCardView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            hName = itemView.findViewById(R.id.rcv_hName);
            address = itemView.findViewById(R.id.rcv_address);
            vaccineName = itemView.findViewById(R.id.rcv_vName);
            feeType = itemView.findViewById(R.id.rcv_fType);
            dose1 = itemView.findViewById(R.id.rcv_dose1);
            dose2 = itemView.findViewById(R.id.rcv_dose2);
            rcvCardView = itemView.findViewById(R.id.rcv_card);
        }
    }
}
