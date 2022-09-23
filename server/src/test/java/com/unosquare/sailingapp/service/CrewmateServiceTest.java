package com.unosquare.sailingapp.service;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateCrewMateDTO;
import com.unosquare.sailingapp.dto.CrewMateDTO;
import com.unosquare.sailingapp.entity.CrewMate;
import com.unosquare.sailingapp.model.CrewmateViewModel;
import com.unosquare.sailingapp.repository.CrewMateRepository;
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
public class CrewmateServiceTest {
    @Fixture
    private CrewMate crewMateFixture;
    @Fixture
    private List<CrewMate> crewMateListFixture;
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
    private CrewMateRepository crewMateRepositoryMock;
    @Mock
    private Mapper mapperMock;
    @Mock
    private JFixture jFixture;
    @InjectMocks
    private CrewMateService classUnderTest;
    private Integer id = 1;
    @BeforeEach
    public void setup() {


        jFixture = new JFixture();
        jFixture.customise()
                .circularDependencyBehaviour().omitSpecimen();



        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void getAllCrewMates_ReturnsOk(){
        when(crewMateRepositoryMock.findAll()).thenReturn(crewMateListFixture);
        when(mapperMock.map(crewMateListFixture, CrewMateDTO.class)).thenReturn(crewMateDTOListFixture);
        classUnderTest.getAllCrewMates();

        assertThat(classUnderTest.getAllCrewMates()).isNotNull();
        assertThat(crewMateListFixture).hasSize(3);
    }

    @Test
    public void getCrewMateByID_ReturnsOk(){
        when(crewMateRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(crewMateFixture));
        when(mapperMock.map(crewMateFixture, CrewMateDTO.class)).thenReturn(crewMateDTOFixture);

        assertThat(crewMateDTOFixture).isNotNull();
        assertThat(classUnderTest.getCrewMateByID(id)).isNotNull();
    }

    @Test
    public void createCrewMate_ReturnsOk(){
        when(mapperMock.map(createCrewMateDTOFixture, CrewMate.class)).thenReturn(crewMateFixture);
        crewMateFixture.setUserId(3);
        when(crewMateRepositoryMock.save(crewMateFixture)).thenReturn(crewMateFixture);
        createCrewMateDTOFixture.setUserId(3);
        classUnderTest.createCrewMate(createCrewMateDTOFixture);

        assertThat(createCrewMateDTOFixture).isNotNull();
        assertThat(createCrewMateDTOFixture.getUserId()).isEqualTo(crewMateFixture.getUserId());
    }
}
