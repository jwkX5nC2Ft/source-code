package jp.example.camera;

import java.io.InputStream;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Fragment;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class SelectImageActivity extends ActionBarActivity {
	
	private static final int REQUEST_GALLERY = 0;
	private ImageView imgView2;
	private String filePath;
	private ImageButton ib;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_image);
		setTitle("添付画像の選択");
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		// ギャラリー呼び出し
    	Intent intent = new Intent();
    	intent.setType("image/*");
    	intent.setAction(Intent.ACTION_GET_CONTENT);
    	startActivityForResult(intent, REQUEST_GALLERY);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
//			try {
//				InputStream in = getContentResolver().openInputStream(data.getData());
//				Bitmap img = BitmapFactory.decodeStream(in);
//				in.close();
				// 選択した画像を表示
//				imgView2.setImageBitmap(img);
//				Toast.makeText(getApplication(), "select!", Toast.LENGTH_SHORT).show();
//				} catch (Exception e) {
					
//				}
			
			
			super.onActivityResult(requestCode, resultCode, data);
	        if (requestCode == REQUEST_GALLERY) {
	            switch (resultCode) {
	                case Activity.RESULT_OK:
	                    String[] projection = {MediaStore.MediaColumns.DATA};
	                    String selection = null;
	                    String[] selectionArgs = null;
	                    String sortOrder = null;
	                    Cursor cursor = getContentResolver().query(data.getData(), projection, selection, selectionArgs, sortOrder);
	                    if (cursor.getCount() == 1) {
	                        cursor.moveToNext();
	                        String filePath = cursor.getString(0);
	                        //Toast.makeText(this, filePath, Toast.LENGTH_LONG).show();
	                        //TextView tv = new TextView(this);
	                        //tv.setText(filePath);
	                        //setContentView(tv);
	                        BitmapFactory.Options options = new BitmapFactory.Options();
	                        options.inJustDecodeBounds = true;
	                        Bitmap bmp2 = BitmapFactory.decodeFile(filePath, options);
	                        int height = options.outHeight;
	                        options.inSampleSize = height/600;
	                        options.inJustDecodeBounds = false;
	                        bmp2 = BitmapFactory.decodeFile(filePath, options);
	                        ImageView image = new ImageView(this);
	                        image.setImageBitmap(bmp2);
	                        setContentView(image, new LayoutParams(WC, WC));
	                        
	                        //データ受け渡し
	                        //Intent intent = new Intent(Intent.ACTION_SEND);
	                        Intent intent = new Intent(SelectImageActivity.this, MailAdressActivity.class);
	                        //intent.putExtra(Intent.EXTRA_STREAM,filePath);
	                        intent.putExtra("filename",filePath);
	                        intent.setType("image/*");
	                        startActivity(intent);
	                        //
	                    }
	            }
	        }
			
			
			}
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_image, menu);
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
