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
import android.widget.TextView;

import com.androidworld.antis.AntisApp.R;


public class MainActivity extends ActionBarActivity {
    public final static String Search_text="test1.com.example.diganguly.basichomepg1.Message" ;

    String[] SearchValues={"mobile phones","android mobiles","dual sim mobile phones","samsung mobiles"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pg);


        AutoCompleteTextView act= (AutoCompleteTextView) findViewById(R.id.inputSearch);
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
