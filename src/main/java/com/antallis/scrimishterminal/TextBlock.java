package com.antallis.scrimishterminal;

import java.util.ArrayList;

public class TextBlock {
  private final ArrayList<String> rows;
  private final int width;
  private final String emptyWidthString;

  public TextBlock(ArrayList<String> rows) {
    ArrayList<String> result = new ArrayList<>();
    int myWidth = 0;

    for (String row : rows) {
      result.add(row);
      // Strip ANSI escape codes to get visible length
      String visibleRow = row.replaceAll("\u001B\\[[;\\d]*m", "");
      myWidth = Math.max(myWidth, visibleRow.length());
    }

    this.rows = result;
    this.width = myWidth;
    this.emptyWidthString = new String(new char[width]).replace('\0', ' ');
  }

  public TextBlock(ArrayList<String> rows, int width) {
    ArrayList<String> result = new ArrayList<>();

    for (String row : rows) {
      result.add(row);
    }

    this.rows = result;
    this.width = width;
    this.emptyWidthString = new String(new char[width]).replace('\0', ' ');
  }

  public TextBlock(ArrayList<String> rows, Color color) {
    ArrayList<String> result = new ArrayList<>();
    int myWidth = 0;

    for (String row : rows) {
      result.add(color.toString() + row + Color.RESET.toString());
      myWidth = Math.max(myWidth, row.length());
    }

    this.rows = result;
    this.width = myWidth;
    this.emptyWidthString = new String(new char[width]).replace('\0', ' ');
  }

  public ArrayList<String> getRows() {
    return rows;
  }

  public TextBlock addRight(TextBlock tb) {
    ArrayList<String> result = new ArrayList<>();

    int leftLen = rows.size();
    int rightLen = tb.getHeight();

    String leftEmptyWidth = getEmptyWidthString();
    String rightEmptyWidth = tb.getEmptyWidthString();

    String seperator = " ";

    int newWidth = width + seperator.length() + tb.getWidth();

    int maxlen = Math.max(leftLen, rightLen);
    for (int i = 0; i < maxlen; i++) {
      String lefty;
      String righty;
      if (i >= rows.size()) {
        lefty = leftEmptyWidth;
      } else {
        lefty = rows.get(i);
      }
      if (i >= tb.getHeight()) {
        righty = rightEmptyWidth;
      } else {
        righty = tb.get(i);
      }
      result.add(lefty + seperator + righty);
    }

    return new TextBlock(result, newWidth);
  }

  public TextBlock addRightBottom(TextBlock tb) {
    ArrayList<String> result = new ArrayList<>();

    int leftLen = rows.size();
    int rightLen = tb.getHeight();

    String leftEmptyWidth = getEmptyWidthString();
    String rightEmptyWidth = tb.getEmptyWidthString();

    String seperator = " ";

    int newWidth = width + seperator.length() + tb.getWidth();

    int maxlen = Math.max(leftLen, rightLen);
    for (int i = 0; i < maxlen; i++) {
      String lefty;
      String righty;

      // Left side logic
      if (i < (maxlen - leftLen)) {
        lefty = leftEmptyWidth;
      } else {
        lefty = rows.get(i - (maxlen - leftLen));
      }

      // Right side logic
      if (i < (maxlen - rightLen)) {
        righty = rightEmptyWidth;
      } else {
        righty = tb.get(i - (maxlen - rightLen));
      }
      result.add(lefty + seperator + righty);
    }

    return new TextBlock(result, newWidth);
  }

  public int getHeight() {
    return rows.size();
  }

  public int getWidth() {
    return width;
  }

  public String getEmptyWidthString() {
    return emptyWidthString;
  }

  public String get(int index) {
    return rows.get(index);
  }

  public String getFirst() {
    return rows.get(0);
  }

  public String getLast() {
    return rows.get(rows.size() - 1);
  }

  @Override
  public String toString() {
    return String.join("\n", rows);
  }
}
