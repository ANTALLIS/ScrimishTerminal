import java.awt.*;
import java.util.*;

public class Player {
	String name;
	ArrayList<ArrayList<Card>> piles;
	static final int MAX_PILE_SIZE = 5;
	static final int num_of_piles = 5;
	public String marker = "----------------------------------------------------------------";

	
	public Player() {
		//No-arg constructor
		setName("No-name");
	}
	
	public Player(String name) {
		//Constructor
		setName(name);
	}
	
	public void setName(String my_name) {
		//Setter method to set name of card
		name = my_name;
	}
	
	public String getName() {
		//Getter method to return name of card
		return name;
	}
	
	private static void printRemainingCards(int[] AL) {
		//Used for the pileSetup Method
		System.out.print("[1] Dagger (" + AL[0] + ")  ");
		System.out.print("[2] Sword (" + AL[1] + ")  ");
		System.out.print("[3] Morning Star (" + AL[2] + ")  ");
		System.out.print("[4] War Axe (" + AL[3] + ")  ");
		System.out.print("[5] Halberd (" + AL[4] + ")  ");
		System.out.print("[6] Longsword (" + AL[5] + ")  ");
		System.out.print("[7] Archer (" + AL[6] + ")  ");
		System.out.println("[8] Shield (" + AL[7] + ")\n");

	}
	
	private static boolean checkArrayValue(int[] testingArray, int comp_number) {
		//Checks the all elements of testingArray are equal to comp_number
		for(int e : testingArray) {
			if(e != comp_number)
				return false;
		}
		return true;
	}
	
	public void resetPiles() {
		// Setting up the piles
		piles = new ArrayList<ArrayList<Card>>();
		for(int i = 0; i < num_of_piles; i++)
			piles.add(new ArrayList<Card>());

	}
	
	public ArrayList<ArrayList<Card>> getPiles() {
		//Returns an array of arrays containing the cards
		return piles;
	}

	public void printPiles() {
		//Prints every card in every piles, if piles is empty then just prints "Empty"
		int i = 1;
		System.out.println(name);
		System.out.println("Top --------------------------> Bottom");
		for(ArrayList<Card> e : piles) {
			System.out.print("\nPile " + i + ": ");
			if(e.isEmpty() == true) {
				System.out.print("Empty\n");
			} else {
				for(Card f : e) {
					System.out.print(f.getName() + "   ");
				}
				System.out.println("");
			}
		i++;
		}
		System.out.println("");

	}
	
	public void printTopCards() {
		//Prints only the top cards
		System.out.print(name + ": ");
		for(ArrayList<Card> e : piles) {
			if(e.isEmpty() == true)
				System.out.print("-------" + "  ");
			else
				System.out.print(e.get(0).getName() + "  ");
		}
		System.out.println("\n");
	}
	
	public void setupDefault() {
		//Default deck just for testing
		resetPiles();
		for(int i = 0; i < 4; i++)
			piles.get(0).add(new Card('1'));
		piles.get(0).add(new Card('C'));
		
		for(int i = 0; i < 4; i++)
			piles.get(1).add(new Card('2'));
		piles.get(1).add(new Card('1'));
		
		piles.get(2).add(new Card('4'));
		for(int i = 0; i < 3; i++)
			piles.get(2).add(new Card('3'));
		piles.get(2).add(new Card('2'));
		
		piles.get(3).add(new Card('6'));
		for(int i = 0; i < 2; i++)
			piles.get(3).add(new Card('5'));
		for(int i = 0; i < 2; i++)
			piles.get(3).add(new Card('4'));
		
		for(int i = 0; i < 2; i++)
			piles.get(4).add(new Card('A'));
		for(int i = 0; i < 2; i++)
			piles.get(4).add(new Card('S'));
		piles.get(4).add(new Card('6'));
	}
	
	public void randomDeck() {
		System.out.println("Hello World");
	}
	
	public void pileSetup() {
		//This allows the player to choose how to set up their piles
		System.out.println(name + " Start setting up your cards");
		this.resetPiles();
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int n = 0;
		int m;
		String tmp;
		Card tmp2;
		
		// Setting up the cards
		int[] remaining_cards = {5, 5, 3, 3, 2, 2, 2, 2};
		
		// Placing crown card
		while(true) {
			System.out.print("Where would you like the crown card (1 - 5): ");
			if(reader.hasNextInt()) {
				n = reader.nextInt();
			} else {
				reader.next();// To use up reader input token
				System.out.println("Not valid input\n");
				continue;
			}
			if(n >= 1 && n <= 5)
				break;
			else
				System.out.println("Error: Out of bounds\n");
		}
		piles.get(n-1).add(new Card('C'));
		System.out.println("Crown card added at " + n + "\n");
		
		// Setup card piles
		System.out.println("Start setting up your cards");
		while(Player.checkArrayValue(remaining_cards, 0) == false) {
			System.out.print("Choose pile (1 - 5), view remaining cards (v), view piles(p) or quit(q): ");
			if(reader.hasNextInt()) {
				n = reader.nextInt();
			} else {
				tmp = reader.next();
				if(tmp.equals("v")) {
					this.printRemainingCards(remaining_cards);
					continue;
				} else if(tmp.equals("p")) {
					this.printPiles();
					continue;
				} else if(tmp.equals("q")) {
					System.out.println("Bye!");
					System.exit(0);
				} else {
					System.out.println("Not valid input\n");
					continue;
				}
			}
			
			if(n < 1 || n > 5) {// Checking that n is within bounds
				System.out.println("Error: Out of bounds\n");
				continue;
			}
			if(piles.get(n-1).size() >= MAX_PILE_SIZE) {// Checking that the pile isn't maxed out
				System.out.println("Error: Pile is at max size\n");
				continue;
			}
			
			System.out.print("What card would you like to put there (1 - 8): ");
			if(reader.hasNextInt()) {
				m = reader.nextInt();
			} else {
				System.out.println("Error: Input not valid\n");
				continue;
			}
			if(m > 8 || m < 1) {
				System.out.println("Error: Out of bounds\n");
				continue;
			} else if (remaining_cards[m-1] == 0) {
				System.out.println("No more of that card\n");
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
					System.out.println("Option not valid\n");
					continue;

			}
			piles.get(n-1).add(0, tmp2);
			remaining_cards[m-1]--;
		}
		System.out.println("Done!");
	}
	
	public void removeCard(int pile_num) {
		//Method to remove the top card of a pile
		piles.get(pile_num).remove(0);
	}
	
	public boolean discardCard() {
		//Discard a card rather than attack this turn
		Scanner reader = new Scanner(System.in);
		int n;
		String o;
		while(true) {
			System.out.print("Choose pile (1 - 5) or (a)ttack: ");
			if(reader.hasNextInt()) {
				n = reader.nextInt();
				if(n > 5 || n < 1) {
					System.out.println("Out of bounds\n");
					continue;
				} else if(piles.get(n).isEmpty()) {
					System.out.println("Is empty\n");
					continue;
				}
				System.out.println("Discarded " + piles.get(n-1).get(0).getName());
				removeCard(n-1);
				return true;
			} else {
				o = reader.next();
				if(o.equals("a"))
					return false;
				System.out.println("Invalid input");
				continue;
			}
		}
	}
	
	public char attackPlayer(Player opp_player) {
		System.out.println(marker);
		//Returns char depending on the result. Also removes the losing cards
		System.out.println(name + " attacks " + opp_player.getName());
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int m = 0, n = 0;
		String o;
		while(true) {
			System.out.print("Choose your pile (1 - 5), (v)iew top cards, (d)iscard card or (q)uit: ");
			if(reader.hasNextInt()) {
				m = reader.nextInt() - 1;
				if(m > 4 || m < 0) {
					System.out.println("Out of bounds\n");
					continue;
				} else if(piles.get(m).isEmpty() == true) {
					System.out.println("That pile is empty.\n");
					continue;
				} else if (piles.get(m).get(0).equals(new Card('S'))) {
					System.out.println("Can't attack with shield\n");
					continue;
				}
			} else {
				o = reader.next();
				if(o.equals("q") || o.equals("Q")){
					System.out.println("\nByeeee!");
					System.exit(0);
				} else if(o.equals("v")) {
					printTopCards();
					continue;
				} else if(o.equals("d")) {
					if(discardCard())
						break;
					else
						continue;
				}
				System.out.println("Error: Input not valid\n");
				continue;
			}
			
			System.out.print("Choose opponent's pile (1 - 5) or (q)uit: ");
			if(reader.hasNextInt()) {
				n = reader.nextInt() - 1;
				if(n > 4 || n < 0) {
					System.out.println("Out of bounds\n");
					continue;
				} else if(opp_player.getPiles().get(n).isEmpty() == true) {
					System.out.println("That pile is empty\n");
					continue;
				}
			} else {
				o = reader.next();
				if(o.equals("q") || o.equals("Q")){
					System.out.println("\nByeeee!");
					System.exit(0);
				}
				System.out.println("Error: Input not valid\n");
				continue;
			}
			break;
		}
		System.out.println(marker);
		
		return findResult(opp_player, m, n);
	}

	public char findResult(Player opp_player, int m, int n) {

		Card p1_card = piles.get(m).get(0);
		Card p2_card = opp_player.getPiles().get(n).get(0);
		char result = p1_card.attack(p2_card);
		System.out.print("Pile " + (m+1) + ": " + p1_card.getName() + " attacks ");
		System.out.println("Pile " + (n+1) + ": " + p2_card.getName());
		System.out.println("result: " + result);
		
		switch(result) {
			case 'W':
				System.out.println(name + " wins!\n");
				return 'W';
			case 'w':
				System.out.println(opp_player.getName() + " loses their card\n");
				opp_player.removeCard(n);
				return 'w';
			case 'L':
				System.out.println(opp_player.getName() + " wins!\n");
				return 'L';
			case 'l':
				System.out.println(name + " loses their card");
				removeCard(m);
				return 'l';
			case 'r':
				System.out.println("Both players return their cards to their piles\n");
				return 'r';
			case 'd':
				System.out.println("Both players discard their cards\n");
				removeCard(m);
				opp_player.removeCard(n);
				return 'd';
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
