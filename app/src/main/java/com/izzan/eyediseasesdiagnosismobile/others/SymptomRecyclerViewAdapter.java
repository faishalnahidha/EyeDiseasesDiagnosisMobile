package com.izzan.eyediseasesdiagnosismobile.others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.izzan.eyediseasesdiagnosismobile.R;
import com.izzan.eyediseasesdiagnosismobile.models.Symptom;

import java.util.List;

/**
 * Created by Aizen on 4 Jul 2017.
 */

public class SymptomRecyclerViewAdapter
        extends RecyclerView.Adapter<SymptomRecyclerViewAdapter.MyViewHolder> {

    private List<Symptom> mSymtomList;
    private Context mContext;
    private OnItemClickListener listener;
    private int position;

    public ColorGenerator mGenerator = ColorGenerator.MATERIAL;

    public SymptomRecyclerViewAdapter(List<Symptom> mSymtomList, Context mContext) {
        this.mSymtomList = mSymtomList;
        this.mContext = mContext;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Symptom getItem(int position){
        return mSymtomList.get(position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public SymptomRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_symptom, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SymptomRecyclerViewAdapter.MyViewHolder holder, int position) {
        Symptom mSymptom = mSymtomList.get(position);
        String number = mSymptom.getId().toString();

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(number, mGenerator.getColor(getItem(position)));
        holder.icon.setImageDrawable(drawable);
        holder.symptomDescription.setText(mSymptom.getName());

        if(mSymptom.getSymptomArise() == 0){
            holder.itemView.setSelected(false);
        } else if (mSymptom.getSymptomArise() == 1){
            holder.itemView.setSelected(true);
        }
    }

    @Override
    public int getItemCount() {
        return mSymtomList == null ? 0 : mSymtomList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView symptomDescription;
        public ImageView icon;

        //private SparseBooleanArray selectedItems = new SparseBooleanArray();

        public MyViewHolder(View itemView) {
            super(itemView);
            symptomDescription = (TextView) itemView.findViewById(R.id.rowSymptom_textViewName);
            icon = (ImageView) itemView.findViewById(R.id.rowSymptom_imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());

/*            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                v.setSelected(false);
            }
            else {
                selectedItems.put(getAdapterPosition(), true);
                v.setSelected(true);
            }*/
        }
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }
}
