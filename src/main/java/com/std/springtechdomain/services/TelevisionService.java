package com.std.springtechdomain.services;

import com.std.springtechdomain.dto.TelevisionDto;
import com.std.springtechdomain.dto.TelevisionInputDto;
import com.std.springtechdomain.exceptions.RecordNotFoundException;
import com.std.springtechdomain.models.Television;
import com.std.springtechdomain.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository repository;

    @Autowired
    public TelevisionService(TelevisionRepository repository) {
        this.repository = repository;
    }

    public TelevisionDto createTv(TelevisionInputDto televisionDto) {

        Television television = convertToTelevision(televisionDto);

        television.setType(televisionDto.type);
        television.setBrand(televisionDto.brand);
        television.setName(televisionDto.name);
        television.setPrice(televisionDto.price);
        television.setAvailableSize(televisionDto.availableSize);
        television.setRefreshRate(televisionDto.refreshRate);
        television.setScreenType(televisionDto.screenType);
        television.setScreenQuality(televisionDto.screenQuality);
        television.setSmartTv(televisionDto.smartTv);
        television.setWifi(televisionDto.wifi);
        television.setVoiceControl(televisionDto.voiceControl);
        television.setHdr(televisionDto.hdr);
        television.setBluetooth(televisionDto.bluetooth);
        television.setAmbiLight(televisionDto.ambiLight);
        television.setOriginalStock(televisionDto.originalStock);
        television.setSold(televisionDto.sold);

        // Na de call hieronder krijgt de television object automatisch een ID(=unieke key).
        // Dat is het effect van "save" actie.
        repository.save(television);

        return convertToDto(television);
    }


    public List<TelevisionDto> getTvsByBrand(Optional<String> brand) {
        List<Television> allTvs = repository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> allDtos = new ArrayList<>();

        for (Television tv : allTvs) {
            TelevisionDto dto = convertToDto(tv);
            allDtos.add(dto);
        }
        return allDtos;
    }


    public List<TelevisionDto> getAllTvs() {

        List<Television> televisions = repository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television tv : televisions) {

            TelevisionDto tvDto = new TelevisionDto();

            // Dit is allemaal overbodig want er is een convertToDto methode die het regelt.
            // Ik laat het erin zitten voor leerdoeleinden want het werkt.
            tvDto.id = tv.getId();
            tvDto.type = tv.getType();
            tvDto.brand = tv.getBrand();
            tvDto.name = tv.getName();
            tvDto.price = tv.getPrice();
            tvDto.availableSize = tv.getAvailableSize();
            tvDto.refreshRate = tv.getRefreshRate();
            tvDto.screenType = tv.getScreenType();
            tvDto.screenQuality = tv.getScreenQuality();
            tvDto.smartTv = tv.getSmartTv();
            tvDto.wifi = tv.getWifi();
            tvDto.voiceControl = tv.getVoiceControl();
            tvDto.hdr = tv.getHdr();
            tvDto.bluetooth = tv.getBluetooth();
            tvDto.ambiLight = tv.getAmbiLight();
            tvDto.originalStock = tv.getOriginalStock();
            tvDto.sold = tv.getSold();

            televisionDtos.add(tvDto);
        }
        return televisionDtos;
    }

    // De oplossing voor de mismatch voor de verwachte return types is
    // het converteren van de Television naar Television Dto en vice versa
    // convertToTelevision() en convertToDto()
    public TelevisionDto getTvById(Long index) {

        Optional<Television> televisionOptional = repository.findById(index);

        if (televisionOptional.isEmpty()) {
            throw new RecordNotFoundException("TV not found.");
        } else {
            Television television = televisionOptional.get();
            return convertToDto(television);
        }
    }

    // Wist een TV aan de hand van de index
    public void deleteTv(@RequestBody Long index) {
        repository.deleteById(index);
    }

    public TelevisionDto updateTV(Long index, TelevisionInputDto newTelevision) {

        Optional<Television> televisionOptional = repository.findById(index);

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

            repository.save(television);
            return convertToDto(television);


        }
    }

    // Er gaat een DTO object in de method als parameter.
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
    public TelevisionDto convertToDto(Television television) {

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
