package com.std.springtechdomain.repositories;

import com.std.springtechdomain.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
    List<RemoteController> findAllByBrandIsIgnoreCase(String brand);
}
