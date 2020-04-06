package entity;

import java.util.Arrays;
import java.util.List;

public class ParkingLot {
  private List<Car> lots;
  private String name;
  private boolean isFull = false;

  public ParkingLot(String n, int a) {
    Car[] arrA = new Car[a];
    this.name = n;
    this.lots = Arrays.asList(arrA);
  }

  public String getName() {
    return name;
  }

  public List<Car> getAllLots() {
    return lots;
  }

  public boolean isFull() {
   if (!lots.contains(null)) {
     isFull = true;
   }
    return isFull;
  }

  public Ticket produceTicket(Car car) {
    Ticket newTicket = null;
    if (this.isFull()) {
      return newTicket;
    } else {
      for (int i = 0; i < lots.size(); i++) {
        if (lots.get(i) == null) {
          lots.set(i, car);
          newTicket = new Ticket(name, (i+1), car.getPlate());
          break;
        }
      }
      car.setTicket(newTicket);
      return newTicket;
    }
  }

  public static void main(String[] args) {
    ParkingLot lots = new ParkingLot("A", 3);
    List<Car> one = lots.getAllLots();
    String park = lots.getName();
    Car myCar = new Car("A123");
    lots.produceTicket(myCar);
    lots.produceTicket(new Car("B456"));
    lots.produceTicket(new Car("C124"));
    System.out.println(myCar.getTicket());
    for (Car i : one) {
      System.out.println("in " + park + ": " + i);
    }
    System.out.println(lots.isFull());
  }
}
