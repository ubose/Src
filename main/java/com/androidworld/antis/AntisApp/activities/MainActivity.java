package com.androidworld.antis.AntisApp.activities;

import android.app.Fragment;
import android.content.Intent;
import android.opengl.Visibility;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.fragments.GridViewFragment;
import com.androidworld.antis.AntisApp.fragments.IFragment;
import com.androidworld.antis.AntisApp.fragments.ItemViewFragment;
import com.androidworld.antis.AntisApp.models.HomePageDataModel;
import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.ProductDisplayCard;
import com.androidworld.antis.AntisApp.models.TrendingProductModel;
import com.androidworld.antis.AntisApp.services.NetworkProvider;
import com.androidworld.antis.AntisApp.utilities.ApplicationUtilities;
import com.squareup.picasso.Picasso;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity implements IFragment {

    public final static String Search_text="test1.com.example.diganguly.basichomepg1.Message" ;

    protected GridViewFragment mFragment;

    protected NetworkProvider mNetworkProvider;

    protected HomePageDataModel mModel;

    protected HashMap<String, TrendingProductModel> mFragmentDataMap;

    String[] SearchValues={"mobile phones","android mobiles","dual sim mobile phones","samsung mobiles"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page_layout);
        if (savedInstanceState == null) {
            this.mNetworkProvider = new NetworkProvider();
            this.mNetworkProvider.initialize(this, this, "homepage");
            this.mNetworkProvider.getModel();
        }
    }

    public void loadFragment(View view){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.home_tab_section);
        for(int i = 0; i < linearLayout.getChildCount(); i++){
            FrameLayout tab = (FrameLayout) linearLayout.getChildAt(i);
            ImageView imageView = (ImageView) tab.findViewById(R.id.tab_indicator);
            imageView.setVisibility(View.INVISIBLE);
        }

        ViewGroup tab = (ViewGroup)view.getParent();
        ImageView imageView = (ImageView) tab.findViewById(R.id.tab_indicator);
        imageView.setVisibility(View.VISIBLE);
        String buttonId = (String) view.getTag();

        if(this.mFragmentDataMap != null &&  this.mFragmentDataMap.containsKey(buttonId)) {
            GridViewFragment gridViewFragment = new GridViewFragment();
            gridViewFragment.initialize(this.mFragmentDataMap.get(buttonId).productItemsList);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.grid_container, gridViewFragment);
            //transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void updateModel(IModel model) {

        if (model instanceof HomePageDataModel) {
            this.mModel = (HomePageDataModel) model;
            this.mFragment = new GridViewFragment();
            this.mFragmentDataMap = new HashMap<>();
            HomePageDataModel homePageDataModel = (HomePageDataModel) model;
            setContentView(R.layout.activity_home_pg);

            //inflate search box
            AutoCompleteTextView act = (AutoCompleteTextView) findViewById(R.id.inputSearch);
            act.setAdapter(new ArrayAdapter<>(this, R.layout.list_basic_search, SearchValues));
            getWindow().getDecorView().clearFocus();
            ImageView searchIcon = (ImageView) findViewById(R.id.search_icon);
            searchIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText ed = (EditText) findViewById(R.id.inputSearch);
                    String S_text = ed.getText() == null ? "" : ed.getText().toString();
                    customsearch(S_text);
                }
            });

            //Inflate headers
            TextView textView = (TextView) findViewById(R.id.heading_text1);
            textView.setText(homePageDataModel.headerText1);

            textView = (TextView) findViewById(R.id.heading_text2);
            textView.setText(homePageDataModel.headerText2);

            textView = (TextView) findViewById(R.id.heading_text3);
            textView.setText(homePageDataModel.headerText3);

            if (homePageDataModel.productDisplayCardList != null) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.product_display_zone);
                for(int i = 0; i<homePageDataModel.productDisplayCardList.size(); i++) {
                    ProductDisplayCard productDisplayCard = homePageDataModel.productDisplayCardList.get(i);
                    LinearLayout productLayout = (LinearLayout) View.inflate(getBaseContext(), R.layout.product_display_layout, null);
                    textView = (TextView) productLayout.findViewById(R.id.product_overlay_text);
                    textView.setText(productDisplayCard.imageDisplayText);

                    textView = (TextView) productLayout.findViewById(R.id.save_count);
                    textView.setText(productDisplayCard.saveCount);

                    textView = (TextView) productLayout.findViewById(R.id.save_display_text);
                    textView.setText(productDisplayCard.saveDisplayText);

                    textView = (TextView) productLayout.findViewById(R.id.reject_count);
                    textView.setText(productDisplayCard.rejectCount);

                    textView = (TextView) productLayout.findViewById(R.id.reject_display_text);
                    textView.setText(productDisplayCard.rejectDisplayText);

                    textView = (TextView) productLayout.findViewById(R.id.filters_count);
                    textView.setText(productDisplayCard.filtersCount);

                    textView = (TextView) productLayout.findViewById(R.id.filters_display_text);
                    textView.setText(productDisplayCard.filtersDisplayText);

                    if(productDisplayCard.productImage != null) {
                        //TODO: fallback image
                        ImageView imageView = (ImageView) productLayout.findViewById(R.id.product_image);
                        ApplicationUtilities.setImageView(this, imageView, productDisplayCard.productImage.src);
                    }

                    linearLayout.addView(productLayout);
                }
            }

            if(homePageDataModel.trendingProductModelList != null) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.home_tab_section);
                for(int i = 0; i < homePageDataModel.trendingProductModelList.size(); i++) {
                    TrendingProductModel trendingProductModel = homePageDataModel.trendingProductModelList.get(i);
                    this.mFragmentDataMap.put(trendingProductModel.id, trendingProductModel);
                    FrameLayout tab_layout = (FrameLayout) View.inflate(getBaseContext(), R.layout.home_tab_layout, null);
                    TextView button = (TextView) tab_layout.findViewById(R.id.tab_button);
                    button.setText(trendingProductModel.headingText);
                    button.setTag(trendingProductModel.id);
                    if(i == 0) {
                        ImageView imageView = (ImageView) tab_layout.findViewById(R.id.tab_indicator);
                        imageView.setVisibility(View.VISIBLE);
                    }
                    linearLayout.addView(tab_layout);
                }

                //inflate the fragment
                this.mFragment.initialize(this.mModel.trendingProductModelList.get(0).productItemsList);
                getSupportFragmentManager().beginTransaction().add(R.id.grid_container, this.mFragment).commit();
            }
        }
    }

    public void customsearch(String S_text){

        Intent intent = new Intent(this, ItemListActivity.class);

        intent.putExtra(Search_text,S_text);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_pg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
