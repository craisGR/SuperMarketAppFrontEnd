package com.example.leonidas.supermarketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.appyvet.rangebar.RangeBar;
import com.example.leonidas.supermarketapp.classes.Datastore;

public class MainActivity extends AppCompatActivity {

    EditText EditTextProductName;
    Spinner SpinnerCategory;
    Spinner SpinnerBrand;
    RangeBar RangeBarPriceBand;
    TextView TextViewMaxRange;
    Button ButtonSearch;

    public void findViews(){
        EditTextProductName = (EditText)findViewById(R.id.EditTextProductName);
        SpinnerCategory = (Spinner)findViewById(R.id.SpinnerCategory);
        SpinnerBrand = (Spinner) findViewById(R.id.SpinnerBrand);
        TextViewMaxRange = (TextView)findViewById(R.id.TextViewMaxRange);
        ButtonSearch = (Button)findViewById(R.id.ButtonSearch);
        RangeBarPriceBand = (RangeBar) findViewById(R.id.RangeBar);
    }

    public void rangebarr(){
        //kwdikas gia to seekbar
        String minvalue =  RangeBarPriceBand.getLeftPinValue();
        String maxvalue =  RangeBarPriceBand.getRightPinValue();

        TextViewMaxRange.setText("Price Range From " +  minvalue + " to " +  maxvalue + " € ");

        RangeBarPriceBand.setOnRangeBarChangeListener(

                new RangeBar.OnRangeBarChangeListener() {
                    @Override
                    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                        TextViewMaxRange.setText("Price Range From " + leftPinValue + " to " + rightPinValue + " € ");
                    }
                }
        );
    }

    public void spinnerr() {
        // kwdikas gia to Spinner
        ArrayAdapter<CharSequence> CategoryAdapter = ArrayAdapter.createFromResource(this, R.array.product_category, android.R.layout.simple_spinner_item); // pws tha fainetai kleisto
        CategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // pws tha fainetai anoixto
        SpinnerCategory.setAdapter(CategoryAdapter); // syndesh arrayadapter me spinner
        //telos kwdika spinner

        final ArrayAdapter<CharSequence> BrandAdapter1 = ArrayAdapter.createFromResource(this, R.array.Brand_names1, android.R.layout.simple_spinner_item); // pws tha fainetai kleisto
        BrandAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> BrandAdapter2 = ArrayAdapter.createFromResource(this, R.array.Brand_names2, android.R.layout.simple_spinner_item); // pws tha fainetai kleisto
        BrandAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> BrandAdapter3 = ArrayAdapter.createFromResource(this, R.array.Brand_names3, android.R.layout.simple_spinner_item); // pws tha fainetai kleisto
        BrandAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> BrandAdapter4 = ArrayAdapter.createFromResource(this, R.array.Brand_names4, android.R.layout.simple_spinner_item); // pws tha fainetai kleisto
        BrandAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> BrandAdapter5 = ArrayAdapter.createFromResource(this, R.array.Brand_names5, android.R.layout.simple_spinner_item); // pws tha fainetai kleisto
        BrandAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // Depend on first spinner value set adapter to 2nd spinner
                if (position == 0) {
                    SpinnerBrand.setAdapter(BrandAdapter1);
                } else if (position == 1) {
                    SpinnerBrand.setAdapter(BrandAdapter2);
                }else if (position == 2) {
                    SpinnerBrand.setAdapter(BrandAdapter3);
                }else if (position == 3) {
                    SpinnerBrand.setAdapter(BrandAdapter4);
                }else if (position == 4) {
                    SpinnerBrand.setAdapter(BrandAdapter5);
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }
        });
    };

    public void buttonn(){
        ButtonSearch.setOnClickListener(new OnClickListener() { // edw orizoume oti me to patima tou koumpiou prepei na ginei kati
            @Override
            public void onClick(View v) { //edw leme oti tha emfaniseis ta parakatw
                //collect user input
                String filterProductName = EditTextProductName.getText().toString();
                int filterCategoryId = SpinnerCategory.getSelectedItemPosition();
                int filterBrandId = SpinnerBrand.getSelectedItemPosition();
                int filterPriceMax = RangeBarPriceBand.getRightIndex();
                int filterPriceMin = RangeBarPriceBand.getLeftIndex();

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra(Datastore.KEY_PRODUCTNAME, filterProductName);
                intent.putExtra(Datastore.KEY_PRICEMAX, filterPriceMax);
                intent.putExtra(Datastore.KEY_PRICEMIN, filterPriceMin);
                intent.putExtra(Datastore.KEY_CATEGORYID, filterCategoryId);
                intent.putExtra(Datastore.KEY_BRANDNAMEID, filterBrandId);
                startActivity(intent);
            }
        });
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        Datastore.Init(getApplicationContext());
        spinnerr();
        rangebarr();
        buttonn();

    }
}

