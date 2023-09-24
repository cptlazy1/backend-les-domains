package com.std.springtechdomain.services;

import com.std.springtechdomain.dto.RemoteControllerDto;
import com.std.springtechdomain.dto.RemoteControllerInputDto;
import com.std.springtechdomain.exceptions.RecordNotFoundException;
import com.std.springtechdomain.models.RemoteController;
import com.std.springtechdomain.repositories.RemoteControllerRepository;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    // create remote Controller
    public RemoteControllerDto createRemoteController(RemoteControllerInputDto dto) {
        RemoteController remoteController = convertToRemoteController(dto);

        remoteController.setBrand(dto.brand);
        remoteController.setBatteryType(dto.batteryType);
        remoteController.setPrice(dto.price);
        remoteController.setCompatibleWith(dto.compatibleWith);
        remoteController.setOriginalStock(dto.originalStock);

        return convertToRemoteControllerDto(remoteController);

    }

    // get all remote controllers
    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController rc : remoteControllers) {
            RemoteControllerDto rcDto = convertToRemoteControllerDto(rc);
            remoteControllerDtos.add(rcDto);
        }
        return remoteControllerDtos;
    }

    // get remote controller by index
    public RemoteControllerDto getRemoteControllerById(Long index) {
        Optional<RemoteController> remoteControllerOptional = remoteControllerRepository.findById(index);

        if (remoteControllerOptional.isEmpty()) {
            throw new RecordNotFoundException("Remote controller not found");
        } else {
            RemoteController remoteController = remoteControllerOptional.get();
            return convertToRemoteControllerDto(remoteController);
        }

    }


    // delete remote controller
    public void deleteRemoteController(@RequestBody Long index) {
        remoteControllerRepository.deleteById(index);
    }

    // update remote controller
    public RemoteControllerDto updateRemoteController(Long index, RemoteControllerInputDto newRemoteController) {
        Optional<RemoteController> remoteControllerOptional = remoteControllerRepository.findById(index);

        if (remoteControllerOptional.isEmpty()) {
            throw new RecordNotFoundException("Remote controller not found.");
        } else {
            RemoteController remoteController = remoteControllerOptional.get();

            remoteController.setBrand(newRemoteController.getBrand());
            remoteController.setBatteryType(newRemoteController.getBatteryType());
            remoteController.setCompatibleWith(newRemoteController.getCompatibleWith());
            remoteController.setPrice(newRemoteController.getPrice());
            remoteController.setOriginalStock(newRemoteController.getOriginalStock());

            RemoteController updatedRemoteController = remoteControllerRepository.save(remoteController);

            return convertToRemoteControllerDto(updatedRemoteController);

        }
    }


    // convert to remote controller
    public RemoteController convertToRemoteController(RemoteControllerInputDto dto) {
        var remoteController = new RemoteController();

        remoteController.setBrand(dto.getBrand());
        remoteController.setBatteryType(dto.getBatteryType());
        remoteController.setPrice(dto.getPrice());
        remoteController.setCompatibleWith(dto.getCompatibleWith());
        remoteController.setOriginalStock(dto.getOriginalStock());

        return remoteController;

    }

    // convert to remote controller dto
    public RemoteControllerDto convertToRemoteControllerDto(RemoteController remoteController) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();

        remoteControllerDto.setBrand(remoteController.getBrand());
        remoteControllerDto.setBatteryType(remoteController.getBatteryType());
        remoteControllerDto.setPrice(remoteController.getPrice());
        remoteControllerDto.setCompatibleWith(remoteControllerDto.getCompatibleWith());
        remoteController.setOriginalStock(remoteController.getOriginalStock());

        return remoteControllerDto;
    }
}
