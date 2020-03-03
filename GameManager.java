package pkg;

import java.util.Scanner;


public class GameManager {
	private int r;
	private int c;
	private boolean loop = true;
	private boolean lose = false;
	private boolean win = false;
	Board hidBoard;
	Board board;
	Scanner input = new Scanner(System.in);
	
	public void startGame(int r, int c) 
	{
		this.r = r;
		this.c = c;
		System.out.print("\n\n-------Welcome to Minesweeper-------\n\n");
		setDifficulty(); // for now this does nothing, difficulty will be constant
		
		this.hidBoard = new Board(r,c);
		hidBoard.build(1);
		hidBoard.genBombs();
		hidBoard.updateNum();
//		hidBoard.printBoard();
		
		this.board = new Board(r, c);
		board.build(2);
		// before this print we must have dialog for difficulty
		board.printBoard();
		while(loop = true) {
			userIn();
			board.printBoard();
			if(loop == false)
				break;
		}
	}
	
	public void setDifficulty() {
//		System.out.print("Enter easy, medium, or hard:\n");
//		String def = input.nextLine().toLowerCase();
//		if (def.equals("easy") == false)
//		{
//				System.out.println("easy");
//		}
//		
		// should return the difficulty here, doing it later.
	}
	
	public void userIn() 
	{
		String type;
		String rs;
		String cs;
		System.out.println("Enter <f> for flag or <m> to make a move:");
		type = input.nextLine();
		System.out.println("Enter the row:");
		rs = input.nextLine();
		System.out.println("Enter the column:");
		cs = input.nextLine();
		int r = Integer.parseInt(rs); // this is bcz nextInt skips the next nextLine, so made them all Strings then converted them to integers
		int c = Integer.parseInt(cs);

		if(r<1 | r>this.r | c<1 | c>this.c) {
			System.out.print("Not valid, try again");
			userIn();
		}else {
		if (type.equals("m"))
			nextMove(r, c);
		else if(type.equals("f"))
			addFlag(r, c);
		}
	}
	
	public void nextMove(int r, int c) 
	{
		r = r-1;
		c = c-1;
		
		String val = hidBoard.getValues(r, c);
		board.updateCell(r, c, val);
		if (val.equals("*")) {
			loop = false;
			System.out.printf("%n%n%n-------You Lost-------%n%n");
		}
		else {
			checkZeros(val);
			winCheck();
		}
	}
	
	public void addFlag(int r, int c) 
	{
		board.updateCell(r-1, c-1, "F");
	}

	public void checkZeros(String val) 
	{
		if (val.equals("0")) 
		{
			while(true) 
			{
				String[][] temp = board.getCopy();
				for(int i = 0; i<r; i++)
				{
					for(int j = 0; j<c; j++)
					{
						if(board.getValues(i, j).equals("0"))
						{
							updateZeros(i,j);
						}
					}
				}
				if(board.compare(temp)== true) 
				{
					break;
				}

			}

		}
	}

	public void updateZeros(int i, int j) {
		// upper row
		if(i-1>=0) 
		{
			if(j-1>=0)
			{
				if(hidBoard.getValues(i-1, j-1).equals("*") == false)
					board.updateCell(i-1, j-1, hidBoard.getValues(i-1, j-1));
			}
			if(hidBoard.getValues(i-1, j).equals("*") == false)
				board.updateCell(i-1, j, hidBoard.getValues(i-1, j));
			
			if(j+1 < c)
			{
				if(hidBoard.getValues(i-1, j+1).equals("*") == false)
					board.updateCell(i-1, j+1, hidBoard.getValues(i-1, j+1));
			}
		}
		// same row
		if(j-1>=0)
		{
			if(hidBoard.getValues(i, j-1).equals("*") == false)
				board.updateCell(i, j-1, hidBoard.getValues(i, j-1));
		}
		
		if(j+1 < c)
		{
			if(hidBoard.getValues(i, j+1).equals("*") == false)
				board.updateCell(i, j+1, hidBoard.getValues(i, j+1));
		}
		// bottom row
		if(i+1<r) 
		{
			if(j-1>=0)
			{
				if(hidBoard.getValues(i+1, j-1).equals("*") == false)
					board.updateCell(i+1, j-1, hidBoard.getValues(i+1, j-1));
			}
			if(hidBoard.getValues(i+1, j).equals("*") == false)
				board.updateCell(i+1, j, hidBoard.getValues(i+1, j));
			
			if(j+1 < c)
			{
				if(hidBoard.getValues(i+1, j+1).equals("*") == false)
					board.updateCell(i+1, j+1, hidBoard.getValues(i+1, j+1));
			}
		}

	}
	
	public void winCheck() 
	{
		int dim = r*c;
		for(int i = 0; i<r; i++)
		{
			for(int j = 0; j<c; j++)
			{
				String a = board.getValues(i, j);
				String b = hidBoard.getValues(i, j);
				if(b.equals(a)) {
					dim -= 1;
				}
				if(b.equals("*")) {
					dim -= 1;
				}
			}
		}
		if (dim == 0) {
			System.out.printf("%n%n%n-------You Win--------%n%n");
			loop = false;
		}
	}
}