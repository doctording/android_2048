package com.example.g2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout{

	public Card(Context context) {
		super(context);
		
		label = new TextView(getContext());
		label.setTextSize(32);
		label.setGravity(Gravity.CENTER);
		label.setBackgroundColor(0x33ffffff);
		
		LayoutParams lp = new LayoutParams(-1,-1);
		lp.setMargins(10, 10, 0, 0);
		addView(label , lp);
		
		setNum(0);
	}
	
	private int num = 0 ;

	public int getNum() {
		return num;
	}

	//设置label 数字 和 颜色
	public void setNum(int num) {
		this.num = num;
		
		if(num <=0)
		{
			label.setText("");
			label.setBackgroundColor(0x33ffffff);
		}
		else
		{
			label.setText(num+"");
			int index = (int)(Math.log(num) / Math.log(2));
			String mBgColor = new Constant().numColor[index] ;
			label.setBackgroundColor(Color.parseColor(mBgColor));
			
		}
	}
	
	private TextView label ;
	
	/**
	 * 判断两个卡片是否可以合并（即数字相等）
	 * @param c
	 * @return
	 */
	public boolean equals(Card c)
	{
		return (getNum() == c.getNum());
	}
	
}
