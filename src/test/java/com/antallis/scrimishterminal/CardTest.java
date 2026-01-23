/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.antallis.scrimishterminal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author antallis
 */
public class CardTest {

  public CardTest() {
  }

  @BeforeAll
  public static void setUpClass() {
  }

  @AfterAll
  public static void tearDownClass() {
  }

  @BeforeEach
  public void setUp() {
  }

  @AfterEach
  public void tearDown() {
  }

  /**
   * Test of getCardType method, of class Card.
   */
  @Test
  public void testGetCardType() {
    System.out.println("getCardType");
    UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class,
        () -> new Card(Integer.toString(0)));
    assertEquals("Weapon values must be between 1 and 6", ex.getMessage());

    for (int i = 1; i <= 6; i++) {
      Card instance = new Card(Integer.toString(i));
      CardType expResult = CardType.WEAPON;
      CardType result = instance.getCardType();
      assertEquals(expResult, result);
    }

    Card instance = new Card("C");
    CardType expResult = CardType.CROWN;
    CardType result = instance.getCardType();
    assertEquals(expResult, result);

    instance = new Card("S");
    expResult = CardType.SHIELD;
    result = instance.getCardType();
    assertEquals(expResult, result);

    instance = new Card("A");
    expResult = CardType.ARCHER;
    result = instance.getCardType();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCardValue method, of class Card.
   */
  @Test
  public void testGetCardValue() {
    System.out.println("getCardValue");
    Card instance = new Card("C");
    int expResult = 0;
    int result = instance.getCardValue();
    assertEquals(expResult, result);
  }

  // /**
  // * Test of getCardName method, of class Card.
  // */
  @Test
  public void testGetCardName() {
    System.out.println("getCardName");
    Card instance = new Card("6");
    String expResult = "Longsword";
    String result = instance.getCardName();
    assertEquals(expResult, result);
  }

  // /**
  // * Test of toTextBlock method, of class Card.
  // */
  // @Test
  // public void testToTextBlock() {
  // System.out.println("toTextBlock");
  // Card instance = null;
  // TextBlock expResult = null;
  // TextBlock result = instance.toTextBlock();
  // assertEquals(expResult, result);
  // // TODO review the generated test code and remove the default call to fail.
  // fail("The test case is a prototype.");
  // }
  //
  // /**
  // * Test of toString method, of class Card.
  // */
  // @Test
  // public void testToString() {
  // System.out.println("toString");
  // Card instance = null;
  // String expResult = "";
  // String result = instance.toString();
  // assertEquals(expResult, result);
  // // TODO review the generated test code and remove the default call to fail.
  // fail("The test case is a prototype.");
  // }
  //
  // /**
  // * Test of getTextBlockEmpty method, of class Card.
  // */
  // @Test
  // public void testGetTextBlockEmpty() {
  // System.out.println("getTextBlockEmpty");
  // TextBlock expResult = null;
  // TextBlock result = Card.getTextBlockEmpty();
  // assertEquals(expResult, result);
  // // TODO review the generated test code and remove the default call to fail.
  // fail("The test case is a prototype.");
  // }
  //
  // /**
  // * Test of getTextBlockHidden method, of class Card.
  // */
  // @Test
  // public void testGetTextBlockHidden() {
  // System.out.println("getTextBlockHidden");
  // TextBlock expResult = null;
  // TextBlock result = Card.getTextBlockHidden();
  // assertEquals(expResult, result);
  // // TODO review the generated test code and remove the default call to fail.
  // fail("The test case is a prototype.");
  // }
  //
  // /**
  // * Test of getEmptyCardWidth method, of class Card.
  // */
  // @Test
  // public void testGetEmptyCardWidth() {
  // System.out.println("getEmptyCardWidth");
  // String expResult = "";
  // String result = Card.getEmptyCardWidth();
  // assertEquals(expResult, result);
  // // TODO review the generated test code and remove the default call to fail.
  // fail("The test case is a prototype.");
  // }
  //
  // /**
  // * Test of strongerThan method, of class Card.
  // */
  // @Test
  // public void testStrongerThan() {
  // System.out.println("strongerThan");
  // Card other = null;
  // Card instance = null;
  // AttackResult expResult = null;
  // AttackResult result = instance.strongerThan(other);
  // assertEquals(expResult, result);
  // // TODO review the generated test code and remove the default call to fail.
  // fail("The test case is a prototype.");
  // }
  //
  // /**
  // * Test of isCrownCard method, of class Card.
  // */
  // @Test
  // public void testIsCrownCard() {
  // System.out.println("isCrownCard");
  // Card instance = null;
  // boolean expResult = false;
  // boolean result = instance.isCrownCard();
  // assertEquals(expResult, result);
  // // TODO review the generated test code and remove the default call to fail.
  // fail("The test case is a prototype.");
  // }
  //
}
