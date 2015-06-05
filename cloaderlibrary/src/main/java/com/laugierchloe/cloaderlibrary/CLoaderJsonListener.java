package com.laugierchloe.cloaderlibrary;


import org.json.JSONObject;

/**
 * Created by chloelaugier on 05/06/15.
 */
public interface CLoaderJsonListener {


    void jsonLoaded(JSONObject json);
    void jsonNotLoaded(String error);

}
