package com.priyesh.tungare.inspiringthoughts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tjeannin.apprate.AppRate;

public class MainActivity extends Activity {
	
	private Button mGetImageButton;  
	private Button mCopyQuoteButton;
	private TextView mTextView;
	private RelativeLayout mRelativeLayout;
	Random randomGenerator = new Random();
	public final static String TAG = MainActivity.class.getSimpleName();
	protected String[] mInspiringThoughts;
	protected int mRandomNumber = 110;
	public static int mCount;
	public static Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();
		setContentView(R.layout.activity_main);
		mTextView = (TextView) findViewById(R.id.textView1);
		mGetImageButton = (Button) findViewById(R.id.button1);
		mCopyQuoteButton = (Button) findViewById(R.id.button2);
		
		mRelativeLayout = (RelativeLayout) findViewById(R.id.relativelayout1);
		mRelativeLayout.setBackgroundResource(R.drawable.background27);
		mCopyQuoteButton.setVisibility(View.INVISIBLE);
		
		mGetImageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCopyQuoteButton.setVisibility(View.VISIBLE);
				mCount++;
				mRandomNumber = randomGenerator.nextInt(97);
				setTextOnClick(mRandomNumber);
			}
		});
		
		mCopyQuoteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				copyQuote();
			}

		});
	}
	

	private void copyQuote() {
		ClipboardManager clipBoard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData data = ClipData.newPlainText("", mInspiringThoughts[mRandomNumber]);
		clipBoard.setPrimaryClip(data);
		Toast.makeText(getApplicationContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();
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
			sharePost(mTextView);
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
	
	private void sharePost(View view) {
		// TODO Auto-generated method stub
		List<Intent> targetedShareIntents = new ArrayList<Intent>();
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		String shareBody = mInspiringThoughts[mRandomNumber] + 
				"- Shared From: Inspiring Thoughts. https://play.google.com/store/apps/details?id=com.priyesh.tungare.inspiringthoughts";
		if(mRandomNumber == 110){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getString(R.string.title));
			builder.setMessage(getString(R.string.error_message));
			builder.setPositiveButton(android.R.string.ok, null);
			AlertDialog dialog = builder.create();
			dialog.show();
		}else{
			
			PackageManager pm = view.getContext().getPackageManager();
			List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
			for(final ResolveInfo app : activityList) {

		         String packageName = app.activityInfo.packageName;
		         Intent targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
		         targetedShareIntent.setType("text/plain");
		         if(TextUtils.equals(packageName, "com.facebook.katana")){
		             targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
		            		 "https://play.google.com/store/apps/details?id=com.priyesh.tungare.inspiringthoughts");
		         } else {
		             targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		         }

		         targetedShareIntent.setPackage(packageName);
		         targetedShareIntents.add(targetedShareIntent);

		    }
			Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), getString(R.string.share_chooser_title));

		    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
		    startActivity(chooserIntent);
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
			AppRate.reset(mContext);
			new AppRate(this).init();
			mCount = -20;
		}
		
	}
	
	public void setBackgroundImage(){
		Random random = new Random();
		int number = random.nextInt(10);
		selectImage(number);
	}

	public void selectImage(int number) {
		switch (number) {
		case 0:
			mRelativeLayout.setBackgroundResource(R.drawable.background1);
			break;
		case 1:
			mRelativeLayout.setBackgroundResource(R.drawable.background2);
			break;
		case 2:
			mRelativeLayout.setBackgroundResource(R.drawable.background3);
			break;
		case 3:
			mRelativeLayout.setBackgroundResource(R.drawable.background4);
			break;
		case 4:
			mRelativeLayout.setBackgroundResource(R.drawable.background5);
			break;
		case 5:
			mRelativeLayout.setBackgroundResource(R.drawable.background6);
			break;
		case 6:
			mRelativeLayout.setBackgroundResource(R.drawable.background7);
			break;
		case 7:
			mRelativeLayout.setBackgroundResource(R.drawable.background8);
			break;
		case 8:
			mRelativeLayout.setBackgroundResource(R.drawable.background9);
			break;
		case 9:
			mRelativeLayout.setBackgroundResource(R.drawable.background10);
			break;
		default:
			mRelativeLayout.setBackgroundResource(R.drawable.background1);
			break;
		}
	}
}
