import entity.Car;
import entity.ParkingLot;
import entity.Ticket;

import java.util.List;

public class ParkRepository {
  private DataUtil util;
  /**
   *  set default parkingLot size;
   */
  private ParkingLot lotA;
  private ParkingLot lotB;

  public ParkRepository() {
    util = new DataUtil();
    int sizeA = util.getSize("A");
    int sizeB = util.getSize("B");
    List<Ticket> aList = util.queryAll("A");
    List<Ticket> bList = util.queryAll("B");
    lotA = new ParkingLot("A", sizeA, aList);
    lotB = new ParkingLot("B", sizeB, bList);

  }

  //用于init的constructor，给新的size
  public ParkRepository(int numA, int numB) {
    util = new DataUtil();
    lotA = new ParkingLot("A", numA);
    lotB = new ParkingLot("B", numB);
    util.setSize("A", numA);
    util.setSize("B", numB);
  }


  public void produceTicketForCar(Car newCar) {
    if (!lotA.isFull()) {
      lotA.produceTicket(newCar);
    } else if (!lotB.isFull()) {
      lotB.produceTicket(newCar);
    }
    /**
     * store in DB
     */
    Ticket ticket = newCar.getTicket();
    if (ticket != null) {
      util.insertData(ticket.getLot(), ticket.getPosition(), ticket.getCarPlate());
    }
  }
}
