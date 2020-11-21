package pl.coderslab.charity.institution.controllers.restControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.DTOS.InstitutionDTO;
import pl.coderslab.charity.institution.exceptions.ObjectNotFoundException;
import pl.coderslab.charity.institution.service.InstitutionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/institution")
@RequiredArgsConstructor
@Slf4j
public class InstitutionRestController {

    private final InstitutionService institutionService;



    @GetMapping
    public ResponseEntity<List<Institution>> findAllWithStatusTrue() {

        List<Institution> institutions = institutionService.getAllByStatus(true);

        return ResponseEntity.ok(institutions);
    }

    @PostMapping
    public ResponseEntity createOne(@Valid @RequestBody
                                            InstitutionDTO institutionDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            try {
                return ResponseEntity.badRequest().body(
                        new ObjectMapper().writeValueAsString
                                (errors.getAllErrors()
                                        .stream()
                                        .collect(Collectors.toMap(
                                                (ObjectError oE) -> oE.getCode(),
                                                oE -> oE.getDefaultMessage()))));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        InstitutionDTO saved = institutionService.saveInstitutionFromDTO(institutionDTO);
        return ResponseEntity.created(URI.create("/api/institutions/" + saved.getId()))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){

        try {
            Institution institution = institutionService.findInstitutionById(id);
            if (institution != null) {
                return ResponseEntity.ok(institution);
            }
        }catch (ObjectNotFoundException o){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {

      Institution InstitutionDeleted = institutionService.delete(id);
        if (InstitutionDeleted != null) {
            return ResponseEntity.ok(InstitutionDeleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOne(@RequestBody Institution institution) {
        Institution institutionUpdated = institutionService.update(institution);
        return ResponseEntity.ok(institutionUpdated);
    }


    @PatchMapping("/{id}")
    public ResponseEntity updatePartially(@PathVariable Long id, @RequestBody Map<String, String> updateMap) {
        Optional<Institution> optionalInstitution = institutionService.getById(id);
        if (optionalInstitution.isPresent()) {
            Institution institutionInDb = optionalInstitution.get();
       if (updateMap.get("description") !="" ||  updateMap.get("name") !="") {
           if (updateMap.containsKey("name") && updateMap.get("name") !=""){
               institutionInDb.setName(updateMap.get("name"));
           }
           if (updateMap.containsKey("description") && updateMap.get("description") !=""){
               institutionInDb.setDescription(updateMap.get("description"));
           }
       }

            institutionService.saveInstitution(institutionInDb);
        }
        return ResponseEntity.noContent().build();
    }

}
