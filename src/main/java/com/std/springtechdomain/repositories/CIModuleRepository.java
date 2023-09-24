package com.std.springtechdomain.repositories;

import com.std.springtechdomain.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CIModuleRepository extends JpaRepository<CIModule, Long> {
    List<CIModule> findAllByNameIgnoreCase(String name);
}
