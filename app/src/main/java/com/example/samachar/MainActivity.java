package com.example.samachar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements com.example.samachar.categoryAdapter.categoryOnClickInterface {
    private ProgressBar loadingPB;
         private ArrayList<Articles>articlesArrayList;
         private ArrayList<categoryModal>categoryModalArrayList;
         private categoryAdapter categoryAdapter;
         private newsAdapter NewsAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView newsRV = findViewById(R.id.idnews);
        RecyclerView categoryRV = findViewById(R.id.idcategories);
        loadingPB = findViewById(R.id.idloading);
        articlesArrayList = new ArrayList<>();
        categoryModalArrayList = new ArrayList<>();

        NewsAdapter = new newsAdapter(articlesArrayList,this);
        categoryAdapter = new categoryAdapter(this, categoryModalArrayList, this);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(NewsAdapter);
        categoryRV.setAdapter(categoryAdapter);
        getcategories();
        getNews("All");
        NewsAdapter.notifyDataSetChanged();
    }
    @SuppressLint("NotifyDataSetChanged")
    private void getcategories(){
        categoryModalArrayList.add(new categoryModal("All"));
        categoryModalArrayList.add(new categoryModal("technology"));
        categoryModalArrayList.add(new categoryModal("science"));
        categoryModalArrayList.add(new categoryModal("sports"));
        categoryModalArrayList.add(new categoryModal("general"));
        categoryModalArrayList.add(new categoryModal("business"));
        categoryModalArrayList.add(new categoryModal("entertainment"));
        categoryModalArrayList.add(new categoryModal("health"));
        categoryAdapter.notifyDataSetChanged();

    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category="+category+"&excludeDomains=stackoverflow.com&sortBy=publishedaAt&language=en&apiKey=e6deefe5ee724ae8a1895d27905466f6";
        String URL = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedaAt&language=en&apiKey=e6deefe5ee724ae8a1895d27905466f6";
        String BASE_URL = "https://newsapi.org";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI RetrofitAPI = retrofit.create(retrofitAPI.class);
        Call<NewsMmodal>call;
        if(category.equals("All")){
              call = RetrofitAPI.getAllNews(URL);

        }
        else {
             call = RetrofitAPI.getAllNews(categoryURL);
        }

        call.enqueue(new Callback<NewsMmodal>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<NewsMmodal> call, @NonNull Response<NewsMmodal> response) {
                if (response.isSuccessful()) {
                    NewsMmodal newsMmodal = response.body();
                    loadingPB.setVisibility(View.GONE);
                    assert newsMmodal != null;
                    try {
                        ArrayList<Articles> articles = newsMmodal.getArticles();
                        for (int i = 0; i < articles.size(); i++) {
                            Articles a = new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(),
                                    articles.get(i).getUrlToImage(), articles.get(i).getUrl(), articles.get(i).getContent());
                            articlesArrayList.add(a);
                        }
                    }
                    catch(NullPointerException e){
                        Toast.makeText(MainActivity.this,"Null pointer exception",Toast.LENGTH_SHORT).show();
                    }

                    NewsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsMmodal> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this,"Failed to Load",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void oncategoryclick(int position) {
        String category = categoryModalArrayList.get(position).getCategory();
        getNews(category);
       // NewsAdapter.notifyDataSetChanged();
    }
}