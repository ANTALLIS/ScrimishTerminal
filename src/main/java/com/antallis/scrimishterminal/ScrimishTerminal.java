package com.antallis.scrimishterminal;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.antallis.scrimishterminal.PlayerMove.MoveType;

/**
 *
 * @author antallis
 */
public class ScrimishTerminal {
  static void printHelp() {
    System.out
        .println("Usage: scrimish --ascii --debug --no-colour --players 2 --server player names, colors AI selection");
  }

  static void printVersion() {
    System.out.println("Version 0.2.0, written by Antonio Allis");
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
    String gapStr = "    ";
    String vsStr = " VS ";

    var aTextBlock = a.toTextBlock();
    var bTextBlock = b.toTextBlock();
    ArrayList<String> middleArrayList = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      if (i == 7 / 2) {
        middleArrayList.add(vsStr);
      } else {
        middleArrayList.add(gapStr);
      }
    }
    TextBlock result = aTextBlock;
    result = result.addRight(new TextBlock(middleArrayList));
    result = result.addRight(bTextBlock);
    return result.toString();
  }

  static void printCardVsCard(Card a, Card b) {
    System.out.println(cardVsCardToString(a, b));
  }

  static String getLastMoveIndicator(int oppStackIndex, int myStackIndex) {
    int halfWidth = Card.getWidth() / 2;
    String topRow = "";
    String bottomRow = "";
    if (myStackIndex >= 0) {
      topRow = new String(new char[(myStackIndex - 1) * (Card.getWidth() + 1) + halfWidth]).replace('\0', ' ') + "Ʌ";
    }
    if (oppStackIndex >= 0) {
      bottomRow = new String(new char[(oppStackIndex - 1) * (Card.getWidth() + 1) + halfWidth]).replace('\0', ' ')
          + "V";
    }
    return topRow + "\n" + bottomRow;
  }

  public static void main(String[] args) {
    // GameOptions opts = GameOptions.parseArgs(args);
    // if (opts.getPrintHelpText()) {
    // printHelp();
    // System.exit(0);
    // }
    //
    // if (opts.getPrintVersionText()) {
    // printVersion();
    // System.exit(0);
    // }

    String fancyText = "           ┏┓   •   • ┓ \n" +
        "           ┗┓┏┏┓┓┏┳┓┓┏┣┓\n" +
        "Welcome to ┗┛┗┛ ┗┛┗┗┗┛┛┗";
    System.out.println(fancyText);
    System.out.println("Press enter to start.");

    Scanner sc = new Scanner(System.in);
    sc.nextLine();

    ArrayList<Player> players = new ArrayList<>();
    players.add(new Player("Princess Bubblegum", Color.BLUE));
    players.add(new Player("Marceline", Color.YELLOW));

    for (Player player : players) {
      // player.setup(sc);
      player.demoSetup();
    }

    // Game loop
    Player winningPlayer = null;
    int playerIndex = 0;
    int opponentIndex = 1;

    boolean sameTurn = false;
    String lastResult = "";
    String moveIndicator = "";

    boolean running = true;
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
        System.out.println(lastResult + "\n\n");
        // Show hidden opponents cards
        System.out.println(opponent.toStringHidden());
        System.out.println(moveIndicator);
        // Show own cards
        System.out.println(player.toString());
        System.out.println("\n\n");
        System.out.println(
            "Pick one of your own stacks (1 - 5) and one of the opponents (1 - 5), discard (d) a card, or quit (q)");
        sameTurn = true;
      }

      // Get player choice
      System.out.print("> ");
      String inputLine = sc.nextLine();
      PlayerMove playerMove = PlayerMove.parseAttack(inputLine);

      switch (playerMove.getMoveType()) {
        case MoveType.EMPTY:
        case MoveType.PLACE:
          continue;
        case MoveType.QUIT:
          System.exit(0);
        case MoveType.INVALID:
          System.out.println("Not a valid choice");
          continue;
        case MoveType.DISCARD:
          int discardStackIndex = playerMove.getTargetIndex() - 1;
          if (discardStackIndex < player.getBoard().getMinStackIndex() ||
              discardStackIndex > player.getBoard().getMaxStackIndex()) {
            System.out.println("Stack index out of bounds");
            continue;
          }
          if (player.getBoard().isEmpty(discardStackIndex)) {
            System.out.println("Cannot discard from an empty stack");
            continue;
          }
          // Card myCard = player.getBoard().discard(discardStackIndex);
          // lastResult = player.getName() + " discarded " + myCard.getCardName() + " from
          // stack "
          player.getBoard().discard(discardStackIndex);
          lastResult = player.getName() + " discarded a card from stack "
              + playerMove.getTargetStr();
          moveIndicator = getLastMoveIndicator(-1, playerMove.getTargetIndex());
          break;
        case MoveType.ATTACK:
          int ownStack = playerMove.getSourceIndex() - 1;
          int otherStack = playerMove.getTargetIndex() - 1;

          // Check result
          Card ownCard;
          Card oppCard;
          try {
            ownCard = player.getBoard().peekCard(ownStack);
            oppCard = opponent.getBoard().peekCard(otherStack);
            if (ownCard.getCardType() == CardType.SHIELD) {
              System.out.println("Cannot attack with shield card");
              continue;
            }
          } catch (NoSuchElementException e) {
            System.out.println("One or both stacks is empty");
            continue;
          } catch (Exception e) {
            System.out.println("Column number out of range");
            continue;
          }
          String cardVsStr = cardVsCardToString(ownCard, oppCard);
          // System.out.println(cardVsStr);
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

          String ownStackStr = playerMove.getSourceStr();
          String otherStackStr = playerMove.getTargetStr();
          cardVsStr = "Stack " + ownStackStr + " vs " + "Stack " + otherStackStr + "\n" + cardVsStr;
          lastResult = cardVsStr + "\n" + lastResult;
          moveIndicator = getLastMoveIndicator(playerMove.getTargetIndex(), playerMove.getSourceIndex());
          break;
      }
      // Check win
      if (winningPlayer != null) {
        System.out.println(ColorTerminal.changeColor(Color.RED) + winningPlayer.getName() + " Wins!"
            + ColorTerminal.setColorToDefault());
        break;
      }
      System.out.println(lastResult);
      System.out.println("");
      System.out.println("Press ENTER to end your turn");
      sc.nextLine();
      // switch players
      playerIndex += 1;
      opponentIndex += 1;
      playerIndex %= players.size();
      opponentIndex %= players.size();
      sameTurn = false;
    }
  }
}
