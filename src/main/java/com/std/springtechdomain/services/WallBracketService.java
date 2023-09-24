package com.std.springtechdomain.services;

import com.std.springtechdomain.dto.WallBracketDto;
import com.std.springtechdomain.dto.WallBracketInputDto;
import com.std.springtechdomain.exceptions.RecordNotFoundException;
import com.std.springtechdomain.models.WallBracket;
import com.std.springtechdomain.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;


    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    // Create Wall Bracket
    public WallBracketDto createWallBracket(WallBracketInputDto wallBracketDto) {

        WallBracket wallBracket = convertToWallBracket(wallBracketDto);

        wallBracket.setName(wallBracketDto.name);
        wallBracket.setAdjustable(wallBracketDto.adjustable);
        wallBracket.setSize(wallBracketDto.size);
        wallBracket.setPrice(wallBracketDto.getPrice());

        wallBracketRepository.save(wallBracket);

        return convertToWallBracketDto(wallBracket);
    }

    // Get all Wall Brackets
    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> wallBrackets = wallBracketRepository.findAll();
        List<WallBracketDto> wallBracketDtos = new ArrayList<>();

        for (WallBracket wallBracket : wallBrackets) {

            WallBracketDto wallBracketDto = convertToWallBracketDto(wallBracket);
            wallBracketDtos.add(wallBracketDto);

        }
        return wallBracketDtos;
    }

    // Get Wall Bracket By Id
    public WallBracketDto getWallBracketById(Long index) {

        Optional<WallBracket> wallBracketOptional = wallBracketRepository.findById(index);

        if (wallBracketOptional.isEmpty()) {
            throw new RecordNotFoundException("Wall Bracket not found.");
        } else {
            WallBracket wallBracket = wallBracketOptional.get();
            return convertToWallBracketDto(wallBracket);
        }

    }
    // Delete Wall Bracket
    public void deleteWallBracket(@RequestBody Long index) {

        wallBracketRepository.deleteById(index);

    }

    // Update Wall Bracket
    public WallBracketDto updateWallBracket(Long index, WallBracketInputDto newWallBracket) {

        Optional<WallBracket> wallBracketOptional = wallBracketRepository.findById(index);

        if (wallBracketOptional.isEmpty()) {
            throw new RecordNotFoundException("Wall Bracket not found.");
        } else {
            WallBracket wallBracket = wallBracketOptional.get();

            wallBracket.setName(newWallBracket.getName());
            wallBracket.setSize(newWallBracket.getSize());
            wallBracket.setAdjustable(newWallBracket.getAdjustable());
            wallBracket.setPrice(newWallBracket.getPrice());

            WallBracket updatedWallBracket = wallBracketRepository.save(wallBracket);

            return convertToWallBracketDto(updatedWallBracket);

        }
    }
    // Convert to Wall Bracket
    public WallBracket convertToWallBracket(WallBracketInputDto dto) {
        var wallbracket = new WallBracket();

        wallbracket.setName(dto.getName());
        wallbracket.setAdjustable(dto.getAdjustable());
        wallbracket.setSize(dto.getSize());
        wallbracket.setPrice(dto.getPrice());

        return wallbracket;
    }

    // Convert to Wall Bracket Dto
    public WallBracketDto convertToWallBracketDto(WallBracket wallBracket) {
        WallBracketDto wallBracketDto = new WallBracketDto();

        wallBracketDto.setName(wallBracket.getName());
        wallBracketDto.setAdjustable(wallBracket.getAdjustable());
        wallBracketDto.setSize(wallBracket.getSize());
        wallBracketDto.setPrice(wallBracket.getPrice());

        return wallBracketDto;
    }
}
