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
		setTitle("HTTP�ʐM");
		 // �{�^���̃I�u�W�F�N�g���擾
        Button btn = (Button)findViewById(R.id.button10);
        Button btn2 = (Button)findViewById(R.id.button11);

        // �N���b�N�C�x���g���󂯎���悤�ɂ���
        btn.setOnClickListener(new OnClickListener() {
            // ���̃��\�b�h���N���b�N���ɌĂяo�����
            public void onClick(View v) {
                // �����ɃN���b�N���ꂽ�Ƃ��̏������L�q
            	Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            // ���̃��\�b�h���N���b�N���ɌĂяo�����
            public void onClick(View v) {
                // �����ɃN���b�N���ꂽ�Ƃ��̏������L�q
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
