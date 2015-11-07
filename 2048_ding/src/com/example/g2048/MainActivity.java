package com.example.g2048;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView tvBestScore;
	private Button btnShare;
	private TextView tvScore ;
	public int score = 0 ;
	public int bestScore = 0 ;
	
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
		tvBestScore.setText(bestScore+"");
	}
	
	public void addScore(int s)
	{
		score += s;
		showScore();
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_3);
		
		tvScore = (TextView) findViewById(R.id.tvScore);
		btnShare = (Button) findViewById(R.id.btnShare);
		tvBestScore = (TextView) findViewById(R.id.tvBestScore);
		
		ShareSDK.initSDK(this);
		
		btnShare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showShare();
			}
		});
	
		ArrayList<String> keys = new ArrayList(); 
		keys.add("bestSocre"); 
		ArrayList<String> arrayList = new ArrayList(); 
		arrayList = new SharedpreferenceUtil().read(getApplication(), 
				"score", keys);
		
		bestScore = 0 ;
		String tmp = null ;
        if(arrayList.size() != 0)
        	 tmp = arrayList.get(0);
        if(tmp == null || tmp.equals("") || tmp.length() == 0){ 
        	tvBestScore.setText("0");
        }else{
			tvBestScore.setText(tmp);
			bestScore = Integer.parseInt(tmp);
		}
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
	
	private void showShare() {
		 OnekeyShare oks = new OnekeyShare();
		 //关闭sso授权
		 oks.disableSSOWhenAuthorize(); 
		 
		// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
		 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		 oks.setTitle(getString(R.string.share));
		 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		 oks.setTitleUrl("http://sharesdk.cn");
		 // text是分享文本，所有平台都需要这个字段
		 oks.setText("我是分享文本");
		 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		 oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		 // url仅在微信（包括好友和朋友圈）中使用
		 oks.setUrl("http://sharesdk.cn");
		 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		 oks.setComment("我是测试评论文本");
		 // site是分享此内容的网站名称，仅在QQ空间使用
		 oks.setSite(getString(R.string.app_name));
		 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		 oks.setSiteUrl("http://sharesdk.cn");
		 
		// 启动分享GUI
		 oks.show(this);
	 }
	
}
