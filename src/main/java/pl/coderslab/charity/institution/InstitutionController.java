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

@RestController // czyli zwracamy bezpośrednie Http Response
@RequestMapping("/api/institutions") // adres wynika z zasobu, który jest przetwarzany
@RequiredArgsConstructor
@Slf4j
public class InstitutionController {

    private final InstitutionRepository institutionRepository;

    @GetMapping
    public List<Institution> findAll() {
        return null;
    }

    // Zgodnie z REST po utworzeniu nowego zasobu
    // powinniśmy zwrócić kod odpowiedzi Http 201 CREATE
    // oraz nagłówek Location z informacją, pod jakim adresem
    // dostępny jest utworzony zasób
    @PostMapping
    public ResponseEntity createOne(@Valid @RequestBody /* Używamy aby obiekt był odczytywany z zawartości żądania, np. json, a nie z parametrów żądania */
                                            Institution institution, BindingResult errors) {
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
        Institution saved = institutionRepository.save(institution);
        return ResponseEntity.created(URI.create("/api/institutions/" + saved.getId()))
                .build();
    }

    @GetMapping("/{id}")
    // Domyślny kod odpowiedzi Springa, to 200 OK
    // W rest, jak nie ma zasobu, to nie 200 OK i pusta treść, a 404 Not Found
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();
            return ResponseEntity.ok(institution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMaping("/{id}/delete <- tak w stylu klienta webowego / przeglądarka
    @DeleteMapping("/{id}") // <- tak w stylu klienta rest / system
    // W REST przy poprawny DELETE zwracamy przeważnie kod 204 No Content bez zawartośc
    // lub 200 OK z usuniętym obiektem w treści odpowiedzi
    public ResponseEntity deleteOne(@PathVariable Long id) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();
            institutionRepository.delete(institution);
            return ResponseEntity.ok(institution);
//            return ResponseEntity.noContent().build(); dla 204 No Content bez treści
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public Institution updateOne(@RequestBody Institution institution) {
        return null;
    }

    @PatchMapping("/{id}")
    public Institution updatePartialy(@PathVariable Long id, @RequestBody Map<String, String> updateMap) {
        return null;
    }

}
