package com.example.catpedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatDetailActivity extends AppCompatActivity {


    private TextView catName;
    private TextView catDescription;
    private TextView catOrigin;
    private TextView catWeight;
    private TextView catTemp;
    private TextView lifespan;
    private ImageView catImage;
    private Button fave;
    private Button wiki;
    private TextView catFriendly;

    public static ArrayList<CatPhoto> dList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        catName = findViewById(R.id.CatName);
        catDescription = findViewById(R.id.CatDescription);
        catOrigin = findViewById(R.id.CatOrigin);
        catWeight = findViewById(R.id.CatWeight);
        catTemp = findViewById(R.id.CatTemp);
        lifespan = findViewById(R.id.CatLifeSpan);
        catImage = findViewById(R.id.CatImage);
        catFriendly = findViewById(R.id.DogFriend);
        fave = findViewById(R.id.fave);
        wiki = findViewById(R.id.wiki);

        Intent intent = getIntent();
        final String breedID = intent.getStringExtra("BreedID");
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url ="https://api.thecatapi.com/v1/images/search?breed_id=" + breedID;

        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Gson gson = new Gson();
                CatPhoto[] photoresponse = gson.fromJson(response,CatPhoto[].class);
                final List<CatPhoto> loadedCat = Arrays.asList(photoresponse);

                String imageUrl = loadedCat.get(0).getUrl();
                String imperial = loadedCat.get(0).getBreeds().get(0).getWeight().getImperial();
                catWeight.setText(imperial + "Kgs");
                catTemp.setText(String.valueOf(loadedCat.get(0).getBreeds().get(0).getDog_friendly()));
                catName.setText(loadedCat.get(0).getBreeds().get(0).getName());
                catDescription.setText(loadedCat.get(0).getBreeds().get(0).getDescription());
                catOrigin.setText(loadedCat.get(0).getBreeds().get(0).getOrigin());
                catTemp.setText(loadedCat.get(0).getBreeds().get(0).getTemperament());
                lifespan.setText(loadedCat.get(0).getBreeds().get(0).getLife_span() + "Years");

                if(imageUrl != null){
                    Glide.with(getApplicationContext()).load(imageUrl).into(catImage);
                }

                wiki.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String url = loadedCat.get(0).getBreeds().get(0).getWiki_url();
                        Intent newIntent = new Intent(Intent.ACTION_VIEW);
                        newIntent.setData(Uri.parse(url));
                        startActivity(newIntent);
                    }

                });
                fave.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dList.add(loadedCat.get(0));
                        String name = loadedCat.get(0).getBreeds().get(0).getName();

                        Toast.makeText(getApplicationContext(), "Added to favourites!",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }


}
