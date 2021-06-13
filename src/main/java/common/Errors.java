package main.java.common;

public enum Errors {
  INVALID_DICE_SELECTION(1, "Invalid dice Selection");

  private final int id;
  private final String message;

  Errors(int id, String message) {
    this.id = id;
    this.message = message;
  }

  public String getMessage() {
    return "ErrorCode: "+ id+"- "+message;
  }
}
