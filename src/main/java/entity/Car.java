package entity;

//car has a plate
// car can have a ticket for parking: aTicket = parkingLot.produceNextTicket(car), car.setTicket(aTicket)

public class Car {
  private String plate;
  private Ticket ticket;

  public Car(String p) {
    this.plate = p;
  }

  public String getPlate() {
    return plate;
  }

  public void setTicket(Ticket t) {
    this.ticket = t;
  }

  public void usedTicket() {
    this.ticket = null;
  }

  @Override
  public String toString() {
    return "Car{" +
            "plate='" + plate + '\'' +
            '}';
  }

  public Ticket getTicket() {
    return ticket;
  }
}
