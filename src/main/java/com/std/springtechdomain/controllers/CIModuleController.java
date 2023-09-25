package com.std.springtechdomain.controllers;

import com.std.springtechdomain.dto.CIModuleDto;
import com.std.springtechdomain.dto.CIModuleInputDto;
import com.std.springtechdomain.services.CIModuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;

    // Constructor
    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    // GetMapping
    @GetMapping
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        return ResponseEntity.ok().body(ciModuleService.getAllCIModules());
    }

    // GetMapping with {index}
    @GetMapping("/{index}")
    public ResponseEntity<CIModuleDto> getCIModuleById(@PathVariable ("index") Long index) {
        return ResponseEntity.ok().body(ciModuleService.getCIModuleById(index));
    }

    // PostMapping
    @PostMapping
    public ResponseEntity<CIModuleDto> createCIModule(@Valid @RequestBody CIModuleInputDto dto) {
        CIModuleDto ciModuleDto = ciModuleService.createCIModule(dto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + ciModuleDto.id).toUriString());
        return ResponseEntity.created(uri).body(ciModuleDto);
    }

    // PutMapping
    @PutMapping("/{index}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable("index") Long index, @Valid @RequestBody CIModuleInputDto dto) {
        CIModuleDto ciModuleDto = ciModuleService.updateCIModule(index, dto);
        return ResponseEntity.ok().body(ciModuleDto);
    }

    // DeleteMapping
    @DeleteMapping("/{index}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable("index") Long index) {
        ciModuleService.deleteCIModule(index);
        return ResponseEntity.noContent().build();
    }

}