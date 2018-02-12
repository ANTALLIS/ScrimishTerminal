import java.awt.*;
import java.util.*;

public class Player {
	private String name;
	private ArrayList<ArrayList<Card>> piles;
	
	public Player(String name, int num_of_piles) {
		setName(name);
		pileSetup(num_of_piles);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	private void printRemainingCards(ArrayList<Card> AL) {
		String output = "";
		System.out.print("Remaining cards: ");
		for(Card e : AL) {
			output += (e.getName() + ", ");
		}
		System.out.println(output.substring(0, s.length - 2));
	}
	
	public void pileSetup(int num_of_piles) {
		piles = new ArrayList<ArrayList<Card>>();
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int n;
		// Setting up the cards
		ArrayList<Card> remaining_cards = new ArrayList<Card>();
		for(int i = 0; i < 5; i++) {
			remaining_cards.add(new Card('1'));
			remaining_cards.add(new Card('2'));
		}
		for(int i = 0; i < 3; i++) {
			remaining_cards.add(new Card('3'));
			remaining_cards.add(new Card('4'));
		}
		for(int i = 0; i < 2; i++) {
			remaining_cards.add(new Card('5'));
			remaining_cards.add(new Card('6'));
			remaining_cards.add(new Card('A'));
			remaining_cards.add(new Card('S'));

		}
		
		// Setting up the piles
		for(int i = 0; i < num_of_piles; i++) {
			piles.add(new ArrayList<Card>());
		}
		
		// Placing crown card
		while(true) {
			System.out.print("Where would you like the crown card (0 - " + (num_of_piles-1) + "): ");
			n = reader.nextInt();
			if(n < num_of_piles && n > 0)
				break;
			System.out.println("Error: Out of bounds");
		}
		
		piles.get(n).add(new Card('C')); // Scans the next token of the input as an int.
		
		// Setup card piles
		System.out.println("Start setting up your cards");
		while(remaining_cards > 0) {
			
		}
		System.out.println("Done!");
	}
	
	public ArrayList<ArrayList<Card>> getPiles() {
		return piles;
	}

	public void printPiles() {
		int i = 0;
		for(ArrayList<Card> e : piles) {
			System.out.print("Pile " + i + ": ")
			for(Card f : e) {
				System.out.print(e.getName() + ", ");
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
