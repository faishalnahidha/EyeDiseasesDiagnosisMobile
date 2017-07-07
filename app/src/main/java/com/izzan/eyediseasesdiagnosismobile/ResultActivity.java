package com.izzan.eyediseasesdiagnosismobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.izzan.eyediseasesdiagnosismobile.models.Disease;
import com.izzan.eyediseasesdiagnosismobile.models.Seriousness;
import com.izzan.eyediseasesdiagnosismobile.others.DiseaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private int[] inputData;
    private CorrelationMeasurement cm = new CorrelationMeasurement();

    private RecyclerView mRecyclerView;
    private DiseaseRecyclerViewAdapter mRecyclerViewAdapter;

    public TextView textViewSeriuousness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //add to activity you want to pull variables from
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.inputData = extras.getIntArray("INPUT_DATA");
        }

        int[] result1 = cm.compute1(inputData);
        int highestResult1 = cm.getHighestResult(result1);
        List<Long> indexResults = new ArrayList<>();
        indexResults.addAll(cm.getHighestResult1Index(result1));

        List<Disease> diseasesResult = new ArrayList<>();

        for (long id : indexResults) {
            Disease mDisease = Disease.getById(id + 1);
            mDisease.setScore(highestResult1);
            diseasesResult.add(mDisease);
        }

        int[] result2 = cm.compute2(result1);
        int highestResult2 = cm.getHighestResult(result2);
        long highestResult2index = cm.getHighestResult2Index(result2);

        Log.i("SERIOUSNESS_SCORE", String.valueOf(highestResult2));
        Log.i("SERIOUSNESS_INDEX", String.valueOf(highestResult2index));

        Seriousness mSeriousness = Seriousness.getById(highestResult2index + 1);
        Log.i("SERIOUSNESS_LEVEL", mSeriousness.getLevel());

        textViewSeriuousness = (TextView) findViewById(R.id.result_textViewSeriousness);
        textViewSeriuousness.setText(mSeriousness.getLevel() + " (" + highestResult2 + ")");

        mRecyclerViewAdapter = new DiseaseRecyclerViewAdapter(diseasesResult, getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.result_recyclerView);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
