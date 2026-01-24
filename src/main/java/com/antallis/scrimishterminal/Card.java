package com.antallis.scrimishterminal;

import java.util.ArrayList;
import java.util.Arrays;

public class Card {
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
          default:
            throw new UnsupportedOperationException("Weapon values must be between 1 and 6");
        }
        break;
    }
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

  public TextBlock toTextBlock() {
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
          default:
            throw new UnsupportedOperationException("Weapon value must be between 1 and 6");
        }
        break;
      default:
        throw new UnsupportedOperationException("Unknown card type");
    }

    result.add("╭" + value + "────╮");
    result.add("│     │");
    result.add("│" + names[0] + "│");
    result.add("│" + names[1] + "│");
    result.add("│" + names[2] + "│");
    result.add("│     │");
    result.add("╰────" + value + "╯");

    return new TextBlock(result);
  }

  @Override
  public String toString() {
    TextBlock result = toTextBlock();
    return result.toString();
  }

  public static TextBlock getTextBlockEmpty() {
    return new TextBlock(new ArrayList<>(Arrays.asList(
        "╭─────╮",
        "│╲   ╱│",
        "│ ╲ ╱ │",
        "│  ╳  │",
        "│ ╱ ╲ │",
        "│╱   ╲│",
        "╰─────╯")));
  }

  public static TextBlock getTextBlockHidden() {
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

    return new TextBlock(result, CARD_WIDTH);
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

  public static int getWidth() {
    return CARD_WIDTH;
  }
}
