package com.mywebsite.mywebsite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Handler;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    //initializing WebView
    private WebView mwebView;
    public static final String FACEBOOK = "https://m.facebook.com/";
    ProgressDialog pd = null;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //WebView
        mwebView = (WebView) findViewById(R.id.myWebView);
        WebSettings webSettings = mwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //improve webView performance
        mwebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        //webSettings.setEnableSmoothTransition(true);


        mwebView.loadUrl("https://m.facebook.com/?refsrc=https%3A%2F%2Fwww.facebook.com%2F&_rdr");
        //force links open in webview only
        mwebView.setWebViewClient(new MyWebviewClient());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_fb) {
            // Handle the camera action
            mwebView.loadUrl("https://m.facebook.com/");
        } else if (id == R.id.nav_fb) {
            mwebView.loadUrl("https://m.facebook.com/");
        } else if (id == R.id.nav_twitter) {
            mwebView.loadUrl("https://twitter.com/");
        } else if (id == R.id.nav_insta) {
            mwebView.loadUrl("https://www.instagram.com/");
        } else if (id == R.id.nav_googp) {
            mwebView.loadUrl("https://plus.google.com/");
        }else if (id == R.id.nav_pinterest) {
            mwebView.loadUrl("https://in.pinterest.com/login/");
        }else if (id == R.id.nav_link) {
            mwebView.loadUrl("https://www.linkedin.com/uas/login");
        }else if (id == R.id.about_dev) {
            mwebView.loadUrl("http://neelkanthdabhi.weebly.com/about.html");
        }else if (id == R.id.nav_menu) {
            mwebView.loadUrl("javascript:try{document.querySelector('#bookmarks_jewel > a').click();}catch(e){window.location.href='" + FACEBOOK + "home.php';}");
        }else if (id == R.id.nav_events) {
            mwebView.loadUrl("https://m.facebook.com/events");
        }else if (id == R.id.nav_trending) {
            mwebView.loadUrl("https://m.facebook.com/search/trending-news/?ref=bookmark&app_id=343553122467255");
        }else if (id == R.id.nav_active) {
            mwebView.loadUrl("https://m.facebook.com/buddylist.php?_e_pi_=7%2CPAGE_ID10%2C8082000183");
        }else if (id == R.id.nav_home) {
            mwebView.loadUrl("https://plus.google.com");
        }else if (id == R.id.nav_communities) {
            mwebView.loadUrl("https://plus.google.com/communities/yours");
        }else if (id == R.id.nav_settings) {
            mwebView.loadUrl("https://plus.google.com/settings");
        }else if (id == R.id.exit) {
            finish();
            System.exit(0);
        }else if(id == R.id.share){
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "android solved");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Why to install tens of individual apps for each social networking Websites? " +
                    "Six social networks, one tiny app " +
                    "Download Wrapper App https://goo.gl/piQ9qk ");
            startActivity(Intent.createChooser(sharingIntent, "share via"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("https://m.facebook.com/")) {
                //open url contents in webview
                return false;
            } else {
                //here open external links in external browser or app

            }

            return false;
        }


        //ProgressDialogue


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            pd = new ProgressDialog(MainActivity.this);
            pd.setTitle("Please Wait..");
            pd.setMessage("App is Loading..");

            pd.show();
            Runnable pr=new Runnable(){
                public void run(){
                    pd.dismiss();
                }
            };
            Handler pdcancel=new Handler();
            pdcancel.postDelayed(pr,9000);
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(pd.isShowing())
                pd.dismiss();
        }
    }

    //goto previous page when pressing back button

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mwebView.canGoBack()) {
                        mwebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
