package ch.fhnw.graueenergie.entity;

public enum QuizButtonState {
  ACTIVE(""),
  INACTIVE("-fx-background-color: lightgray"),
  CORRECT("-fx-background-color: #5BC236"),
  INCORRECT("-fx-background-color: #ed4337");

  public static final String BLUE = "-fx-background-color: #0089e5";
  public static final String RED = "-fx-background-color: #ed4337";
  public static final String GREEN = "-fx-background-color: #5BC236";
  public static final String YELLOW = "-fx-background-color: yellow";

  public final String color;

  QuizButtonState(String color) {
    this.color = color;
  }

  public static String getActiveColor(int buttonId) {
    return switch (buttonId) {
      case 0 -> RED;
      case 1 -> BLUE;
      case 2 -> YELLOW;
      case 3 -> GREEN;
      default -> "";
    };
  }
}
