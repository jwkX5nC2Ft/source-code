package jp.example.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Acount extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acount);
		setTitle("Googleアカウント設定");
		
		
        
		Button btn5 = (Button)findViewById(R.id.button1);
		btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	EditText editText1 = (EditText)findViewById(R.id.editText01);
        		EditText editText2 = (EditText)findViewById(R.id.editText02);
                // 入力された文字を取得
                final String text1 = editText1.getText().toString();
                final String text2 = editText2.getText().toString();
                SharedPreferences prefs = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                //editor.clear();
                //editor.apply();
                editor.putString("mailadress0", text1);
                editor.putString("password0", text2);
                editor.apply();

                Intent intent = new Intent(Acount.this,MainActivity.class );
                startActivity(intent);
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acount, menu);
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
