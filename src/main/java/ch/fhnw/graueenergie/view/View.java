package ch.fhnw.graueenergie.view;

import ch.fhnw.graueenergie.entity.DisplayState;

public interface View {

  void updateDisplay(DisplayState displayState);

  void answerQuestion(int quizButtonId);
}
