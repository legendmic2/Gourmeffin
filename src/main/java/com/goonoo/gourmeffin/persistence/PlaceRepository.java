package com.goonoo.gourmeffin.persistence;

import com.goonoo.gourmeffin.model.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<PlaceEntity, String> {
    List<PlaceEntity> findByName(String name);
}
