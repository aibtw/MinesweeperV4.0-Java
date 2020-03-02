package pkg;

import java.util.Scanner;


public class GameManager {
	private int r;
	private int c;
	private boolean loop = true;
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
		while(true) {
			userIn();
//			hidBoard.printBoard();
			board.printBoard();
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
	}
	
	public void addFlag(int r, int c) 
	{
		board.updateCell(r-1, c-1, "F");
	}
}
