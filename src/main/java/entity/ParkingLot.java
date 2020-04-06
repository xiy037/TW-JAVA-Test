package entity;

import java.util.ArrayList;
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

  public int getSize() {return size;}

  public Ticket[] getAllLots() {
    return lots;
  }

  public void removeCar(int position) {
    lots[position - 1] = null;
  }

  public boolean isFull() {
   if (!Arrays.asList(lots).contains(null)) {
     isFull = true;
   }
    return isFull;
  }

  public void produceTicket(Car car) {
    Ticket newTicket = null;
//    if (this.isFull()) {
//      return newTicket;
//    } else {
    for (int i = 0; i < lots.length; i++) {
      if (lots[i] == null) {
        newTicket = new Ticket(name, (i + 1), car);
        lots[i] = newTicket;
        break;
      }
    }
    car.setTicket(newTicket);
//  }
  }

  public static void main(String[] args) {
    ParkingLot lots = new ParkingLot("A", 5);
    Ticket[] one = lots.getAllLots();
    String park = lots.getName();
    Car myCar = new Car("A123");
    lots.produceTicket(new Car("D001"));
    lots.produceTicket(new Car("B456"));
    lots.produceTicket(new Car("C124"));
    lots.produceTicket(myCar);
    lots.removeCar(2);
    System.out.println("my car's ticket: " + myCar.getTicket());
    for (int i = 0; i < one.length; i++) {
      System.out.println("in " + park + ": " + one[i]);
    }
    System.out.println("=======================");

    List<Ticket> another = new ArrayList<>();
    for (Ticket i : one) {
      if (i != null) {
        another.add(i);
      }
    }
    ParkingLot lots2 = new ParkingLot("B", 6, another);
    Ticket[] two = lots2.getAllLots();
    for (int i = 0; i < two.length; i++) {
      System.out.println("in " + lots2.getName()+ ": " + two[i]);
    }
  }
}
