package com.izzan.eyediseasesdiagnosismobile.others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.izzan.eyediseasesdiagnosismobile.R;
import com.izzan.eyediseasesdiagnosismobile.models.Disease;
import com.izzan.eyediseasesdiagnosismobile.models.Symptom;

import java.util.List;

/**
 * Created by Aizen on 5 Jul 2017.
 */

public class DiseaseRecyclerViewAdapter
extends RecyclerView.Adapter<DiseaseRecyclerViewAdapter.MyViewHolder>{

    private List<Disease> mDiseaseList;
    private Context mContext;

    public DiseaseRecyclerViewAdapter(List<Disease> mDiseaseList, Context mContext) {
        this.mDiseaseList = mDiseaseList;
        this.mContext = mContext;
    }

    @Override
    public DiseaseRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_disease, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DiseaseRecyclerViewAdapter.MyViewHolder holder, int position) {
        Disease mDisease = mDiseaseList.get(position);

        holder.diseaseName.setText(mDisease.getName());
        holder.score.setText(String.valueOf(mDisease.getScore()));
    }

    @Override
    public int getItemCount() {
        return mDiseaseList == null ? 0 : mDiseaseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView diseaseName, score;

        public MyViewHolder(View itemView) {
            super(itemView);
            diseaseName = (TextView) itemView.findViewById(R.id.cardDisease_textViewDisease);
            score = (TextView) itemView.findViewById(R.id.cardDisease_textViewScore);
        }
    }
}
