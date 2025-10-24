package org.example.Implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.Dto.*;
import org.example.Entity.*;
// import org.example.Exception.ResourceNotFoundException; // No longer needed
import org.example.Service.MaterialService;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class MaterialImplementation implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private UomRepository uomRepository;
    @Autowired
    private MaterialTypeRepository materialTypeRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private EntityManager entityManager;


    @Transactional
    public MaterialDto createMaterial(MaterialRequest materialDto) {


        Material existingMaterial = materialRepository.findByMaterialName(materialDto.getMaterialName());
        if (existingMaterial != null) {
            throw new IllegalArgumentException("Material with name " + materialDto.getMaterialName() + " already exists");
        }

        // This is the "check its valid or not"
        Uom uom = uomRepository.findById(materialDto.getMaterialUomld())
                               .orElseThrow(() -> new IllegalArgumentException("Uom not found with id: " + materialDto.getMaterialUomld()));

        MaterialType type = materialTypeRepository.findById(materialDto.getMaterialTypeld())
                                                  .orElseThrow(() -> new IllegalArgumentException("MaterialType not found with id: " + materialDto.getMaterialTypeld()));

        Manufacturer manufacturer = manufacturerRepository.findById(materialDto.getMaterialManufacturerld())
                                                          .orElseThrow(() -> new IllegalArgumentException("Manufacturer not found with id: " + materialDto.getMaterialManufacturerld()));

        Vendor vendor = vendorRepository.findById(materialDto.getMaterialVendorld())
                                        .orElseThrow(() -> new IllegalArgumentException("Vendor not found with id: " + materialDto.getMaterialVendorld()));


        Material material = new Material();
        material.setMaterialName(materialDto.getMaterialName());
        material.setMaterialRatePerPack(materialDto.getMaterialRatePerPack());
        material.setMaterialPackSize(materialDto.getMaterialPackSize());
        material.setMaterialDesc(materialDto.getMaterialDesc());
        material.setActive(true);
        // take from token createdBy and updatedBy
        material.setCreatedBy("");
        material.setUpdatedBy("");

        material.setCreatedOn(LocalDateTime.now());



        material.setUom(uom);
        material.setType(type);
        material.setManufacturer(manufacturer);
        material.setVendor(vendor);


        Material savedMaterial = materialRepository.save(material);


        return mapToDto(savedMaterial);
    }


    @Transactional
    public MaterialDto updateMaterial(String id, MaterialRequest materialDto) {



        // 2. Find existing material
        Material existingMaterial = materialRepository.findById(id)
                                                      .orElseThrow(() -> new IllegalArgumentException("Material not found with id: " + id));


        Uom uom = uomRepository.findById(materialDto.getMaterialUomld())
                               .orElseThrow(() -> new IllegalArgumentException("Uom not found with id: " + materialDto.getMaterialUomld()));

        MaterialType type = materialTypeRepository.findById(materialDto.getMaterialTypeld())
                                                  .orElseThrow(() -> new IllegalArgumentException("MaterialType not found with id: " + materialDto.getMaterialTypeld()));

        Manufacturer manufacturer = manufacturerRepository.findById(materialDto.getMaterialManufacturerld())
                                                          .orElseThrow(() -> new IllegalArgumentException("Manufacturer not found with id: " + materialDto.getMaterialManufacturerld()));

        Vendor vendor = vendorRepository.findById(materialDto.getMaterialVendorld())
                                        .orElseThrow(() -> new IllegalArgumentException("Vendor not found with id: " + materialDto.getMaterialVendorld()));


        existingMaterial.setMaterialName(materialDto.getMaterialName());
        existingMaterial.setMaterialRatePerPack(materialDto.getMaterialRatePerPack());
        existingMaterial.setMaterialPackSize(materialDto.getMaterialPackSize());
        existingMaterial.setMaterialDesc(materialDto.getMaterialDesc());
        existingMaterial.setActive(true);
        existingMaterial.setUpdatedOn(LocalDateTime.now());
        existingMaterial.setUpdatedBy("");


        existingMaterial.setUom(uom);
        existingMaterial.setType(type);
        existingMaterial.setManufacturer(manufacturer);
        existingMaterial.setVendor(vendor);


        Material updatedMaterial = materialRepository.save(existingMaterial);


        return mapToDto(updatedMaterial);
    }

    public List<MaterialDto> getAllMaterials(MaterialListingDto request) {

        int page = request.getPage()-1;
        int size = request.getSize();
        String sortBy = request.getSortBy()==null?"id":request.getSortBy();
        String sortDir = request.getSortOrder();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Material> cq = cb.createQuery(Material.class);
        Root<Material> root = cq.from(Material.class);


        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isTrue(root.get("isActive")));

        if (request.getSearch() != null) {

            if (StringUtils.hasText(request.getSearch().getMaterialName())) {
                predicates.add(cb.like(cb.lower(root.get("materialName")),
                        "%" + request.getSearch().getMaterialName().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(request.getSearch().getMaterialUomld())) {
                predicates.add(cb.equal(root.get("uom").get("uomld"), request.getSearch().getMaterialUomld()));
            }

            if (StringUtils.hasText(request.getSearch().getMaterialTypeld())) {
                predicates.add(cb.equal(root.get("type").get("materialTypeld"), request.getSearch().getMaterialTypeld()));
            }

            if (StringUtils.hasText(request.getSearch().getMaterialManufacturerld())) {
                predicates.add(cb.equal(root.get("manufacturer").get("materialManufacturerld"), request.getSearch().getMaterialManufacturerld()));
            }

            if (StringUtils.hasText(request.getSearch().getMaterialVendorld())) {
                predicates.add(cb.equal(root.get("vendor").get("materialVendorld"), request.getSearch().getMaterialVendorld()));
            }

        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        cq.select(root);

        if (sortDir.equalsIgnoreCase("asc")) {
            cq.orderBy(cb.asc(root.get(sortBy)));
        } else {
            if(sortBy.equalsIgnoreCase("")) {
                cq.orderBy(cb.desc(root.get("id")));
            }else {
                cq.orderBy(cb.desc(root.get(sortBy)));
            }

        }


        TypedQuery<Material> query = entityManager.createQuery(cq);

    if(page!=0 && size!=0 ) {
        query.setFirstResult(page * size);
        query.setMaxResults(size);
    }

        List<Material> materialList = query.getResultList();


        return materialList.stream()
                                 .map(this::mapToDto)
                                 .collect(Collectors.toList());
    }

    public MaterialDto getMaterialById(String id) {
        Material material = materialRepository.findByIdAndIsActive(id,true);

        if(material == null) {
            throw  new IllegalArgumentException("Material not found with id: " + id);
        }
        return mapToDto(material);
    }

    @Transactional
    public void deleteMaterial(String id) {
        Material material = materialRepository.findById(id)
                                              .orElseThrow(() -> new IllegalArgumentException("Material not found with id: " + id));

        material.setActive(false);
        materialRepository.save(material);
    }

    // --- Helper method to map Entity to DTO ---
    private MaterialDto mapToDto(Material material) {
        MaterialDto dto = new MaterialDto();
        dto.setMaterialld(material.getId());
//        dto.setExternalld(material.getExternalld());
        dto.setMaterialName(material.getMaterialName());
        dto.setMaterialRatePerPack(material.getMaterialRatePerPack());
        dto.setMaterialPackSize(material.getMaterialPackSize());
        dto.setMaterialDesc(material.getMaterialDesc());
        dto.setActive(material.isActive());
        dto.setCreatedBy(material.getCreatedBy());
        dto.setCreatedOn(material.getCreatedOn());
        dto.setUpdatedBy(material.getUpdatedBy());
        dto.setUpdatedOn(material.getUpdatedOn());

        // Map related entities
        dto.setUom(mapUomToDto(material.getUom()));
        dto.setType(mapMaterialTypeToDto(material.getType()));
        dto.setManufacturer(mapManufacturerToDto(material.getManufacturer()));
        dto.setVendor(mapVendorToDto(material.getVendor()));

        return dto;
    }

    private UomDto mapUomToDto(Uom uom) {
        if (uom == null) return null;
        UomDto dto = new UomDto();
        dto.setUomld(uom.getUomld());
        dto.setUomName(uom.getUomName());
        dto.setUomSymbol(uom.getUomSymbol());
        return dto;
    }

    private MaterialTypeDto mapMaterialTypeToDto(MaterialType type) {
        if (type == null) return null;
        MaterialTypeDto dto = new MaterialTypeDto();
        dto.setMaterialTypeld(type.getMaterialTypeld());
        dto.setMaterialTypeName(type.getMaterialTypeName());
        dto.setMaterialTypeDesc(type.getMaterialTypeDesc());
        return dto;
    }

    private ManufacturerDto mapManufacturerToDto(Manufacturer manufacturer) {
        if (manufacturer == null) return null;
        ManufacturerDto dto = new ManufacturerDto();
        dto.setMaterialManufacturerld(manufacturer.getMaterialManufacturerld());
        dto.setMaterialManufacturerName(manufacturer.getMaterialManufacturerName());
        dto.setMaterialManufacturerContactPerson(manufacturer.getMaterialManufacturerContactPerson());
        dto.setMaterialManufacturerContactNumber(manufacturer.getMaterialManufacturerContactNumber());
        dto.setMaterialManufacturerEmail(manufacturer.getMaterialManufacturerEmail());
        return dto;
    }

    private VendorDto mapVendorToDto(Vendor vendor) {
        if (vendor == null) return null;
        VendorDto dto = new VendorDto();
        dto.setMaterialVendorld(vendor.getMaterialVendorld());
        dto.setMaterialVendorName(vendor.getMaterialVendorName());
        dto.setMaterialVendorContactPerson(vendor.getMaterialVendorContactPerson());
        dto.setMaterialVendorContactNumber(vendor.getMaterialVendorContactNumber());
        dto.setMaterialVendorEmail(vendor.getMaterialVendorEmail());
        return dto;
    }



}

