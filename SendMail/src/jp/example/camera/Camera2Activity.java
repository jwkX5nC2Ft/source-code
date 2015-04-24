package jp.example.camera;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;

public class Camera2Activity extends ActionBarActivity {

	static final int REQUEST_CAPTURE_IMAGE = 100;
	Button button1;
	//Button button2;
	ImageView imageView1;
	private Uri mImageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera2);
		setTitle("é ê^ÇéBâeÇ∑ÇÈ");
		findViews();
		setListeners();
		
		

	}
	
	protected void findViews(){
		button1 = (Button)findViewById(R.id.button1);
		//imageView1 = (ImageView)findViewById(R.id.imageView1);
	}
	
	protected void setListeners(){
		button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				String filename = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", java.util.Locale.JAPAN).format(new Date()) + ".jpg";
			    
			    ContentValues values = new ContentValues();
			    values.put(MediaStore.Images.Media.TITLE, filename);
			    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
			    mImageUri = getContentResolver().insert(
			            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			    
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
				startActivityForResult(intent,REQUEST_CAPTURE_IMAGE);
			}
		});
		

		

	}
	
	
	@Override
	protected void onActivityResult(
		int requestCode, 
		int resultCode, 
		Intent data) {
		if(REQUEST_CAPTURE_IMAGE == requestCode 
			&& resultCode == Activity.RESULT_OK ){
            
            Intent intent = new Intent(Camera2Activity.this, SelectImageActivity.class);
            startActivity(intent);
		}
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera2, menu);
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
