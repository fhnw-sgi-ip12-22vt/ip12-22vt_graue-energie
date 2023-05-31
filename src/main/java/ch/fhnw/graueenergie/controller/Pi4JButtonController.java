package ch.fhnw.graueenergie.controller;

import ch.fhnw.graueenergie.view.DisplayView;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;

public class Pi4JButtonController implements ButtonController {

  private static final int PIN_WHITE_BUTTON = 20;
  private static final int PIN_GREEN_BUTTON = 13;
  private static final int PIN_RED_BUTTON = 19;
  private static final int PIN_BLUE_BUTTON = 21;
  private static final int PIN_YELLOW_BUTTON = 12;

  private Context pi4j;
  private final DisplayView view;

  public Pi4JButtonController(DisplayView view) {
    this.view = view;
  }

  public void start() {
    pi4j = Pi4J.newAutoContext();

    DigitalInput whiteButton = createButton(PIN_WHITE_BUTTON);

    whiteButton.addListener(e -> {
      if (e.state() == DigitalState.LOW) {
        view.playTutorialVideo();
      }
    });

    createQuestionAnswerButtons();
  }

  private void createQuestionAnswerButtons() {
    int[] buttonPins = new int[]{PIN_RED_BUTTON, PIN_BLUE_BUTTON,
        PIN_YELLOW_BUTTON, PIN_GREEN_BUTTON};

    for (int i = 0; i < buttonPins.length; i++) {
      DigitalInput button = createButton(buttonPins[i]);

      int finalI = i;
      button.addListener(e -> {
        if (e.state() == DigitalState.LOW) {
          view.answerQuestion(finalI);
        }
      });
    }
  }

  private DigitalInput createButton(int pinNumber) {
    DigitalInputConfigBuilder buttonConfig = DigitalInput.newConfigBuilder(pi4j)
        .id("button" + pinNumber)
        .name("button" + pinNumber)
        .address(pinNumber)
        .pull(PullResistance.PULL_UP)
        .debounce(3000L)
        .provider("pigpio-digital-input");
    return pi4j.create(buttonConfig);
  }
}
