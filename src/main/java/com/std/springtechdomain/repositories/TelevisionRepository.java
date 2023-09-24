package com.std.springtechdomain.repositories;

import com.std.springtechdomain.dto.TelevisionDto;
import com.std.springtechdomain.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TelevisionRepository extends JpaRepository<Television, Long> {
    List<Television> findAllTelevisionsByBrandEqualsIgnoreCase(Optional<String> brand);
}
