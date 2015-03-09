package jp.example.sample;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.text.Editable;
import android.graphics.Color;

public class MainActivity extends ActionBarActivity {
	
	int s = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        // ボタンのオブジェクトを取得
        Button btn = (Button)findViewById(R.id.button1);

        // クリックイベントを受け取れるようにする
        btn.setOnClickListener(new OnClickListener() {
            // このメソッドがクリック毎に呼び出される
            public void onClick(View v) {
                // ここにクリックされたときの処理を記述
            	s++;
            	if(s%2!=0){
            		TextView textview1=(TextView)findViewById(R.id.digitalClock1);
            		textview1.setTextColor(Color.RED);
            	}
            	else{
            		TextView textview1=(TextView)findViewById(R.id.digitalClock1);
            		textview1.setTextColor(Color.BLACK);
            	}
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
