package org.example.repository;


import org.example.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {

    Material findByIdAndIsActive(String id, boolean active);

List<Material> findByIsActive(boolean active);

    Material findByMaterialName(String materialName);
}
