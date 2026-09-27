package com.petcaremax.dao;

import com.petcaremax.model.Veterinarian;
import java.util.List;

public interface VeterinarianDAO {

    boolean addVeterinarian(Veterinarian veterinarian);

    boolean updateVeterinarian(Veterinarian veterinarian);

    boolean deleteVeterinarian(int veterinarianId);

    List<Veterinarian> getAllVeterinarians();
}