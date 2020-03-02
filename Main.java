package pkg;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		GameManager gM = new GameManager();
		gM.startGame(3,4);
		
	}
}