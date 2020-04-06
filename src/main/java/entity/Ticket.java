package entity;

public class Ticket {
  private String lot;
  private int position;
  private String carPlate;

  public Ticket(String l, int p, Car car) {
    this.lot = l;
    this.position = p;
    this.carPlate = car.getPlate();
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

  public void setLot(String lot) {
    this.lot = lot;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void setCarPlate(String carPlate) {
    this.carPlate = carPlate;
  }

  @Override
  public String toString() {
    return String.format("%s,%d,%s", lot, position, carPlate);
  }
}
