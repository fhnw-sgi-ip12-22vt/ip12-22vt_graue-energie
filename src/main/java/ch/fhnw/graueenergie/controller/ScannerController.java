package ch.fhnw.graueenergie.controller;

import java.io.IOException;

public interface ScannerController {

  void start();

  void stop() throws IOException;
}
