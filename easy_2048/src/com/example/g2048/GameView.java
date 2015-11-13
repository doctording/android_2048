package com.example.g2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout{

	public GameView(Context context) {
		super(context);
		initGameView();
	}
	
	public GameView(Context context , AttributeSet attrs)
	{
		super(context , attrs);
		initGameView();
	}
	public GameView(Context context , AttributeSet attrs , int defStyle)
	{
		super(context , attrs , defStyle);
		initGameView();
	}
	private void initGameView()
	{
		setColumnCount(4);
		// 主体背景色
		setBackgroundColor(0xffbbadc0); 
		
		setOnTouchListener(new View.OnTouchListener() {
			
			private float startx , offsetx ;
			private float starty , offsety ;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch(event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					startx = event.getX();
					starty = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetx = event.getX() - startx;
					offsety = event.getY() - starty;
					
					if(Math.abs(offsetx) > Math.abs(offsety))
					{
						if(offsetx < -5)
						{
							//  left
							swipeLeft();
						}else if(offsetx > 5){
							// right 
							swipeRight();
						}
					}else{
						if(offsety < -5)
						{
							// up ;
							swipeUp();
						}else if(offsety > 5){
							// down
							swipeDown();
						}
					}
					break;
				}
				return true;
			}
		});
	}
	
	
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		int cardWidth = (Math.min(w, h) - 10) / 4 ;
		addCards(cardWidth,cardWidth);
		
		startGame();  // 开始game
	}
	
	private void addCards(int cardWidth,int cardHeight)
	{
		Card c ;
		for(int y = 0 ; y < 4 ; y++)
		{
			for(int x = 0 ; x < 4 ; x++)
			{
				c = new Card(getContext());
				c.setNum(0);
				addView(c, cardWidth, cardHeight);
				
				cardsMap[x][y] = c ;
			}
		}
	}
	
	private void swipeLeft()
	{
		boolean merge = false ;  // 是否要产生新的
		for(int y = 0 ; y < 4 ; y++)
		{
			for(int x = 0 ; x < 4 ; x++)
			{
				
				for(int x1 = x + 1; x1 < 4; x1++)
				{
					if(cardsMap[x1][y].getNum() > 0){
						if(cardsMap[x][y].getNum() <= 0)
						{
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x-- ;
							merge = true ;
						}else if(cardsMap[x][y].equals(cardsMap[x1][y])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge = true ;
						}
						break ;
					}
				}
				
			}			
		}
		if(merge)
		{
			addRandomNum();
			CheckComplete();
		}
	}
	private void swipeRight()
	{
		boolean merge = false ;  // 是否要产生新的
		for(int y = 0 ; y < 4 ; y++)
		{
			for(int x = 3 ; x >= 0 ; x--)
			{
				
				for(int x1 = x - 1; x1 >= 0; x1--)
				{
					if(cardsMap[x1][y].getNum() > 0){
						if(cardsMap[x][y].getNum() <= 0)
						{
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x++ ;
							merge = true ;
						}else if(cardsMap[x][y].equals(cardsMap[x1][y])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge = true ;
						}
						break ;
					}
				}
				
			}
		}
		if(merge)
		{
			addRandomNum();
			CheckComplete();
		}
	}
	private void swipeUp()
	{
		boolean merge = false ;  // 是否要产生新的
		for(int x = 0 ; x < 4 ; x++)
		{
			for(int y = 0 ; y < 4 ; y++)
			{
				
				for(int y1 = y + 1; y1 < 4; y1++)
				{
					if(cardsMap[x][y1].getNum() > 0){
						if(cardsMap[x][y].getNum() <= 0)
						{
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y-- ;
							merge = true ;
						}else if(cardsMap[x][y].equals(cardsMap[x][y1])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge = true ;
						}
						break ;
					}
				}
				
			}
		}
		if(merge)
		{
			addRandomNum();
			CheckComplete();
		}
	}
	private void swipeDown()
	{
		boolean merge = false ;  // 是否要产生新的
		for(int x = 0 ; x < 4 ; x++)
		{
			for(int y = 3 ; y >= 0 ; y--)
			{
				
				for(int y1 = y - 1; y1 >= 0; y1--)
				{
					if(cardsMap[x][y1].getNum() > 0){
						if(cardsMap[x][y].getNum() <= 0)
						{
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y++ ;
							merge = true ;
						}else if(cardsMap[x][y].equals(cardsMap[x][y1])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge = true ;
						}
						break ;
					}
				}
				
			}
		}
		if(merge)
		{
			addRandomNum();
			CheckComplete();
		}
	}
	
	private Card[][] cardsMap = new Card[4][4] ;
	
	private List<Point> emptyPoints= new ArrayList<Point>();
	
	private void addRandomNum()
	{
		emptyPoints.clear();
		for(int y = 0 ; y < 4 ; y++)
		{
			for(int x = 0 ; x < 4 ; x++)
			{
				if(cardsMap[x][y].getNum() <= 0)
				{
					emptyPoints.add(new Point(x,y));
				}
			}
		}
		
		Point p = emptyPoints.remove(
				(int)(Math.random()*emptyPoints.size()));
		cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
	}
	
	private void startGame()
	{
		for(int y = 0 ; y < 4 ; y++)
		{
			for(int x = 0 ; x < 4 ; x++)
			{
				cardsMap[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}
	
	// 判断结束 满足 1 2就不结束 否则就结束
	// 1 有一个空位置
	// 2 四周有两个是相同的
	private void CheckComplete()
	{
		GameAlgorithm ga = new GameAlgorithm();
		
		int cmp = ga.isOver(cardsMap);
		
		if(cmp == -1){
			new AlertDialog.Builder(getContext())
			.setTitle("Sorry")
			.setMessage("Game over")
			.setPositiveButton("ReStart", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					startGame();
				}
			}).show();
		}
		
	}
}
