import java.awt.*;
import java.util.*;

public class AutoPlayer extends Player {
	private String name;
	private ArrayList<ArrayList<Card>> piles;
	private static final int MAX_PILE_SIZE = 5;
	private static final int num_of_piles = 5;
	
	public AutoPlayer(String choice) {
		if(choice.equals("random"))
			randomDeck();
		else if(choice.equals("default"))
			setupDefault();
		else
			setupDefault();
	}
	
	public char attackPlayer(Player opp_player) {
		//Returns char depending on the result. Also removes the losing cards
		System.out.println(name + " attacks " + opp_player.getName());

		int n, m;
		n = (int)(Math.random() * 5);
		m = (int)(Math.random() * 5);
		System.out.print("Choose your pile (1 - 5) or (q)uit: " + (n+1));
		System.out.print("Choose opponent's pile (1 - 5) or (q)uit: " + (m+1));

		Card p1_card = piles.get(n).get(0);
		Card p2_card = opp_player.getPiles().get(m).get(0);
		char result = p1_card.attack(p2_card);
		System.out.print("Pile " + (n+1) + ": " + p1_card.getName() + " attacks ");
		System.out.println("Pile " + (m+1) + ": " + p2_card.getName());
		System.out.println("result: " + result);
		
		switch(result) {
			case 'W':
				System.out.println("Player " + name + " wins!\n");
				return 'W';
			case 'w':
				System.out.println("Opponent loses their card\n");
				opp_player.removeCard(m);
				break;
			case 'L':
				System.out.println("Player " + name + " loses!\n");
				return 'L';
			case 'l':
				System.out.println("You lose your card");
				removeCard(n);
				break;
			case 'r':
				System.out.println("Both players return their cards to their piles\n");
				break;
			case 'd':
				System.out.println("Both players discard their cards\n");
				removeCard(n);
				opp_player.removeCard(m);
				break;
			case 'e':
				System.out.println("Error e!\n");
				System.exit(0);
			default:
				System.out.println("Error default!\n");
				System.exit(0);
		}
		return 'x';
		
	}
}
