package ch.fhnw.graueenergie.entity;

public final class ScannerEntity {
  private Integer id;
  private String value;

  public ScannerEntity(String id, String value) {
    this.id = Integer.valueOf(id);
    this.value = value;
  }

  public Integer getId() {
    return id;
  }

  public void setId(String id) {
    this.id = Integer.getInteger(id);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
