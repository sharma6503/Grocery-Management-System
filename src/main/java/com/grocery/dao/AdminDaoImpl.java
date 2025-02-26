package com.grocery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.grocery.beans.Admin;
import com.grocery.utility.DBConnection;

public class AdminDaoImpl implements AdminDao {

	@Override
	public boolean addAdmin(String name, String phonenumber, String email, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertQuery = "INSERT INTO Admin (name,phonenumber, email, password) VALUES (?, ?,?, ?)";
		try {

			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, phonenumber);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (Exception exception) {
			System.out.println("Error adding admin: " + exception.getMessage());
			return false;
		} finally {
			DBConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean loginAdmin(String email, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectQuery = "SELECT * FROM admin WHERE email = ? and email like '%@tcs%' ";

		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next() && resultSet.getString("password").equals(password);
		} catch (Exception exception) {
			System.out.println("Error logging in admin: " + exception.getMessage());
			return false;
		} finally {
			DBConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM Product WHERE product_id = ?";

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, productId);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Product deleted successfully: " + productId);
				return true;
			} else {
				System.out.println("No product found to delete.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Error deleting product: " + e.getMessage());
		} finally {
			DBConnection.closeConnection(connection);
		}
		return false;
	}

	public boolean checkProductExists(int productId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT COUNT(*) FROM Product WHERE product_id = ?";
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, productId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return false;
	}

	public boolean insertProduct(int productId, String productName, double price, int quantity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO Product (product_id, name, price, quantity) VALUES (?, ?, ?, ?)";

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, productId);
			preparedStatement.setString(2, productName);
			preparedStatement.setDouble(3, price);
			preparedStatement.setInt(4, quantity);

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {

				System.out.println("Product inserted successfully: " + productId);
				return true;
			} else {
				System.out.println("No rows inserted. Check your input.");
				return false;
			}

		} catch (SQLException | ClassNotFoundException e) {

			System.err.println("Error inserting product: " + e.getMessage());

		} finally {
			DBConnection.closeConnection(connection);
		}
		return false;

	}

	@Override
	public boolean updateProduct(int productId, String productName, double price, int quantity) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE Product SET name = ?, price = ?, quantity = ? WHERE product_id = ?";

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productName);
			preparedStatement.setDouble(2, price);
			preparedStatement.setInt(3, quantity);
			preparedStatement.setInt(4, productId);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Product updated successfully: " + productId);
				return true;
			} else {
				System.out.println("No product found to update.");
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Error updating product: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return false;

	}

	public Admin getAdminByEmail(String email) {
		// TODO Auto-generated method stub
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    String query = "SELECT * FROM Admin WHERE email = ?";
		        try {
		        	connection=DBConnection.getConnection();
		        	preparedStatement=connection.prepareStatement(query);
		        	System.out.println("dao "+email);
		        	preparedStatement.setString(1, email);
		        	System.out.println("dai"+email);
		            ResultSet resultSet = preparedStatement.executeQuery();

		            if (resultSet.next()) {
		                return new Admin(
		                    resultSet.getInt("admin_id"),
		                    resultSet.getString("name"),
		                    resultSet.getString("phoneNumber"),
		                    resultSet.getString("email"),
		                    resultSet.getString("password")
		                );
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    	finally {
		    		DBConnection.closeConnection(connection);
				}

		return null;
	}

	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE Admin SET name = ?, phoneNumber = ?, email = ?, password = ? WHERE admin_id = ?";
        try{
        	connection=DBConnection.getConnection();
        	preparedStatement=connection.prepareStatement(query);      			
        	preparedStatement.setString(1, admin.getAdminName());
            preparedStatement.setString(2, admin.getPhoneNumber());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setInt(5, admin.getAdminId());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    	finally {
    		DBConnection.closeConnection(connection);
		}
        return false;
	}
	
	
}
