// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Service;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// This DAO class handles database operations for this feature.
public class ServiceDAOImpl implements ServiceDAO {

    @Override
    public boolean addService(Service service) {

        String sql = "INSERT INTO services "
                + "(service_name, description, price, status) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, service.getServiceName());
            statement.setString(2, service.getDescription());
            statement.setDouble(3, service.getPrice());
            statement.setString(4, service.getStatus());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error adding service: " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public boolean updateService(Service service) {

        String sql = "UPDATE services SET "
                + "service_name = ?, "
                + "description = ?, "
                + "price = ?, "
                + "status = ? "
                + "WHERE service_id = ?";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, service.getServiceName());
            statement.setString(2, service.getDescription());
            statement.setDouble(3, service.getPrice());
            statement.setString(4, service.getStatus());
            statement.setInt(5, service.getServiceId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteService(int serviceId) {

        String sql = "DELETE FROM services WHERE service_id = ?";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, serviceId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Service> getAllServices() {

        List<Service> services = new ArrayList<>();

        String sql = "SELECT service_id, service_name, description, "
                + "price, status "
                + "FROM services "
                + "ORDER BY service_id DESC";

        try (Connection connection =
                     DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Service service = new Service(
                        resultSet.getInt("service_id"),
                        resultSet.getString("service_name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getString("status")
                );

                services.add(service);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return services;
    }
}
