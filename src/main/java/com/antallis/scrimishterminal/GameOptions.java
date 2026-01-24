package com.antallis.scrimishterminal;

public class GameOptions {
  private int numberOfPlayers;
  private String[] playerNames;
  private Color[] playerColors;
  private int numberOfAI;
  private int[] AILevels;
  private boolean isServer;
  private boolean printHelpText;
  private boolean printVersionText;
  private boolean withColour;
  private boolean onlyASCII;
  private boolean showAllCards;

  private final int DEFAULT_NUMBER_OF_PLAYERS = 2;
  private final String[] DEFAULT_PLAYER_NAMES = { "Finn", "Jake", "Marceline", "Princess Bubblegum",
      "Lumpy Space Princess", "The Lich", "Simon", "BMO" };
  private final int DEFAULT_NUMBER_OF_AI = 0;

  private GameOptions(int numberOfPlayers, String[] playerNames) {
    // .println("Usage: scrimish --ascii --debug --no-colour --players 2 --server
    // player names, colors AI selection");
    // teams?
    this.numberOfPlayers = DEFAULT_NUMBER_OF_PLAYERS;
    this.playerNames = DEFAULT_PLAYER_NAMES;
    this.numberOfAI = DEFAULT_NUMBER_OF_AI;
    this.AILevels = new int[] {};
    this.isServer = false;
    this.printHelpText = false;
    this.printVersionText = false;
    this.withColour = true;
    this.onlyASCII = false;
    this.showAllCards = false;
  }

  // TODO: Look into the picocli library.
  public static GameOptions parseArgs(String[] args) {
    GameOptions opts = new GameOptions(2, new String[] {});
    for (String arg : args) {
      switch (arg) {
        case "--help":
        case "-h":
          opts.printHelpText = true;
          break;
        case "--version":
        case "-v":
          opts.printVersionText = true;
          break;
        case "--debug":
        case "-d":
          opts.showAllCards = true;
          break;
      }
    }
    return opts;
  }

  public int getNumberOfPlayers() {
    return numberOfPlayers;
  }

  public String[] getPlayerNames() {
    return playerNames;
  }

  public int getNumberOfAI() {
    return numberOfAI;
  }

  public boolean getPrintHelpText() {
    return printHelpText;
  }

  public boolean getPrintVersionText() {
    return printVersionText;
  }
}
