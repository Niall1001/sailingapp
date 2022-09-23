package com.unosquare.sailingapp.controller;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.BoatDTO;
import com.unosquare.sailingapp.dto.CreateBoatDTO;
import com.unosquare.sailingapp.dto.UpdateBoatDTO;
import com.unosquare.sailingapp.model.BoatViewModel;
import com.unosquare.sailingapp.model.CreateBoatViewModel;
import com.unosquare.sailingapp.model.UpdateBoatViewModel;
import com.unosquare.sailingapp.service.BoatService;
import com.unosquare.sailingapp.util.ResourceUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BoatControllerTest{
    private static final String CREATE_BOAT_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/CreateBoat.json");
    @Fixture
    private List<BoatViewModel> boatViewModelListFixture;
    @Fixture
    private List<BoatDTO> boatDTOListFixture;
    @Fixture
    private BoatDTO boatDTOFixture;
    @Fixture
    private CreateBoatDTO createBoatDTOFixture;
    @Fixture
    private UpdateBoatDTO updateBoatDTOFixture;
    @Fixture
    private BoatViewModel boatViewModelFixture;
    @Fixture
    private CreateBoatViewModel createBoatViewModelFixture;
    @Fixture
    private UpdateBoatViewModel updateBoatViewModelFixture;
    @Mock
    private BoatService boatServiceMock;
    @Mock
    private Mapper mapperMock;
    @Mock
    private MockMvc mockMvc;
    @Mock
    private JFixture jFixture;


    @BeforeEach
    public void setup() {


        jFixture = new JFixture();
        jFixture.customise()
                .circularDependencyBehaviour().omitSpecimen();



        FixtureAnnotations.initFixtures(this, jFixture);



        mockMvc = MockMvcBuilders.standaloneSetup(
                        new BoatController(boatServiceMock, mapperMock))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getBoat_ReturnsOk() throws Exception{
        when(mapperMock.map(boatDTOListFixture, BoatViewModel.class)).thenReturn(boatViewModelListFixture);
        when(boatServiceMock.getAllBoats()).thenReturn(boatDTOListFixture);


        mockMvc.perform(get("/boats/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getBoatByID_ReturnsOk() throws Exception{
        when(mapperMock.map(boatDTOFixture, BoatViewModel.class)).thenReturn(boatViewModelFixture);
        when(boatServiceMock.getBoatByID(anyInt())).thenReturn(boatDTOFixture);


        mockMvc.perform(get("/boats/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createBoat_ReturnsOk() throws Exception{
        when(mapperMock.map(any(CreateBoatViewModel.class), any())).thenReturn(createBoatDTOFixture);
        when(boatServiceMock.createBoat(createBoatDTOFixture)).thenReturn(boatDTOFixture);
        when(mapperMock.map(boatDTOFixture, BoatViewModel.class)).thenReturn(boatViewModelFixture);

        mockMvc.perform(post("/boats/create")
                        .content(CREATE_BOAT_VALID_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());
    }

    @Test
    public void updateBoat_ReturnsOk() throws Exception{
        when(boatServiceMock.updateBoat(anyInt(), any(UpdateBoatViewModel.class))).thenReturn(boatDTOFixture);

        mockMvc.perform(put("/boats/1")
                        .content(CREATE_BOAT_VALID_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
        public void deleteBoat_ReturnsNoContent() throws Exception{

        mockMvc.perform(delete("/boats/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}