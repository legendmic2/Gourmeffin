package com.goonoo.gourmeffin.controller;

import com.goonoo.gourmeffin.dto.PlaceDTO;
import com.goonoo.gourmeffin.dto.ResponseDTO;
import com.goonoo.gourmeffin.model.PlaceEntity;
import com.goonoo.gourmeffin.service.PlaceService;
import com.goonoo.gourmeffin.service.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("place")
public class PlaceController {

    private final PlaceService service;

    public PlaceController(PlaceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createPlace(@RequestBody PlaceDTO dto) {
        // @AuthenticationPrincipal String userId
        try {
            // (1) PlaceEntity로 변환한다.
            PlaceEntity entity = PlaceDTO.toEntity(dto);

            // (2) id를 null로 초기화한다. 생성 당시에는 id가 없어야 하기 때문이다.
            entity.setId(null);

            // (3) 임시 사용자 아이디를 설정해준다.
            // 기존 temporary-user 대신 @AuthenticationPrincipal에서 넘어온 userId로 설정해준다.
            // entity.setUserId(userId);

            // (4) 서비스를 이용해 Place Entity를 생성한다.
            List<PlaceEntity> entities = service.create(entity);

            // (5) Java Stream을 이용해 return된 Entity List를 PlaceDTO 리스트로 변환한다.
            List<PlaceDTO> dtos = entities.stream().map(PlaceDTO::new).collect(Collectors.toList());

            // (6) 변환된 PlaceDTO List를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<PlaceDTO> response = ResponseDTO.<PlaceDTO>builder().data(dtos).build();

            // (7) ResponseDTO를 return한다
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            // (8) 혹시 예외가 있는 경우 dto 대신 error에 메세지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDTO<PlaceDTO> response = ResponseDTO.<PlaceDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 하나만 가져오는건 일단 보류, 전체 반환만 우선 구현
    @GetMapping
    public ResponseEntity<?> retrievePlaceList() {
        // @AuthenticationPrincipal String userId
        // (1) 서비스 메서드의 retrieve() 메소드를 사용해 Place 리스트를 가져온다.
        List<PlaceEntity> entities = service.retrieveAll();

        // (2) Java Stream을 이용해 리턴된 엔티티 리스트를 PlaceDTO List로 변환한다.
        List<PlaceDTO> dtos = entities.stream().map(PlaceDTO::new).collect(Collectors.toList());

        // (3) 변환된 PlaceDTO 리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<PlaceDTO> response = ResponseDTO.<PlaceDTO>builder().data(dtos).build();

        // (4) ResponseDTO를 리턴한다.
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updatePlace(@RequestBody PlaceDTO dto) {
        // String temporaryUserId = "temporary-user"; // temporary user id.

        // (1) dto를 entity로 변환한다.
        PlaceEntity entity = PlaceDTO.toEntity(dto);

        // (2) id를 userId로 초기화한다.
        // entity.setUserId(temporaryUserId);

        // (3) 서비스를 이용해 entity를 업데이트한다.
        List<PlaceEntity> entities = service.update(entity);

        // (4) Java Stream을 이용해 리턴된 엔티티 리스트를 PlaceDTO List로 변환한다.
        List<PlaceDTO> dtos = entities.stream().map(PlaceDTO::new).collect(Collectors.toList());

        // (5) 변환된 PlaceDTO 리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<PlaceDTO> response = ResponseDTO.<PlaceDTO>builder().data(dtos).build();

        // (6) ResponseDTO를 리턴한다.
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePlace(@RequestBody PlaceDTO dto) {
        // @AuthenticationPrincipal String userId,
        try {
            // (1) PlaceEntity로 변환한다.
            PlaceEntity entity = PlaceDTO.toEntity(dto);

            // (2) 임시 사용자 아이디를 설정해준다.
//            entity.setUserId(userId);

            // (3) 서비스를 이용해 entity를 삭제한다.
            List<PlaceEntity> entities = service.delete(entity);

            // (4) Java Stream을 이용해 리턴된 엔티티 리스트를 PlaceDTO List로 변환한다.
            List<PlaceDTO> dtos = entities.stream().map(PlaceDTO::new).collect(Collectors.toList());

            // (5) 변환된 PlaceDTO 리스트를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<PlaceDTO> response = ResponseDTO.<PlaceDTO>builder().data(dtos).build();

            // (6) ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // (7) 혹시 예외가 있는 경우 dto 대신 error에 메세지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDTO<PlaceDTO> response = ResponseDTO.<PlaceDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
