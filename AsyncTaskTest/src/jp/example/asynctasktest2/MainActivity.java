package jp.example.asynctasktest2;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("HTTP通信");
		 // ボタンのオブジェクトを取得
        Button btn = (Button)findViewById(R.id.button10);
        Button btn2 = (Button)findViewById(R.id.button11);

        // クリックイベントを受け取れるようにする
        btn.setOnClickListener(new OnClickListener() {
            // このメソッドがクリック毎に呼び出される
            public void onClick(View v) {
                // ここにクリックされたときの処理を記述
            	Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            // このメソッドがクリック毎に呼び出される
            public void onClick(View v) {
                // ここにクリックされたときの処理を記述
            	Intent intent = new Intent(MainActivity.this, HTTPGet.class);
                startActivity(intent);
            }
        });
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
