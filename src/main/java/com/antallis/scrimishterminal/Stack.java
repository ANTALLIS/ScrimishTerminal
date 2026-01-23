package com.antallis.scrimishterminal;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Stack {
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

  public TextBlock toTextBlockDown() {
    if (isEmpty()) {
      return getTextBlockEmpty();
    }

    ArrayList<String> result = new ArrayList<>();

    int i = 0;
    for (; i < size() - 1; i++) {
      Card c = cards.get(i);
      result.add(c.toTextBlock().getFirst());
    }
    Card c = cards.get(i);
    result.addAll(c.toTextBlock().getRows());

    return new TextBlock(result);
  }

  public TextBlock toTextBlockUp() {
    if (isEmpty()) {
      return getTextBlockEmpty();
    }

    ArrayList<String> result = new ArrayList<>();
    List<Card> revCards = cards.reversed();

    Card c = revCards.get(0);
    result.addAll(c.toTextBlock().getRows());

    for (int i = 1; i < size(); i++) {
      c = revCards.get(i);
      result.add(c.toTextBlock().getLast());
    }

    return new TextBlock(result);
  }

  public TextBlock toTextBlock() {
    return toTextBlockDown();
  }

  @Override
  public String toString() {
    TextBlock stringRep = toTextBlock();
    String result = stringRep.toString();
    return result;
  }

  public static TextBlock getTextBlockEmpty() {
    return Card.getTextBlockEmpty();
  }

  public TextBlock getTextBlockHiddenDown() {
    if (isEmpty()) {
      return getTextBlockEmpty();
    }

    ArrayList<String> result = new ArrayList<>();

    for (int i = 0; i < size() - 1; i++) {
      result.add(Card.getTextBlockHidden().getFirst());
    }
    result.addAll(Card.getTextBlockHidden().getRows());

    return new TextBlock(result);
  }

  public TextBlock getTextBlockHidden() {
    return getTextBlockHiddenDown();
  }
}
