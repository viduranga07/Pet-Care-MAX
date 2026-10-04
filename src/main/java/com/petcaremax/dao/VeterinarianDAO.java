// This class handles one part of the PetCareMAX application.
package com.petcaremax.dao;

import com.petcaremax.model.Veterinarian;
import java.util.List;

// This DAO class handles database operations for this feature.
public interface VeterinarianDAO {

    boolean addVeterinarian(Veterinarian veterinarian);

    boolean updateVeterinarian(Veterinarian veterinarian);

    boolean deleteVeterinarian(int veterinarianId);

    List<Veterinarian> getAllVeterinarians();
}
