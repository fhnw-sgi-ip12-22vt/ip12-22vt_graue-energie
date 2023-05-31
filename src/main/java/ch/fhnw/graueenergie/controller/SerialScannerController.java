package ch.fhnw.graueenergie.controller;

import ch.fhnw.graueenergie.entity.ScannerEntity;
import ch.fhnw.graueenergie.mapper.CardToEnergyEntityIdMapper;
import ch.fhnw.graueenergie.mapper.ScannerEntityToEnergyEntityMapper;
import ch.fhnw.graueenergie.model.Model;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public final class SerialScannerController implements ScannerController, SerialPortEventListener {

  private static final String BEGINNING_SEPARATOR = "@";
  private static final String ENDING_SEPARATOR = "#";
  private static final String VALUE_SEPARATOR = ";";
  private static final String SCANNER_SEPARATOR = "!";

  private final SerialPort sp;
  private final Model model;
  private String buffer = "";

  public SerialScannerController(Model model, String device) {
    this.model = model;
    this.sp = new SerialPort(device);
  }

  @Override
  public void start() {
    try {
      sp.openPort();
      sp.setParams(9600, 8, 1, 0);
      sp.addEventListener(this);
    } catch (SerialPortException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public void serialEvent(SerialPortEvent e) {
    try {
      buffer += sp.readString();

      buffer = buffer.replaceAll("null", "");
      buffer = buffer.replaceAll(" ", "");
      buffer = buffer.replaceAll("\n", "");
      buffer = buffer.replaceAll("\r", "");

      if (buffer.contains(BEGINNING_SEPARATOR) && buffer.indexOf(ENDING_SEPARATOR) > buffer.indexOf(
          BEGINNING_SEPARATOR)) {
        String read = buffer.substring(buffer.indexOf(BEGINNING_SEPARATOR) + 1, buffer.indexOf(
            ENDING_SEPARATOR));
        buffer = buffer.substring(buffer.indexOf(ENDING_SEPARATOR) + 1);

        if (read.contains(BEGINNING_SEPARATOR)) {
          read = "";
        }

        String[] scanners = read.split(SCANNER_SEPARATOR);
        ScannerEntity[] scannerEntities = new ScannerEntity[scanners.length];
        if (scanners.length == 6 || scanners.length == 5) {
          for (int i = 0; i < scanners.length; i++) {
            String[] data = scanners[i].split(VALUE_SEPARATOR);
            if (data.length == 2) {
              scannerEntities[i] = new ScannerEntity(
                  data[0],
                  CardToEnergyEntityIdMapper.map(data[1]).toString()
              );
            }
          }

          model.changeBoardState(ScannerEntityToEnergyEntityMapper.map(scannerEntities));
        }
      } else if (buffer.contains(ENDING_SEPARATOR)
          && buffer.indexOf(ENDING_SEPARATOR) < buffer.indexOf(
          BEGINNING_SEPARATOR)) {
        buffer = buffer.substring(buffer.indexOf(BEGINNING_SEPARATOR));
      }

    } catch (SerialPortException ex) {
      stop();
    }
  }

  @Override
  public void stop() {
    try {
      sp.closePort();
    } catch (SerialPortException e) {
      throw new RuntimeException(e);
    }
  }
}
