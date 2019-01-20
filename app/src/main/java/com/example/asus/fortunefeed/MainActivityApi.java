package com.example.asus.fortunefeed;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.asus.fortunefeed.adapter.CustomAdapter;
import com.example.asus.fortunefeed.model.Articles;
import com.example.asus.fortunefeed.model.News;
import com.example.asus.fortunefeed.network.GetDataService;
import com.example.asus.fortunefeed.network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityApi extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    private ProgressBar loader;
    private ShareActionProvider mShareActionProvider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_api);

        progressDialog = new ProgressDialog(MainActivityApi.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<Articles> call = service.getArticlesData();

        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                progressDialog.dismiss();
                generateArticles(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Toast.makeText(MainActivityApi.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void generateArticles(ArrayList<News> newsArrayList) {
        recyclerView = findViewById(R.id.news_list);
        adapter = new CustomAdapter(this, newsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivityApi.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        /*recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(MainActivityApi.this, DetailsActivity.class);
                i.putExtra("url", dataList.get(+position).get(KEY_URL));
                startActivity(i);
            }
        });*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}