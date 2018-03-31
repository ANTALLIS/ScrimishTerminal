import java.awt.*;
import java.util.*;

public class AutoPlayer extends Player {
	private String name;
	private ArrayList<ArrayList<Card>> piles;
	private static final int MAX_PILE_SIZE = 5;
	private static final int num_of_piles = 5;
	
	public AutoPlayer(String name) {
		super(name);
	}
	
	public char attackPlayer(Player opp_player) {
		//Returns char depending on the result. Also removes the losing cards
		System.out.println(name + "(AI) attacks " + opp_player.getName());

		int n, m;
		n = (int)(Math.random() * 5);
		m = (int)(Math.random() * 5);
		System.out.println("Choose your pile (1 - 5) or (q)uit: " + (n+1));
		System.out.println("Choose opponent's pile (1 - 5) or (q)uit: " + (m+1));

		return findResult(opp_player, m, n);
	}
}
