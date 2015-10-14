package com.ingame.demo;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.ingamesdk.manager.InGameSDK;

public class MainActivity extends Activity {

	public static InGameSDK m_InGameSDK = null;
	public static MainActivity instance;
	Button btnPayment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		instance = this;

		m_InGameSDK = InGameSDK.getInstance();
		m_InGameSDK.callSendInstallationEvent(this);
		m_InGameSDK.init(this, "your callback url");
		btnPayment = (Button) findViewById(R.id.btnPayment);

	}

	public void makePayment(View v) {
		m_InGameSDK.callPayment(System.currentTimeMillis()+"");
	}

	public static String random() {
		Random generator = new Random();
		StringBuilder randomStringBuilder = new StringBuilder();
		int randomLength = generator.nextInt(15);
		char tempChar;
		for (int i = 0; i < randomLength; i++) {
			tempChar = (char) (generator.nextInt(96) + 32);
			randomStringBuilder.append(tempChar);
		}
		return randomStringBuilder.toString();
	}

	@Override
	protected void onResume() {
		super.onResume();
		InGameSDK.getInstance().onResume(this);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(true);
		}
		return super.onKeyDown(keyCode, event);
	}

	// Alternative variant for API 5 and higher
	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
	}

}
