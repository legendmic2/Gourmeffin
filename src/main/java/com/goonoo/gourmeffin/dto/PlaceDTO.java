package com.goonoo.gourmeffin.dto;

import com.goonoo.gourmeffin.model.PlaceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceDTO {
    // 현재는 Place 외의 table이 존재하지 않아서, Entity와 DTO의 field가 동일
    private String id;
    private String name;
    private String address;
    private String placeType;
    private String description;
    private double rating;

    public PlaceDTO(final PlaceEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.placeType = entity.getPlaceType();
        this.description = entity.getDescription();
        this.rating = entity.getRating();
    }

    public static PlaceEntity toEntity(final PlaceDTO dto) {
        return PlaceEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .placeType(dto.getPlaceType())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .build();
    }
}
