package com.example.g2048;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private Button btnShare;
	private TextView tvScore ;
	private int score = 0 ;
	
	private static MainActivity mainActivity = null ;
	
	public static MainActivity getMainActivity() {
		return mainActivity;
	}

	public static void setMainActivity(MainActivity mainActivity) {
		MainActivity.mainActivity = mainActivity;
	}

	// 构造函数
	public MainActivity()
	{
		mainActivity = this ;
	}
	
	public void clearScore()
	{
		score = 0 ;
		showScore();
	}
	
	public void showScore()
	{
		tvScore.setText(score+"");
	}
	
	public void addScore(int s)
	{
		score += s;
		showScore();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_2);
		
		tvScore = (TextView) findViewById(R.id.tvScore);
		btnShare = (Button) findViewById(R.id.btnShare);
		
				
		btnShare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
			}
		});
	}

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
