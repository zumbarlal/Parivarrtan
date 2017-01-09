package in.zums.webmobileapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    WebView myWebView;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    LinearLayout lay1,lay2;

    String website_url = "http://parivarrtanclasses.in/parent/";
    String domain_name = "parivarrtanclasses.in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Show menu icon
//        final ActionBar ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.drawable.logowithname);
//        ab.setDisplayHomeAsUpEnabled(true);


        myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setAllowFileAccess(true);

        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        myWebView.setWebViewClient(new MyWebViewClient());


//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        myWebView.loadUrl(website_url);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//

//
//        getSupportActionBar().setHomeButtonEnabled(true);

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//
//
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//                toolbar,
//                R.string.empty, R.string.empty) {
//
//            /** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//                myWebView.bringToFront();
//            }
//
//            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
////                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
////                mDrawerLayout.bringToFront();
//            }
//
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//                mDrawerLayout.bringToFront();
//            }
//
//
//        };
//        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        final Drawable homeIcon = ContextCompat.getDrawable(this,R.drawable.logowithname);
//        mDrawerToggle.setDrawerIndicatorEnabled(true);
//        mDrawerToggle.setHomeAsUpIndicator(R.drawable.logowithname);
//        getSupportActionBar().setHomeAsUpIndicator(homeIcon);
//        getSupportActionBar().setTitle("");

//        mDrawerLayout.setVisibility(View.GONE);
        myWebView.bringToFront();

//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);

//        lay1 = (LinearLayout) findViewById(R.id.lay1);
//        lay2 = (LinearLayout) findViewById(R.id.lay2);
//
//        lay1.setOnClickListener(this);
//        lay2.setOnClickListener(this);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }else if(!myWebView.canGoBack()) {
            showExitWarning();
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.lay1:
//                shareThisApp();
//                break;
//            case R.id.lay2:
//                showExitWarning();
//                break;
//
//        }
    }

    public void showExitWarning(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Rate", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        rateThisApp();
                    }
                })
                //Set your icon here
                .setTitle(getString(R.string.app_name))
                .setIcon(R.mipmap.ic_launcher);
        AlertDialog alert = builder.create();
        alert.show();//showing the dialog

    }

    public void rateThisApp(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=org.kjffoscia.sreyah"));
        startActivity(intent);
    }

    public void shareThisApp(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.app_name)));
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            if (Uri.parse(url).getHost().contains(domain_name)) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
//            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            startActivity(intent);
//            return true;
        }
    }



    private ShareActionProvider mShareActionProvider;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate menu resource file.
//        getMenuInflater().inflate(R.menu.sharemenu, menu);
//
//        // Locate MenuItem with ShareActionProvider
//        MenuItem item = menu.findItem(R.id.menu_item_share);
//
//        // Fetch and store ShareActionProvider
//        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
//
////        Intent sendIntent = new Intent();
////        sendIntent.setAction(Intent.ACTION_SEND);
////        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
////        sendIntent.setType("text/plain");
////        setShareIntent(sendIntent);
//
//        MainActivity.this.invalidateOptionsMenu();
//
//        // Return true to display menu
//        return true;
//    }

    // Call to update the share intent
//    private void setShareIntent(Intent shareIntent) {
//        if (mShareActionProvider != null) {
//            mShareActionProvider.setShareIntent(shareIntent);
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                // User chose the "Settings" item, show the app settings UI...

                shareThisApp();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



}

