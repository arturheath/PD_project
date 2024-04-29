package pt.isec.pdmovies.pdmovies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isec.pdmovies.pdmovies.dto.CelebrityDto;
import pt.isec.pdmovies.pdmovies.dto.CelebritySaveDto;
import pt.isec.pdmovies.pdmovies.service.CelebrityService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/celebrities")
@RequiredArgsConstructor
public class CelebrityController {
    private final CelebrityService celebrityService;

    @PostMapping
    public ResponseEntity<CelebrityDto> createCelebrity(@RequestBody CelebritySaveDto celebritySaveDto) {
        CelebrityDto celebrity = celebrityService.createCelebrity(celebritySaveDto);
        return ResponseEntity.ok(celebrity);
    }

    @GetMapping
    public ResponseEntity<List<CelebrityDto>> getAllCelebrities() {
        List<CelebrityDto> celebrities = celebrityService.getAllCelebrities();
        return ResponseEntity.ok(celebrities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CelebrityDto> getCelebrity(@PathVariable UUID id) {
        CelebrityDto celebrity = celebrityService.getCelebrity(id);
        return ResponseEntity.ok(celebrity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CelebrityDto> updateCelebrity(@PathVariable UUID id, @RequestBody CelebritySaveDto celebritySaveDto) {
        CelebrityDto celebrity = celebrityService.updateCelebrity(id, celebritySaveDto);
        return ResponseEntity.ok(celebrity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCelebrity(@PathVariable UUID id) {
        celebrityService.deleteCelebrity(id);
        return ResponseEntity.ok().build();
    }
}
