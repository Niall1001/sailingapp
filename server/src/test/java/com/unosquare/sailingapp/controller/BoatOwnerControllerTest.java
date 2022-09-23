package com.unosquare.sailingapp.controller;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.BoatOwnerDTO;
import com.unosquare.sailingapp.dto.CreateBoatOwnerDTO;
import com.unosquare.sailingapp.model.BoatOwnerViewModel;
import com.unosquare.sailingapp.model.CreateBoatOwnerViewModel;
import com.unosquare.sailingapp.service.BoatOwnerService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BoatOwnerControllerTest {
    private static final String CREATE_BOAT_OWNER_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/CreateBoatOwner.json");
    @Fixture
    private BoatOwnerDTO boatOwnerDTOFixture;
    @Fixture
    private List<BoatOwnerDTO> boatOwnerDTOListFixture;
    @Fixture
    private List<BoatOwnerViewModel> boatOwnerViewModelListFixture;
    @Fixture
    private BoatOwnerViewModel boatOwnerViewModelFixture;
    @Fixture
    private CreateBoatOwnerDTO createBoatOwnerDTOFixture;
    @Mock
    private BoatOwnerService boatOwnerServiceMock;
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
                        new BoatOwnerController(boatOwnerServiceMock, mapperMock))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getBoatOwner_ReturnsOk() throws Exception{
        when(mapperMock.map(boatOwnerDTOListFixture, BoatOwnerViewModel.class)).thenReturn(boatOwnerViewModelListFixture);
        when(boatOwnerServiceMock.getAllBoatOwners()).thenReturn(boatOwnerDTOListFixture);


        mockMvc.perform(get("/boat-owners/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getBoatOwnerByID_ReturnsOk() throws Exception{
        when(mapperMock.map(boatOwnerDTOFixture, BoatOwnerViewModel.class)).thenReturn(boatOwnerViewModelFixture);
        when(boatOwnerServiceMock.getBoatOwnerByID(anyInt())).thenReturn(boatOwnerDTOFixture);

        mockMvc.perform(get("/boat-owners/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createBoatOwner_ReturnsOk() throws Exception{
        when(mapperMock.map(any(CreateBoatOwnerViewModel.class), any())).thenReturn(createBoatOwnerDTOFixture);
        when(boatOwnerServiceMock.createBoatOwner(createBoatOwnerDTOFixture)).thenReturn(boatOwnerDTOFixture);
        when(mapperMock.map(boatOwnerDTOFixture, BoatOwnerViewModel.class)).thenReturn(boatOwnerViewModelFixture);

        mockMvc.perform(post("/boat-owners/create")
                        .content(CREATE_BOAT_OWNER_VALID_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
