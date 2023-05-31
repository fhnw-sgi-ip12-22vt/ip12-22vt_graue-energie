package ch.fhnw.graueenergie.exception;

public class PositionNotFoundException extends RuntimeException {
  public PositionNotFoundException() {
    super("Position not found Exception!");
  }
}
