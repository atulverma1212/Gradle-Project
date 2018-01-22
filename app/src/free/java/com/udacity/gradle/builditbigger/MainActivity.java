package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Joke;
import com.example.averma1212.myandroidlibrary.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String JOKE = "joke";
    private InterstitialAd mInterstitialAd;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.instructions_text_view)
    TextView heading;
    @BindView(R.id.button)
    Button button;
    private String joke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
                                          @Override
                                          public void onAdClosed() {
                                              mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                              displayJoke();
                                          }
                                      }
        );
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

    void displayJoke(){
        Intent intent = new Intent(MainActivity.this, JokeActivity.class);
        intent.putExtra(JOKE,joke);
        startActivity(intent);
    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask(){
            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
                joke=s;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    displayJoke();
                }
            }
        }.execute();
    }


}
