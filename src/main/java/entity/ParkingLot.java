package entity;

import java.util.Arrays;
import java.util.List;

public class ParkingLot {
  private Ticket[] lots;
  private String name;
  private int size;
  private boolean isFull = false;

  public ParkingLot(String n, int a) {
    Ticket[] arrA = new Ticket[a];
    this.name = n;
    this.size = a;
    this.lots = arrA;
  }

  /**
   * constructor for parkingLot when it has leftover cars, i.e. not init application.
   *
   * @param n          parkingLot name, should be "A" or "B" in this case.
   * @param a          size for the parkingLot
   * @param carTickets List representing leftover cars
   */
  public ParkingLot(String n, int a, List<Ticket> carTickets) {
    Ticket[] arrA = new Ticket[a];
    for (int i = 0; i < carTickets.size(); i++) {
      Ticket ticket = carTickets.get(i);
      int index = ticket.getPosition() - 1;
      arrA[index] = ticket;
    }
    this.name = n;
    this.size = a;
    this.lots = arrA;
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }

  public Ticket[] getAllLots() {
    return lots;
  }

  public boolean isFull() {
    if (!Arrays.asList(lots).contains(null)) {
      isFull = true;
    }
    return isFull;
  }

  /**
   * parking position for the car in the parking lot is associated with lots array index: position = index + 1
   *
   * @param car parkingLot produces a ticket for car, car provides the plate number and will be setTicket if got ticket.
   */
  public void produceTicket(Car car) {
    Ticket newTicket = null;
    for (int i = 0; i < lots.length; i++) {
      if (lots[i] == null) {
        newTicket = new Ticket(name, (i + 1), car);
        lots[i] = newTicket;
        break;
      }
    }
    car.setTicket(newTicket);
  }

  public void removeCar(int position) {
    lots[position - 1] = null;
  }
}
