import java.awt.*;
import java.util.*;

public class Player {
	private String name;
	private ArrayList<ArrayList<Card>> piles;
	private static final int MAX_PILE_SIZE = 5;
	
	public Player(String name) {
		setName(name);
		pileSetup();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	private static void printRemainingCards(int[] AL) {
		System.out.print("[1] Dagger (" + AL[0] + ")  ");
		System.out.print("[2] Sword (" + AL[1] + ")  ");
		System.out.print("[3] Morning Star (" + AL[2] + ")  ");
		System.out.print("[4] War Axe (" + AL[3] + ")  ");
		System.out.print("[5] Halberd (" + AL[4] + ")  ");
		System.out.print("[6] Longsword (" + AL[5] + ")  ");
		System.out.print("[7] Archer (" + AL[6] + ")  ");
		System.out.println("[8] Shield (" + AL[7] + ")");

	}
	
	private static boolean checkArrayValue(int[] testingArray, int comp_number) {
		for(int e : testingArray) {
			if(e != comp_number)
				return false;
		}
		return true;
	}
	
	public void pileSetup() {
		final int num_of_piles = 5;
		piles = new ArrayList<ArrayList<Card>>();
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int n, m;
		String tmp;
		
		// Setting up the cards
		int[] remaining_cards = {5, 5, 3, 3, 2, 2, 2, 2};
		
		// Setting up the piles
		for(int i = 0; i < num_of_piles+1; i++) {
			piles.add(new ArrayList<Card>());
		}
		
		// Placing crown card
		while(true) {
			System.out.print("Where would you like the crown card (1 - 5): ");
			n = reader.nextInt();
			if(n <= 5 && n >= 1)
				break;
			System.out.println("Error: Out of bounds");
		}
		
		piles.get(n-1).add(new Card('C')); // Scans the next token of the input as an int.
		
		// Setup card piles
		System.out.println("Start setting up your cards");
		while(Player.checkArrayValue(remaining_cards, 0) == false) {
			System.out.print("Choose pile (1 - 5) or view remaining cards (v): ");
			tmp = reader.next();
			Card tmp2;
			if(tmp.equals("v")) {
				Player.printRemainingCards(remaining_cards);
				continue;
			} else if (!tmp.matches("[0-9+]")) {
				System.out.println("Option not valid");
				continue;
			}
			n = Integer.parseInt(tmp) + 1;
			if(n < 1 || n > 6) {// Checking that n is within bounds
				System.out.println("Error: Out of bounds");
				continue;
			} else if(piles.get(n).size() > MAX_PILE_SIZE) {// Checking that the pile isn't maxed out
				System.out.println("Error: Pile is at max size");
				continue;
			}
			System.out.print("What card would you like to put there (1 - 8): ");
			tmp = reader.next();
			if (!tmp.matches("[0-9+]")){
				System.out.println("Option not valid");
				continue;
			}
			m = Integer.parseInt(tmp);
			if(m > 8 || m < 1) {
				System.out.println("Error: Out of bounds");
				continue;
			} else if (remaining_cards[m-1] == 0) {
				System.out.println("No more of that card");
				continue;
			}
			switch(m) {
				case 1:
					tmp2 = new Card('1');
					break;
				case 2:
					tmp2 = new Card('2');
					break;
				case 3:
					tmp2 = new Card('3');
					break;
				case 4:
					tmp2 = new Card('4');
					break;
				case 5:
					tmp2 = new Card('5');
					break;
				case 6:
					tmp2 = new Card('6');
					break;
				case 7:
					tmp2 = new Card('A');
					break;
				case 8:
					tmp2 = new Card('S');
					break;
				default:
					System.out.println("Option not valid");
					continue;

			}
			piles.get(n-1).add(tmp2);
			remaining_cards[m-1]--;
		}
		System.out.println("Done!");
	}
	
	public ArrayList<ArrayList<Card>> getPiles() {
		return piles;
	}

	public void printPiles() {
		int i = 0;
		for(ArrayList<Card> e : piles) {
			System.out.print("Pile " + i + ": ");
			for(Card f : e) {
				System.out.print(f.getName() + ", ");
			}
		i++;
		}
	}
	
	public void printTopCards() {
		for(ArrayList<Card> e : piles) {
			System.out.print(e.get(0).getName() + ", ");
		}
	}
}
