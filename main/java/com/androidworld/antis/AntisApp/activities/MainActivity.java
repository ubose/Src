package com.androidworld.antis.AntisApp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.fragments.IFragment;
import com.androidworld.antis.AntisApp.models.HomePageDataModel;
import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.ProductDisplayCard;
import com.androidworld.antis.AntisApp.services.NetworkProvider;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity implements IFragment {

    public final static String Search_text="test1.com.example.diganguly.basichomepg1.Message" ;

    protected NetworkProvider mNetworkProvider;

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

    public void updateModel(IModel model) {

        if (model instanceof HomePageDataModel) {
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
                        Picasso.with(this).load(productDisplayCard.productImage.src).fit().into(imageView);
                    }

                    linearLayout.addView(productLayout);
                }
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
