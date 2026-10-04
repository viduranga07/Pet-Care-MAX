// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Customer;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// This DAO class handles database operations for this feature.
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean addCustomer(Customer customer) {

        String sql = """
                INSERT INTO customers
                (full_name, phone, email, address)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error adding customer: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {

        String sql = """
                SELECT *
                FROM customers
                WHERE customer_id = ?
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding customer: " + e.getMessage()
            );
        }

        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<>();

        String sql = """
                SELECT *
                FROM customers
                ORDER BY customer_id DESC
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                customers.add(
                        mapResultSetToCustomer(resultSet)
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error loading customers: " + e.getMessage()
            );
        }

        return customers;
    }

    @Override
    public boolean updateCustomer(Customer customer) {

        String sql = """
                UPDATE customers
                SET full_name = ?,
                    phone = ?,
                    email = ?,
                    address = ?
                WHERE customer_id = ?
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getCustomerId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error updating customer: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int customerId) {

        String sql = """
                DELETE FROM customers
                WHERE customer_id = ?
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting customer: " + e.getMessage()
            );

            return false;
        }
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet)
            throws SQLException {

        int customerId = resultSet.getInt("customer_id");

        String fullName =
                resultSet.getString("full_name");

        String phone =
                resultSet.getString("phone");

        String email =
                resultSet.getString("email");

        String address =
                resultSet.getString("address");

        Timestamp timestamp =
                resultSet.getTimestamp("created_at");

        LocalDateTime createdAt =
                timestamp != null
                        ? timestamp.toLocalDateTime()
                        : null;

        return new Customer(
                customerId,
                fullName,
                phone,
                email,
                address,
                createdAt
        );
    }
}
