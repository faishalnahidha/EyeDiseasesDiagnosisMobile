package com.izzan.eyediseasesdiagnosismobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.izzan.eyediseasesdiagnosismobile.models.Symptom;
import com.izzan.eyediseasesdiagnosismobile.others.DividerItemDecoration;
import com.izzan.eyediseasesdiagnosismobile.others.SymptomRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity
        implements SymptomRecyclerViewAdapter.OnItemClickListener {

    private List<Symptom> mSymptomList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private SymptomRecyclerViewAdapter mRecyclerViewAdapter;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinationLayout);

        mRecyclerViewAdapter = new SymptomRecyclerViewAdapter(mSymptomList, getApplicationContext());
        mRecyclerViewAdapter.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext()));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(ScrollingActivity.this, ResultActivity.class);
                intent.putExtra("INPUT_DATA", getInputDataArray());
                startActivity(intent);
            }
        });

        reloadList();
        Log.i("SYMPTOMS_SIZE", String.valueOf(mSymptomList.size()));
    }

    private void reloadList(){
        List<Symptom> list = Symptom.getAll();
        mSymptomList.clear();
        mSymptomList.addAll(list);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    private int[] getInputDataArray(){

        int[] inputData = new int[27];
        int i = 0;

        for(Symptom mSymptom : mSymptomList){
            inputData[i] = mSymptom.getSymptomArise();
            i++;
        }

        return inputData;
    }

    @Override
    public void onClick(View view, int position) {
        if (position > -1) {
            Log.d("LIST_POSITION", String.valueOf(position));
            Symptom mSymptom = mSymptomList.get(position);
            // Save the selected positions to the SparseBooleanArray
            if (selectedItems.get(position, false)) {
                //item not selected
                selectedItems.delete(position);
                view.setSelected(false);
                mSymptom.setSymptomArise(0);
            } else {
                //item selected
                selectedItems.put(position, true);
                view.setSelected(true);
                mSymptom.setSymptomArise(1);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
