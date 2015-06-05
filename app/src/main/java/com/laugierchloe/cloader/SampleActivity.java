package com.laugierchloe.cloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.laugierchloe.cloaderlibrary.CLoader;
import com.laugierchloe.cloaderlibrary.CLoaderImageListener;
import com.laugierchloe.cloaderlibrary.CLoaderJsonListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SampleActivity extends ActionBarActivity {



    ListView list;
    SampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        list=(ListView)findViewById(R.id.listView);
        adapter=new SampleAdapter(this, new ArrayList<String>());
        list.setAdapter(adapter);


        final Context ctx = this;

        CLoader.loadJson("https://www.zalora.com.my/mobile-api/women/clothing/", this, new CLoaderJsonListener() {
            @Override
            public void jsonLoaded(JSONObject json) {
                try {
                    JSONObject obj = json.getJSONObject("metadata");
                    JSONArray results = obj.getJSONArray("results");
                    for (int i=0; i< results.length(); i++){
                        JSONObject product = results.getJSONObject(i);
                        JSONArray images = product.getJSONArray("images");
                        for (int j=0; j<images.length(); j++) {

                            JSONObject image = images.getJSONObject(j);
                            String imagePath = image.getString("path");


                            adapter.add(imagePath);



                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void jsonNotLoaded(String error) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample, menu);
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
