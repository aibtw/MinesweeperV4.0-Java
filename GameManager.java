package pkg;

public class GameManager {
	public void startGame(int r, int c) {
		Board hidBoard = new Board(r,c);
		hidBoard.build(1);
		hidBoard.genBombs();
		hidBoard.updateNum();
		hidBoard.printBoard();
		
		Board board = new Board(r, c);
	}
}
