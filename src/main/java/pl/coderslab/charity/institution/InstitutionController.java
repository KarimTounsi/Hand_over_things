package pl.coderslab.charity.institution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api/institutions")
@RequiredArgsConstructor
@Slf4j
public class InstitutionController {

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
        Institution saved = institutionService.saveInstitutionFromDTO(institutionDTO);
        return ResponseEntity.created(URI.create("/api/institutions/" + saved.getId()))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Institution> optionalInstitution = institutionService.getById(id);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();
            return ResponseEntity.ok(institution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        Optional<Institution> optionalInstitution = institutionService.getById(id);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();
            institution.setStatus(false);
            institutionService.saveInstitution(institution);
            return ResponseEntity.ok(institution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOne(@RequestBody Institution institution) {
        Optional<Institution> optionalInstitution = institutionService.getById(institution.getId());
        if (optionalInstitution.isPresent()) {
            Institution institutionInDb = optionalInstitution.get();
            institutionInDb.setName(institution.getName());
            institutionInDb.setDescription(institution.getDescription());
            institutionService.saveInstitution(institutionInDb);
        }
        return ResponseEntity.noContent().build();
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
