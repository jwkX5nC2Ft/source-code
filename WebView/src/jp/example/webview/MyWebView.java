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
		
		//���C�A�E�g�Ŏw�肵��WebView��ID���w�肷��B
		WebView  myWebView = (WebView)findViewById(R.id.webView1);
		 
		//�����N���^�b�v�����Ƃ��ɕW���u���E�U���N�������Ȃ�
		myWebView.setWebViewClient(new WebViewClient());
		 
		//�ŏ���Yahoo! Japan�̃y�[�W��\������B
		myWebView.loadUrl("http://www.yahoo.co.jp/");
		//�Y�[���@�\��L���ɂ���
		myWebView.getSettings().setBuiltInZoomControls(true);
		//javascript��������
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
