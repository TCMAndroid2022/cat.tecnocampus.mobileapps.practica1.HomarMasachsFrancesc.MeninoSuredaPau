package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

    Context context;
    List<Expositor> expositors;
    OnNoteListener onNoteListener;

    public Adaptador(Context context, List expositors, OnNoteListener onNoteListener) {
        this.context = context;
        this.expositors = expositors;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout, parent, false);
        return new MyViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.expositorsTxt.setText(expositors.get(position).getExpositors());
        holder.tipologiaTxt.setText(expositors.get(position).getTipologia());
        holder.nStandTxt.setText(expositors.get(position).getnStand());

        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete contact")
                        .setMessage("Are you sure you want to delete?")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                expositors.remove(holder.getBindingAdapterPosition());
                                notifyItemRemoved(holder.getBindingAdapterPosition());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();

                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return expositors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView expositorsTxt, tipologiaTxt, nStandTxt;
        ConstraintLayout mainLayout;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            expositorsTxt = itemView.findViewById(R.id.expositorsTxt);
            tipologiaTxt = itemView.findViewById(R.id.tipologiaTxt);
            nStandTxt = itemView.findViewById(R.id.nStandTxt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }



}
