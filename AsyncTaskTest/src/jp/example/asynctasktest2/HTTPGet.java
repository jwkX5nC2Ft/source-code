package jp.example.asynctasktest2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.support.v7.app.ActionBarActivity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HTTPGet extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_httpget);
		setTitle("HTMLéÊìæ");
		final TextView text = (TextView)findViewById(R.id.textView12);

		new AsyncTask<Void, Void, String>() {

		    @Override
		    protected String doInBackground(Void... params) {
		    	try {
					AndroidHttpClient client = AndroidHttpClient.newInstance("Android UserAgent");
					HttpResponse res = client.execute(new HttpGet("http://da5.html.xdomain.jp/"));
						
					// HttpResponseÇÃEntityÉfÅ[É^ÇStringÇ÷ïœä∑
			                BufferedReader reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), "UTF-8"));
			                StringBuilder builder = new StringBuilder();
			                String line = null;
			                while ((line = reader.readLine()) != null) {
			        	        builder.append(line + "\n");
			                }
				        
			                return builder.toString();
				} catch (Exception e) {
					e.getStackTrace();
					return "";
				}
		    }

		    @Override
		    protected void onPostExecute(String result) {
		    	text.setText(result);
		    }
		}.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.httpget, menu);
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
