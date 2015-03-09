package jp.example.webview;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MyMapView extends ActionBarActivity {
	
	// GoogleMap�I�u�W�F�N�g�̐錾
    private GoogleMap googleMap;
    
    @SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_map_view);
		
		// MapFragment�I�u�W�F�N�g���擾
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        try {
            // GoogleMap�I�u�W�F�N�g�̎擾
            googleMap = mapFragment.getMap();

            // Activity������Ő������ꂽ�Ƃ�
            if (savedInstanceState == null) {

                // MapFragment�̃I�u�W�F�N�g���Z�b�g
                mapFragment.setRetainInstance(true);

                // �n�}�̏����ݒ���s�����\�b�h�̌Ăяo��
                mapInit();
            }
        }
        // GoogleMap���g�p�s�̂Ƃ��̂��߂�try catch�ň͂��Ă��܂��B
        catch (Exception e) {
        }
	}
    
 // �n�}�̏����ݒ胁�\�b�h
    private void mapInit() {

        // �n�}�^�C�v�ݒ�
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // ���݈ʒu�{�^���̕\�����s�Ȃ�
        googleMap.setMyLocationEnabled(true);

        // ����w�̈ʒu�A�Y�[���ݒ�
        CameraPosition camerapos = new CameraPosition.Builder()
                .target(new LatLng(35.705833, 139.665556)).zoom(15.5f).build();

        // �n�}�̒��S�̕ύX����
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camerapos));
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_map_view, menu);
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
