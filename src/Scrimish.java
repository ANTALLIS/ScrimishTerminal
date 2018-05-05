import java.util.*;
import java.io.IOException;
import javax.swing.*;

public class Scrimish {
	public void startGame() throws IOException {
		Player p1 = new Player("Bob");
		AutoPlayer p2 = new AutoPlayer("Dave");
		int turn = 1;
		char result = 'W';
		
		p1.setName(JOptionPane.showInputDialog("Your name:"));
		p2.setName("Dave");
		
		p1.pileSetup();
		p2.setupDefault();

		Scanner s = new Scanner(System.in);

		while(true) {
			p1.printTopCards();
			p2.printTopCards();
			
			if(turn == 1) {
				result = p1.attackPlayer(p2);
				turn = 2;
			} else if(turn == 2) {
				result = p2.attackPlayer(p1);
				turn = 1;
			}
			
			if(result == 'W' || result == 'L')
				break;
			
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
