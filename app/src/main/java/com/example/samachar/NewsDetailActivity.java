package com.example.samachar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
       String title,desc,content,imageUrl,url;
       private ImageView imageView;
       private TextView Title, Desc, Content;
       private Button button = findViewById(R.id.idBload);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageUrl = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

        imageView = findViewById(R.id.idIVnews);
        Title = findViewById(R.id.idTVtitle);
        Desc = findViewById(R.id.idTVdesc);
        Content = findViewById(R.id.idTVsubdesc);
        Title.setText(title);
        Desc.setText(desc);
        Content.setText(content);
        Picasso.get().load(imageUrl).into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}