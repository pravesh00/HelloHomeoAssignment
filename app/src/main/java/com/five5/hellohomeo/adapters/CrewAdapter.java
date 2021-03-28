package com.five5.hellohomeo.adapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.five5.hellohomeo.HomeActivity;
import com.five5.hellohomeo.R;
import com.five5.hellohomeo.models.Crew;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {
    List<Crew> crews= new ArrayList<>();
    Context context;
    Application application;
    public CrewAdapter(List<Crew> crews, Application app) {
        this.crews = crews;
        this.application=app;
        this.context=application.getApplicationContext();
    }

    @NonNull
    @Override

    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_item,parent,false);
        return new CrewViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        Crew current= crews.get(position);
        holder.name.setText(current.getName());
        holder.status.setText(current.getStatus());
        holder.agency.setText(current.getAgency());
        holder.wiki.setText(current.getWikipedia());
        Glide.with(context).load(current.getImage()).into(holder.proImage);

    }

    @Override
    public int getItemCount() {
        return crews.size();
    }

    public class CrewViewHolder extends RecyclerView.ViewHolder{
        private ImageView proImage;
        private TextView name,agency,status,wiki;
        public CrewViewHolder(@NonNull View itemView) {
            super(itemView);
            proImage=(ImageView)itemView.findViewById(R.id.imgPro);
            name=(TextView)itemView.findViewById(R.id.txtName);
            agency=(TextView)itemView.findViewById(R.id.txtAgent);
            status=(TextView)itemView.findViewById(R.id.txtStatus);
            wiki=(TextView)itemView.findViewById(R.id.txtWiki);
        }
    }
}
