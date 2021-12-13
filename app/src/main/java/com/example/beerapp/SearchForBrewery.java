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

                //TextView tipAmountDisplay = findViewById(R.id.tipAmountText);

                apiCallZipCode(zipCodeString);
                while(fakeSemaphore == false){

                }

                int i = 0;
                while(i == 0){
                    if(NamesArray.size() == 0){
                        TextView apiTestTextView = findViewById(R.id.apitTestTextView);
                        apiTestTextView.setText("No Results found");


                    }else{
                        String allTheNames = "";
                        int j = 0;
                        while(j < NamesArray.size()){
                            allTheNames = allTheNames +" "+ NamesArray.get(j)+",";
                            j = j + 1;
                        }
                        TextView apiTestTextView = findViewById(R.id.apitTestTextView);
                        apiTestTextView.setText("");
                        apiTestTextView.setText(allTheNames);
                        i = 1;
                    }

                }
                Intent intent = new Intent(SearchForBrewery.this, SearchResults.class);
                intent.putExtra("NamesArray",NamesArray);
                startActivity(intent);



            }
    });}



    //public static SearchForBrewery getZipCodes(){
        //return NamesArray;
    //}
    //API STUFF




    public void apiCallZipCode(String zipcode){
        NamesArray.clear();
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
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
                        while (i < jsonarray.length()) {
                            NamesArray.add(jsonarray.getJSONObject(i).getString("name")) ;
                            i = i + 1;
                        }

                        Log.d("Amount of results from api call: ", String.valueOf(jsonarray.length()));



                    } catch (JSONException e) {
                        Log.d("apitest","Catch clause occurred");
                        e.printStackTrace();
                    }




                    /*SearchForBrewery.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                           TextView apiTestTextView = findViewById(R.id.apitTestTextView);
                            apiTestTextView.setText(myResponse);
                        }
                    });*/


                }
            }
        });
    fakeSemaphore = true;
    }



}