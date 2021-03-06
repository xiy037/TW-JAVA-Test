import entity.Car;
import entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
  public static final String CLASSNAME = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/parkingLots";
  private static final String USER = "jbank";
  private static final String PASSWORD = "Jbank123!";
  private Connection connection;

  public DataUtil() {
    try {
      Class.forName(CLASSNAME);
      this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public List<Ticket> queryAll(String lotName) {
    List<Ticket> result = new ArrayList<>();
    String tableName = getTableName(lotName);
    String sql = String.format("SELECT * FROM %s", tableName);
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Car oneCar = new Car(rs.getString("plate"));
        result.add(new Ticket(lotName, rs.getInt("id"), oneCar));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  private String getTableName(String lotName) {
    String tableName = "";
    String sql = "SELECT lot_table FROM parking_lots_management WHERE lot_id = ?";
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      stmt.setString(1, lotName);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        tableName = rs.getString(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tableName;
  }

  public String queryData(String table, int position) {
    String result = "";
    String sql = String.format("SELECT plate FROM %s WHERE id = ?", table);
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      stmt.setInt(1, position);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = rs.getString(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public void insertData(String lotName, int position, String plate) {
    String table = getTableName(lotName);
    String sql = String.format("INSERT INTO %s VALUES (?, ?)", table);
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      stmt.setInt(1, position);
      stmt.setString(2, plate);
      int addedRow = stmt.executeUpdate();
      System.out.println(addedRow + " row has been inserted!");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteData(String lotName, int position) {
    String table = getTableName(lotName);
    String sql = String.format("DELETE FROM %s WHERE id = ?", table);
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      stmt.setInt(1, position);
      int deletedRow = stmt.executeUpdate();
      if (deletedRow == 0) {
        System.out.println("The row did not exist");
      }
      System.out.println(deletedRow + " row has been deleted");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Truncate table for a parking lot when initing application.
   *
   * @param lotName is the abbreviation for parking lot, should be "A" or "B".
   */
  public void truncateAll(String lotName) {
    String table = getTableName(lotName);
    String sql = String.format("DELETE FROM %s", table);
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      int deletedRows = stmt.executeUpdate();
      System.out.println(deletedRows + " rows have been all deleted");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * get table size for different parking lots.
   *
   * @param a is parking lot abbreviation, should be "A" or "B"
   * @return the size
   */
  public int getSize(String a) {
    int size = 0;
    String sql = "SELECT lot_size FROM parking_lots_management WHERE lot_id = ?";
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      stmt.setString(1, a);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        size = rs.getInt("lot_size");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return size;
  }

  /**
   * Set size when initializing application.
   */
  public void setSize(String a, int numA) {
    String sql = "UPDATE parking_lots_management SET lot_size = ? WHERE lot_id = ?";
    try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
      stmt.setInt(1, numA);
      stmt.setString(2, a);
      int updatedRow = stmt.executeUpdate();
      System.out.println(updatedRow + " row's size has been updated!");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public void closeConnection() {
    try {
      this.connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}

