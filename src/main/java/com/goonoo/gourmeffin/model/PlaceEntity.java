package com.goonoo.gourmeffin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "place")
public class PlaceEntity {
    @Id
    @GeneratedValue(generator = "system-uuid") // object를 DB에 저장할 때 id를 자동으로 생성하겠다
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String address;
    private String placeType;
    private String description;
    private double rating;
}
