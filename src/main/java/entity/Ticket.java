package entity;

public class Ticket {
  private String lot;
  private int position;
  private String carPlate;

  public Ticket(String l, int p, String c) {
    this.lot = l;
    this.position = p;
    this.carPlate = c;
  }

  public String getLot() {
    return lot;
  }

  public int getPosition() {
    return position;
  }

  public String getCarPlate() {
    return carPlate;
  }

  @Override
  public String toString() {
    return "Ticket{" +
            "lot='" + lot + '\'' +
            ", position=" + position +
            ", carPlate='" + carPlate + '\'' +
            '}';
  }
}
