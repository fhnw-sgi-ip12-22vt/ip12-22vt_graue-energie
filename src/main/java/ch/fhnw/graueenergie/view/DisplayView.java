package ch.fhnw.graueenergie.view;

import ch.fhnw.graueenergie.entity.DisplayState;
import ch.fhnw.graueenergie.entity.EnergyConsumer;
import ch.fhnw.graueenergie.entity.LabelsEntity;
import ch.fhnw.graueenergie.entity.QuestionEntity;
import ch.fhnw.graueenergie.entity.QuizButtonState;
import ch.fhnw.graueenergie.entity.ScreenState;
import ch.fhnw.graueenergie.helper.JsonHelper;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DisplayView implements Initializable, View {

  private static final String TUTORIAL_PATH = "/video/tutorial.mp4";

  @FXML
  private Text instructionText;

  @FXML
  private Text energyBarPercentText;

  @FXML
  private Text ubpBarPercentText;

  @FXML
  private Text energyLabel;

  @FXML
  private Text ubpLabel;

  @FXML
  private Text climateTargetLabel;

  @FXML
  private Text startScreen;

  @FXML
  private TabPane tabPane;

  @FXML
  private MediaView mediaView;

  @FXML
  private ProgressBar energyBar, ubpBar;

  private MediaPlayer mediaPlayer;

  @FXML
  private TextArea questionTextArea;

  @FXML
  private Button answerButton0;

  @FXML
  private Button answerButton1;

  @FXML
  private Button answerButton2;

  @FXML
  private Button answerButton3;

  private QuestionEntity currentQuestion;

  private boolean questionAnswered = true;
  private DisplayState lastDisplayState;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadTutorialVideo();
    LabelsEntity labelsEntity = JsonHelper.read("/json/labels.json", LabelsEntity.class);

    energyLabel.setText(labelsEntity.energy());
    ubpLabel.setText(labelsEntity.environmentalImpact());
    climateTargetLabel.setText(labelsEntity.climateTarget());
    startScreen.setText(labelsEntity.startScreen());
  }

  @FXML
  public void updateDisplay(DisplayState displayState) {
    if (displayState == null) {
      changeTab(ScreenState.START_SCREEN);
      return;
    }

    updateScreen(displayState);

    updateEnergyBar(displayState.energyBarProgress());
    setEnergyBarPercentText(displayState.energyBarProgress());
    updateUBPBar(displayState.ubpBarProgress());
    setUbpBarPercentText(displayState.ubpBarProgress());
    setInstruction(displayState.instructionText());

    if (displayState.questionEntity() != null) {
      setQuiz(displayState.questionEntity());
    }

    lastDisplayState = displayState;
  }

  @Override
  public void answerQuestion(int quizButtonId) {
    if (currentQuestion == null || questionAnswered) {
      return;
    }

    if (currentQuestion.correctAnswerId() == quizButtonId) {
      setButtonState(QuizButtonState.INACTIVE);
      setButtonState(currentQuestion.correctAnswerId(), QuizButtonState.CORRECT);
    } else {
      setButtonState(QuizButtonState.INACTIVE);
      setButtonState(quizButtonId, QuizButtonState.INCORRECT);
      setButtonState(currentQuestion.correctAnswerId(), QuizButtonState.CORRECT);
    }
  }

  @FXML
  private void updateEnergyBar(double progress) {
    Platform.runLater(() -> energyBar.setProgress(progress));
  }

  @FXML
  private void updateUBPBar(double progress) {
    Platform.runLater(() -> {
      ubpBar.setProgress(progress);

      if (progress > 0.6) {
        ubpBar.setStyle(ubpBar.getStyle().replace("blue", "red"));
      } else {
        ubpBar.setStyle(ubpBar.getStyle().replace("red", "blue"));
      }
    });
  }

  @FXML
  private void setInstruction(String instruction) {
    Platform.runLater(() -> instructionText.setText(instruction));
  }

  @FXML
  private void setQuiz(QuestionEntity question) {
    currentQuestion = question;
    questionAnswered = false;

    Platform.runLater(() -> {
      questionTextArea.setText(question.question());

      answerButton0.setText(question.answers()[0]);
      answerButton1.setText(question.answers()[1]);
      answerButton2.setText(question.answers()[2]);
      answerButton3.setText(question.answers()[3]);

      setButtonState(QuizButtonState.ACTIVE);
    });
  }

  @FXML
  private void setEnergyBarPercentText(double progress) {
    Platform.runLater(() -> energyBarPercentText.setText((int) (progress * 100) + "%"));
  }

  @FXML
  private void setUbpBarPercentText(double progress) {
    Platform.runLater(() -> ubpBarPercentText.setText((int) (progress * 100) + "%"));
  }

  private void updateScreen(DisplayState displayState) {
    if (tutorialStillPlaying()) {
      mediaPlayer.setOnEndOfMedia(() -> updateDisplay(displayState));
      return;
    }

    if (displayState.energyConsumer() == EnergyConsumer.NO_CONSUMER) {
      changeTab(ScreenState.START_SCREEN);
    } else if (getCurrentScreen() != ScreenState.GAME_SCREEN) {
      changeTab(ScreenState.GAME_SCREEN);
    }
  }

  public void playTutorialVideo() {
    if (tutorialStillPlaying()) {
      mediaPlayer.seek(mediaPlayer.getTotalDuration());
      return;
    }

    mediaView.setMediaPlayer(mediaPlayer);
    mediaPlayer.seek(mediaPlayer.getStartTime());

    changeTab(ScreenState.TUTORIAL_SCREEN);

    mediaPlayer.play();

    mediaPlayer.setOnEndOfMedia(() -> updateDisplay(lastDisplayState));
  }

  private boolean tutorialStillPlaying() {
    if (mediaPlayer != null) {
      return mediaPlayer.getCurrentTime().greaterThan(Duration.ZERO) && mediaPlayer.getCurrentTime()
          .lessThan(mediaPlayer.getTotalDuration());
    }

    return false;
  }

  private void loadTutorialVideo() {
    Media media = new Media(
        new File(getClass().getResource(TUTORIAL_PATH).getPath()).toURI().toString());

    mediaPlayer = new MediaPlayer(media);
  }

  private ScreenState getCurrentScreen() {
    return ScreenState.valueOf(tabPane.getSelectionModel().getSelectedIndex());
  }

  private void changeTab(ScreenState tabPaneState) {
    tabPane.getSelectionModel().select(tabPaneState.tabIndex);
  }

  private void setButtonState(QuizButtonState quizButtonState) {
    for (int i = 0; i < 4; i++) {
      setButtonState(i, quizButtonState);
    }
  }

  @FXML
  private void setButtonState(int quizButtonId, QuizButtonState quizButtonState) {
    String color = quizButtonState == QuizButtonState.ACTIVE
        ? QuizButtonState.getActiveColor(quizButtonId)
        : quizButtonState.color;

    Platform.runLater(() -> {
      switch (quizButtonId) {
        case 0 -> answerButton0.setStyle(color);
        case 1 -> answerButton1.setStyle(color);
        case 2 -> answerButton2.setStyle(color);
        case 3 -> answerButton3.setStyle(color);
      }
    });
  }
}

