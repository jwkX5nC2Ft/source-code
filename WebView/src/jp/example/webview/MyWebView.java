package jp.example.webview;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends ActionBarActivity {
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_web_view);
		
		//レイアウトで指定したWebViewのIDを指定する。
		WebView  myWebView = (WebView)findViewById(R.id.webView1);
		 
		//リンクをタップしたときに標準ブラウザを起動させない
		myWebView.setWebViewClient(new WebViewClient());
		 
		//最初にYahoo! Japanのページを表示する。
		myWebView.loadUrl("http://www.yahoo.co.jp/");
		//ズーム機能を有効にする
		myWebView.getSettings().setBuiltInZoomControls(true);
		//javascriptを許可する
        myWebView.getSettings().setJavaScriptEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_web_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
