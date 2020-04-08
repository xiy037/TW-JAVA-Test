import entity.Car;
import entity.ParkingLot;
import entity.Ticket;
import exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParkRepository {
  private DataUtil util;
  public ArrayList<ParkingLot> lotList = new ArrayList<>();
  private static final String[] ALLPARKS = {"A", "B"};

  /**
   * get size from DB, and leftover parked-cars list, i.e. all data in DB.
   * new local parkingLot with the data from DB. parkingLot in lotList is corresponding to ALLPARKS name, respectively.
   */
  public ParkRepository() {
    util = new DataUtil();
    for (int i = 0; i < ALLPARKS.length; i++) {
      int sizeX = util.getSize(ALLPARKS[i]);
      List<Ticket> xList = util.queryAll(ALLPARKS[i]);
      ParkingLot lotX = new ParkingLot(ALLPARKS[i], sizeX, xList);
      lotList.add(lotX);
    }
  }

  /**
   * init application and reset size of parkingLots. Truncate table in DB to delete all leftover parked-cars
   */
  public ParkRepository(HashMap<String, Integer> nums) {
    util = new DataUtil();
    lotList = new ArrayList<>();
    for (int i = 0; i < ALLPARKS.length; i++) {
      //这里是默认hashMap初始化所有的停车场大小。否则要加if(num.get(lotName) != null)的判断，不是null则新给size。是null的时候取DB里的原始数据作为size。
      String lotName = ALLPARKS[i];
      int newSize = nums.get(lotName);
      util.truncateAll(lotName);
      lotList.add(new ParkingLot(lotName, newSize));
      util.setSize(lotName, newSize);
    }
  }


  public void produceTicketForCar(Car newCar) {
    //lotList的顺序生成的时候就是ABC的unicode顺序（参考ALLPARKS的顺序），如果需要改变顺序，则先深拷贝，再排序，再遍历，不要改变原来的List
    for (ParkingLot parkingLot : lotList) {
      if (!parkingLot.isFull()) {
        parkingLot.produceTicket(newCar);
        break;
      }
    }
    Ticket ticket = newCar.getTicket();
    if (ticket != null) {
      util.insertData(ticket.getLot(), ticket.getPosition(), ticket.getCarPlate());
    } else {
      throw new ParkingLotFullException();
    }
  }

  //这里因为lotList和ALLPARKS是一一对应，所以可以根据相同index这么找，否则得遍历lotList里每一项parkingLot的name。
  private ParkingLot findLot(String lot) {
    ParkingLot customLot = null;
    for (int i = 0; i < ALLPARKS.length; i++) {
      if (ALLPARKS[i].equals(lot)) {
        customLot = lotList.get(i);
      }
    }
    return customLot;
  }

  public boolean isTicketValid(String lot, int position, String plate) {
    ParkingLot customLot = findLot(lot);
    if (!Arrays.asList(ALLPARKS).contains(lot)) {
      return false;
    } else if (position > customLot.getSize()) {
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

  public void removeCarByTicket(String lot, int id) {
    ParkingLot singleLot = findLot(lot);
    singleLot.removeCarByPosition(id);
    util.deleteData(lot, id);
  }

  public void exit() {
    util.closeConnection();
  }

}
