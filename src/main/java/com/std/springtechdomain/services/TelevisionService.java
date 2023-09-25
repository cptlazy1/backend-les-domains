package com.std.springtechdomain.services;

import com.std.springtechdomain.dto.TelevisionDto;
import com.std.springtechdomain.dto.TelevisionInputDto;
import com.std.springtechdomain.exceptions.RecordNotFoundException;
import com.std.springtechdomain.models.Television;
import com.std.springtechdomain.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository tvRepository;

    public TelevisionService(TelevisionRepository repository) {
        this.tvRepository = repository;
    }

    public TelevisionDto createTv(TelevisionInputDto dto) {

        Television television = convertToTelevision(dto);

        television.setType(dto.type);
        television.setBrand(dto.brand);
        television.setName(dto.name);
        television.setPrice(dto.price);
        television.setAvailableSize(dto.availableSize);
        television.setRefreshRate(dto.refreshRate);
        television.setScreenType(dto.screenType);
        television.setScreenQuality(dto.screenQuality);
        television.setSmartTv(dto.smartTv);
        television.setWifi(dto.wifi);
        television.setVoiceControl(dto.voiceControl);
        television.setHdr(dto.hdr);
        television.setBluetooth(dto.bluetooth);
        television.setAmbiLight(dto.ambiLight);
        television.setOriginalStock(dto.originalStock);
        television.setSold(dto.sold);

        // Na de call hieronder krijgt de television object automatisch een ID(=unieke key).
        // Dat is het effect van "save" actie.
        tvRepository.save(television);

        return convertToTelevisionDto(television);
    }


//    public List<TelevisionDto> getTvsByBrand(String brand) {
//        List<Television> allTvs = tvRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
//        List<TelevisionDto> allDtos = new ArrayList<>();
//
//        for (Television tv : allTvs) {
//            TelevisionDto dto = convertToDto(tv);
//            allDtos.add(dto);
//        }
//        return allDtos;
//    }


    public List<TelevisionDto> getAllTvs() {

        List<Television> televisions = tvRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television tv : televisions) {

            TelevisionDto tvDto = convertToTelevisionDto(tv);
            televisionDtos.add(tvDto);
        }
        return televisionDtos;
    }

    // De oplossing voor de mismatch voor de verwachte return types is
    // het converteren van de Television naar Television Dto en vice versa
    // convertToTelevision() en convertToDto()
    public TelevisionDto getTvById(Long index) {

        Optional<Television> tvOptional = tvRepository.findById(index);

        if (tvOptional.isEmpty()) {
            throw new RecordNotFoundException("TV not found.");
        } else {
            Television television = tvOptional.get();
            return convertToTelevisionDto(television);
        }
    }

    // Wist een TV aan de hand van de index
    public void deleteTv(@RequestBody Long index) {
        tvRepository.deleteById(index);
    }

    // Updatet een TV aan de hand van de index
    public TelevisionDto updateTV(Long index, TelevisionInputDto newTelevision) {

        Optional<Television> televisionOptional = tvRepository.findById(index);

        if (televisionOptional.isEmpty()) {
            throw new RecordNotFoundException("TV not found.");

        } else {
            Television television = televisionOptional.get();

            television.setType(newTelevision.getType());
            television.setBrand(newTelevision.getBrand());
            television.setName(newTelevision.getName());
            television.setPrice(newTelevision.getPrice());
            television.setAvailableSize(newTelevision.getAvailableSize());
            television.setRefreshRate(newTelevision.getRefreshRate());
            television.setScreenType(newTelevision.getScreenType());
            television.setScreenQuality(newTelevision.getScreenQuality());
            television.setSmartTv(newTelevision.getSmartTv());
            television.setWifi(newTelevision.getWifi());
            television.setVoiceControl(newTelevision.getVoiceControl());
            television.setHdr(newTelevision.getHdr());
            television.setBluetooth(newTelevision.getBluetooth());
            television.setAmbiLight(newTelevision.getAmbiLight());
            television.setOriginalStock(newTelevision.getOriginalStock());
            television.setSold(newTelevision.getSold());

            Television updatedTelevision = tvRepository.save(television);
            return convertToTelevisionDto(updatedTelevision);
        }
    }

    // Er gaat een input DTO object in de method als parameter.
    // Return is een Television object. Setters van de Television class
    // krijgen hun parameter via de getters van de TelevisionInputDto class.
    public Television convertToTelevision(TelevisionInputDto dto) {

        var television = new Television();

        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());

        return television;
    }

    // Hier gaat een Television object in de method als parameter.
    // Return is een Dto object. Setters van de TelevisionDto class
    // krijgen hun parameter via de getters van de Television class.
    public TelevisionDto convertToTelevisionDto(Television television) {

        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.setId(television.getId());
        televisionDto.setType(television.getType());
        televisionDto.setBrand(television.getBrand());
        televisionDto.setName(television.getName());
        televisionDto.setPrice(television.getPrice());
        televisionDto.setAvailableSize(television.getAvailableSize());
        televisionDto.setRefreshRate(television.getRefreshRate());
        televisionDto.setScreenType(television.getScreenType());
        televisionDto.setScreenQuality(television.getScreenQuality());
        televisionDto.setSmartTv(television.getSmartTv());
        televisionDto.setWifi(television.getWifi());
        televisionDto.setVoiceControl(television.getVoiceControl());
        televisionDto.setHdr(television.getHdr());
        televisionDto.setBluetooth(television.getBluetooth());
        televisionDto.setBluetooth(television.getBluetooth());
        television.setAmbiLight(television.getAmbiLight());
        televisionDto.setOriginalStock(television.getOriginalStock());
        televisionDto.setSold(television.getSold());

        return televisionDto;

    }

}
