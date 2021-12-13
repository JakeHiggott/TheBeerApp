package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

//Source 1: This youtube video shows how to use okhttp and it is a library I am using to handle the API
//https://www.youtube.com/watch?v=oGWJ8xD2W6k
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;



public class SearchForBrewery extends AppCompatActivity {
    String apiName = "";
    Boolean fakeSemaphore = false;

    ArrayList<String> NamesArray = new ArrayList<>();
    ArrayList<String> BreweryTypeArray = new ArrayList<>();
    ArrayList<String> BreweryAddressArray = new ArrayList<>();
    ArrayList<String> BreweryCityArray = new ArrayList<>();
    ArrayList<String> BreweryStateArray = new ArrayList<>();
    ArrayList<String> BreweryPhoneArray = new ArrayList<>();
    ArrayList<String> BreweryWebsiteArray = new ArrayList<>();
    ArrayList<String> BreweryLongArray = new ArrayList<>();
    ArrayList<String> BreweryLatArray = new ArrayList<>();


    @Override



    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_brewery);

        brewSearchButton();
        findBrewBackButton();










    }
    private void findBrewBackButton() {
        Button displayButton = findViewById(R.id.findBrewBackButton);
        displayButton.setOnClickListener(view -> finish());
    }
    private void brewSearchButton() {
        fakeSemaphore = false;
        Button displayButton = findViewById(R.id.brewSearchButton);
        displayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                apiName = "";
                EditText zipCodeRaw = findViewById(R.id.EnterZipCodeText);
                String zipCodeString = zipCodeRaw.getText().toString();



                apiCallZipCode(zipCodeString);
                while(fakeSemaphore == false){
                    //Log.d("NAMES ARRAY ", String.valueOf(NamesArray.size()));

                }
                if(NamesArray.size() == 0){
                    Log.d("ERROR ", "No values returned from api");

                }else{

                    //opens the recycle vc and passes in the array's it needs
                    Log.d("NAMES ARRAY 2", String.valueOf(NamesArray.size()));
                    Intent intent = new Intent(SearchForBrewery.this, SearchResults.class);
                    intent.putExtra("NamesArray",NamesArray);
                    intent.putExtra("BreweryTypeArray",BreweryTypeArray);
                    intent.putExtra("BreweryAddressArray",BreweryAddressArray);
                    intent.putExtra("BreweryCityArray",BreweryCityArray);
                    intent.putExtra("BreweryStateArray",BreweryStateArray);
                    intent.putExtra("BreweryWebsiteArray",BreweryWebsiteArray);
                    intent.putExtra("BreweryPhoneArray",BreweryPhoneArray);
                    intent.putExtra("BreweryLongArray",BreweryLongArray);
                    intent.putExtra("BreweryLatArray",BreweryLatArray);

                    startActivity(intent);

                }


               /* int i = 0;
                while(i == 0){
                    if(NamesArray.size() == 0 ){
                        //TextView apiTestTextView = findViewById(R.id.apitTestTextView);
                        //apiTestTextView.setText("No Results found");


                    }else{
                        String allTheNames = "";
                        int j = 0;
                        while(j < NamesArray.size()){
                            allTheNames = allTheNames +" "+ NamesArray.get(j)+",";
                            j = j + 1;
                        }
                        //TextView apiTestTextView = findViewById(R.id.apitTestTextView);
                        //apiTestTextView.setText("");
                        //apiTestTextView.setText(allTheNames);
                        i = 1;
                    }


                }*/






            }
    });}








    public void apiCallZipCode(String zipcode){

        fakeSemaphore = false;
        NamesArray.clear();
        BreweryTypeArray.clear();
        BreweryAddressArray.clear();
        BreweryCityArray.clear();
        BreweryStateArray.clear();
        BreweryLatArray.clear();
        BreweryLongArray.clear();
        BreweryPhoneArray.clear();
        BreweryWebsiteArray.clear();

        OkHttpClient client = new OkHttpClient();

        String url = "https://api.openbrewerydb.org/breweries?by_postal=";
        Request request = new Request.Builder()
                .url(url+zipcode)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse = response.body().string();



                    try {
                        Log.d("apitest",myResponse);
                        JSONArray jsonarray = new JSONArray(myResponse);
                        int i=0;
                        String name = "";
                        if(jsonarray.length()==0){
                            fakeSemaphore = true;

                        }else {

                            while (i < jsonarray.length()) {
                                NamesArray.add(jsonarray.getJSONObject(i).getString("name"));
                                BreweryTypeArray.add(jsonarray.getJSONObject(i).getString("brewery_type"));
                                BreweryAddressArray.add(jsonarray.getJSONObject(i).getString("street"));
                                BreweryCityArray.add(jsonarray.getJSONObject(i).getString("city"));
                                BreweryStateArray.add(jsonarray.getJSONObject(i).getString("state"));
                                BreweryPhoneArray.add(jsonarray.getJSONObject(i).getString("phone"));
                                BreweryWebsiteArray.add(jsonarray.getJSONObject(i).getString("website_url"));
                                BreweryLongArray.add(jsonarray.getJSONObject(i).getString("longitude"));
                                BreweryLatArray.add(jsonarray.getJSONObject(i).getString("latitude"));
                                i = i + 1;
                            }
                            fakeSemaphore=true;
                        }

                        Log.d("Amount of results from api call: ", String.valueOf(jsonarray.length()));



                    } catch (JSONException e) {
                        Log.d("apitest","Catch clause occurred");
                        e.printStackTrace();
                    }


                }
            }
        });
        //fakeSemaphore = true;

    }



}