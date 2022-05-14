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

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

    Context context;
    String[] expositors, tipologia, nStand;

    public Adaptador(Context context, String[] expositors, String[] tipologia, String[] nStand) {
        this.context = context;
        this.expositors = expositors;
        this.tipologia = tipologia;
        this.nStand = nStand;
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
        holder.expositorsTxt.setText(expositors[position]);
        holder.tipologiaTxt.setText(tipologia[position]);
        holder.nStandTxt.setText(nStand[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("expositor",expositors[holder.getBindingAdapterPosition()]);
                intent.putExtra("tipologia",tipologia[holder.getBindingAdapterPosition()]);
                intent.putExtra("nStand",nStand[holder.getBindingAdapterPosition()]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expositors.length;
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
