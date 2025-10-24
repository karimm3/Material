package org.example.repository;

import org.example.Entity.Material;
import org.example.Entity.Uom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UomRepository extends JpaRepository<Uom, String> {

//    List<Uom> findBymaterialId(Material material);
}
