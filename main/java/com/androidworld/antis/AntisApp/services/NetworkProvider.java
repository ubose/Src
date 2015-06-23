package com.androidworld.antis.AntisApp.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.androidworld.antis.AntisApp.fragments.IFragment;
import com.androidworld.antis.AntisApp.models.IModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by utbose on 6/22/2015.
 */
public class NetworkProvider extends AsyncTask<String, Void, String> {

    private Context mContext;

    private IFragment mFragment;

    private String mDataTransformId;

    private DataDeserializer mDataDesrializer;

    private String mStringUrl;

    public NetworkProvider(){

    }

    public boolean isConnectedToNetwork(){
        ConnectivityManager connMgr = (ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void getModel(){
        execute(this.mStringUrl);
    }

    public void initialize(Context context, IFragment fragment, String dataTransformId){
        this.mContext = context;
        this.mFragment = fragment;
        this.mDataTransformId = dataTransformId;
        this.mStringUrl = "http://sports.services.appex.bing.com/LeagueV1.svc/LiveScores/tennis_intl/?mkt=en-us&client_adapter=Tennis_LiveTournament&eventType=tennis_atp&eventId=90601000001493554&lang=en-us&timezone=India%20Standard%20Time&utcOffset=&ET=1&FORM=APXS01&client=Phone_V8";
        if (this.mDataDesrializer == null) {
            this.mDataDesrializer = new DataDeserializer();
        }
    }

    @Override
    protected String doInBackground(String... urls) {

        // params comes from the execute() call: params[0] is the url.
        try {
            return downloadUrl(urls[0]);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        Object model = this.mDataDesrializer.getObjectModel(result, this.mDataTransformId);
        this.mFragment.updateModel((IModel) model);
    }

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("DEBUG_TAG", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, is.available());
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
