package jp.example.camera;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import android.app.ProgressDialog;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
//import android.content.pm.PackageInstaller.Session;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri.Builder;
import android.widget.AdapterView.OnItemSelectedListener;
import com.sun.mail.smtp.*;
import android.widget.AdapterView;

public class MailAdressActivity extends ActionBarActivity {
	
	private String path;
	private String ad;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail_adress);
		setTitle("送信先を指定する");
		
		progressDialog = new ProgressDialog(this);
    	progressDialog.setTitle("メール送信");
    	progressDialog.setMessage("送信中");
    	progressDialog.setIndeterminate(false);
    	//progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    	progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	progressDialog.setMax(100);
    	progressDialog.incrementProgressBy(0);
		
    	// ArrayAdapter を作成    
        String items[] = {"docomo.ne.jp","ezweb.ne.jp","softbank.ne.jp"
        		,"i.softbank.jp","d.vodafone.ne.jp","h.vodafone.ne.jp"
        		,"t.vodafone.ne.jp","c.vodafone.ne.jp","r.vodafone.ne.jp"
        		,"k.vodafone.ne.jp","n.vodafone.ne.jp","s.vodafone.ne.jp"
        		,"q.vodafone.ne.jp"};
        final ArrayAdapter<String> adapter  
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);  
        // ドロップダウンリストのレイアウトを設定     
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Spinnerオブジェクトを取得
        final Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        spinner.setAdapter(adapter); 
        
		Button btn4 = (Button) findViewById(R.id.button4);
		Button btn5 = (Button) findViewById(R.id.button5);

		btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MailAdressActivity.this,
                    MainActivity.class );
                startActivity(intent);
            }
        });
		btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	//プログレスダイアログ
            	progressDialog.show();
            	// EditTextオブジェクトを取得
                EditText editText = (EditText)findViewById(R.id.editText1);
                // 入力された文字を取得
                final String text = editText.getText().toString();
                // 選択されているアイテムのIndexを取得
                //int idx = spinner.getSelectedItemPosition();
                // 選択されているアイテムを取得
                final String item = (String)spinner.getSelectedItem();
                ad = text+"@"+item;
            	Intent intent = getIntent();
            	path = intent.getStringExtra("filename");
            	ad = "stad3415@gmail.com";
            	switch(v.getId()){
            	case R.id.button5:
            	// ----- 非同期通信
            	Uri.Builder builder = new Uri.Builder();
            	AsyncHttpRequest task = new AsyncHttpRequest();
            	task.execute(builder);
            	} 
            }
        });
		

		
	}
	
	// ------------------------------
	// 非同期通信
	// ------------------------------
	public class AsyncHttpRequest extends AsyncTask<Uri.Builder, Void, String> {
		@SuppressLint("UnlocalizedSms") @Override
	protected String doInBackground(Builder... params) {
		try {
			// ----- メール送信
			MailSender ms = new MailSender();
			ms.send();
		} catch (Exception e) {
			return e.toString();
		}
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
	//Toast.makeText(getBaseContext(), "メールを送信しました", Toast.LENGTH_SHORT).show();
	progressDialog.dismiss();
	Intent intent = new Intent(MailAdressActivity.this,
            FinishActivity.class );
        startActivity(intent);
	}
	}
	
	// -----------------------------------------------
	// メール送信
	// -----------------------------------------------
	public class MailSender {
	private Properties properties;
	//String filename = "c:\\image\\sample.jpg";
	String filename = path;
	public MailSender(){
	properties = System.getProperties();
	}
	public void send(){
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "587");
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable", "true");
	Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
	protected PasswordAuthentication getPasswordAuthentication(){
	return new PasswordAuthentication("stad3415@gmail.com", "android01");
	}
	});
	MimeMessage message = new MimeMessage(session);
	try {
	String from = "stad3415@gmail.com";
	String Sender = "stad3415@gmail.com";
	String[] to = {ad};
	message.setFrom(new InternetAddress(from));
	message.setSender(new InternetAddress(Sender));
	InternetAddress[] toAddress = new InternetAddress[to.length];
	for(int i = 0; i < to.length; i++ ){
	toAddress[i] = new InternetAddress(to[i]);
	}
	for(int i = 0; i < toAddress.length; i++){
	message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	}
	message.setSubject("メールのタイトル");
	// マルチパートオブジェクトを生成
	Multipart mp = new MimeMultipart();
	// １つ目は本文
	MimeBodyPart mbp1 = new MimeBodyPart();
	mbp1.setText("メールの本文", "iso-2022-jp");
	mbp1.setHeader("Content-Type","text/plain");
	mp.addBodyPart(mbp1);
	
	if(path != null){
	// ２つ目は画像を添付する
	MimeBodyPart mbp2 = new MimeBodyPart();
	// 添付するファイルデータソースを指定
	FileDataSource fds = new FileDataSource(filename);
	mbp2.setDataHandler(new DataHandler(fds));
	//mbp2.setFileName(MimeUtility.encodeWord(fds.getName()));
	//mbp2.setDataHandler(new DataHandler(new FileDataSource(filename)));
	//mbp2.setContentID(filename);
	mbp2.setFileName(filename);
	//mbp2.setFileName(MimeUtility.encodeWord(filename));
	mp.addBodyPart(mbp2);
	}
	
	// マルチパートオブジェクトをメッセージに設定
	message.setContent(mp);
	message.setSentDate(new java.util.Date());
	//message.setText("メールの本文");
	Transport.send(message);
	} catch (AddressException e) {
	e.printStackTrace();
	} catch (MessagingException e) {
	e.printStackTrace();
	}
	}
	}

	

	
	  
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mail_adress, menu);
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
