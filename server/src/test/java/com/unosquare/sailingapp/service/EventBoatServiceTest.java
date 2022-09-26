package com.unosquare.sailingapp.service;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateEventBoatDTO;
import com.unosquare.sailingapp.dto.EventBoatDTO;
import com.unosquare.sailingapp.entity.EventBoat;
import com.unosquare.sailingapp.model.EventBoatViewModel;
import com.unosquare.sailingapp.model.UpdateEventBoatViewModel;
import com.unosquare.sailingapp.repository.EventBoatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventBoatServiceTest {
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
    @Fixture
    private UpdateEventBoatViewModel updateEventBoatViewModelFixture;
    @Fixture
    private List<EventBoat> eventBoatListFixture;
    @Fixture
    private EventBoat eventBoatFixture;
    @InjectMocks
    private EventBoatService classUnderTest;
    @Mock
    private EventBoatRepository eventBoatRepositoryMock;
    @Mock
    private Mapper mapperMock;
    @Mock
    private MockMvc mockMvc;
    @Mock
    private JFixture jFixture;
    private Integer id = 1;

    @BeforeEach
    public void setup() {


        jFixture = new JFixture();
        jFixture.customise()
                .circularDependencyBehaviour().omitSpecimen();



        FixtureAnnotations.initFixtures(this, jFixture);

    }

    @Test
    public void getAllEventBoat_WhenCalledWithValidData_ReturnsOk(){
        when(eventBoatRepositoryMock.findAll()).thenReturn(eventBoatListFixture);
        when(mapperMock.map(eventBoatListFixture, EventBoatDTO.class)).thenReturn(eventBoatDTOListFixture);
        classUnderTest.getAllEventBoats();

        assertThat(classUnderTest.getAllEventBoats()).isNotNull();
        assertThat(eventBoatDTOListFixture).hasSize(3);
    }

    @Test
    public void getEventBoatByID_WhenCalledWithValidData_ReturnsOk(){
        when(eventBoatRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(eventBoatFixture));
        when(mapperMock.map(eventBoatFixture, EventBoatDTO.class)).thenReturn(eventBoatDTOFixture);

        assertThat(eventBoatDTOFixture).isNotNull();
        assertThat(classUnderTest.getEventBoatByID(id)).isNotNull();
    }

    @Test
    public void createEventBoat_WhenCalledWithValidData_ReturnsOk(){
        when(mapperMock.map(createEventBoatDTOFixture, EventBoat.class)).thenReturn(eventBoatFixture);
        eventBoatFixture.setPosition(3);
        eventBoatFixture.setEventId(2);
        when(eventBoatRepositoryMock.save(eventBoatFixture)).thenReturn(eventBoatFixture);
        createEventBoatDTOFixture.setPosition(3);
        createEventBoatDTOFixture.setEventId(2);
        classUnderTest.createEventBoat(createEventBoatDTOFixture);

        assertThat(createEventBoatDTOFixture).isNotNull();
        assertThat(eventBoatFixture.getPosition()).isEqualTo(createEventBoatDTOFixture.getPosition());
    }

    @Test
    public void updateEventBoat_WhenCalledWithValidData_ReturnsOk(){
        when(eventBoatRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(eventBoatFixture));
        eventBoatFixture.setPosition(3);
        when(eventBoatRepositoryMock.save(eventBoatFixture)).thenReturn(eventBoatFixture);
        updateEventBoatViewModelFixture.setPosition(3);
        classUnderTest.updateEventBoat(id, updateEventBoatViewModelFixture);

        assertThat(updateEventBoatViewModelFixture).isNotNull();
        assertThat(eventBoatFixture.getPosition()).isEqualTo(updateEventBoatViewModelFixture.getPosition());
    }

    @Test
    public void deleteEventBoat_WhenCalledWithValidData_ReturnsOk(){
        classUnderTest.deleteEventBoat(id);

        assertThat(eventBoatRepositoryMock.findById(id).isEmpty());
    }



}
