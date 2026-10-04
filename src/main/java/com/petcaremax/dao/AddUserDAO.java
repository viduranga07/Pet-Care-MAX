// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.User;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface AddUserDAO {

    boolean createUser(User user);

    User getUserById(int userId);

    User getUserByUsername(String username);

    User findByUsername(String username);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUser(int userId);

    boolean usernameExists(String username);
}
