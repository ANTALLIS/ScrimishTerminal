/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.antallis.scrimishterminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

enum Color {
  // ANSI color codes
  RESET("\033[0m"),
  BLACK("\033[0;30m"),
  RED("\033[0;31m"),
  GREEN("\033[0;32m"),
  YELLOW("\033[0;33m"),
  BLUE("\033[0;34m"),
  MAGENTA("\033[0;35m"),
  CYAN("\033[0;36m"),
  WHITE("\033[0;37m"),

  // Bright colors
  BRIGHT_BLACK("\033[0;90m"),
  BRIGHT_RED("\033[0;91m"),
  BRIGHT_GREEN("\033[0;92m"),
  BRIGHT_YELLOW("\033[0;93m"),
  BRIGHT_BLUE("\033[0;94m"),
  BRIGHT_MAGENTA("\033[0;95m"),
  BRIGHT_CYAN("\033[0;96m"),
  BRIGHT_WHITE("\033[0;97m");

  private final String code;

  Color(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }
}

class ColorTerminal {
  /**
   * Changes the text color for subsequent text
   * 
   * @param colorCode The ANSI color code
   * @return The escape sequence for the specified color
   */
  public static String changeColor(Color colorCode) {
    return colorCode.toString();
  }

  /**
   * Resets text color to default terminal color
   * 
   * @return The reset escape sequence
   */
  public static Color setColorToDefault() {
    return Color.RESET;
  }

  /**
   * Prints text with specified color and automatically resets
   * 
   * @param text      The text to print
   * @param colorCode The ANSI color code
   */
  public static void printColored(String text, Color colorCode) {
    System.out.print(colorCode + text + Color.RESET);
  }

  /**
   * Prints text with specified color and automatically resets, with newline
   * 
   * @param text      The text to print
   * @param colorCode The ANSI color code
   */
  public static void printlnColored(String text, Color colorCode) {
    System.out.println(colorCode + text + Color.RESET);
  }
}

enum CardType {
  CROWN, WEAPON, ARCHER, SHIELD,
}

enum AttackResult {
  EQUAL, WIN, LOSE, BOUNCE;
}

class Card {
  private CardType cardType;
  private int cardValue;
  private String cardName;
  private static final int CARD_WIDTH = 7;

  public static int cardBack = 2;

  public Card(String cardValue) {
    switch (cardValue) {
      case "c":
      case "C":
        this.cardName = "Crown";
        this.cardType = CardType.CROWN;
        this.cardValue = 0;
        break;
      case "a":
      case "A":
        this.cardName = "Archer";
        this.cardType = CardType.ARCHER;
        this.cardValue = 0;
        break;
      case "s":
      case "S":
        this.cardName = "Shield";
        this.cardType = CardType.SHIELD;
        this.cardValue = 0;
        break;
      default:
        this.cardType = CardType.WEAPON;
        int rank = Integer.parseInt(cardValue);
        this.cardValue = rank;
        switch (rank) {
          case 1:
            this.cardName = "Dagger";
            break;
          case 2:
            this.cardName = "Sword";
            break;
          case 3:
            this.cardName = "Morning Star";
            break;
          case 4:
            this.cardName = "War Axe";
            break;
          case 5:
            this.cardName = "Halberd";
            break;
          case 6:
            this.cardName = "Longsword";
            break;
        }
        break;
    }
  }

  // Copy constructor
  public Card(Card other) {
    this.cardValue = other.cardValue;
  }

  public CardType getCardType() {
    return cardType;
  }

  public int getCardValue() {
    return cardValue;
  }

  public String getCardName() {
    return cardName;
  }

  public ArrayList<String> toPrintable() {
    ArrayList<String> result = new ArrayList<>();

    String value = "∅";
    String[] names = { "     ", "     ", "     " };
    switch (cardType) {
      case CROWN:
        value = "C";
        names[1] = "CROWN";
        break;
      case ARCHER:
        value = "A";
        names[0] = "ARC  ";
        names[1] = "  HER";
        break;
      case SHIELD:
        value = "S";
        names[0] = "SHI  ";
        names[1] = "  ELD";
        break;
      case WEAPON:
        value = Integer.toString(cardValue);
        switch (cardValue) {
          case 1:
            names[0] = "DAG  ";
            names[1] = "  GER";
            break;
          case 2:
            names[1] = "SWORD";
            break;
          case 3:
            names[0] = "MORN ";
            names[1] = " ING ";
            names[2] = " STAR";
            break;
          case 4:
            names[0] = "WAR  ";
            names[1] = "  AXE";
            break;
          case 5:
            names[0] = "HAL  ";
            names[1] = " BERD";
            break;
          case 6:
            names[0] = "LONG ";
            names[1] = "SWORD";
            break;
        }
        break;
    }

    result.add("╭" + value + "────╮");
    result.add("│     │");
    result.add("│" + names[0] + "│");
    result.add("│" + names[1] + "│");
    result.add("│" + names[2] + "│");
    result.add("│     │");
    result.add("╰────" + value + "╯");

    return result;
  }

  @Override
  public String toString() {
    ArrayList<String> stringRep = toPrintable();
    String result = String.join("\n", stringRep);
    return result;
  }

  public static ArrayList<String> getPrintableEmpty() {
    ArrayList<String> result = new ArrayList<>();

    result.add("╭─────╮");
    result.add("│╲   ╱│");
    result.add("│ ╲ ╱ │");
    result.add("│  ╳  │");
    result.add("│ ╱ ╲ │");
    result.add("│╱   ╲│");
    result.add("╰─────╯");

    return result;
  }

  public static ArrayList<String> getPrintableHidden() {
    ArrayList<String> result = new ArrayList<>();

    switch (cardBack) {
      case 0:
        result.add("╭─────╮");
        result.add("│🮐🮐🮐🮐🮐│");
        result.add("│🮐🮐🮐🮐🮐│");
        result.add("│🮐🮐🮐🮐🮐│");
        result.add("│🮐🮐🮐🮐🮐│");
        result.add("│🮐🮐🮐🮐🮐│");
        result.add("╰─────╯");
        break;

      case 1:
        result.add("╭─────╮");
        result.add("│'''''│");
        result.add("│'''''│");
        result.add("│'''''│");
        result.add("│'''''│");
        result.add("│'''''│");
        result.add("╰─────╯");
        break;

      case 2:
        result.add("╭─────╮");
        result.add("│🭞🭜🭘🭈🭆│");
        result.add("│🭈🭆🭂█🭞│");
        result.add("│█🭞🭜🭘🭈│");
        result.add("│🭘🭈🭆🭂🭞│");
        result.add("│🭂🭞🭜🭘🭈│");
        result.add("╰─────╯");
        break;
    }

    return result;
  }

  public static String getEmptyCardWidth() {
    StringBuilder sb = new StringBuilder("");
    for (int i = 0; i < CARD_WIDTH; i++) {
      sb.append(' ');
    }
    return sb.toString();
  }

  public AttackResult strongerThan(Card other) {
    switch (cardType) {
      case CardType.CROWN:
        if (other.cardType == CardType.CROWN) {
          return AttackResult.WIN;
        }
        return AttackResult.LOSE;
      case CardType.ARCHER:
        if (other.cardType == CardType.SHIELD) {
          return AttackResult.BOUNCE;
        }
        return AttackResult.WIN;
      case CardType.SHIELD:
        throw new UnsupportedOperationException("Shield cards cannot be used to attack.");
      case CardType.WEAPON:
        switch (other.cardType) {
          case CardType.CROWN:
            return AttackResult.WIN;
          case CardType.ARCHER:
            return AttackResult.WIN;
          case CardType.SHIELD:
            return AttackResult.EQUAL;
          case CardType.WEAPON:
            if (cardValue > other.cardValue) {
              return AttackResult.WIN;
            } else if (cardValue < other.cardValue) {
              return AttackResult.LOSE;
            } else {
              return AttackResult.EQUAL;
            }
        }
      default:
        throw new AssertionError("Unreachable");
    }
  }

  public boolean isCrownCard() {
    return cardType == CardType.CROWN;
  }
}

class Stack {
  private static final int MAX_SIZE = 5;
  private ArrayList<Card> cards;

  public Stack() {
    this.cards = new ArrayList<Card>();
  }

  public Card popCard() {
    if (cards.isEmpty()) {
      throw new NoSuchElementException("Stack is empty");
    }
    return cards.removeLast();
  }

  public void pushCard(Card myCard) {
    if (cards.size() >= MAX_SIZE) {
      throw new IllegalStateException("Stack is full (maximum " + MAX_SIZE + " cards)");
    }
    cards.add(myCard);
  }

  public Card peekCard() throws NoSuchElementException {
    if (cards.isEmpty()) {
      throw new NoSuchElementException("Stack is empty");
    }
    return cards.getLast();
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  public boolean isFull() {
    return cards.size() >= MAX_SIZE;
  }

  public int size() {
    return cards.size();
  }

  public ArrayList<String> toPrintableDown() {
    if (isEmpty()) {
      return getPrintableEmpty();
    }

    ArrayList<String> result = new ArrayList<>();

    int i = 0;
    for (; i < size() - 1; i++) {
      Card c = cards.get(i);
      result.add(c.toPrintable().getFirst());
    }
    Card c = cards.get(i);
    result.addAll(c.toPrintable());

    return result;
  }

  public ArrayList<String> toPrintableUp() {
    if (isEmpty()) {
      return getPrintableEmpty();
    }

    ArrayList<String> result = new ArrayList<>();
    List<Card> revCards = cards.reversed();

    Card c = revCards.get(0);
    result.addAll(c.toPrintable());

    for (int i = 1; i < size(); i++) {
      c = revCards.get(i);
      result.add(c.toPrintable().getLast());
    }

    return result;
  }

  public ArrayList<String> toPrintable() {
    return toPrintableDown();
  }

  @Override
  public String toString() {
    ArrayList<String> stringRep = toPrintable();
    String result = String.join("\n", stringRep);
    return result;
  }

  public static ArrayList<String> getPrintableEmpty() {
    ArrayList<String> result = Card.getPrintableEmpty();
    return result;
  }

  public ArrayList<String> getPrintableHiddenDown() {
    if (isEmpty()) {
      return getPrintableEmpty();
    }

    ArrayList<String> result = new ArrayList<>();

    for (int i = 0; i < size() - 1; i++) {
      result.add(Card.getPrintableHidden().getFirst());
    }
    result.addAll(Card.getPrintableHidden());

    return result;
  }

  public ArrayList<String> getPrintableHidden() {
    return getPrintableHiddenDown();
  }
}

class Board {
  private static final int MAX_SIZE = 5;
  private ArrayList<Stack> stacks;
  public HashMap<String, Stack> deck;

  public Board() {
    this.stacks = new ArrayList<Stack>();
    for (int i = 0; i < MAX_SIZE; i++) {
      stacks.add(new Stack());
    }

    deck = new HashMap<>();

    Stack crownStack = new Stack();
    Card crownCard = new Card("C");
    crownStack.pushCard(crownCard);
    deck.put("c", crownStack);

    Stack daggerStack = new Stack();
    for (int i = 0; i < 5; i++) {
      Card daggerCard = new Card("1");
      daggerStack.pushCard(daggerCard);
    }
    deck.put("1", daggerStack);

    Stack swordStack = new Stack();
    for (int i = 0; i < 5; i++) {
      Card swordCard = new Card("2");
      swordStack.pushCard(swordCard);
    }
    deck.put("2", swordStack);

    Stack morningStarStack = new Stack();
    for (int i = 0; i < 3; i++) {
      Card morningStarCard = new Card("3");
      morningStarStack.pushCard(morningStarCard);
    }
    deck.put("3", morningStarStack);

    Stack warAxeStack = new Stack();
    for (int i = 0; i < 3; i++) {
      Card warAxeCard = new Card("4");
      warAxeStack.pushCard(warAxeCard);
    }
    deck.put("4", warAxeStack);

    Stack halberdStack = new Stack();
    for (int i = 0; i < 2; i++) {
      Card halberdCard = new Card("5");
      halberdStack.pushCard(halberdCard);
    }
    deck.put("5", halberdStack);

    Stack longSwordStack = new Stack();
    for (int i = 0; i < 2; i++) {
      Card longSwordCard = new Card("6");
      longSwordStack.pushCard(longSwordCard);
    }
    deck.put("6", longSwordStack);

    Stack archerStack = new Stack();
    for (int i = 0; i < 2; i++) {
      Card archerCard = new Card("A");
      archerStack.pushCard(archerCard);
    }
    deck.put("a", archerStack);

    Stack shieldStack = new Stack();
    for (int i = 0; i < 2; i++) {
      Card shieldCard = new Card("S");
      shieldStack.pushCard(shieldCard);
    }
    deck.put("s", shieldStack);
  }

  public Card popCard(int columnNumber) {
    if (columnNumber >= MAX_SIZE || columnNumber < 0) {
      throw new NoSuchElementException("ColumnNumber out of bounds");
    }
    Stack myStack = stacks.get(columnNumber);
    if (myStack.isEmpty()) {
      throw new NoSuchElementException("Stack is empty");
    }
    return myStack.popCard();
  }

  public Card discard(int columnNumber) {
    return popCard(columnNumber);
  }

  public Card discardOnPurpose(int columnNumber) {
    Card myCard = stacks.get(columnNumber).peekCard();
    if (myCard.getCardType() == CardType.CROWN) {
      throw new UnsupportedOperationException("CROWN card cannot be discarded");
    }
    return popCard(columnNumber);
  }

  public void pushCard(int columnNumber, Card myCard) {
    if (columnNumber >= MAX_SIZE || columnNumber < 0) {
      throw new NoSuchElementException("ColumnNumber out of bounds");
    }
    Stack myStack = stacks.get(columnNumber);
    myStack.pushCard(myCard);
  }

  public Card peekCard(int columnNumber) throws NoSuchElementException {
    if (columnNumber >= MAX_SIZE || columnNumber < 0) {
      throw new NoSuchElementException("ColumnNumber out of bounds");
    }
    Stack myStack = stacks.get(columnNumber);
    return myStack.peekCard();
  }

  public boolean isEmpty(int columnNumber) {
    if (columnNumber >= MAX_SIZE || columnNumber < 0) {
      throw new NoSuchElementException("ColumnNumber out of bounds");
    }
    Stack myStack = stacks.get(columnNumber);
    return myStack.isEmpty();
  }

  public boolean isFull(int columnNumber) {
    if (columnNumber >= MAX_SIZE || columnNumber < 0) {
      throw new NoSuchElementException("ColumnNumber out of bounds");
    }
    Stack myStack = stacks.get(columnNumber);
    return myStack.isFull();
  }

  public int size(int columnNumber) {
    if (columnNumber >= MAX_SIZE || columnNumber < 0) {
      throw new NoSuchElementException("ColumnNumber out of bounds");
    }
    Stack myStack = stacks.get(columnNumber);
    return myStack.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    ArrayList<ArrayList<String>> printableStacks = new ArrayList<>();

    for (Stack s : stacks) {
      printableStacks.add(s.toPrintableUp());
    }

    boolean exitLoop = false;
    while (!exitLoop) {
      exitLoop = true;
      for (int stackNum = 0; stackNum < MAX_SIZE; stackNum++) {
        String myPiece;
        try {
          myPiece = printableStacks.get(stackNum).removeFirst();
          exitLoop = false;
        } catch (Exception e) {
          myPiece = Card.getEmptyCardWidth();
        }
        sb.append(myPiece);
        sb.append(' ');
      }
      sb.append('\n');
    }

    return sb.toString();
  }

  public String toStringHidden() {
    StringBuilder sb = new StringBuilder();

    ArrayList<ArrayList<String>> printableStacks = new ArrayList<>();

    for (Stack s : stacks) {
      printableStacks.add(s.getPrintableHidden());
    }

    boolean exitLoop = false;
    while (!exitLoop) {
      exitLoop = true;
      for (int stackNum = 0; stackNum < MAX_SIZE; stackNum++) {
        String myPiece;
        try {
          myPiece = printableStacks.get(stackNum).removeFirst();
          exitLoop = false;
        } catch (Exception e) {
          myPiece = Card.getEmptyCardWidth();
        }
        sb.append(myPiece);
        sb.append(' ');
      }
      sb.append('\n');
    }

    return sb.toString();
  }

  public String deckToStringFull() {
    StringBuilder sb = new StringBuilder();

    ArrayList<ArrayList<String>> printableStacks = new ArrayList<>();

    ArrayList<Stack> myStacks = new ArrayList<>(deck.values());
    for (Stack s : myStacks) {
      if (s.isEmpty()) {
        continue;
      }
      printableStacks.add(s.toPrintable());
    }

    boolean exitLoop = false;
    while (!exitLoop) {
      exitLoop = true;
      for (int stackNum = 0; stackNum < myStacks.size(); stackNum++) {
        String myPiece;
        try {
          myPiece = printableStacks.get(stackNum).removeFirst();
          exitLoop = false;
        } catch (Exception e) {
          myPiece = Card.getEmptyCardWidth();
        }
        sb.append(myPiece);
        sb.append(' ');
      }
      sb.append('\n');
    }

    return sb.toString();
  }

  public String deckToStringShort() {
    StringBuilder sb = new StringBuilder();
    // ArrayList<Stack> myStacks = new ArrayList<>(deck.values());
    var myStacks = new ArrayList<>(deck.keySet());

    for (var stackType : myStacks) {
      System.out.print(stackType + ": ");
      int output;
      try {
        var stack = deck.get(stackType);
        output = stack.size();
      } catch (Exception e) {
        output = 0;
      }
      System.out.println(output);
    }

    return sb.toString();
  }

  public boolean isDeckEmpty() {
    ArrayList<Stack> stacks = new ArrayList<Stack>(deck.values());
    for (Stack stack : stacks) {
      if (!stack.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  public void setup(Scanner sc) {
    boolean crownHasBeenPlaced = false;
    boolean printStatus = true;
    while (!isDeckEmpty()) {
      if (printStatus) {
        // Show status
        ScrimishTerminal.clearScreen();
        System.out.println("Your stacks:");
        System.out.println(toString());
        System.out.println("Cards left to pick from:");
        System.out.println(deckToStringFull());
        printStatus = false;
      }

      // Get player input
      System.out.println("Which card (1 - 6, A, S, C) and in which column do you want to place it (1 - 5) ");
      System.out.print("> ");
      String selectedCardType = sc.next().toLowerCase();
      if (selectedCardType.equals("q")) {
        System.out.println("Quitting...");
        System.exit(0);
      }
      String selectedBoardColumnStr = sc.next();
      if (!crownHasBeenPlaced && !selectedCardType.equals("c")) {
        System.out.println("Crown card must be placed first");
        continue;
      }
      crownHasBeenPlaced = true;

      int selectedBoardColumn;

      // Try to parse the column into a string.
      try {
        selectedBoardColumn = Integer.parseInt(selectedBoardColumnStr) - 1;
      } catch (Exception e) {
        System.out.println("Column must be a number");
        continue;
      }

      if (selectedBoardColumn < 0 || selectedBoardColumn >= MAX_SIZE) {
        System.out.println("Please select a column from 1 - " + Integer.toString(MAX_SIZE));
        continue;
      }

      Stack fromStack = deck.get(selectedCardType);
      Stack toStack = stacks.get(selectedBoardColumn);
      if (fromStack == null) {
        System.out.println("Not a valid card type");
        continue;
      }
      if (fromStack.isEmpty()) {
        System.out.println("Cannot chose an empty stack");
        continue;
      } else if (toStack.isFull()) {
        System.out.println("Cannot chose a full stack");
        continue;
      }
      var myCard = fromStack.popCard();
      toStack.pushCard(myCard);
      printStatus = true;
    }
  }
}

class Player {
  private String name;
  private Board board;
  private Color color;

  public Player(String name, Color color) {
    this.name = name;
    this.color = color;
    this.board = new Board();
  }

  public String getName() {
    return name;
  }

  public Board getBoard() {
    return board;
  }

  public void setup(Scanner sc) {
    board.setup(sc);
  }

  public void demoSetup() {
    String cardString = "c 3\n" +
        "1 1\n" +
        "1 1\n" +
        "1 1\n" +
        "1 1\n" +

        "2 2\n" +
        "2 2\n" +
        "2 2\n" +
        "2 2\n" +
        "2 2\n" +

        "1 3\n" +
        "3 3\n" +
        "3 3\n" +
        "4 3\n" +
        "4 1\n" +

        "4 4\n" +
        "5 4\n" +
        "5 4\n" +
        "6 4\n" +
        "6 4\n" +

        "3 5\n" +
        "a 5\n" +
        "a 5\n" +
        "s 5\n" +
        "s 5\n";
    Scanner sc = new Scanner(cardString);
    board.setup(sc);
  }

  @Override
  public String toString() {
    return ColorTerminal.changeColor(color) + board.toString();
  }

  public String toStringHidden() {
    return ColorTerminal.changeColor(color) + board.toStringHidden();
  }
}

/**
 *
 * @author antallis
 */
public class ScrimishTerminal {
  static void printHelp() {
    // System.out.println("Usage: scrimish --ascii --debug --no-colour --players 2
    // --server");
  }

  static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  static String padRight(String s, int n) {
    return String.format("%-" + n + "s", s);
  }

  static void printConfirmation(String name) {
    System.out.println("" +
        "\n\n\n\n\n\n" +
        "      ┌───────────────────────────────────────────────────────┐\n" +
        "      │                                                       │\n" +
        "      │                                                       │\n" +
        "      │                  Player " + padRight(name, 30) + "│\n" +
        "      │            Press enter to start your turn.            │\n" +
        "      │                                                       │\n" +
        "      │                                                       │\n" +
        "      └───────────────────────────────────────────────────────┘\n" +
        "");
  }

  static String cardVsCardToString(Card a, Card b) {
    StringBuilder result = new StringBuilder();
    var aPrintable = a.toPrintable();
    var bPrintable = b.toPrintable();
    int midpoint = aPrintable.size() / 2;
    String gapStr = "      ";
    String vsStr = "  VS  ";
    for (int i = 0; i < aPrintable.size(); i++) {
      if (i == midpoint) {
        result.append(aPrintable.get(i) + vsStr + bPrintable.get(i) + "\n");
      } else {
        result.append(aPrintable.get(i) + gapStr + bPrintable.get(i) + "\n");
      }
    }
    return result.toString();
  }

  static void printCardVsCard(Card a, Card b) {
    System.out.println(cardVsCardToString(a, b));
  }

  public static void main(String[] args) {
    String fancyText = "𝔰𝔠𝔯𝔦𝔪𝔦𝔰𝔥";
    String fancyTextTwo = "𝖘𝖈𝖗𝖎𝖒𝖎𝖘𝖍";
    String fancyTextThree = "𝓼𝓬𝓻𝓲𝓶𝓲𝓼𝓱";
    String fancyTextFour = "𝓈𝒸𝓇𝒾𝓂𝒾𝓈𝒽";
    String fancyTextFive = "ꜱᴄʀɪᴍɪꜱʜ";
    String fancyTextSix = "𝔖𝔠𝔯𝔦𝔪𝔦𝔰𝔥";
    String fancyTextSeven = "𝕾𝖈𝖗𝖎𝖒𝖎𝖘𝖍";
    String fancyTextEight = "  _________            .__        .__       .__     \n" +
        " /   _____/ ___________|__| _____ |__| _____|  |__  \n" +
        " \\_____  \\_/ ___\\_  __ \\  |/     \\|  |/  ___/  |  \\ \n" +
        " /        \\  \\___|  | \\/  |  Y Y  \\  |\\___ \\|   Y  \\\n" +
        "/_______  /\\___  >__|  |__|__|_|  /__/____  >___|  /\n" +
        "        \\/     \\/               \\/        \\/     \\/ ";
    String fancyTextNine = "┏┓   •   • ┓ \n" +
        "┗┓┏┏┓┓┏┳┓┓┏┣┓\n" +
        "┗┛┗┛ ┗┛┗┗┗┛┛┗";
    System.out.println(fancyTextNine);

    ArrayList<Player> players = new ArrayList<>();
    players.add(new Player("Antonio", Color.BLUE));
    players.add(new Player("Unamta", Color.YELLOW));

    Scanner sc = new Scanner(System.in);

    for (Player player : players) {
      player.setup(sc);
      // player.demoSetup();
    }

    System.out.println(players.get(0).getBoard().toString());
    System.out.println(players.get(0).getBoard().deckToStringFull());

    // Game loop
    boolean running = true;
    Player winningPlayer = null;
    int playerIndex = 0;
    int opponentIndex = 1;

    boolean sameTurn = false;
    String lastResult = "";
    String cardVsStr = "";
    while (running) {
      Player player = players.get(playerIndex);
      Player opponent = players.get(opponentIndex);

      if (!sameTurn) {
        // Clear screen
        clearScreen();
        // Wait for player confirmation
        printConfirmation(player.getName());
        sc.nextLine();
        clearScreen();
        System.out.println("LAST TURN:");
        System.out.println(lastResult);
        System.out.println(cardVsStr);
        // Show hidden opponents cards
        System.out.println(opponent.toStringHidden());
        // Show own cards
        System.out.println(player.toString());
      }
      // Get player choice
      int ownStack;
      int otherStack;
      System.out.println("Pick one of your own stacks (1 - 5) and one of the opponents (1 - 5)");
      System.out.print("> ");
      String ownStackStr = sc.next();
      // Player wants to exit
      if (ownStackStr.equals("q")) {
        System.exit(0);
      }
      String otherStackStr = sc.next();
      // Consume the newline char
      sc.nextLine();
      if (ownStackStr.equals("d")) {
        System.out.println("Discarding not implemented yet sorry (>_<)");
        sameTurn = true;
        continue;
      }
      try {
        ownStack = Integer.parseInt(ownStackStr) - 1;
        otherStack = Integer.parseInt(otherStackStr) - 1;
      } catch (Exception e) {
        System.out.println("Not a valid stack index");
        sameTurn = true;
        continue;
      }
      // Check result
      Card ownCard;
      Card oppCard;
      try {
        ownCard = player.getBoard().peekCard(ownStack);
        oppCard = opponent.getBoard().peekCard(otherStack);
        if (ownCard.getCardType() == CardType.SHIELD) {
          System.out.println("Cannot attack with shield card");
          sameTurn = true;
          continue;
        }
      } catch (NoSuchElementException e) {
        System.out.println("One or both stacks is empty");
        sameTurn = true;
        continue;
      } catch (Exception e) {
        System.out.println("Column number out of range");
        sameTurn = true;
        continue;
      }
      cardVsStr = cardVsCardToString(ownCard, oppCard);
      System.out.println(cardVsStr);
      var result = ownCard.strongerThan(oppCard);
      switch (result) {
        case AttackResult.WIN:
          lastResult = "WIN: " + ownCard.getCardName() + " beats " + oppCard.getCardName();
          if (opponent.getBoard().discard(otherStack).isCrownCard()) {
            winningPlayer = player;
          }
          break;
        case AttackResult.LOSE:
          lastResult = "LOSE: " + oppCard.getCardName() + " beats " + ownCard.getCardName();
          if (player.getBoard().discard(ownStack).isCrownCard()) {
            winningPlayer = opponent;
          }
          break;
        case AttackResult.EQUAL:
          lastResult = "EQUAL: Both " + ownCard.getCardName() + " and " + oppCard.getCardName() + " are discarded";
          if (opponent.getBoard().discard(otherStack).isCrownCard()) {
            winningPlayer = player;
          } else if (player.getBoard().discard(ownStack).isCrownCard()) {
            winningPlayer = opponent;
          }
          break;
        case AttackResult.BOUNCE:
          lastResult = "BOUNCE: Both " + ownCard.getCardName() + " and " + ownCard.getCardName()
              + " are returned to their piles";
          break;
      }
      // Check win
      if (winningPlayer != null) {
        System.out.println(ColorTerminal.changeColor(Color.RED) + winningPlayer.getName() + " Wins!"
            + ColorTerminal.setColorToDefault());
        break;
      }
      System.out.println(lastResult);
      cardVsStr = ownStackStr + " vs " + otherStackStr + "\n" + cardVsStr;
      System.out.println("Press ENTER to end your turn");
      sc.nextLine();
      // switch players
      playerIndex += 1;
      opponentIndex += 1;
      playerIndex %= players.size();
      opponentIndex %= players.size();
      sameTurn = false;
    }

    sc.close();
  }
}
