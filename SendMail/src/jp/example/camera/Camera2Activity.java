package jp.example.camera;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera2);
		setTitle("写真を撮影する");
		findViews();
		setListeners();
		
		

	}
	
	protected void findViews(){
		button1 = (Button)findViewById(R.id.button1);
		imageView1 = (ImageView)findViewById(R.id.imageView1);
	}
	
	protected void setListeners(){
		button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(
					intent,
					REQUEST_CAPTURE_IMAGE);
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
			Bitmap capturedImage = 
				(Bitmap) data.getExtras().get("data");
			imageView1.setImageBitmap(capturedImage);
			String name = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", java.util.Locale.JAPAN).format(new Date()) + ".jpg";
            MediaStore.Images.Media.insertImage(getContentResolver(), capturedImage, name, null);
            
            //SDカードへの保存
            //String path = Environment.getExternalStorageDirectory() + "\\" +name;
            //saveToSD(data,path);
            

            
            Toast.makeText(Camera2Activity.this, "保存しました。", Toast.LENGTH_SHORT).show();
            
            File pathExternalPublicDir =
            		Environment.getExternalStoragePublicDirectory(
            		Environment.DIRECTORY_DCIM);
            		// DCIMフォルダーのパス
            String dir = pathExternalPublicDir.getPath();
            
            //データ受け渡し
            //String name2 = dir + "\\" + name;
            Intent intent = new Intent(Camera2Activity.this, SelectImageActivity.class);
            //intent.putExtra("filename",name2);
            //intent.setType("image/*");
            startActivity(intent);
            //
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
