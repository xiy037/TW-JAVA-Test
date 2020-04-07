import entity.Car;
import entity.Ticket;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;

import java.util.Scanner;

public class Application {
  private static ParkRepository parkRepo = new ParkRepository();

  public static void main(String[] args) {
    operateParking();
  }

  public static void operateParking() {
    while (true) {
      System.out.println("1. 初始化停车场数据\n2. 停车\n3. 取车\n4. 退出\n请输入你的选择(1~4)：");
      Scanner printItem = new Scanner(System.in);
      String choice = printItem.next();
      if (choice.equals("4")) {
        System.out.println("系统已退出");
        quit();
        break;
      }
      try {
        handle(choice);
      } catch (InvalidTicketException | ParkingLotFullException e) {
        System.out.println(e.getMessage());
        break;
      }

    }
  }

  private static void quit() {
    parkRepo.exit();
  }

  private static void handle(String choice) {
    Scanner scanner = new Scanner(System.in);
    if (choice.equals("1")) {
      System.out.println("请输入初始化数据\n格式为\"停车场编号1：车位数,停车场编号2：车位数\" 如 \"A:8,B:9\"：");
      String initInfo = scanner.next();
      init(initInfo);
    }
    else if (choice.equals("2")) {
      System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
      String carInfo = scanner.next();
      String ticket = park(carInfo);
      String[] ticketDetails = ticket.split(",");
      System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：%s，请您妥善保存。\n", ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);
    }
    else if (choice.equals("3")) {
      System.out.println("请输入停车券信息\n格式为\"停车场编号1,车位编号,车牌号\" 如 \"A,1,8\"：");
      String ticket = scanner.next();
      String car = fetch(ticket);
      System.out.format("已为您取到车牌号为%s的车辆，很高兴为您服务，祝您生活愉快!\n", car);
    }
  }

  public static void init(String initInfo) {
    String[] initInfoArr = initInfo.split(",");
    int numA = Integer.parseInt(initInfoArr[0].split(":")[1]);
    int numB = Integer.parseInt(initInfoArr[1].split(":")[1]);
    parkRepo = new ParkRepository(numA, numB);
  }

  public static String park(String carNumber) {
    Car newCar = new Car(carNumber);
    parkRepo.produceTicketForCar(newCar);
    Ticket ticket = newCar.getTicket();
    if (ticket == null) {
      return "";
    } else {
      return ticket.toString();
    }

  }

  public static String fetch(String ticket) {
    String[] ticketInfo = ticket.split(",");
    String lot = ticketInfo[0];
    int id = Integer.parseInt(ticketInfo[1]);
    String plate = ticketInfo[2];
    if (parkRepo.isTicketValid(lot, id, plate)) {
      parkRepo.removeCarByTicket(lot, id);
    } else {
      throw new InvalidTicketException();
    }
    return plate;
  }

}
