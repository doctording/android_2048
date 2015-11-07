package com.example.g2048;

/**
 * 游戏规则说明
 * @author dingwenlong
 *
 */
public class GameAlgorithm {

	/**
	 * 棋盘被数字填满，无法进行有效移动，判负，游戏结束
	 * 棋盘上出现2048，判胜，游戏结束
	 * @param cards
	 * @return  
	 * 1 胜利 而结束
	 * -1 失败而结束
	 * 0  并未结束			
	 */
	public int isOver(Card[][] cards)
	{
		for(int y = 0 ; y < 4 ; y ++)
		{
			for(int x = 0 ; x < 4 ; x++)
			{
				if(cards[x][y].getNum() == 2048)
				{
					return 1 ;
				}else if(cards[x][y].getNum() <= 0){
					return 0 ;
				}else{
					if(isRight(x , y-1) && cards[x][y-1].getNum() == cards[x][y].getNum())
					{
						return 0 ;
					}
					if(isRight(x , y+1) && cards[x][y+1].getNum() == cards[x][y].getNum())
					{
						return 0 ;
					}
					if(isRight(x-1 , y) && cards[x-1][y].getNum() == cards[x][y].getNum())
					{
						return 0 ;
					}
					if(isRight(x+1 , y) && cards[x+1][y].getNum() == cards[x][y].getNum())
					{
						return 0 ;
					}
				}
			}
		}
		return -1 ;
	}
	public boolean isRight(int x, int y)
	{
		if( x < 0 || x >= 4 || y < 0 || y >= 4)
			return false ;
		return true ;
	}
	
}
/*
游戏规则：

游戏初始化
　　开始时棋盘内随机出现两个数字，出现的数字仅可能为2或4

有效移动
　　玩家可以选择上下左右四个方向，若棋盘内的数字出现位移或合并，视为有效移动

合并规则
　　玩家选择的方向上若有相同的数字则合并，每次有效移动可以同时合并，但不可以连续合并

得分
　　合并所得的所有新生成数字想加即为该步的有效得分

随机产生使游戏能继续
　　玩家选择的方向行或列前方有空格则出现位移

　　每有效移动一步，棋盘的空位(无数字处)随机出现一个数字(依然可能为2或4)

游戏结束
　　棋盘被数字填满，无法进行有效移动，判负，游戏结束

　　棋盘上出现2048，判胜，游戏结束

*/


/*
switch (mNumber)
		{
		case 0:
			mBgColor = "#CCC0B3";
			break;
		case 2:
			mBgColor = "#EEE4DA";
			break;
		case 4:
			mBgColor = "#EDE0C8";
			break;
		case 8:
			mBgColor = "#F2B179";// #F2B179
			break;
		case 16:
			mBgColor = "#F49563";
			break;
		case 32:
			mBgColor = "#F5794D";
			break;
		case 64:
			mBgColor = "#F55D37";
			break;
		case 128:
			mBgColor = "#EEE863";
			break;
		case 256:
			mBgColor = "#EDB04D";
			break;
		case 512:
			mBgColor = "#ECB04D";
			break;
		case 1024:
			mBgColor = "#EB9437";
			break;
		case 2048:
			mBgColor = "#EA7821";
			break;
		default:
			mBgColor = "#EA7821";
			break;
		}
*/