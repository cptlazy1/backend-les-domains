package com.std.springtechdomain.controllers;

import com.std.springtechdomain.dto.WallBracketDto;
import com.std.springtechdomain.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // GetMapping with {index}

    // PostMapping

    // PutMapping

    // DeleteMapping


}
