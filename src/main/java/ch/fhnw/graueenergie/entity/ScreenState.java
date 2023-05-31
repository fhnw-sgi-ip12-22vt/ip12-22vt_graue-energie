package ch.fhnw.graueenergie.entity;

public enum ScreenState {
  START_SCREEN(0),
  GAME_SCREEN(1),
  TUTORIAL_SCREEN(2);

  public final int tabIndex;

  ScreenState(int tabIndex) {
    this.tabIndex = tabIndex;
  }

  public static ScreenState valueOf(int index) {
    return ScreenState.values()[index];
  }
}
