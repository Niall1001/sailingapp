package com.unosquare.sailingapp.service;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.BoatOwnerDTO;
import com.unosquare.sailingapp.dto.CreateBoatOwnerDTO;
import com.unosquare.sailingapp.entity.BoatOwner;
import com.unosquare.sailingapp.model.BoatOwnerViewModel;
import com.unosquare.sailingapp.repository.BoatOwnerRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoatOwnerServiceTest {
    @Fixture
    private BoatOwnerDTO boatOwnerDTOFixture;
    @Fixture
    private BoatOwner boatOwnerFixture;
    @Fixture
    private List<BoatOwnerDTO> boatOwnerDTOListFixture;
    @Fixture
    private List<BoatOwner> boatOwnerListFixture;
    @Fixture
    private List<BoatOwnerViewModel> boatOwnerViewModelListFixture;
    @Fixture
    private BoatOwnerViewModel boatOwnerViewModelFixture;
    @Fixture
    private CreateBoatOwnerDTO createBoatOwnerDTOFixture;
    @Mock
    private BoatOwnerRepository boatOwnerRepositoryMock;
    @Mock
    private Mapper mapperMock;
    @Mock
    private JFixture jFixture;
    @InjectMocks
    private BoatOwnerService classUnderTest;

    private int id = 1;

    @BeforeEach
    public void setup() {


        jFixture = new JFixture();
        jFixture.customise()
                .circularDependencyBehaviour().omitSpecimen();



        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void getAllBoatOwners_WhenCalledWithValidData_ReturnsOk(){
       when(boatOwnerRepositoryMock.findAll()).thenReturn(boatOwnerListFixture);
       when(mapperMock.map(boatOwnerListFixture, BoatOwnerDTO.class)).thenReturn(boatOwnerDTOListFixture);
       classUnderTest.getAllBoatOwners();

        assertThat(classUnderTest.getAllBoatOwners()).isNotNull();
        assertThat(boatOwnerListFixture).hasSize(3);
        verify(boatOwnerRepositoryMock, times(2)).findAll();
    }

    @Test
    public void getBoatOwnerByID_WhenCalledWithValidData_ReturnsOk(){
        when(boatOwnerRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(boatOwnerFixture));
        when(mapperMock.map(boatOwnerFixture, BoatOwnerDTO.class)).thenReturn(boatOwnerDTOFixture);


        assertThat(classUnderTest.getBoatOwnerByID(id)).isNotNull();
        verify(boatOwnerRepositoryMock, times(1)).findById(id);
        verify(mapperMock, times(1)).map(boatOwnerListFixture, BoatOwnerDTO.class);
    }

    @Test
    public void createBoatOwner_WhenCalledWithValidData_ReturnsOk(){
        when(mapperMock.map(createBoatOwnerDTOFixture, BoatOwner.class)).thenReturn(boatOwnerFixture);
        boatOwnerFixture.setBoatId(3);
        when(boatOwnerRepositoryMock.save(boatOwnerFixture)).thenReturn(boatOwnerFixture);
        classUnderTest.createBoatOwner(createBoatOwnerDTOFixture);
        createBoatOwnerDTOFixture.setBoatId(3);

        assertThat(createBoatOwnerDTOFixture).isNotNull();
        assertThat(boatOwnerFixture.getBoatId()).isEqualTo(createBoatOwnerDTOFixture.getBoatId());
        verify(mapperMock, times(1)).map(createBoatOwnerDTOFixture, BoatOwner.class);

    }

}
