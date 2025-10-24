package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Dto.ApiResponse;
import org.example.Dto.MaterialDto;
import org.example.Dto.MaterialListingDto;
import org.example.Dto.MaterialRequest;
import org.example.Exception.ResourceNotFoundException;
import org.example.Service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // POST /api/materials - Create a new material
    @PostMapping
    public ApiResponse createMaterial(@Valid @RequestBody MaterialRequest request, BindingResult bindingResult) {
        ApiResponse obj=new ApiResponse();
        try {
            if (bindingResult.hasErrors()) {

                // --- THIS IS THE ERROR CHECKING LOGIC ---
                List<Map<String, String>> allFieldErrors = new ArrayList<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    Map<String, String> errorDetails = new LinkedHashMap<>();
                    errorDetails.put("field", error.getField());
                    errorDetails.put("message", error.getDefaultMessage());
                    allFieldErrors.add(errorDetails);
                }
                obj.setData(new LinkedList<>());
                obj.setErrorMessage(allFieldErrors);
                obj.setErrorCode(412);
                obj.setMessage("Validation Failed");
                obj.setSuccess(false);
                obj.setTimestamp(LocalDateTime.now());
                return obj;
            }
        MaterialDto createdMaterial = materialService.createMaterial(request);

            obj.setData(createdMaterial);
            obj.setErrorCode(200);
            obj.setMessage("success");
            obj.setSuccess(true);
        }catch (IllegalArgumentException e) {
            obj.setData(new LinkedList<>());
            obj.setErrorCode(412);
            obj.setMessage(e.getMessage());
            obj.setSuccess(false);
            obj.setTimestamp(LocalDateTime.now());
        }
        catch (Exception e) {
            obj.setData(new LinkedList<>());
            obj.setErrorCode(500);
            obj.setMessage("error");
            obj.setSuccess(false);
            obj.setTimestamp(LocalDateTime.now());
        }
        return obj;

    }

    // PUT /api/materials/{id} - Update a material
    @PutMapping("/{id}")
    public ApiResponse updateMaterial(@PathVariable String id, @Valid @RequestBody MaterialRequest materialDto, BindingResult bindingResult) {
        ApiResponse obj=new ApiResponse();
        try {
            if (bindingResult.hasErrors()) {

                // --- THIS IS THE ERROR CHECKING LOGIC ---
                List<Map<String, String>> allFieldErrors = new ArrayList<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    Map<String, String> errorDetails = new LinkedHashMap<>();
                    errorDetails.put("field", error.getField());
                    errorDetails.put("message", error.getDefaultMessage());
                    allFieldErrors.add(errorDetails);
                }
                obj.setData(new LinkedList<>());
                obj.setErrorMessage(allFieldErrors);
                obj.setErrorCode(412);
                obj.setMessage("Validation Failed");
                obj.setSuccess(false);
                obj.setTimestamp(LocalDateTime.now());
                return obj;
            }
            MaterialDto updatedMaterial = materialService.updateMaterial(id, materialDto);

            obj.setData(updatedMaterial);
            obj.setErrorCode(200);
            obj.setMessage("success");
            obj.setSuccess(true);
        }catch (IllegalArgumentException e) {
            obj.setData(new LinkedList<>());
            obj.setErrorCode(412);
            obj.setMessage(e.getMessage());
            obj.setSuccess(false);
            obj.setTimestamp(LocalDateTime.now());
        }
        catch (ResourceNotFoundException e) {
            obj.setData(new LinkedList<>());
            obj.setErrorCode(412);
            obj.setMessage(e.getMessage());
            obj.setSuccess(false);
            obj.setTimestamp(LocalDateTime.now());
        }
        catch (Exception e) {
            obj.setData(new LinkedList<>());
            obj.setErrorCode(500);
            obj.setMessage(e.getMessage());
            obj.setSuccess(false);
            obj.setTimestamp(LocalDateTime.now());
        }
        return obj;

    }

    // GET /api/materials - Get all materials
    @PostMapping("getALl")
    public ApiResponse getAllMaterials(@RequestBody MaterialListingDto materialDto) {
            ApiResponse obj=new ApiResponse();
            try {

                List<MaterialDto> materials = materialService.getAllMaterials(materialDto);

                obj.setData(materials);
                obj.setErrorCode(500);
                obj.setMessage("success");
                obj.setSuccess(true);
            }catch (IllegalArgumentException e) {
                obj.setData(new LinkedList<>());
                obj.setErrorCode(412);
                obj.setMessage(e.getMessage());
                obj.setSuccess(false);
                obj.setTimestamp(LocalDateTime.now());
            }
            catch (Exception e) {
                obj.setData(new LinkedList<>());
                obj.setErrorCode(500);
                obj.setMessage("error");
                obj.setSuccess(false);
                obj.setTimestamp(LocalDateTime.now());
            }
            return obj;

        }



    // GET /api/materials/{id} - Get a single material by ID
    @GetMapping("/{id}")
    public ApiResponse getMaterialById(@PathVariable String id) {

            ApiResponse obj=new ApiResponse();
            try {

                MaterialDto material = materialService.getMaterialById(id);

                obj.setData(material );
                obj.setErrorCode(200);
                obj.setMessage("success");
                obj.setSuccess(true);
            }catch (IllegalArgumentException e) {
                obj.setData(new LinkedList<>());
                obj.setErrorCode(412);
                obj.setMessage(e.getMessage());
                obj.setSuccess(false);
                obj.setTimestamp(LocalDateTime.now());
            }
            catch (Exception e) {
                obj.setData(new LinkedList<>());
                obj.setErrorCode(500);
                obj.setMessage("error");
                obj.setSuccess(false);
                obj.setTimestamp(LocalDateTime.now());
            }
            return obj;

        }






    // DELETE /api/materials/{id} - Delete a material
    @DeleteMapping("/{id}")
    public ApiResponse deleteMaterial(@PathVariable String id)
    {

                ApiResponse obj=new ApiResponse();
                try {

                    materialService.deleteMaterial(id);

                    obj.setData( new LinkedList<>());
                    obj.setErrorCode(200);
                    obj.setMessage("success");
                    obj.setSuccess(true);
                }catch (IllegalArgumentException e) {
                    obj.setData(new LinkedList<>());
                    obj.setErrorCode(412);
                    obj.setMessage(e.getMessage());
                    obj.setSuccess(false);
                    obj.setTimestamp(LocalDateTime.now());
                }
                catch (Exception e) {
                    obj.setData(new LinkedList<>());
                    obj.setErrorCode(500);
                    obj.setMessage("error");
                    obj.setSuccess(false);
                    obj.setTimestamp(LocalDateTime.now());
                }
                return obj;

            }



}