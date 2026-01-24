package com.antallis.scrimishterminal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.antallis.scrimishterminal.PlayerMove.MoveType;

public class PlayerMoveTest {
  @Test
  public void firstTest() {
    System.out.println("firstTest");
    assertEquals(true, true);
  }

  @Test
  public void parseAttackWithInvalidTest() {
    String input = "X X\n";
    PlayerMove actual = PlayerMove.parseAttack(input);
    assertEquals(MoveType.INVALID, actual.getMoveType());
  }

  @Test
  public void parseAttackWithQuitTest() {
    String input = "q q\n";
    PlayerMove actual = PlayerMove.parseAttack(input);
    assertEquals(MoveType.QUIT, actual.getMoveType());
    input = "q\n";
    actual = PlayerMove.parseAttack(input);
    assertEquals(MoveType.QUIT, actual.getMoveType());
  }

  @Test
  public void parseAttackWithDiscardTest() {
    String input = "d 3\n";
    PlayerMove actual = PlayerMove.parseAttack(input);
    assertEquals(MoveType.DISCARD, actual.getMoveType());
    assertEquals(3, actual.getTargetIndex());
  }

  @Test
  public void parseAttackWithTwoStacksTest() {
    String input = "3 2\n";
    PlayerMove actual = PlayerMove.parseAttack(input);
    assertEquals(MoveType.ATTACK, actual.getMoveType());
    assertEquals(3, actual.getSourceIndex());
    assertEquals(2, actual.getTargetIndex());
  }

  @Test
  public void parseSetupWithInvalidTest() {
    String input = "X X\n";
    PlayerMove actual = PlayerMove.parseAttack(input);
    assertEquals(MoveType.INVALID, actual.getMoveType());
  }

  @Test
  public void parseSetupWithCardPlacementTest() {
    String input = "c 4\n";
    PlayerMove actual = PlayerMove.parseSetup(input);
    assertEquals(MoveType.PLACE, actual.getMoveType());
    assertEquals("c", actual.getSourceStr());
    assertEquals(4, actual.getTargetIndex());
  }
}
