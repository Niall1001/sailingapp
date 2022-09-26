package com.unosquare.sailingapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.BoatDTO;
import com.unosquare.sailingapp.dto.CreateBoatDTO;
import com.unosquare.sailingapp.dto.UpdateBoatDTO;
import com.unosquare.sailingapp.entity.Boat;
import com.unosquare.sailingapp.model.BoatViewModel;
import com.unosquare.sailingapp.model.CreateBoatViewModel;
import com.unosquare.sailingapp.model.UpdateBoatViewModel;
import com.unosquare.sailingapp.repository.BoatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoatServiceTest {
    @Fixture
    private List<BoatViewModel> boatViewModelListFixture;
    @Fixture
    private List<Boat> boatListFixture;
    @Fixture
    private Boat boatFixture;
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
    private BoatRepository boatRepositoryMock;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private Mapper mapperMock;
    @Mock
    private JFixture jFixture;
    @InjectMocks
    private BoatService classUnderTest;
    private int id = 1;
    @BeforeEach
    public void setup() {


        jFixture = new JFixture();
        jFixture.customise()
                .circularDependencyBehaviour().omitSpecimen();



        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void getAllBoats_ReturnsOk(){
        when(boatRepositoryMock.findAll()).thenReturn(boatListFixture);
        when(mapperMock.map(boatListFixture, BoatDTO.class)).thenReturn(boatDTOListFixture);
        classUnderTest.getAllBoats();

        assertThat(classUnderTest.getAllBoats()).isNotNull();
        assertThat(boatDTOListFixture).hasSize(3);
    }

    @Test
    public void getBoatByID_ReturnsOk(){
        when(boatRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(boatFixture));
        boatFixture.setSailNo("1956");
        when(mapperMock.map(boatFixture, BoatDTO.class)).thenReturn(boatDTOFixture);
        classUnderTest.getBoatByID(id);
        classUnderTest.getBoatByID(id).setSailNo("1956");

        assertThat(boatFixture).isNotNull();
        assertThat(classUnderTest.getBoatByID(id)).isNotNull();
        assertThat(classUnderTest.getBoatByID(id).getSailNo()).isEqualTo(boatFixture.getSailNo());
    }

    @Test
    public void createBoat_WhenCalledWithValidData_ReturnsOk(){
        when(mapperMock.map(createBoatDTOFixture, Boat.class)).thenReturn(boatFixture);
        boatFixture.setName("Lizante");
        boatFixture.setAge(2004);
        boatFixture.setSailNo("1967");
        when(boatRepositoryMock.save(boatFixture)).thenReturn(boatFixture);
        createBoatDTOFixture.setName("Lizante");
        createBoatDTOFixture.setAge(2004);
        createBoatDTOFixture.setSailNo("1967");
        classUnderTest.createBoat(createBoatDTOFixture);

        assertThat(createBoatDTOFixture).isNotNull();
        assertThat(createBoatDTOFixture.getName()).isEqualTo(boatFixture.getName());
        assertThat(createBoatDTOFixture.getAge()).isEqualTo(boatFixture.getAge());
        assertThat(createBoatDTOFixture.getSailNo()).isEqualTo(boatFixture.getSailNo());
    }

    @Test
    public void updateBoat_WhenCalledWithValidData_ReturnsOk(){
        when(boatRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(boatFixture));
        boatFixture.setName("Lizante");
        when(boatRepositoryMock.save(boatFixture)).thenReturn(boatFixture);
        updateBoatViewModelFixture.setName("Lizante");
        classUnderTest.updateBoat(id, updateBoatViewModelFixture);

        assertThat(updateBoatViewModelFixture).isNotNull();
        assertThat(boatFixture.getName()).isEqualTo(updateBoatViewModelFixture.getName());
    }

    @Test
    public void deleteBoat_WhenCalledWithValidData_ReturnsOk(){
        classUnderTest.deleteBoat(id);

        assertThat(boatRepositoryMock.findById(id)).isEmpty();
    }
}
