package com.priyesh.tungare.inspiringthoughts;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tjeannin.apprate.AppRate;

public class MainActivity extends Activity {
	
	private Button mGetImageButton;  
	private TextView mTextView;
	private RelativeLayout mRelativeLayout;
	Random randomGenerator = new Random();
	public final static String TAG = MainActivity.class.getSimpleName();
	protected String[] mInspiringThoughts;
	protected int mRandomNumber = 110;
	public static final int MENU_SHARE = Menu.FIRST;
	public static final int MENU_ABOUT = Menu.FIRST + 1;
	public static final int MENU_RATE = Menu.FIRST + 2;
	public static int mCount;
	public static Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();
		setContentView(R.layout.activity_main);
		
		mTextView = (TextView) findViewById(R.id.textView1);
		mGetImageButton = (Button) findViewById(R.id.button1);
		mRelativeLayout = (RelativeLayout) findViewById(R.id.relativelayout1);
		
		mRelativeLayout.setBackgroundResource(R.drawable.background27);
		
		mGetImageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCount++;
				mRandomNumber = randomGenerator.nextInt(97);
				setTextOnClick(mRandomNumber);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present
		//aboutMenu = menu.findItem(R.id.action_overflow);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		switch (item.getItemId()) {
		case R.id.action_share:
			sharePost();
			return true;
		
		case R.id.about_us:
			aboutUs();
			return true;
			
		case R.id.rate:
			new AppRate(this).init();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void aboutUs(){
		Intent aboutIntent = new Intent(Intent.ACTION_SEND);
		aboutIntent.setType("text/plain");
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.about_title));
		builder.setMessage(getString(R.string.about_msg));
		builder.setPositiveButton(android.R.string.ok, null);
		AlertDialog dialog = builder.create();
		dialog.show();	
	}
	
	private void sharePost() {
		// TODO Auto-generated method stub
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		if(mRandomNumber == 110){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getString(R.string.title));
			builder.setMessage(getString(R.string.error_message));
			builder.setPositiveButton(android.R.string.ok, null);
			AlertDialog dialog = builder.create();
			dialog.show();
		}else{
			shareIntent.putExtra(Intent.EXTRA_TEXT, mInspiringThoughts[mRandomNumber]+
					" - Shared From: Inspiring Thoughts");
			startActivity(Intent.createChooser(shareIntent, getString(R.string.share_chooser_title)));
		}
		
	}

	public void setTextOnClick(final int mRandomNumber) {
		Resources res = getResources();
		setBackgroundImage();
		mInspiringThoughts = res.getStringArray(R.array.quotes);
		mTextView.setText(mInspiringThoughts[mRandomNumber]);
		mTextView.setTextColor(Color.WHITE);
		mTextView.setTextSize(24);
		if(mCount > 10){
			new AppRate(this).init();
			mCount = -20;
		}
		
	}
	
	public void setBackgroundImage(){
		Random random = new Random();
		int number = random.nextInt(20);
		selectImage(number);
	}

	public void selectImage(int number) {
		switch (number) {
		case 0:
			mRelativeLayout.setBackgroundResource(R.drawable.background24);
			break;
		case 1:
			mRelativeLayout.setBackgroundResource(R.drawable.background23);
			break;
		case 2:
			mRelativeLayout.setBackgroundResource(R.drawable.background21);
			break;
		case 3:
			mRelativeLayout.setBackgroundResource(R.drawable.background22);
			break;
		case 4:
			mRelativeLayout.setBackgroundResource(R.drawable.background20);
			break;
		case 5:
			mRelativeLayout.setBackgroundResource(R.drawable.background18);
			break;
		case 6:
			mRelativeLayout.setBackgroundResource(R.drawable.background19);
			break;
		case 7:
			mRelativeLayout.setBackgroundResource(R.drawable.background17);
			break;
		case 8:
			mRelativeLayout.setBackgroundResource(R.drawable.background15);
			break;
		case 9:
			mRelativeLayout.setBackgroundResource(R.drawable.background14);
			break;
		case 10:
			mRelativeLayout.setBackgroundResource(R.drawable.background13);
			break;
		case 11:
			mRelativeLayout.setBackgroundResource(R.drawable.background12);
			break;
		case 12:
			mRelativeLayout.setBackgroundResource(R.drawable.background11);
			break;
		case 13:
			mRelativeLayout.setBackgroundResource(R.drawable.background10);
			break;
		case 14:
			mRelativeLayout.setBackgroundResource(R.drawable.background9);
			break;
		case 15:
			mRelativeLayout.setBackgroundResource(R.drawable.background8);
			break;
		case 16:
			mRelativeLayout.setBackgroundResource(R.drawable.background7);
			break;
		case 17:
			mRelativeLayout.setBackgroundResource(R.drawable.background5);
			break;
		case 18:
			mRelativeLayout.setBackgroundResource(R.drawable.background4);
			break;
		case 19:
			mRelativeLayout.setBackgroundResource(R.drawable.background25);
			break;
		case 20:
			mRelativeLayout.setBackgroundResource(R.drawable.background2);
			break;
		default:
			mRelativeLayout.setBackgroundResource(R.drawable.background25);
			break;
		}
	}
}
