package com.laugierchloe.cloaderlibrary;

import android.content.Context;
import android.graphics.Bitmap;
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



    /**
     * Load image <br />
     *
     * @param url        Image URL (i.e. "http://site.com/image.png")
     * @param context    context
     * @param listener    {@linkplain CLoaderImageListener Listener} for image loading.
     * @return request you can use if you want to cancel
     */
    static public CImageRequest loadImage(String url, Context context, final CLoaderImageListener listener ) {

        CImageRequest request = new CImageRequest(url,
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
        return request;



    }


    /**
     * Load image with tag<br />
     *
     * @param url        Image URL (i.e. "http://site.com/image.png")
     * @param context    context
     * @param listener    {@linkplain CLoaderImageListener Listener} for image loading.
     * @param tag     to cancel multiple request you can use tag
     * @return request you can use if you want to cancel
     */
    static public CImageRequest loadImage(String url, Context context, final CLoaderImageListener listener, String tag ) {

        CImageRequest request = CLoader.loadImage(url, context, listener);
        request.setTag(tag);
        return request;



    }

    /**
     * Loads and display image in ImageView provided.<br />
     *
     * @param url        Image URL (i.e. "http://site.com/image.png")
     * @param context    context
     * @param imageView   imageView to contain image
     * @param animation   do you want fading animation
     * @return request you can use if you want to cancel
     */
    static public CImageRequest displayImage(String url, Context context, final ImageView imageView, final boolean animation) {
        CImageRequest r = CLoader.loadImage(url, context, new CLoaderImageListener() {
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
        return r;


    }

    /**
     * Loads and display image in ImageView provided with tag.<br />
     *
     * @param url        Image URL (i.e. "http://site.com/image.png")
     * @param context    context
     * @param imageView   imageView to contain image
     * @param animation   do you want fading animation
     * @return request you can use if you want to cancel
     */
    static public CImageRequest displayImage(String url, Context context, final ImageView imageView, final boolean animation, String tag) {
        CImageRequest request = CLoader.displayImage(url, context, imageView, animation);
        request.setTag(tag);
        return request;
    }


    /**
     * Load json <br />
     *
     * @param url        json URL (i.e. "http://site.com/json.json")
     * @param context    context
     * @param listener    {@linkplain CLoaderJsonListener Listener} for json loading.
     */
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
