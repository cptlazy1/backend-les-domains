package com.std.springtechdomain.services;

import com.std.springtechdomain.dto.CIModuleDto;
import com.std.springtechdomain.dto.CIModuleInputDto;
import com.std.springtechdomain.exceptions.RecordNotFoundException;
import com.std.springtechdomain.models.CIModule;
import com.std.springtechdomain.repositories.CIModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;


    public CIModuleService(CIModuleRepository CIModuleRepository) {
        this.ciModuleRepository = CIModuleRepository;
    }

    // create ci module
    public CIModuleDto createCIModule(CIModuleInputDto dto) {

        CIModule ciModule = convertToCIModule(dto);

        ciModule.setName(dto.name);
        ciModule.setType(dto.type);
        ciModule.setPrice(dto.price);

        ciModuleRepository.save(ciModule);

        return convertToCIModuleDto(ciModule);

    }

    // get all ci modules
    public List<CIModuleDto> getAllCIModules() {
        List<CIModule> ciModules = ciModuleRepository.findAll();
        List<CIModuleDto> ciModuleDtos = new ArrayList<>();

        for (CIModule cm : ciModules) {
            CIModuleDto ciModuleDto = convertToCIModuleDto(cm);
            ciModuleDtos.add(ciModuleDto);

        }
        return ciModuleDtos;
    }

    // get ci module by index
    public CIModuleDto getCIModuleById(Long index) {
        Optional<CIModule> ciModuleOptional = ciModuleRepository.findById(index);
        if (ciModuleOptional.isEmpty()) {
            throw new RecordNotFoundException("CI Module not found");
        } else {
            CIModule ciModule = ciModuleOptional.get();
            return convertToCIModuleDto(ciModule);
        }
    }


    // delete ci module
    public void deleteCIModule(@RequestBody Long index) {
        ciModuleRepository.deleteById(index);
    }

    // update ci module
    public CIModuleDto updateCIModule(Long index, CIModuleInputDto newCIModule) {

        Optional<CIModule> ciModuleOptional = ciModuleRepository.findById(index);

        if (ciModuleOptional.isEmpty()) {
            throw new RecordNotFoundException("CI Module not found.");
        } else {
            CIModule ciModule = ciModuleOptional.get();

            ciModule.setName(newCIModule.getName());
            ciModule.setType(newCIModule.getType());
            ciModule.setPrice(newCIModule.getPrice());

            CIModule updatedCIModule = ciModuleRepository.save(ciModule);
            return convertToCIModuleDto(updatedCIModule);
        }

    }

    // convert to ci module
    public CIModule convertToCIModule(CIModuleInputDto dto) {

        var ciModule = new CIModule();

        ciModule.setType(dto.getType());
        ciModule.setName(dto.getName());
        ciModule.setPrice(dto.getPrice());

        return ciModule;
    }

    // convert to ci module dto
    public CIModuleDto convertToCIModuleDto(CIModule ciModule) {

        CIModuleDto ciModuleDto = new CIModuleDto();

        ciModuleDto.setId(ciModule.getId());
        ciModuleDto.setName(ciModule.getName());
        ciModuleDto.setType(ciModule.getType());
        ciModuleDto.setPrice(ciModule.getPrice());

        return ciModuleDto;
    }

}
