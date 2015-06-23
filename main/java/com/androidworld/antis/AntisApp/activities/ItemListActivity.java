package com.androidworld.antis.AntisApp.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidworld.antis.AntisApp.R;
import com.androidworld.antis.AntisApp.fragments.FilterDialogueFragment;
import com.androidworld.antis.AntisApp.fragments.IFragment;
import com.androidworld.antis.AntisApp.fragments.ItemViewFragment;
import com.androidworld.antis.AntisApp.models.IModel;
import com.androidworld.antis.AntisApp.models.ProductListDataModel;
import com.androidworld.antis.AntisApp.services.NetworkProvider;
import com.androidworld.antis.AntisApp.utilities.StringUtilities;

import org.w3c.dom.Text;


public class ItemListActivity extends FragmentActivity implements IFragment {

    protected ItemViewFragment mFragment;

    protected NetworkProvider mNetworkProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
            String query= intent.getStringExtra(MainActivity.Search_text);
            if(StringUtilities.isNullOrWhitespace(query) || !query.contains("android")) {
                return;
            }

        setContentView(R.layout.loading_page_layout);
        if (savedInstanceState == null) {
            this.mNetworkProvider = new NetworkProvider();
            this.mNetworkProvider.initialize(this, this, "android_mobiles");
            this.mNetworkProvider.getModel();
        }
    }

    @Override
    public void updateModel(IModel model){

        this.mFragment = new ItemViewFragment();
        if(model instanceof ProductListDataModel) {
            setContentView(R.layout.activity_main);
            ProductListDataModel productListDataModel = (ProductListDataModel) model;

            TextView textView = (TextView) findViewById(R.id.phone_count);
            textView.setText(productListDataModel.headerCountText);

            textView = (TextView) findViewById(R.id.phone_display_text);
            textView.setText(productListDataModel.headerDisplayText);

            if (productListDataModel.filtersList != null) {
                LinearLayout filterLayout = (LinearLayout) findViewById(R.id.filter_zone);
                for (int i = 0; i < productListDataModel.filtersList.size(); i+=2) {
                    if (i + 1 < productListDataModel.filtersList.size()) {
                        LinearLayout filterPairs = (LinearLayout) View.inflate(getBaseContext(), R.layout.button_pair_layout, null);
                        TextView buttonText1 = (TextView) filterPairs.findViewById(R.id.button1);
                        buttonText1.setText(productListDataModel.filtersList.get(i).second);
                        TextView buttonText2 = (TextView) filterPairs.findViewById(R.id.button2);
                        buttonText2.setText(productListDataModel.filtersList.get(i + 1).second);
                        filterLayout.addView(filterPairs);
                    }
                }
            }



            //inflate the fragment
            this.mFragment.initialize(productListDataModel.productList);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, this.mFragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void sendMessage(View view){
        ItemViewFragment newFragment = new ItemViewFragment();
        if(this.mFragment == null) {
            return;
        }
        Bundle args = new Bundle();
        Button buttonView = (Button) view;
        args.putCharSequence(ItemViewFragment.ARG_POSITION, buttonView.getText());

        showFilterDialogue();
       // this.mFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, this.mFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void showFilterDialogue(){
        FilterDialogueFragment dialogueFragment = new FilterDialogueFragment();
        dialogueFragment.show(getFragmentManager(), "FilterDialogueFragment");
    }
}
