package com.trustinno.win.jobagtrustinno.Employer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trustinno.win.jobagtrustinno.R;

import java.util.ArrayList;

/**
 * Created by zarni on 1/17/17.
 */

public class Employercv_dataAdapter extends RecyclerView.Adapter<Employercv_dataAdapter.ViewHolder> {

    private ArrayList<String> countries;

    public Employercv_dataAdapter(ArrayList<String> countries) {
        this.countries = countries;
    }

    @Override
    public Employercv_dataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employercv_role, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvcountry.setText(countries.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void addItem(String country) {
        countries.add(country);
        notifyItemInserted(countries.size());
    }

    public void removeItem(int position) {
        countries.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, countries.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvcountry;

        public ViewHolder(View view) {
            super(view);

            tvcountry = (TextView) view.findViewById(R.id.tv_country);
        }
    }


}











