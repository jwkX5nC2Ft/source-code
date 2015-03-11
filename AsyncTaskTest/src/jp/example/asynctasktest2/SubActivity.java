package jp.example.asynctasktest2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.support.v7.app.ActionBarActivity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class SubActivity extends ActionBarActivity {
	
	private URL url = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		setTitle("�摜�f�[�^�擾");
		try {
		    url = new URL("http://da5.html.xdomain.jp/share/sample.jpg");
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		}
		new AsyncTask<URL, Void, Drawable>() {

		    @Override
		    protected Drawable doInBackground(URL... params) {

		        InputStream inputStream = null;
		        try {
		            inputStream = params[0].openStream();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		        // �o�b�N�O���E���h �X���b�h�ŁA�X�g���[������摜�f�[�^��ǂݍ���
		        Drawable drawable = Drawable.createFromStream(inputStream, null);
		        return drawable;
		    }

		    @Override
		    protected void onPostExecute(Drawable image) {
		        ImageView imageView = (ImageView) findViewById(R.id.imageView1);

		        // UI�X���b�h�ŁAView�ɉ摜��ݒ肷��
		        imageView.setImageDrawable(image);
		    }
		}.execute(url);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sub, menu);
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
