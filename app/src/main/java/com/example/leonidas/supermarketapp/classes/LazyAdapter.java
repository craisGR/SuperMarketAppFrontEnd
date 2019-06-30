package com.example.leonidas.supermarketapp.classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leonidas.supermarketapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leonidas on 10-Apr-16.
 */
public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, Object>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, ArrayList<HashMap<String, Object>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_item, null);

        TextView productName = (TextView)vi.findViewById(R.id.item_title);
        TextView brand = (TextView)vi.findViewById(R.id.item_brand);
        TextView category = (TextView)vi.findViewById(R.id.item_category);
        TextView price = (TextView)vi.findViewById(R.id.item_price);
        ImageView thumbImage=(ImageView)vi.findViewById(R.id.item_photo);

        HashMap<String, Object> Items = new HashMap<String, Object>();
        Items = data.get(position);

        // Setting all values in listview
        productName.setText((String)Items.get(Datastore.KEY_PRODUCTNAME));
        brand.setText((String) Items.get(Datastore.KEY_BRANDNAMEID));
        category.setText((String) Items.get(Datastore.KEY_CATEGORYID));

        //na metatrepei tin timi se string gia na emfanozetai
        double itemPrice =(double)Items.get(Datastore.KEY_PRICE);
        String pricetext = Double.toString(itemPrice);
        price.setText(pricetext);

        imageLoader.DisplayImage((String)Items.get(Datastore.KEY_COVERURL), thumbImage);
        return vi;
    }


}
