package com.goonoo.gourmeffin.controller;

import com.google.gson.Gson;
import com.goonoo.gourmeffin.dto.PlaceDTO;
import com.goonoo.gourmeffin.model.PlaceEntity;
import com.goonoo.gourmeffin.service.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PlaceControllerTest {

    @InjectMocks
    private PlaceController placeController;

    @Mock
    private PlaceService placeService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(placeController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // UTF-8 필터 추가
                .build();
    }

    @Test
    @DisplayName("Place Create Test")
    void createPlace() throws Exception {
        PlaceDTO requestBody = createPlaceDTOForTest();
        List<PlaceEntity> responseBody = new ArrayList<>();
        responseBody.add(PlaceDTO.toEntity(requestBody));
        // PlaceController.create(PlaceDTO) => return List<PlaceEntity>
        // ???? ResponseEntity?? (PlaceController - line59)

        // given
        doReturn(responseBody).when(placeService)
                .create(any(PlaceEntity.class)); // placeService.create(PlaceEntity) => return List<PlaceEntity>

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(requestBody)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data[0].name").exists());

        // then
//        MvcResult mvcResult = (MvcResult) resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("id", responseBody.get(0).getId()).exists())
                // id는 return되지 않음
//                .andDo(print())
//                .andExpect(jsonPath("$.data[0].name").exists());
//                .andExpect(jsonPath("$.data"))

//                .andExpect(jsonPath("name", responseBody.get(0).getName()).exists())
//                .andExpect(jsonPath("address", responseBody.get(0).getAddress()).exists())
//                .andExpect(jsonPath("placeType", responseBody.get(0).getPlaceType()).exists())
//                .andExpect(jsonPath("description", responseBody.get(0).getDescription()).exists())
//                .andExpect(jsonPath("rating", responseBody.get(0).getRating()).exists());

    }

    private PlaceDTO createPlaceDTOForTest() {
        return PlaceDTO.builder()
                .name("테스트맛집")
                .address("테스트구 테스트동")
                .placeType("식당")
                .description("테스트를 위한 맛집입니다")
                .rating(5.0)
                .build();
    }
}
