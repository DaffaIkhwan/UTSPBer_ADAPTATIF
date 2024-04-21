package com.example.utspweb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<Employee> emplist;

    public Adapter(ArrayList<Employee> emplist) {
        this.emplist = emplist;
    }

    // This method creates a new ViewHolder object for each item in the RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item and return a new ViewHolder object
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list, parent, false);
        return new MyViewHolder(itemView);
    }

    // This method returns the total
    // number of items in the data set
    @Override
    public int getItemCount() {
        return emplist.size();
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Employee currentEmp = emplist.get(position);
        holder.Nama.setText(currentEmp.getNama());
        holder.NIDN.setText(currentEmp.getNIDN());
        holder.Gender.setText(currentEmp.getGender());
        holder.Keahlian.setText(currentEmp.getKeahlian());

        Glide.with(holder.itemView.getContext())
                .load(currentEmp.getFoto()) // Assuming getPhotoUrl() returns the URL of the photo
                .placeholder(R.drawable.user3) // Placeholder image while loading
                .error(R.drawable.user3) // Error image if loading fails
                .into(holder.foto);
    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Nama;
        private TextView NIDN;

        private TextView Gender;

        private TextView Keahlian;

        private ImageView foto;


        public MyViewHolder(View itemView) {
            super(itemView);
            Nama = itemView.findViewById(R.id.tvNama);
            NIDN = itemView.findViewById(R.id.tvNIDN);
            Gender = itemView.findViewById(R.id.tvGender);
            Keahlian = itemView.findViewById(R.id.tvKeahlian);
            foto =  itemView.findViewById(R.id.imageView9);
        }
    }
}

