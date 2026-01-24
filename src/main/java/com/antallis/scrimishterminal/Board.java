package com.antallis.scrimishterminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.antallis.scrimishterminal.PlayerMove.MoveType;

public class Board {
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

  public TextBlock toTextBlock() {
    TextBlock result = null;

    for (Stack s : stacks) {
      if (result == null) {
        result = s.toTextBlockUp();
      } else {
        TextBlock tb = s.toTextBlockUp();
        result = result.addRight(tb);
      }
    }

    return result;
  }

  @Override
  public String toString() {
    return toTextBlock().toString();
    // StringBuilder sb = new StringBuilder();
    //
    // ArrayList<TextBlock> printableStacks = new ArrayList<>();
    //
    // for (Stack s : stacks) {
    // printableStacks.add(s.toTextBlockUp());
    // }
    //
    // boolean exitLoop = false;
    // while (!exitLoop) {
    // exitLoop = true;
    // for (int stackNum = 0; stackNum < MAX_SIZE; stackNum++) {
    // String myPiece;
    // try {
    // myPiece = printableStacks.get(stackNum).removeFirst();
    // exitLoop = false;
    // } catch (Exception e) {
    // myPiece = Card.getEmptyCardWidth();
    // }
    // sb.append(myPiece);
    // sb.append(' ');
    // }
    // sb.append('\n');
    // }
    //
    // return sb.toString();
  }

  public String toStringHidden() {
    TextBlock result = null;

    for (Stack s : stacks) {
      if (result == null) {
        result = s.getTextBlockHidden();
      } else {
        TextBlock tb = s.getTextBlockHidden();
        result = result.addRight(tb);
      }
    }

    return result.toString();
    // StringBuilder sb = new StringBuilder();
    //
    // ArrayList<ArrayList<String>> printableStacks = new ArrayList<>();
    //
    // for (Stack s : stacks) {
    // printableStacks.add(s.getPrintableHidden());
    // }
    //
    // boolean exitLoop = false;
    // while (!exitLoop) {
    // exitLoop = true;
    // for (int stackNum = 0; stackNum < MAX_SIZE; stackNum++) {
    // String myPiece;
    // try {
    // myPiece = printableStacks.get(stackNum).removeFirst();
    // exitLoop = false;
    // } catch (Exception e) {
    // myPiece = Card.getEmptyCardWidth();
    // }
    // sb.append(myPiece);
    // sb.append(' ');
    // }
    // sb.append('\n');
    // }
    //
    // return sb.toString();
  }

  public String deckToStringFull() {
    TextBlock result = null;

    for (Stack s : deck.values()) {
      if (result == null) {
        result = s.toTextBlock();
      } else {
        TextBlock tb = s.toTextBlock();
        result = result.addRightBottom(tb);
      }
    }

    return result.toString();

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

  public void setup(Scanner sc, String name) {
    boolean crownHasBeenPlaced = false;
    boolean printStatus = true;
    while (!isDeckEmpty()) {
      if (printStatus) {
        // Show status
        ScrimishTerminal.clearScreen();
        System.out.println(name + " Setup");
        System.out.println("Your stacks:");
        System.out.println(toString());
        System.out.println("Cards left to pick from:");
        System.out.println(deckToStringFull());
        System.out.println("Which card (1 - 6, A, S, C) and in which column do you want to place it (1 - 5)?");
        printStatus = false;
      }

      // Get player input
      System.out.print("> ");
      String inputLine = sc.nextLine();
      PlayerMove playerMove = PlayerMove.parseSetup(inputLine);

      if (playerMove.getMoveType() == MoveType.EMPTY) {
        continue;
      } else if (playerMove.getMoveType() == MoveType.INVALID) {
        System.out.println("Invalid choice");
        continue;
      } else if (playerMove.getMoveType() == MoveType.QUIT) {
        System.out.println("Quitting...");
        System.exit(0);
      }

      String selectedCardType = playerMove.getSourceStr();
      if (!crownHasBeenPlaced && !selectedCardType.equals("c")) {
        System.out.println("Crown card must be placed first");
        continue;
      }

      int selectedBoardColumn = playerMove.getTargetIndex() - 1;

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
      if (selectedCardType.equals("c")) {
        crownHasBeenPlaced = true;
      }
      printStatus = true;
    }
  }

  public int getMinStackIndex() {
    return 0;
  }

  public int getMaxStackIndex() {
    return MAX_SIZE - 1;
  }
}
