package com.goonoo.gourmeffin.service;

import com.goonoo.gourmeffin.model.PlaceEntity;

import java.util.List;

public interface PlaceService {
    public List<PlaceEntity> create(final PlaceEntity entity);
    public List<PlaceEntity> retrieve(final String name);
    public List<PlaceEntity> retrieveAll();
    public List<PlaceEntity> update(final PlaceEntity entity);
    public List<PlaceEntity> delete(final PlaceEntity entity);
}
