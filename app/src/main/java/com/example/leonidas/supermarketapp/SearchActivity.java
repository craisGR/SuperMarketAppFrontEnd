package com.example.leonidas.supermarketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.leonidas.supermarketapp.classes.Datastore;
import com.example.leonidas.supermarketapp.classes.LazyAdapter;


public class SearchActivity extends AppCompatActivity {

    ListView listViewItems;

    private void findViews() {
        listViewItems = (ListView)findViewById(R.id.listViewItems);
    }

    private void LoadListedItems(){
        Intent intent = getIntent();
        String filterProductName = intent.getStringExtra(Datastore.KEY_PRODUCTNAME);
        int filterBrandId = intent.getIntExtra(Datastore.KEY_BRANDNAMEID,0);
        int filterPriceMax = intent.getIntExtra(Datastore.KEY_PRICEMAX, 0);
        int filterPriceMin = intent.getIntExtra(Datastore.KEY_PRICEMIN, 0);
        int filterCategoryId = intent.getIntExtra(Datastore.KEY_CATEGORYID, 0);

        Datastore.LoadItems(filterProductName,  filterPriceMax, filterPriceMin, filterCategoryId, filterBrandId);

        LazyAdapter ItemAdapter = new LazyAdapter(this, Datastore.Items);

        /*
        ListAdapter ItemAdapter = new SimpleAdapter(
                this,
                Datastore.Items,
                R.layout.list_item,
                new String[]{
                        Datastore.KEY_PRODUCTNAME,
                        Datastore.KEY_CATEGORYID,
                        Datastore.KEY_BRANDNAMEID,
                        Datastore.KEY_PRICE
                },
                new int[]{
                        R.id.item_title,
                        R.id.item_category,
                        R.id.item_brand,
                        R.id.item_price
                }
        );

        */
        listViewItems.setAdapter(ItemAdapter);

        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(SearchActivity.this, DetailsActivity.class);
                detailsIntent.putExtra(Datastore.KEY_POSITION, position);
                startActivity(detailsIntent);
            }
        });
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


            findViews();
            LoadListedItems();


    }
}
