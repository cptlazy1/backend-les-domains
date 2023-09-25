package com.std.springtechdomain.controllers;

import com.std.springtechdomain.dto.WallBracketDto;
import com.std.springtechdomain.dto.WallBracketInputDto;
import com.std.springtechdomain.services.WallBracketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wallbrackets")
public class WallBracketController {

    private final WallBracketService wallBracketService;

    // Constructor
    public WallBracketController(WallBracketService wallBracketService){
        this.wallBracketService = wallBracketService;
    }

    // GetMapping
    @GetMapping
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {
        return ResponseEntity.ok().body(wallBracketService.getAllWallBrackets());
    }

    // GetMapping with {index}
    @GetMapping("/{index}")
    public ResponseEntity<WallBracketDto> getWallBracketById(@PathVariable("index") Long index) {
        return ResponseEntity.ok().body(wallBracketService.getWallBracketById(index));
    }

    // PostMapping
    @PostMapping
    public ResponseEntity<WallBracketDto> createWallBracket(@Valid @RequestBody WallBracketInputDto dto) {
        WallBracketDto wallBracketDto = wallBracketService.createWallBracket(dto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + wallBracketDto.id).toUriString());
        return ResponseEntity.created(uri).body(wallBracketDto);
    }

    // PutMapping
    @PutMapping("/{index}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable("index") Long index, @Valid @RequestBody WallBracketInputDto dto) {
        WallBracketDto wallBracketDto = wallBracketService.updateWallBracket(index, dto);
        return ResponseEntity.ok().body(wallBracketDto);
    }

    // DeleteMapping
    @DeleteMapping("/{index}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable("index") Long index) {
        wallBracketService.deleteWallBracket(index);
        return ResponseEntity.noContent().build();
    }

}