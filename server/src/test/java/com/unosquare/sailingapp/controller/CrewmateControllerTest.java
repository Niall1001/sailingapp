package com.unosquare.sailingapp.controller;


import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateCrewMateDTO;
import com.unosquare.sailingapp.dto.CrewMateDTO;
import com.unosquare.sailingapp.model.CreateCrewMateViewModel;
import com.unosquare.sailingapp.model.CrewmateViewModel;
import com.unosquare.sailingapp.service.CrewMateService;
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
public class CrewmateControllerTest {
    private static final String CREATE_CREW_MATE_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/CreateCrewmate.json");
    @Fixture
    private CrewMateDTO crewMateDTOFixture;
    @Fixture
    private List<CrewMateDTO> crewMateDTOListFixture;
    @Fixture
    private List<CrewmateViewModel> crewMateViewModelListFixture;
    @Fixture
    private CrewmateViewModel crewMateViewModelFixture;
    @Fixture
    private CreateCrewMateDTO createCrewMateDTOFixture;
    @Mock
    private CrewMateService crewMateServiceMock;
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
                        new CrewmateController(crewMateServiceMock, mapperMock))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getCrewMates_ReturnsOk() throws Exception{
        when(mapperMock.map(crewMateDTOListFixture, CrewmateViewModel.class)).thenReturn(crewMateViewModelListFixture);
        when(crewMateServiceMock.getAllCrewMates()).thenReturn(crewMateDTOListFixture);


        mockMvc.perform(get("/crews/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCrewMatesByID_ReturnsOk() throws Exception{
        when(mapperMock.map(crewMateDTOFixture,  CrewmateViewModel.class)).thenReturn(crewMateViewModelFixture);
        when(crewMateServiceMock.getCrewMateByID(anyInt())).thenReturn(crewMateDTOFixture);

        mockMvc.perform(get("/crews/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createCrewMate_ReturnsOk() throws Exception{
        when(mapperMock.map(any(CreateCrewMateViewModel.class), any())).thenReturn(createCrewMateDTOFixture);
        when(crewMateServiceMock.createCrewMate(createCrewMateDTOFixture)).thenReturn(crewMateDTOFixture);
        when(mapperMock.map(crewMateDTOFixture, CrewmateViewModel.class)).thenReturn(crewMateViewModelFixture);

        mockMvc.perform(post("/crews/create")
                        .content(CREATE_CREW_MATE_VALID_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
