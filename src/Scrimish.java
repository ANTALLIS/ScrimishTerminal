import java.util.*;
import java.io.IOException;

public class Scrimish {
	public void startGame() throws IOException {
		Player p1 = new Player("Bob");
		AutoPlayer p2 = new AutoPlayer("Dave");
		String marker = "------------------------------------------------------------------------------";
		int turn = 1;
		char result = 'W';
		
		p1.setName("Bob");
		p2.setName("Dave");
		
		p1.setupDefault();
		p2.setupDefault();

		Scanner s = new Scanner(System.in);

		while(true) {
			p1.printPiles();
			p2.printPiles();
			
			if(turn == 1) {
				result = p1.attackPlayer(p2);
				turn = 2;
			} else if(turn == 2) {
				result = p2.attackPlayer(p1);
				turn = 1;
			}
			
			if(result == 'W' || result == 'L')
				break;
			
			System.out.println(marker);
			System.in.read();
		}
	}
	
	public static void main(String[] args) {
		Scrimish game1 = new Scrimish();
		try {
			game1.startGame();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
