package com.laugierchloe.cloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.laugierchloe.cloaderlibrary.CLoader;

import java.util.ArrayList;

/**
 * Created by chloelaugier on 05/06/15.
 */
public class SampleAdapter  extends BaseAdapter {


    private ArrayList<String> data;
    private static LayoutInflater inflater=null;
    private Context context;


    public SampleAdapter(Context ctx, ArrayList<String> imagesPath) {

        this.data=imagesPath;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = context;
    }

    public void add(String path) {
        data.add(path);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.cell_image_sample, null);

        ImageView image=(ImageView)vi.findViewById(R.id.img_cell);
        CLoader.displayImage(data.get(position), context, image, true);
        return vi;
    }
}
