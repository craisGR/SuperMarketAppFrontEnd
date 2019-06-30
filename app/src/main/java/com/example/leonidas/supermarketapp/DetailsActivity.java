package com.example.leonidas.supermarketapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leonidas.supermarketapp.classes.Datastore;
import com.example.leonidas.supermarketapp.classes.ImageLoader;

import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    Button ButtonBuyIt;
    TextView itemViewTitle;
    TextView itemViewCategory;
    TextView itemViewBrand;
    TextView itemViewPrice;
    TextView itemViewDetails;
    HashMap<String, Object> Item = null;
    ImageView imageViewPhoto;
    ImageLoader imageLoader;


    private void findViews() {
        itemViewTitle = (TextView)findViewById(R.id.item_title);
        itemViewCategory = (TextView)findViewById(R.id.item_category);
        itemViewBrand = (TextView)findViewById(R.id.item_brand);
        itemViewPrice = (TextView)findViewById(R.id.item_price);
        itemViewDetails = (TextView)findViewById(R.id.item_details);
        ButtonBuyIt = (Button)findViewById(R.id.ButtonBuy);
        imageViewPhoto = (ImageView)findViewById(R.id.imageViewPhoto);

    }

    private void buttonn(){
        ButtonBuyIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemSMUrl = (String)Item.get(Datastore.KEY_SMURL);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemSMUrl));
                startActivity(browserIntent);
            }
        });

    }

    private void fillTextView(){
        Intent intent = getIntent();
        int itemposition = intent.getIntExtra(Datastore.KEY_POSITION, 0);
        Item = Datastore.Items.get(itemposition);
        String itemTitle = (String)Item.get(Datastore.KEY_PRODUCTNAME);
        String itemBrand = (String)Item.get(Datastore.KEY_BRANDNAMEID);
        String itemCategory = (String)Item.get(Datastore.KEY_CATEGORYID);
        String itemCoverUrl = (String)Item.get(Datastore.KEY_COVERURL);
        double itemPrice =(double)Item.get(Datastore.KEY_PRICE);
        String itemDetails = (String) Item.get(Datastore.KEY_DETAILS);

        String price = Double.toString(itemPrice);

        itemViewTitle.setText(itemTitle);
        itemViewBrand.setText(itemBrand);
        itemViewCategory.setText(itemCategory);
        itemViewPrice.setText(price);
        itemViewDetails.setText(itemDetails);

        imageLoader = new ImageLoader(getApplicationContext());
        imageLoader.DisplayImage(itemCoverUrl, imageViewPhoto);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        findViews();
        fillTextView();
        buttonn();

    }
}



