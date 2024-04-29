package pt.isec.pdmovies.pdmovies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isec.pdmovies.pdmovies.dto.PersonDto;
import pt.isec.pdmovies.pdmovies.dto.PersonSaveDto;
import pt.isec.pdmovies.pdmovies.service.PersonService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonSaveDto personSaveDto) {
        PersonDto person = personService.createPerson(personSaveDto);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        List<PersonDto> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable UUID id) {
        PersonDto person = personService.getPerson(id);
        return ResponseEntity.ok(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable UUID id, @RequestBody PersonSaveDto personSaveDto) {
        PersonDto person = personService.updatePerson(id, personSaveDto);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
