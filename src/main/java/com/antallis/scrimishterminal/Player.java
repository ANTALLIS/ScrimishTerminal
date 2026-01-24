package com.antallis.scrimishterminal;

import java.util.Scanner;

public class Player {
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

  public void randomSetup() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public String toString() {
    return ColorTerminal.changeColor(color) + board.toString() + ColorTerminal.setColorToDefault();
  }

  public String toStringHidden() {
    return ColorTerminal.changeColor(color) + board.toStringHidden() + ColorTerminal.setColorToDefault();
  }
}
