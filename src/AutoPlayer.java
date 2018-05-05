import java.awt.*;
import java.util.*;

public class AutoPlayer extends Player {
	public String marker = "----------------------------------------------------------------";
	
	public AutoPlayer() {
		//No-arg constructor
		setName("No-name (AI)");
	}
	
	public AutoPlayer(String name) {
		//constructor
		setName(name + " (AI)");
	}
	
	public char attackPlayer(Player opp_player) {
		//Returns char depending on the result. Also removes the losing cards
		System.out.println(marker);
		System.out.println(name + " attacks " + opp_player.getName());
		int m, n;
		char result;
		
		while(true) {
			m = (int)(Math.random() * 5);
			n = (int)(Math.random() * 5);
			
			if(piles.get(m).isEmpty())
				continue; // If the pile is empty
			else if(opp_player.getPiles().get(n).isEmpty())
				continue; // If the opponent's pile is empty
			else if(piles.get(m).get(0).equals(new Card('S')))
				continue; // If the top card is a shield
			break;
		}

		System.out.print("Pile: ");
		System.out.println(m + 1);

		System.out.print("Opponent's pile: ");
		System.out.println(n + 1);
		result = findResult(opp_player, m, n);

		System.out.println(marker);
		return result;
	}
}
