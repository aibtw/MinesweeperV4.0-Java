package pkg;

import java.util.Random;

public class Board 
{
	private int r;
	private int c;
	private String board[][];
	private Random rnd = new Random();
	private int bombsNum = 9;
	
	public Board (int r, int c)
	{
		this.r = r;
		this.c = c;
		board = new String[r][c];
	}
	
	public void build(int cond)
	{
		for(int i = 0; i<r; i++)
		{
			for (int j = 0; j<c; j++)
			{
				if(cond == 1) // make hidden board
					board[i][j] = "0";
				else // make known board
					board[i][j] = "-";
			}
		}
	}
	
	public void genBombs() 
	{
		int placedB = 0;
		while (placedB < bombsNum)
		{
			int x = rnd.nextInt(r);
			int y = rnd.nextInt(c);
			if (board[x][y] != "*")
			{
				updateCell(x,y,"*");
				placedB ++;
			}
		}
	}
	
	public void printBoard()
	{
		for(int i = 0; i<r; i++)
		{
			for (int j = 0; j<c; j++)
			{
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public void updateCell(int r, int c, String v)
	{
		if (v != null)
		board[r][c] = v;
	}
	
	public void updateNum() 
	{
		for(int i = 0; i<r; i++)
		{
			for(int j = 0; j<c; j++)
			{
				if(board[i][j] == "*")
				{
					updateAdj(i,j);
				}
			}
			
		}
	}

	public void updateAdj(int i,int j) 
	{
		// upper row
		if(i-1>=0) 
		{
			if(j-1>=0)
			{
				String s = conv(board[i-1][j-1]); 
				updateCell(i-1,j-1, s);
			}
			String s = conv(board[i-1][j]); 
			updateCell(i-1,j, s);
			
			if(j+1 < c)
			{
				s = conv(board[i-1][j+1]); 
				updateCell(i-1,j+1, s);
			}
		}
		// same row
		if(j-1>=0)
		{
			String s = conv(board[i][j-1]); 
			updateCell(i,j-1, s);
		}
		
		if(j+1 < c)
		{
			String s = conv(board[i][j+1]); 
			updateCell(i,j+1, s);
		}
		// bottom row
		if(i+1<r) 
		{
			if(j-1>=0)
			{
				String s = conv(board[i+1][j-1]); 
				updateCell(i+1,j-1, s);
			}
			String s = conv(board[i+1][j]); 
			updateCell(i+1,j, s);
			
			if(j+1 < c)
			{
				s = conv(board[i+1][j+1]); 
				updateCell(i+1,j+1, s);
			}
		}
	}
	
	public String conv(String s) 
	{
		try {
		int temp = Integer.parseInt(s);
		temp ++;
		s = Integer.toString(temp);
		return s;
		}
		catch(Exception NumberFormatException) 
		{
			return null;
		}
	}
	
}

	

