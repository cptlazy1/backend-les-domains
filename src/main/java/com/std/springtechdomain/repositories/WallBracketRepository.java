package com.std.springtechdomain.repositories;

import com.std.springtechdomain.models.WallBracket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WallBracketRepository extends JpaRepository<WallBracket, Long> {
    List<WallBracket> findAllByNameIgnoreCase(String name);
}
