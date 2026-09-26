package com.petcaremax.dao;

import com.petcaremax.model.Pet;
import com.petcaremax.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {

    @Override
    public boolean addPet(Pet pet) {

        String sql = """
                INSERT INTO pets
                (customer_id, pet_name, species, breed, gender,
                 date_of_birth, weight, notes)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, pet.getCustomerId());
            statement.setString(2, pet.getPetName());
            statement.setString(3, pet.getSpecies());
            statement.setString(4, pet.getBreed());
            statement.setString(5, pet.getGender());

            if (pet.getDateOfBirth() != null) {
                statement.setDate(
                        6,
                        Date.valueOf(pet.getDateOfBirth())
                );
            } else {
                statement.setNull(
                        6,
                        java.sql.Types.DATE
                );
            }

            if (pet.getWeight() != null) {
                statement.setDouble(
                        7,
                        pet.getWeight()
                );
            } else {
                statement.setNull(
                        7,
                        java.sql.Types.DECIMAL
                );
            }

            statement.setString(8, pet.getNotes());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePet(Pet pet) {

        String sql = """
                UPDATE pets
                SET customer_id = ?,
                    pet_name = ?,
                    species = ?,
                    breed = ?,
                    gender = ?,
                    date_of_birth = ?,
                    weight = ?,
                    notes = ?
                WHERE pet_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, pet.getCustomerId());
            statement.setString(2, pet.getPetName());
            statement.setString(3, pet.getSpecies());
            statement.setString(4, pet.getBreed());
            statement.setString(5, pet.getGender());

            if (pet.getDateOfBirth() != null) {
                statement.setDate(
                        6,
                        Date.valueOf(pet.getDateOfBirth())
                );
            } else {
                statement.setNull(
                        6,
                        java.sql.Types.DATE
                );
            }

            if (pet.getWeight() != null) {
                statement.setDouble(
                        7,
                        pet.getWeight()
                );
            } else {
                statement.setNull(
                        7,
                        java.sql.Types.DECIMAL
                );
            }

            statement.setString(8, pet.getNotes());
            statement.setInt(9, pet.getPetId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePet(int petId) {

        String sql = """
                DELETE FROM pets
                WHERE pet_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, petId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Pet> getAllPets() {

        List<Pet> pets = new ArrayList<>();

        String sql = """
                SELECT pet_id,
                       customer_id,
                       pet_name,
                       species,
                       breed,
                       gender,
                       date_of_birth,
                       weight,
                       notes
                FROM pets
                ORDER BY pet_id DESC
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Date sqlDate =
                        resultSet.getDate("date_of_birth");

                Double weight =
                        resultSet.getObject("weight", Double.class);

                Pet pet = new Pet(
                        resultSet.getInt("pet_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getString("pet_name"),
                        resultSet.getString("species"),
                        resultSet.getString("breed"),
                        resultSet.getString("gender"),
                        sqlDate != null
                                ? sqlDate.toLocalDate()
                                : null,
                        weight,
                        resultSet.getString("notes")
                );

                pets.add(pet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pets;
    }
}