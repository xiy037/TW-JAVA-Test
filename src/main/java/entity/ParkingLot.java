package entity;

import java.util.Arrays;
import java.util.List;

public class ParkingLot {
  private List<Ticket> lots;
  private String name;
  private int size;
  private boolean isFull = false;

  public ParkingLot(String n, int a) {
    Ticket[] arrA = new Ticket[a];
    this.name = n;
    this.size = a;
    this.lots = Arrays.asList(arrA);
  }

  public ParkingLot(String n, int a, List<Ticket> carTickets) {
    //保证cars的大小比a小，在DataUtil里存的时候控制
    Ticket[] arrA = new Ticket[a];
    for (int i = 0; i < carTickets.size(); i++) {
      arrA[i] = carTickets.get(i);
    }
    this.name = n;
    this.size = a;
    this.lots = Arrays.asList(arrA);
  }



  public String getName() {
    return name;
  }

  public int getSize() {return size;}

  public List<Ticket> getAllLots() {
    return lots;
  }

  public boolean isFull() {
   if (!lots.contains(null)) {
     isFull = true;
   }
    return isFull;
  }

  public void produceTicket(Car car) {
    Ticket newTicket = null;
//    if (this.isFull()) {
//      return newTicket;
//    } else {
    for (int i = 0; i < lots.size(); i++) {
      if (lots.get(i) == null) {
        newTicket = new Ticket(name, (i + 1), car);
        lots.set(i, newTicket);
        break;
      }
    }
    car.setTicket(newTicket);
//  }
  }

  public static void main(String[] args) {
    ParkingLot lots = new ParkingLot("A", 3);
    List<Ticket> one = lots.getAllLots();
    String park = lots.getName();
    Car myCar = new Car("A123");
    lots.produceTicket(myCar);
    lots.produceTicket(new Car("B456"));
    lots.produceTicket(new Car("C124"));
    System.out.println(myCar.getTicket());
    for (Ticket i : one) {
      System.out.println("in " + park + ": " + i);
    }
    System.out.println(lots.isFull());
  }
}
