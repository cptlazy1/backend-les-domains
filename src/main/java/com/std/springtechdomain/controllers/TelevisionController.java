package com.std.springtechdomain.controllers;

import com.std.springtechdomain.dto.TelevisionDto;
import com.std.springtechdomain.dto.TelevisionInputDto;
import com.std.springtechdomain.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/televisions")
public class TelevisionController {
    private final TelevisionService televisionService;

    // Constructor
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    // Returnt alle TVs
    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTvs() {
        return ResponseEntity.ok().body(televisionService.getAllTvs());
    }

    // Returnt de TV op de index positie
    @GetMapping(value = "/{index}")
    public ResponseEntity <TelevisionDto> getTv(@PathVariable("index") Long index) {
        return ResponseEntity.ok().body(televisionService.getTvById(index));
    }
    // Werkt niet en zorgt ervoor dat de methode om te zoeken met {index} ook niet meer werkt
//    @GetMapping(value = "/{brand}")
//    public ResponseEntity<List<TelevisionDto>> getTvsByBrand(@RequestParam(value = "brand", required = false) Optional<String> brand) {
//        return ResponseEntity.ok().body(televisionService.getTvsByBrand(brand));
//    }



    // Maakt een TV aan
    @PostMapping
    public ResponseEntity<TelevisionDto> createTv(@Valid @RequestBody TelevisionInputDto televisionInputDto) {

        TelevisionDto televisionDto = televisionService.createTv(televisionInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + televisionDto.id).toUriString());
        return ResponseEntity.created(uri).body(televisionDto);
    }

    // Verwijdert een TV
    @DeleteMapping("/{index}")
    public ResponseEntity<Object> deleteTv(@PathVariable Long index) {
        televisionService.deleteTv(index);
        return ResponseEntity.noContent().build();
    }

    // TV wordt aangepast
    @PutMapping("/{index}")
    public ResponseEntity<TelevisionDto> updateTv(@PathVariable Long index, @Valid @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto dto = televisionService.updateTV(index, televisionInputDto);
        return ResponseEntity.ok().body(dto);
    }

}
