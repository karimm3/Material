package org.example.Service;

import jakarta.validation.Valid;
import org.example.Dto.MaterialDto;
import org.example.Dto.MaterialListingDto;
import org.example.Dto.MaterialRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService {

    MaterialDto createMaterial(@Valid MaterialRequest materialDto);

    List<MaterialDto> getAllMaterials(MaterialListingDto materialDto);

    MaterialDto getMaterialById(String id);

    MaterialDto updateMaterial(String id, @Valid MaterialRequest materialDto);

    void deleteMaterial(String id);
}
