package com.antallis.scrimishterminal;

public class ColorTerminal {
  /**
   * Changes the text color for subsequent text
   * 
   * @param colorCode The ANSI color code
   * @return The escape sequence for the specified color
   */
  public static String changeColor(Color colorCode) {
    return colorCode.toString();
  }

  /**
   * Resets text color to default terminal color
   * 
   * @return The reset escape sequence
   */
  public static Color setColorToDefault() {
    return Color.RESET;
  }

  /**
   * Prints text with specified color and automatically resets
   * 
   * @param text      The text to print
   * @param colorCode The ANSI color code
   */
  public static void printColored(String text, Color colorCode) {
    System.out.print(colorCode + text + Color.RESET);
  }

  /**
   * Prints text with specified color and automatically resets, with newline
   * 
   * @param text      The text to print
   * @param colorCode The ANSI color code
   */
  public static void printlnColored(String text, Color colorCode) {
    System.out.println(colorCode + text + Color.RESET);
  }
}
