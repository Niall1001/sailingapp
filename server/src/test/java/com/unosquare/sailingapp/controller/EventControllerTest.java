package com.unosquare.sailingapp.controller;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateEventDTO;
import com.unosquare.sailingapp.dto.EventDTO;
import com.unosquare.sailingapp.model.CreateEventViewModel;
import com.unosquare.sailingapp.model.EventViewModel;
import com.unosquare.sailingapp.model.UpdateEventViewModel;
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
public class EventControllerTest {
    private static final String CREATE_EVENT_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/CreateEvent.json");
    private static final String UPDATE_EVENT_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/UpdateEvent.json");
    @Fixture
    private EventDTO eventDTOFixture;
    @Fixture
    private List<EventDTO> eventDTOListFixture;
    @Fixture
    private List<EventViewModel> eventViewModelListFixture;
    @Fixture
    private EventViewModel eventViewModelFixture;
    @Fixture
    private CreateEventDTO createEventDTOFixture;
    @Mock
    private EventService eventServiceMock;
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
                        new EventController(eventServiceMock, mapperMock))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getEvents_ReturnsOk() throws Exception{
        when(mapperMock.map(eventDTOListFixture, EventViewModel.class)).thenReturn(eventViewModelListFixture);
        when(eventServiceMock.getAllEvents()).thenReturn(eventDTOListFixture);


        mockMvc.perform(get("/events/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getEventsByID_ReturnsOk() throws Exception{
        when(mapperMock.map(eventDTOFixture, EventViewModel.class)).thenReturn(eventViewModelFixture);
        when(eventServiceMock.getEventByID(anyInt())).thenReturn(eventDTOFixture);

        mockMvc.perform(get("/events/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createEvent_ReturnsOk() throws Exception{
        when(mapperMock.map(any(CreateEventViewModel.class), any())).thenReturn(createEventDTOFixture);
        when(eventServiceMock.createEvent(createEventDTOFixture)).thenReturn(eventDTOFixture);
        when(mapperMock.map(eventDTOFixture, EventViewModel.class)).thenReturn(eventViewModelFixture);

        mockMvc.perform(post("/events/create")
                        .content(CREATE_EVENT_VALID_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateEvent_ReturnsOk() throws Exception{
        when(eventServiceMock.updateEvent(anyInt(), any(UpdateEventViewModel.class))).thenReturn(eventDTOFixture);

        mockMvc.perform(put("/events/1")
                .content(UPDATE_EVENT_VALID_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
