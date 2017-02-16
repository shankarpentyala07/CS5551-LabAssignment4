package com.example.shankarpentyala.labassignment4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

import static com.example.shankarpentyala.labassignment4.R.id.image;
import static com.example.shankarpentyala.labassignment4.R.id.imageView;
import static okhttp3.Request.Builder.*;

public class Webservice extends AppCompatActivity {

    ///String SearchQuery;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    EditText Textsearch;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webservice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button Image_search1 = (Button) findViewById (R.id.Image_search);
         Textsearch  = (EditText) findViewById(R.id.search_text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Image_search1.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                images();
            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void Logoff(View V) {
       Intent redirect = new Intent(Webservice.this, Login.class);
       startActivity(redirect);
    }

    public void images() {

        String SearchQuery =Textsearch.getText().toString();
        String Url = "https://kgsearch.googleapis.com/v1/entities:search?query=" + Textsearch.getText().toString() +
                "&key=AIzaSyB82KHibkqFCPiYbTs-G_Z60Y-YjM2dEU8&limit=1&indent=True";
        OkHttpClient  Client = new OkHttpClient ();
        Request request = new Request.Builder()
                                     .url(Url)
                                     .build();
        Client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final JSONObject jsonResult;
                final String result =response.body().string();
                image = (ImageView) findViewById (R.id.imageView);

                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("itemListElement");
                        final String cText;
                cText=convertedTextArray.getJSONObject(0).getJSONObject ("result").getJSONObject ("image").get ("contentUrl").toString ();
                       runOnUiThread (new Runnable ( ) {
                           @Override
                           public void run() {
                               Picasso.with (getApplicationContext ())
                                       .load(cText)
                                       .into(image);
                           }
                       });

                    } catch (JSONException e) {
                        e.printStackTrace ( );
                    }
          }
        });


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Webservice Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
