package com.webkitclase;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends Activity {
    WebView engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        engine = (WebView) findViewById(R.id.web_engine);
        engine.getSettings().setJavaScriptEnabled(true);
        engine.addJavascriptInterface(new WebAppInterface(this), "Android");
        engine.loadUrl("http://developer.android.com/intl/zh-cn/index.html");

        engine.setWebViewClient(new WebViewClient());// permite la navegacion web desde la misma aplicacion

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history  utilizando el boton de back para ver el historial de la pagina
        if ((keyCode == KeyEvent.KEYCODE_BACK) && engine.canGoBack()) {
            engine.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web, menu);
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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class WebAppInterface {
        Context cOtro;
        String toast="Mi curso de webview";

        WebAppInterface(Context c) {
            cOtro=c;
            showToast(toast);
        }

        //Para mostrar un toast en el webview
        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(cOtro,toast,Toast.LENGTH_LONG).show();

        }

    }
}
