package com.izzan.eyediseasesdiagnosismobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
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

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    public ColorGenerator mGenerator = ColorGenerator.MATERIAL;

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

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

        final FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScrollingActivity.this, ResultActivity.class);
                intent.putExtra("INPUT_DATA", getInputDataArray());
                startActivity(intent);
            }
        });

        reloadList();
        Log.i("SYMPTOMS_SIZE", String.valueOf(mSymptomList.size()));
    }

    private void reloadList() {
        List<Symptom> list = Symptom.getAll();
        mSymptomList.clear();
        mSymptomList.addAll(list);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    private int[] getInputDataArray() {

        int[] inputData = new int[27];
        int i = 0;

        for (Symptom mSymptom : mSymptomList) {
            inputData[i] = mSymptom.getSymptomArise();
            i++;
        }

        return inputData;
    }

    private void showAboutDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //set title dialog
        alertDialogBuilder.setTitle(R.string.action_about);

        //set pesan dari dialog
        alertDialogBuilder
                .setMessage(getString(R.string.text_app_version) +
                        "\nCopyright \u00a9 2017 by Faishal Nahidha"
                        + "\nPoliteknik Elektronika Negeri Surabaya"
                        + "\n\nPlease visit : " +
                        "\n\t faishalnahidha.890.com" +
                        "\n\t izzan.carbonmade.com " +
                        "\n\t www.persona.my.id")
                .setIcon(R.mipmap.ic_pens)
                .setCancelable(false)
                .setPositiveButton(R.string.button_thanks, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        //membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        //menampilkan alert dialog
        alertDialog.show();
    }

    @Override
    public void onClick(View view, int position) {
        if (position > -1) {
            Log.d("LIST_POSITION", String.valueOf(position));
            Symptom mSymptom = mSymptomList.get(position);

            SymptomRecyclerViewAdapter.MyViewHolder itemView =
                    (SymptomRecyclerViewAdapter.MyViewHolder) mRecyclerView.findViewHolderForLayoutPosition(position);

            TextDrawable originalDrawable = (TextDrawable) itemView.icon.getDrawable();

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
        if (id == R.id.action_about) {
            showAboutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(mCoordinatorLayout, "Please click BACK again to exit", Snackbar.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }

}
