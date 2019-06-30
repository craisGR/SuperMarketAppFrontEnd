package com.example.leonidas.supermarketapp.classes;

import android.content.Context;
import com.example.leonidas.supermarketapp.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Datastore {

    public static String KEY_PRICEMIN = "PRICEMIN";
    public static String KEY_PRODUCTNAME = "PRODUCTNAME";
    public static String KEY_PRICEMAX = "PRICEMAX";
    public static String KEY_CATEGORYID = "CATEGORYID";
    public static String KEY_BRANDNAMEID = "BRANDNAMEID";
    public static String KEY_SMURL = "SMURL";
    public static String KEY_COVERURL = "COVERURL";
    public static String KEY_ID = "ID";
    public static String KEY_POSITION = "POSITION";
    public static String KEY_PRICE = "PRICE";
    public static String KEY_DETAILS = "DETAILS";

    public static String[] Categories;
    public static String[] Brands;
    public static ArrayList<HashMap<String, Object>> Items;
    public static Context AppContext;

    public static void Init (Context ctx) {
        AppContext = ctx;
        Items = new ArrayList<HashMap<String, Object>>();
        Categories = AppContext.getResources().getStringArray(R.array.product_category);

    }

    public static void LoadItems(String filterProductName, int filterPriceMax, int filterPriceMin,int filterCategoryId, int filterBrandId) {
        Datastore.Items.clear();

        //Read from file in Assets
        String contents = AssetsUtils.getFileContentsFromAssets(AppContext, "Items.json");

        //Read from URL
        //filterProductName = NetworkUtils.UrlEncode(filterProductName);
        //String urlString = String.format("http://informatics.teicm.gr/msc/android/Items_sample.json.txt?productname=%s&pricemax=%d&&pricemin=%d&categoryid=%d&brandid=%d",filterProductName, filterPriceMax, filterPriceMin, filterCategoryId,filterBrandId);
        //String contents = NetworkUtils.getFileContentsFromUrl(urlString);

        JSONObject json = JsonParser.getJsonObject(contents);
        JSONArray jItems = json.optJSONArray("Items");
        if (jItems == null) return;
        int nItems = jItems.length();
        for (int i=0; i<nItems; i++) {
            JSONObject jCurItem = jItems.optJSONObject(i);
            int ItemID = jCurItem.optInt(KEY_ID, 0);
            String ItemProductName = jCurItem.optString(KEY_PRODUCTNAME);
            int ItemCategoryId = jCurItem.optInt(KEY_CATEGORYID, 0);
            int ItemBrandNameId = jCurItem.optInt(KEY_BRANDNAMEID, 0);
            String ItemSMUrl = jCurItem.optString(KEY_SMURL);
            String ItemCoverUrl = jCurItem.optString(KEY_COVERURL);
            double ItemPrice = jCurItem.optDouble(KEY_PRICE);
            String ItemDetails = jCurItem.optString(KEY_DETAILS);

            //get Category name by ID
            String ItemCategoryName = Categories[ItemCategoryId];

            //get Brand name by ID depends onCategory
            if (ItemCategoryId == 1) {
                Brands = AppContext.getResources().getStringArray(R.array.Brand_names2);
            } else if (ItemCategoryId == 2) {
                Brands = AppContext.getResources().getStringArray(R.array.Brand_names3);
            }else if (ItemCategoryId == 3) {
                Brands = AppContext.getResources().getStringArray(R.array.Brand_names4);
            }else if (ItemCategoryId == 4) {
                Brands = AppContext.getResources().getStringArray(R.array.Brand_names5);
            }
            String ItemBrandName = Brands[ItemBrandNameId];

            // hold each Item in a HashMap (Associative Array)
            HashMap<String, Object> Item = new HashMap<String, Object>();
            Item.put(KEY_ID, ItemID);
            Item.put(KEY_PRODUCTNAME, ItemProductName);
            Item.put(KEY_CATEGORYID, ItemCategoryName);
            Item.put(KEY_BRANDNAMEID, ItemBrandName);
            Item.put(KEY_SMURL, ItemSMUrl);
            Item.put(KEY_COVERURL, ItemCoverUrl);
            Item.put(KEY_PRICE, ItemPrice);
            Item.put(KEY_DETAILS, ItemDetails);

            Items.add(Item);
        }
    }
}
