import entity.Car;
import entity.ParkingLot;
import entity.Ticket;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;

import java.util.Arrays;
import java.util.List;

public class ParkRepository {
  private DataUtil util;
  private ParkingLot lotA;
  private ParkingLot lotB;
  private static final String[] ALLPARKS = {"A", "B"};

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
    util.truncateAllData("A");
    util.truncateAllData("B");
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
    } else {
      throw new ParkingLotFullException();
    }
  }


  public void exit() {
    util.closeConnection();
  }

  public void removeCarByTicket(String lot, int id) {
    switch (lot) {
      case "A":
        lotA.removeCar(id);
        break;
      case "B":
        lotB.removeCar(id);
        break;
    }
    util.deleteData(lot, id);
  }

  public boolean isTicketValid(String lot, int position, String plate) {
   ParkingLot customLot = lot.equals("A") ? lotA : lotB;
    if (!Arrays.asList(ALLPARKS).contains(lot)) {
      return false;
    } else if ( position > customLot.getSize()) {
      return false;
    } else {
      try {
        String foundPlate = customLot.getAllLots()[position - 1].getCarPlate();
        return foundPlate.equals(plate);
      } catch (Exception e) {
        return false;
      }

    }
  }
}
