package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.el.ListELResolver;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Result;

import model.Car;

public class CarDAO {
	private Connection getConnection() throws Exception {
		// nap driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// tao lien ket den database
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/car_store", "root", "");
	}

	public Car getCarById(int id) {
		Car car = null;
		try (Connection conn = getConnection()) {
			String sql = "SELECT * FROM cars WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				car = new Car();
				car.setId(rs.getInt("id"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setYear(rs.getInt("year"));
				car.setMileage(rs.getInt("km")); // Sử dụng cột 'km' trong database
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

	public List<Car> searchCars(String keyword, String location) {
		List<Car> list = new ArrayList<>();

		String sql = "SELECT * FROM cars WHERE 1=1 ";

		if (keyword != null && !keyword.trim().isEmpty()) {
			sql += "AND (brand LIKE ? OR model LIKE ?) ";
		}

		if (location != null && !location.trim().isEmpty()) {
			sql += "AND location = ? ";
		}

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			int index = 1;

			if (keyword != null && !keyword.trim().isEmpty()) {
				ps.setString(index++, "%" + keyword + "%");
				ps.setString(index++, "%" + keyword + "%");
			}

			if (location != null && !location.trim().isEmpty()) {
				ps.setString(index++, location);
			}

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Car car = new Car();
				car.setId(rs.getInt("id"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setYear(rs.getInt("year"));
				car.setMileage(rs.getInt("km"));
				car.setPrice(rs.getDouble("price"));
				car.setLocation(rs.getString("location"));
				car.setImage(rs.getString("image"));

				list.add(car);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Car> getCarsSortedByPriceAsc() {
		String sql = "SELECT * FROM cars ORDER BY price ASC";
		return getCarsByQuery(sql);
	}

	public List<Car> getCarsSortedByPriceDesc() {
		String sql = "SELECT * FROM cars ORDER BY price DESC";
		return getCarsByQuery(sql);
	}

	private List<Car> getCarsByQuery(String sql) {
		List<Car> list = new ArrayList<>();
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Car car = new Car();
				car.setId(rs.getInt("id"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setYear(rs.getInt("year"));
				car.setMileage(rs.getInt("mileage"));
				car.setPrice(rs.getDouble("price"));
				car.setLocation(rs.getString("location"));
				car.setImage(rs.getString("image"));

				list.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Car> getAllCars() {
		String sql = "SELECT * FROM cars";
		return getCarsByQuery(sql);
	}

	public List<Car> searchByName(String keyword) {
	    List<Car> list = new ArrayList<>();
	    String sql = "SELECT * FROM cars WHERE CONCAT(brand, ' ', model) LIKE ?";
	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, "%" + keyword + "%");
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Car car = new Car();
	            car.setId(rs.getInt("id"));
	            car.setBrand(rs.getString("brand"));
	            car.setModel(rs.getString("model"));
	            car.setYear(rs.getInt("year"));
	            car.setMileage(rs.getInt("mileage"));
	            car.setPrice(rs.getDouble("price"));
	            car.setLocation(rs.getString("location"));
	            car.setImage(rs.getString("image"));
	            car.setDescription(rs.getString("description"));
	            list.add(car);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}