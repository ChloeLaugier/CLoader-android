package com.laugierchloe.cloaderlibrary;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

/**
 * Created by chloelaugier on 06/06/15.
 */
public class CImageRequest extends ImageRequest {

    public CImageRequest(String url, Response.Listener<Bitmap> listener, int maxWidth, int maxHeight, Bitmap.Config decodeConfig, Response.ErrorListener errorListener) {
        super(url, listener, maxWidth, maxHeight, decodeConfig, errorListener);
    }
}
