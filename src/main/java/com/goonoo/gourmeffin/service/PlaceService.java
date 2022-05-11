package com.goonoo.gourmeffin.service;

import com.goonoo.gourmeffin.model.PlaceEntity;
import com.goonoo.gourmeffin.persistence.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlaceService {

    @Autowired
    private PlaceRepository repository;

    public String testService() {
        // PlaceEntity 생성
        PlaceEntity entity = PlaceEntity.builder()
                .name("테스트맛집")
                .address("테스트시 테스트구 테스트동")
                .placeType("테스트음식")
                .description("테스트를 위한 맛집입니다")
                .rating(3.0)
                .build();
        // PlaceEntity 저장
        repository.save(entity);
        // PlaceEntity 검색
        PlaceEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getName();
    }

    public List<PlaceEntity> create(final PlaceEntity entity) {
        validate(entity);

        repository.save(entity);

        log.info("Entity Id : {} is saved.", entity.getId());

        return repository.findByName(entity.getName());
    }

    // 지금은 이름에 해당하는 place entity만 return하는데, 추후에 pagination 등등을 추가해야 할 것
    public List<PlaceEntity> retrieve(final String name) {
        return repository.findByName(name);
    }

    public List<PlaceEntity> retrieveAll() {
        return repository.findAll();
    }

    public List<PlaceEntity> update(final PlaceEntity entity) {
        // (1) 저장할 엔티티가 유효한지 확인한다.
        validate(entity);

        // (2) 넘겨받은 엔티티 id를 이용해 Place Entity를 가져온다. 존재하지 않는 엔티티는 업데이트할 수 없기 때문이다.
        final Optional<PlaceEntity> original = repository.findById(entity.getId());

        original.ifPresent(place -> {
            // (3) 반환된 PlaceEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
            place.setName(entity.getName());
            place.setAddress(entity.getAddress());
            place.setPlaceType(entity.getPlaceType());
            place.setDescription(entity.getDescription());

            // (4) 데이터베이스에 새 값을 저장한다.
            repository.save(place);
        });

        // 2.3.2 update된 place를 return한다.
        return retrieve(entity.getName());
    }

    public List<PlaceEntity> delete(final PlaceEntity entity) {
        // (1) 저장할 엔티티가 유효한지 확인한다.
        validate(entity);

        try {
            // (2) 엔티티를 삭제한다.
            repository.delete(entity);
        } catch (Exception e) {
            // (3) exception 발생 시 id와 exception을 로깅한다.
            log.error("error deleting entity ", entity.getId(), e);

            // (4) 컨트롤러로 exception을 보낸다. 데이터베이스 내부 로직을 캡슐화하려면 e를 리턴하지 않고 새 exception 오브젝트를 리턴한다.
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        // (5) 모든 place entity list를 return한다.
        return retrieveAll();
    }

    private void validate(final PlaceEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getName() == null) {
            log.warn("Unknown place.");
            throw new RuntimeException("Unknown place.");
        }
    }
}
