package ch.fhnw.graueenergie.view;

import ch.fhnw.graueenergie.entity.DisplayState;

public class MockView implements View {

  public DisplayState displayState;

  @Override
  public void updateDisplay(DisplayState displayState) {
    this.displayState = displayState;
  }

  @Override
  public void answerQuestion(int buttonId) {

  }
}
