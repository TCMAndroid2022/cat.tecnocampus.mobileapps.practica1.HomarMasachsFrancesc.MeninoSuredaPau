package com.example.practica1_homarmasachsfrancesc_meninosuredapau;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

    Context context;
    List<Expositor> expositors;

    public Adaptador(Context context, List expositors) {
        this.context = context;
        this.expositors = expositors;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.expositorsTxt.setText(expositors.get(position).getExpositors());
        holder.tipologiaTxt.setText(expositors.get(position).getTipologia());
        holder.nStandTxt.setText(expositors.get(position).getnStand());

        holder.mainLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("expositor",expositors.get(holder.getBindingAdapterPosition()).getExpositors());
                intent.putExtra("tipologia",expositors.get(holder.getBindingAdapterPosition()).getTipologia());
                intent.putExtra("nStand",expositors.get(holder.getBindingAdapterPosition()).getnStand());
                intent.putExtra("telefon",expositors.get(holder.getBindingAdapterPosition()).getTelefon());
                intent.putExtra("nif",expositors.get(holder.getBindingAdapterPosition()).getNif());
                intent.putExtra("coordenades",expositors.get(holder.getBindingAdapterPosition()).getCoordenades());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expositors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView expositorsTxt, tipologiaTxt, nStandTxt;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expositorsTxt = itemView.findViewById(R.id.expositorsTxt);
            tipologiaTxt = itemView.findViewById(R.id.tipologiaTxt);
            nStandTxt = itemView.findViewById(R.id.nStandTxt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
