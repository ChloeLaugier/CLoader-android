package com.laugierchloe.cloaderlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by chloelaugier on 05/06/15.
 */
public class CLoader {

    static public void loadImage(String url, Context context, final CLoaderImageListener listener ) {

        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        listener.imageLoaded(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        listener.imageNotLoaded(error.getMessage());
                    }
                });
        RequestQueueSingleton.getInstance(context).addToRequestQueue(request);



    }

    static public void displayImage(String url, Context context, final ImageView imageView, final boolean animation) {
        CLoader.loadImage(url, context, new CLoaderImageListener() {
            @Override
            public void imageLoaded(Bitmap bitmap) {

                if (imageView != null && bitmap!=null) {
                    //imageView.setAlpha(0);
                    if (imageView.getDrawable() == null && animation) {
                        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
                        animation1.setDuration(1000);
                        animation1.setFillAfter(true);
                        imageView.startAnimation(animation1);
                    }
                    imageView.setImageBitmap(bitmap);


                }

            }

            @Override
            public void imageNotLoaded(String error) {

            }
        });


    }


    static public void loadJson(String url, Context context, final CLoaderJsonListener listener) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTxtDisplay.setText("Response: " + response.toString());
                        listener.jsonLoaded(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.jsonNotLoaded(error.getMessage());

                    }
                });

        RequestQueueSingleton.getInstance(context).addToRequestQueue(jsObjRequest);

    }


    static public void cancelRequestsWithTag(Context context, String tag) {

        if (RequestQueueSingleton.getInstance(context) != null) {
            RequestQueue rq = RequestQueueSingleton.getInstance(context).getRequestQueue();
            rq.cancelAll(tag);
        }
    }






}
