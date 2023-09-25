package com.std.springtechdomain.controllers;

import com.std.springtechdomain.dto.RemoteControllerDto;
import com.std.springtechdomain.dto.RemoteControllerInputDto;
import com.std.springtechdomain.services.RemoteControllerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/remotecontrollers")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    // GetMapping
    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {
        return ResponseEntity.ok().body(remoteControllerService.getAllRemoteControllers());
    }

    // GetMapping with {index}
    @GetMapping("/{index}")
    public ResponseEntity<RemoteControllerDto> getRemoteControllerById(@PathVariable("index") Long index) {
        return ResponseEntity.ok().body(remoteControllerService.getRemoteControllerById(index));
    }

    // PostMapping
    @PostMapping
    public ResponseEntity<RemoteControllerDto> createRemoteController(@Valid @RequestBody RemoteControllerInputDto dto) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.createRemoteController(dto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + remoteControllerDto.id).toUriString());
        return ResponseEntity.created(uri).body(remoteControllerDto);
    }

    // PutMapping
    @PutMapping("/{index}")
    public ResponseEntity<RemoteControllerDto> updateRemoteController(@PathVariable("index") Long index, @Valid @RequestBody RemoteControllerInputDto dto) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.updateRemoteController(index, dto);
        return ResponseEntity.ok().body(remoteControllerDto);
    }

    // DeleteMapping
    @DeleteMapping("/{index}")
    public ResponseEntity<RemoteControllerDto> deleteRemoteController(@PathVariable("index") Long index) {
        remoteControllerService.deleteRemoteController(index);
        return ResponseEntity.noContent().build();
    }

}