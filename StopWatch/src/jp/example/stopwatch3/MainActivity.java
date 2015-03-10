package jp.example.stopwatch3;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	private TextView timer;     //�^�C�}�[
    private Button startbtn;    //�X�^�[�g�{�^��
    private Button stopbtn;     //�X�g�b�v�{�^��
    private Button resetbtn;    //���Z�b�g�{�^��
    private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");   
    private long myStartTime = 0;   //�J�n���ԕێ�
    private long myOffsetTime = 0;  //�I�t�Z�b�g�^�C��
    private long myCurrentTime = 0;
    private LoopEngine loopEngine = new LoopEngine();
    long timer_cnt = 0;
    private int i =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("�X�g�b�v�E�H�b�`");
		//--- ���i�̂Ђ��t�� ---//
        timer = (TextView)findViewById(R.id.textView1);
        startbtn = (Button)findViewById(R.id.button1);
        stopbtn = (Button)findViewById(R.id.button2);
        resetbtn = (Button)findViewById(R.id.button3);
        //--- �C�x���g���� ---//
        //�X�^�[�g�{�^��
        startbtn.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
            	if(i == 0) loopEngine.start();
            	else loopEngine.restart();
            }
        });
        //�X�g�b�v�{�^��
        stopbtn.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
            	myOffsetTime += System.currentTimeMillis() - myStartTime;
                loopEngine.stop();
            }
        });
        //���Z�b�g�{�^��
        resetbtn.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                loopEngine.reset();
            }
        });
	}
	//�n���h���[����
    class LoopEngine extends Handler{
        private boolean isUpdate;
        public void start(){
        	myStartTime = System.currentTimeMillis();
            this.isUpdate = true;
            handleMessage(new Message());
        }
        public void restart(){
            this.isUpdate = true;
            handleMessage(new Message());
        }
        public void stop(){
        	i = 1;
            this.isUpdate = false;
        }
        public void reset(){
        	i = 0;
            this.isUpdate = false;
            timer_cnt = 0;
            timer.setText("00:00.000");
        }
        @Override
        public void handleMessage(Message msg){
            this.removeMessages(0);    //�����̃��b�Z�[�W�͍폜
            if(this.isUpdate){
                timer.setText(String.valueOf(sdf.format(myCurrentTime)));
                sendMessageDelayed(obtainMessage(0), 1);
                myCurrentTime = System.currentTimeMillis()-myStartTime;
            }
        }
    };



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
