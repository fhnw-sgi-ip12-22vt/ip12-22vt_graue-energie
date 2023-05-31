package ch.fhnw.graueenergie.controller;

import ch.fhnw.graueenergie.entity.ScannerEntity;
import ch.fhnw.graueenergie.mapper.ScannerEntityToEnergyEntityMapper;
import ch.fhnw.graueenergie.model.Model;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public final class SocketScannerController implements ScannerController {

  private static final int PORT = 4444;

  private final Model model;
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private boolean running;

  public PrintWriter out;
  public BufferedReader in;

  public SocketScannerController(Model model) {
    this.model = model;
  }

  public void start() {
    new Thread(() -> {
      startSocket();

      try {
        String inputLine;
        while (running && (inputLine = in.readLine()) != null) {

          ScannerEntity[] scannerEntities = new Gson().fromJson(inputLine, ScannerEntity[].class);

          model.changeBoardState(ScannerEntityToEnergyEntityMapper.map(scannerEntities));
          out.println(inputLine);
        }
      } catch (IOException e) {
        System.out.println(Arrays.toString(e.getStackTrace()));
      }
    }).start();
  }

  private void startSocket() {
    try {
      serverSocket = new ServerSocket(PORT);
      clientSocket = serverSocket.accept();

      running = true;
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    } catch (IOException e) {
      System.out.println("Error while connecting to server try again");
      startSocket();
    }
  }

  public void stop() throws IOException {
    running = false;
    in.close();
    out.close();
    clientSocket.close();
    serverSocket.close();
  }
}
