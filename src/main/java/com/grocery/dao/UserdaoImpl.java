package com.grocery.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.grocery.beans.Cart;
import com.grocery.beans.Order;
import com.grocery.beans.Product;
import com.grocery.beans.Transactions;
import com.grocery.beans.User;
import com.grocery.utility.DBConnection;

public class UserdaoImpl implements UserDao {

	// Add a new user to the database
	public boolean addUser(String name, String phonenumber, String email, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertQuery = "INSERT INTO customer (name,phonenumber, email, password) VALUES (?, ?,?, ?)";
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
			System.out.println("Error adding user: " + exception.getMessage());
			return false;
		} finally {

			DBConnection.closeConnection(connection);
		}
	}

	// login user
	public boolean loginUser(String email, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectQuery = "SELECT * FROM CUSTOMER WHERE email = ?";
		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
//			System.out.println(resultSet.getString("password"));
			return resultSet.getString("password").equalsIgnoreCase(password);
		} catch (Exception exception) {
			System.out.println("Error logging in user: " + exception.getMessage());
			return false;
		} finally {
			DBConnection.closeConnection(connection);
		}
	}

	// list all products in cart table
	public List<Cart> getCartProducts(int customerId) {
		List<Cart> cartProducts = new ArrayList<Cart>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectQuery = "SELECT ci.product_id,p.name,ci.quantity,ci.price_per_unit"
				+ " from cart_items ci join product p on p.product_id=ci.product_id"
				+ " join cart ca on ca.cart_id=ci.cart_id"
				+ " join customer c on c.customer_id=ca.customer_id   where c.customer_id=?";
		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				cartProducts.add(new Cart(resultSet.getInt("product_id"), resultSet.getString("product_name"),
						resultSet.getInt("quantity"), resultSet.getDouble("price_product_unit")));
			}
			return cartProducts;

		} catch (Exception exception) {
			System.out.println("Error in fetching cart products: " + exception.getMessage());
			return List.of();
		} finally {
			DBConnection.closeConnection(connection);
		}
	}

	public int getUserIdFromEmail(String email) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectQuery = "SELECT customer_id FROM customer WHERE email = ?";
		System.out.println("getting userId from email");
		try {

			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getInt("customer_id"));
			return resultSet.getInt("customer_id");
		} catch (Exception exception) {
			System.out.println("Error in getting customer_id: " + exception.getMessage());
			return -1;
		} finally {
			DBConnection.closeConnection(connection);
		}
	}

	// get all products to products page
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectQuery = "SELECT * FROM PRODUCT";
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Product product = new Product(resultSet.getInt("PRODUCT_ID"), resultSet.getString("NAME"),
						resultSet.getDouble("PRICE"), resultSet.getInt("QUANTITY"));
				productList.add(product);
			}

		} catch (Exception exception) {
			System.out.println("Error fetching products: " + exception.getMessage());
			return List.of();
		} finally {
			DBConnection.closeConnection(connection);
		}

		return productList;
	}

	// search product by name
	public List<Product> searchProducts(String productName) {
		List<Product> productList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectQuery = "SELECT * FROM PRODUCT where name Like ?";
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, "%" + productName + "%");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Product product = new Product(resultSet.getInt("PRODUCT_ID"), resultSet.getString("NAME"),
						resultSet.getDouble("PRICE"), resultSet.getInt("QUANTITY"));
				productList.add(product);
			}

		} catch (Exception exception) {
			System.out.println("Error fetching products: " + exception.getMessage());
			return List.of();
		} finally {
			DBConnection.closeConnection(connection);
		}

		return productList;
	}

	public boolean updateCartItemQuantity(int userId, int productId, int quantity) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		boolean success = false;
		Connection connection = null;
		String query = "UPDATE cart SET quantity = ? WHERE customer_id = ? AND product_id = ?";
		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(3, productId);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}

		return success;
	}

	// retreive all products in cart table of the user 
	public List<Cart> getCartItems(int userId) {
		// TODO Auto-generated method stub
		List<Cart> cartItems = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		String query = "SELECT product_id, p_name, quantity, price FROM cart where customer_id = ?";

		try {

			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Cart cartItem = new Cart(resultSet.getInt("product_id"), resultSet.getString("p_name"),
						resultSet.getInt("quantity"), resultSet.getDouble("price"));
				cartItems.add(cartItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		System.out.println(cartItems);
		return cartItems;
	}

	// add product to cart
	public boolean addToCart(int userId, int productId, String pname, int quantity, double price) {
	    boolean success = false;
	    Connection connection = null;
	    PreparedStatement checkCartStmt = null;
	    PreparedStatement updateCartStmt = null;
	    PreparedStatement insertCartStmt = null;
	    PreparedStatement checkProductStmt = null;
	    PreparedStatement updateProductStmt = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBConnection.getConnection();
	        connection.setAutoCommit(false); // Start transaction

	        // Check if the product already exists in the cart for the given user
	        String checkCartQuery = "SELECT quantity FROM Cart WHERE customer_id = ? AND product_id = ?";
	        checkCartStmt = connection.prepareStatement(checkCartQuery);
	        checkCartStmt.setInt(1, userId);
	        checkCartStmt.setInt(2, productId);
	        resultSet = checkCartStmt.executeQuery();

	        if (resultSet.next()) {
	            // Product exists, update quantity
	            int existingQuantity = resultSet.getInt("quantity");
	            String updateCartQuery = "UPDATE Cart SET quantity = ? WHERE customer_id = ? AND product_id = ?";
	            updateCartStmt = connection.prepareStatement(updateCartQuery);
	            updateCartStmt.setInt(1, existingQuantity + quantity);
	            updateCartStmt.setInt(2, userId);
	            updateCartStmt.setInt(3, productId);
	            updateCartStmt.executeUpdate();
	        } else {
	            // Product does not exist, insert a new entry
	            String insertCartQuery = "INSERT INTO Cart(customer_id, product_id, p_name, quantity, price) VALUES (?, ?, ?, ?, ?)";
	            insertCartStmt = connection.prepareStatement(insertCartQuery);
	            insertCartStmt.setInt(1, userId);
	            insertCartStmt.setInt(2, productId);
	            insertCartStmt.setString(3, pname);
	            insertCartStmt.setInt(4, quantity);
	            insertCartStmt.setDouble(5, price);
	            insertCartStmt.executeUpdate();
	        }

	        // Check available quantity in the Product table
	        String checkProductQuery = "SELECT quantity FROM Product WHERE product_id = ?";
	        checkProductStmt = connection.prepareStatement(checkProductQuery);
	        checkProductStmt.setInt(1, productId);
	        resultSet = checkProductStmt.executeQuery();

	        if (resultSet.next()) {
	            int productQuantity = resultSet.getInt("quantity");

	            // Ensure there is enough stock
	            if (productQuantity >= quantity) {
	                String updateProductQuery = "UPDATE Product SET quantity = ? WHERE product_id = ?";
	                updateProductStmt = connection.prepareStatement(updateProductQuery);
	                updateProductStmt.setInt(1, productQuantity - quantity);
	                updateProductStmt.setInt(2, productId);
	                updateProductStmt.executeUpdate();
	            } else {
	                throw new SQLException("Insufficient stock for product ID: " + productId);
	            }
	        } else {
	            throw new SQLException("Product not found with ID: " + productId);
	        }

	        connection.commit(); // Commit transaction
	        success = true;

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        try {
	            if (connection != null) {
	                connection.rollback(); // Rollback on error
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        // Close resources
	        try {
	            if (resultSet != null) resultSet.close();
	            if (checkCartStmt != null) checkCartStmt.close();
	            if (updateCartStmt != null) updateCartStmt.close();
	            if (insertCartStmt != null) insertCartStmt.close();
	            if (checkProductStmt != null) checkProductStmt.close();
	            if (updateProductStmt != null) updateProductStmt.close();
	            if (connection != null) DBConnection.closeConnection(connection);
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
	    }

	    return success;
	}

	// remove item from the cart
	public boolean removeCartItem(int userId, int productId) {
	    boolean success = false;

	    // SQL Queries
	    String checkCartQuery = "SELECT quantity FROM Cart WHERE customer_id = ? AND product_id = ?";
	    String deleteQuery = "DELETE FROM Cart WHERE customer_id = ? AND product_id = ?";
	    String checkProductQuery = "SELECT quantity FROM Product WHERE product_id = ?";
	    String updateProductQuery = "UPDATE Product SET quantity = ? WHERE product_id = ?";

	    // Declare resources outside the try block
	    Connection connection = null;
	    PreparedStatement checkCartStmt = null;
	    PreparedStatement deleteStmt = null;
	    PreparedStatement checkProductStmt = null;
	    PreparedStatement updateProductStmt = null;
	    ResultSet cartResultSet = null;
	    ResultSet productResultSet = null;

	    try {
	        connection = DBConnection.getConnection();
	        connection.setAutoCommit(false); // Start transaction

	        // Check if the item exists in the cart and get its quantity
	        checkCartStmt = connection.prepareStatement(checkCartQuery);
	        checkCartStmt.setInt(1, userId);
	        checkCartStmt.setInt(2, productId);
	        cartResultSet = checkCartStmt.executeQuery();

	        int cartQuantity = 0;
	        if (cartResultSet.next()) {
	            cartQuantity = cartResultSet.getInt("quantity");
	        } else {
	            return false; // Item does not exist in the cart
	        }

	        // Remove the item from the cart
	        deleteStmt = connection.prepareStatement(deleteQuery);
	        deleteStmt.setInt(1, userId);
	        deleteStmt.setInt(2, productId);
	        int rowsDeleted = deleteStmt.executeUpdate();

	        if (rowsDeleted > 0) {
	            // Retrieve the current stock of the product
	            checkProductStmt = connection.prepareStatement(checkProductQuery);
	            checkProductStmt.setInt(1, productId);
	            productResultSet = checkProductStmt.executeQuery();

	            int productStock = 0;
	            if (productResultSet.next()) {
	                productStock = productResultSet.getInt("quantity");
	            } else {
	                throw new SQLException("Product not found with ID: " + productId);
	            }

	            // Update the product stock by adding the removed quantity
	            updateProductStmt = connection.prepareStatement(updateProductQuery);
	            updateProductStmt.setInt(1, productStock + cartQuantity);
	            updateProductStmt.setInt(2, productId);
	            int rowsUpdated = updateProductStmt.executeUpdate();

	            if (rowsUpdated > 0) {
	                success = true;
	            }
	        }

	        connection.commit(); // Commit transaction

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        try {
	            if (connection != null) {
	                connection.rollback(); // Rollback in case of an error
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        // Close all resources
	        try {
	            if (cartResultSet != null) cartResultSet.close();
	            if (productResultSet != null) productResultSet.close();
	            if (checkCartStmt != null) checkCartStmt.close();
	            if (deleteStmt != null) deleteStmt.close();
	            if (checkProductStmt != null) checkProductStmt.close();
	            if (updateProductStmt != null) updateProductStmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
	    }

	    return success;
	}


	public HashMap<Boolean, List<Transactions>> checkoutCartAndTransactions(int userId) {
	    String insertQuery = "INSERT INTO Orders (customer_id, product_id, p_name, quantity, price) "
	            + "SELECT customer_id, product_id, p_name, quantity, price FROM Cart WHERE customer_id = ?";
	    String deleteQuery = "DELETE FROM Cart WHERE customer_id = ?";
	    String selectQuery = "SELECT product_id, p_name, quantity, price FROM Cart WHERE customer_id = ?";
	    HashMap<Boolean, List<Transactions>> checkouts = new HashMap<>();
	    boolean success = false;
	    List<Transactions> transactions = new ArrayList<>();

	    try (Connection connection = DBConnection.getConnection()) {
	        connection.setAutoCommit(false);

	        // Fetch cart items
	        try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
	            selectStmt.setInt(1, userId);
	            try (ResultSet resultSet = selectStmt.executeQuery()) {
	                while (resultSet.next()) {
	                    transactions.add(new Transactions(
	                            resultSet.getString("p_name"),
	                            resultSet.getInt("quantity"),
	                            resultSet.getDouble("price")
	                    ));
	                }
	            }
	        }

	        if (transactions.isEmpty()) {
	            // No items to process; return empty map
	            checkouts.put(false, transactions);
	            return checkouts;
	        }

	        // Insert cart items into orders
	        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
	             PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {

	            insertStmt.setInt(1, userId);
	            int rowsInserted = insertStmt.executeUpdate();

	            if (rowsInserted > 0) {
	                // Delete cart items
	                deleteStmt.setInt(1, userId);
	                int rowsDeleted = deleteStmt.executeUpdate();

	                if (rowsDeleted > 0) {
	                    success = true;
	                    connection.commit(); // Commit if both operations succeed
	                } else {
	                    connection.rollback(); // Rollback on delete failure
	                }
	            } else {
	                connection.rollback(); // Rollback on insert failure
	            }
	        } catch (SQLException e) {
	            connection.rollback(); // Rollback on failure
	            throw e;
	        } finally {
	            connection.setAutoCommit(true); // Restore auto-commit
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        success = false;
	    }

	    checkouts.put(success, transactions); // Add result to the map
	    return checkouts;
	}

	
	
	
	public User getUserFromEmail(String email) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		String query = "SELECT * FROM customer WHERE email = ?";
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return new User(resultSet.getInt("customer_id"), resultSet.getString("name"),
						resultSet.getString("phoneNumber"), resultSet.getString("email"),
						resultSet.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}

		return null;
	}

	public boolean updateUserDetails(User user) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;

		String query = "UPDATE customer SET name = ?, phoneNumber = ?, email = ?, password = ? WHERE customer_id = ?";
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPhoneNumber());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setInt(5, user.getUserId());

			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return false;
	}

	@Override
	public HashMap<Boolean, List<Transactions>> checkoutCart(int userId) {
		// TODO Auto-generated method stub
		String insertQuery = "INSERT INTO Orders (customer_id, product_id, p_name, quantity, price) "
	            + "SELECT customer_id, product_id, p_name, quantity, price FROM Cart WHERE customer_id = ?";
	    String deleteQuery = "DELETE FROM Cart WHERE customer_id = ?";
	    String selectQuery = "SELECT product_id, p_name, quantity, price FROM Cart WHERE customer_id = ?";
	    HashMap<Boolean, List<Transactions>> checkouts = new HashMap<>();
	    boolean success = false;
	    List<Transactions> transactions = new ArrayList<>();

	    try (Connection connection = DBConnection.getConnection()) {
	        connection.setAutoCommit(false);

	        // Fetch cart items
	        try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
	            selectStmt.setInt(1, userId);
	            try (ResultSet resultSet = selectStmt.executeQuery()) {
	                while (resultSet.next()) {
	                    transactions.add(new Transactions(
	                            resultSet.getString("p_name"),
	                            resultSet.getInt("quantity"),
	                            resultSet.getDouble("price")
	                    ));
	                }
	            }
	        }

	        if (transactions.isEmpty()) {
	            // No items to process; return empty map
	            checkouts.put(false, transactions);
	            return checkouts;
	        }

	        // Insert cart items into orders
	        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
	             PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {

	            insertStmt.setInt(1, userId);
	            int rowsInserted = insertStmt.executeUpdate();

	            if (rowsInserted > 0) {
	                // Delete cart items
	                deleteStmt.setInt(1, userId);
	                int rowsDeleted = deleteStmt.executeUpdate();

	                if (rowsDeleted > 0) {
	                    success = true;
	                    connection.commit(); // Commit if both operations succeed
	                } else {
	                    connection.rollback(); // Rollback on delete failure
	                }
	            } else {
	                connection.rollback(); // Rollback on insert failure
	            }
	        } catch (SQLException e) {
	            connection.rollback(); // Rollback on failure
	            throw e;
	        } finally {
	            connection.setAutoCommit(true); // Restore auto-commit
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        success = false;
	    }

	    checkouts.put(success, transactions); // Add result to the map
	    return checkouts;
	}

	public List<Order> getMyOrders(int userId) {
		// TODO Auto-generated method stub
		List<Order>myOrders= new ArrayList<Order>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectQuery = "Select order_id,p_name,quantity,price,order_time from Orders where customer_id=?";
		try {
			connection = DBConnection.getConnection();

			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int orderId = rs.getInt("order_id");
                String productName = rs.getString("p_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                // Fetching the Timestamp
               String orderTime = rs.getString("order_time");

                // Formatting the Timestamp to desired format "yyyy-MM-dd HH:mm:ss"
                if (orderTime != null) {
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date=LocalDate.parse(orderTime,DateTimeFormatter.ISO_DATE);
                  Date orderDate=Date.valueOf(date);

                    // Print or use the formatted date-time value
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Product: " + productName);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Price: " + price);
                    myOrders.add(new Order(orderId, productName, quantity, price, orderDate.toString()));
                }	
			}
			return myOrders;

		} catch (Exception exception) {
			System.out.println("Error in fetching Ordered products: " + exception.getMessage());
			return List.of();
		} finally {
			DBConnection.closeConnection(connection);
		}
	}
	
}