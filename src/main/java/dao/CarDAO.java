package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Car;

public class CarDAO {
	private Connection getConnection() throws Exception{
		// nap driver
		Class.forName("com.mysql.cj.jbdc.Driver");
		//tao lien ket den database
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/car_store", "root", "");
	}

	public Car getCarById(int id) {
		Car car=null;
		try(Connection conn = getConnection()){
			String sql="SELECT * FROM cars WHERE id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
                car = new Car();
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setMileage(rs.getInt("mileage"));
                car.setPrice(rs.getDouble("price"));
                car.setLocation(rs.getString("location"));
                car.setDescription(rs.getString("description"));
                car.setImage(rs.getString("image"));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return car;
	}
}