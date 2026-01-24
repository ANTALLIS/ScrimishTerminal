package com.antallis.scrimishterminal;

import java.util.Scanner;

public class PlayerMove {
  public enum MoveType {
    EMPTY,
    ATTACK,
    DISCARD,
    PLACE,
    QUIT,
    INVALID,
  }

  public final MoveType moveType;
  public final String sourceStr;
  public final String targetStr;
  public final int sourceIndex;
  public final int targetIndex;

  private PlayerMove(
      MoveType moveType,
      String sourceStackStr,
      String targetStackStr,
      int sourceStackIndex,
      int targetStackIndex) {
    this.moveType = moveType;
    this.sourceStr = sourceStackStr;
    this.targetStr = targetStackStr;
    this.sourceIndex = sourceStackIndex;
    this.targetIndex = targetStackIndex;
  }

  private PlayerMove(MoveType moveType) {
    this.moveType = moveType;
    this.sourceStr = null;
    this.targetStr = null;
    this.sourceIndex = 0;
    this.targetIndex = 0;
  }

  public static PlayerMove parseAttack(String inputLine) {
    inputLine = inputLine.trim();

    if (inputLine == null) {
      return new PlayerMove(MoveType.INVALID);
    } else if (inputLine.isEmpty()) {
      return new PlayerMove(MoveType.EMPTY);
    }

    Scanner sc = new Scanner(inputLine);

    if (!sc.hasNext()) {
      return new PlayerMove(MoveType.INVALID);
    }
    String firstToken = sc.next().toLowerCase();

    if (firstToken.equals("q")) {
      return new PlayerMove(MoveType.QUIT);
    }

    if (!sc.hasNext()) {
      return new PlayerMove(MoveType.INVALID);
    }
    String secondToken = sc.next().toLowerCase();

    int targetStackIndex;
    try {
      targetStackIndex = Integer.parseInt(secondToken);
    } catch (Exception e) {
      return new PlayerMove(MoveType.INVALID);
    }

    if (firstToken.equals("d")) {
      return new PlayerMove(MoveType.DISCARD, null, secondToken, 0, targetStackIndex);
    }

    int sourceStackIndex;
    try {
      sourceStackIndex = Integer.parseInt(firstToken);
      return new PlayerMove(MoveType.ATTACK, firstToken, secondToken, sourceStackIndex, targetStackIndex);
    } catch (Exception e) {
      return new PlayerMove(MoveType.INVALID);
    }
  }

  public static PlayerMove parseSetup(String inputLine) {
    inputLine = inputLine.trim();

    if (inputLine == null) {
      return new PlayerMove(MoveType.INVALID);
    } else if (inputLine.isEmpty()) {
      return new PlayerMove(MoveType.EMPTY);
    }

    Scanner sc = new Scanner(inputLine);

    if (!sc.hasNext()) {
      return new PlayerMove(MoveType.INVALID);
    }
    String firstToken = sc.next().toLowerCase();

    if (firstToken.equals("q")) {
      return new PlayerMove(MoveType.QUIT);
    }

    if (!sc.hasNext()) {
      return new PlayerMove(MoveType.INVALID);
    }
    String secondToken = sc.next().toLowerCase();

    int targetStackIndex;
    try {
      targetStackIndex = Integer.parseInt(secondToken);
    } catch (Exception e) {
      return new PlayerMove(MoveType.INVALID);
    }

    return new PlayerMove(MoveType.PLACE, firstToken, secondToken, 0, targetStackIndex);
  }

  public MoveType getMoveType() {
    return moveType;
  }

  public String getSourceStr() {
    return sourceStr;
  }

  public String getTargetStr() {
    return targetStr;
  }

  public int getSourceIndex() {
    return sourceIndex;
  }

  public int getTargetIndex() {
    return targetIndex;
  }
}
