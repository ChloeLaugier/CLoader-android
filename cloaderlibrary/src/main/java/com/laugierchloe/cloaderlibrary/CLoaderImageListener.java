package com.laugierchloe.cloaderlibrary;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

/**
 * Created by chloelaugier on 05/06/15.
 */
public interface CLoaderImageListener {


    void imageLoaded(Bitmap bitmap);
    void imageNotLoaded(String error);
}
