import java.awt.*;
import java.util.*;

public class Player {
	private String name;
	private ArrayList<ArrayList<Card>> piles;
	private static final int MAX_PILE_SIZE = 5;
	private static final int num_of_piles = 5;
	
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
		System.out.println("[8] Shield (" + AL[7] + ")\n");

	}
	
	private static boolean checkArrayValue(int[] testingArray, int comp_number) {
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
		int i = 1;
		System.out.println("Top --------------------------> Bottom");
		for(ArrayList<Card> e : piles) {
			System.out.print("\nPile " + i + ": ");
			for(Card f : e) {
				System.out.print(f.getName() + "   ");
			}
			System.out.println("");
		i++;
		}
	}
	
	public void printTopCards() {
		System.out.println("Top cards of piles:");
		int i = 1;
		for(ArrayList<Card> e : piles) {
			System.out.print(i + ":" + e.get(0).getName() + "  ");
			i++;
		}
		System.out.println("");
	}
	
	public void pileSetup() {
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
		piles.get(pile_num).remove(0);
	}
	
	public char attackPlayer(Player opp_player) {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int n, m;
		while(true) {
			System.out.print("Choose your pile (1 - 5): ");
			if(reader.hasNextInt()) {
				n = reader.nextInt();
				if(n > 5 || n < 1) {
					System.out.println("Out of bounds");
					continue;
				} else if(piles.get(n).isEmpty() == true) {
					System.out.println("That pile is empty.");
				}
			} else {
				System.out.println("Error: Input not valid\n");
				reader.next();
				continue;
			}
			
			System.out.print("Choose opponent's pile (1 - 5): ");
			if(reader.hasNextInt()) {
				m = reader.nextInt();
				if(m > 5 || m < 1) {
					System.out.println("Out of bounds");
					continue;
				} else if(opp_player.getPiles().get(m).isEmpty() == true) {
					System.out.println("That pile is empty");
					continue;
				}
			} else {
				System.out.println("Error: Input not valid\n");
				reader.next();
				continue;
			}
			break;
		}

		Card p1_card = piles.get(n-1).get(0);
		Card p2_card = opp_player.getPiles().get(0).get(m-1);
		char result = p1_card.attack(p2_card);
		System.out.print("Pile " + n + ": " + p1_card.getName() + " attacks ");
		System.out.println("Pile " + m + ": " + p2_card.getName());
		System.out.println("result: " + result);
		switch(result) {
			case 'W':
				System.out.println("Player " + name + " wins!");
				return 'W';
			case 'w':
				System.out.println("Opponent loses their card");
				opp_player.removeCard(n-1);
				break;
			case 'L':
				System.out.println("Player " + name + " loses!");
				return 'L';
			case 'l':
				System.out.println("You lose your card");
				this.removeCard(m-1);
				break;
			case 'r':
				System.out.println("Both players return their cards to their piles");
				break;
			case 'd':
				System.out.println("Both players discard their cards");
				this.removeCard(n-1);
				opp_player.removeCard(m-1);
				break;
			case 'e':
				System.out.println("Error e!");
				System.exit(0);
			default:
				System.out.println("Error default!");
				System.exit(0);
		}
		return 'x';
	}
}
