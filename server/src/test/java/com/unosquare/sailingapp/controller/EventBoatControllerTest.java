package com.unosquare.sailingapp.controller;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateEventBoatDTO;
import com.unosquare.sailingapp.dto.CreateEventDTO;
import com.unosquare.sailingapp.dto.EventBoatDTO;
import com.unosquare.sailingapp.dto.EventDTO;
import com.unosquare.sailingapp.model.*;
import com.unosquare.sailingapp.service.EventBoatService;
import com.unosquare.sailingapp.service.EventService;
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
public class EventBoatControllerTest {
    private static final String CREATE_EVENT_BOAT_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/CreateEventBoat.json");
    private static final String UPDATE_EVENT_BOAT_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/UpdateEventBoat.json");
    @Fixture
    private EventBoatDTO eventBoatDTOFixture;
    @Fixture
    private List<EventBoatDTO> eventBoatDTOListFixture;
    @Fixture
    private List<EventBoatViewModel> eventBoatViewModelListFixture;
    @Fixture
    private EventBoatViewModel eventBoatViewModelFixture;
    @Fixture
    private CreateEventBoatDTO createEventBoatDTOFixture;
    @Mock
    private EventBoatService eventBoatServiceMock;
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
                        new EventBoatController(eventBoatServiceMock, mapperMock))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getEventsBoats_ReturnsOk() throws Exception{
        when(mapperMock.map(eventBoatDTOListFixture, EventBoatViewModel.class)).thenReturn(eventBoatViewModelListFixture);
        when(eventBoatServiceMock.getAllEventBoats()).thenReturn(eventBoatDTOListFixture);


        mockMvc.perform(get("/events-boats/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getEventsBoatsByID_ReturnsOk() throws Exception{
        when(mapperMock.map(eventBoatDTOFixture, EventBoatViewModel.class)).thenReturn(eventBoatViewModelFixture);
        when(eventBoatServiceMock.getEventBoatByID(anyInt())).thenReturn(eventBoatDTOFixture);

        mockMvc.perform(get("/events-boats/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createEventBoat_ReturnsOk() throws Exception{
        when(mapperMock.map(any(CreateEventBoatViewModel.class), any())).thenReturn(createEventBoatDTOFixture);
        when(eventBoatServiceMock.createEventBoat(createEventBoatDTOFixture)).thenReturn(eventBoatDTOFixture);
        when(mapperMock.map(eventBoatDTOFixture, EventBoatViewModel.class)).thenReturn(eventBoatViewModelFixture);

        mockMvc.perform(post("/events-boats/create")
                        .content(CREATE_EVENT_BOAT_VALID_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateEventBoat_ReturnsOk() throws Exception{
        when(eventBoatServiceMock.updateEventBoat(anyInt(), any(UpdateEventBoatViewModel.class))).thenReturn(eventBoatDTOFixture);

        mockMvc.perform(put("/events-boats/1")
                        .content(UPDATE_EVENT_BOAT_VALID_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteEventBoat_ReturnsOk() throws Exception{
        mockMvc.perform(delete("/events-boats/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
