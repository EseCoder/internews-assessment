package com.internews.assmt;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.internews.assmt.adapter.SuperHeroAdapter;
import com.internews.assmt.model.SuperHero;
import com.internews.assmt.network.DataManager;
import com.internews.assmt.network.WebServiceCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView supsList;
    private SuperHeroAdapter adapter;
    private ProgressBar progressBar;
    private Button retry;
    private List<SuperHero> superHeroes;
    private List<SuperHero> backupList = new ArrayList<>();

    private WebServiceCallback<JSONObject> callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);

        retry = findViewById(R.id.retry);
        retry.setOnClickListener(this);

        supsList = findViewById(R.id.sups_list);

        callback = new WebServiceCallback<JSONObject>() {
            @Override
            public void onDataReturned(JSONObject model, int resCode, ResponseBody errorBody) {
                progressBar.setVisibility(View.GONE);

                try {
                    if (resCode == 200) {
                        superHeroes = DataManager.persistSups(model);

                        if (superHeroes != null) {
                            if (superHeroes.isEmpty()) {
                                supsList.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, getString(R.string.no_sups), Toast.LENGTH_LONG).show();
                                retry.setVisibility(View.VISIBLE);
                            } else {
                                backupList.addAll(superHeroes);
                                supsList.setVisibility(View.VISIBLE);
                                adapter = new SuperHeroAdapter(MainActivity.this, superHeroes);
                                supsList.setAdapter(adapter);
                            }
                        } else {
                            retry.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.e(TAG, errorBody.string());
                        Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                        retry.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                    retry.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onError(Throwable t) {
                progressBar.setVisibility(View.GONE);
                supsList.setVisibility(View.GONE);
                retry.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();


                t.printStackTrace();
            }

            @Override
            public void onNetworkUnavailable() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                supsList.setVisibility(View.GONE);
                retry.setVisibility(View.VISIBLE);
            }

            @Override
            public void onProgress() {
                progressBar.setVisibility(View.VISIBLE);
                supsList.setVisibility(View.GONE);
                retry.setVisibility(View.GONE);
            }
        };

        //fetch data
        DataManager.getSups(this, callback);

        final EditText search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //adapter.getFilter().filter(search.getText().toString());

                if (!search.getText().toString().equals("")) {
                    List<SuperHero> filteredList = new ArrayList<>();
                    for (SuperHero superHero : superHeroes) {
                        if (!superHero.getName().toLowerCase().startsWith(search.getText().toString().toLowerCase())) {
                            filteredList.add(superHero);
                        }
                    }
                    superHeroes.removeAll(filteredList);
                    adapter.notifyDataSetChanged();
                } else {
                    superHeroes.clear();
                    superHeroes.addAll(backupList);
                    adapter.notifyDataSetChanged();
                    //DataManager.getSups(MainActivity.this, callback);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        retry.setVisibility(View.GONE);
        DataManager.cancelRequest(callback);
        DataManager.getSups(this, callback);
    }
}