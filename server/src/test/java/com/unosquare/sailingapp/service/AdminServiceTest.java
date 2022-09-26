package com.unosquare.sailingapp.service;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.AdminDTO;
import com.unosquare.sailingapp.dto.CreateAdminDTO;
import com.unosquare.sailingapp.entity.Admin;
import com.unosquare.sailingapp.model.AdminViewModel;
import com.unosquare.sailingapp.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Fixture
    private AdminDTO adminDTOFixture;
    @Fixture
    private Admin adminFixture;
    @Fixture
    private List<AdminDTO> adminDTOListFixture;
    @Fixture
    private List<Admin> adminListFixture;
    @Fixture
    private List<AdminViewModel> adminViewModelListFixture;
    @Fixture
    private CreateAdminDTO createAdminDTOFixture;
    @Mock
    private AdminRepository adminRepositoryMock;
    @Mock
    private Mapper mapperMock;
    @Mock
    private JFixture jFixture;
    @InjectMocks
    private AdminService classUnderTest;
    private int id = 1;
    @BeforeEach
    public void setup() {


        jFixture = new JFixture();
        jFixture.customise()
                .circularDependencyBehaviour().omitSpecimen();



        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void getAllAdmins_WhenCalledWithValidData_ReturnsOk(){
        when(adminRepositoryMock.findAll()).thenReturn(adminListFixture);
        when(mapperMock.map(adminListFixture, AdminDTO.class)).thenReturn(adminDTOListFixture);
        classUnderTest.getAllAdmins();

        assertThat(classUnderTest.getAllAdmins()).isNotNull();
        assertThat(adminListFixture).hasSize(3);
        verify(mapperMock, times(2)).map(adminListFixture, AdminDTO.class);
    }

    @Test
    public void getAdminByID_WhenCalledWithValidData_ReturnsOk(){
        when(adminRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(adminFixture));
        adminFixture.setUserId(3);
        when(mapperMock.map(adminFixture, AdminDTO.class)).thenReturn(adminDTOFixture);
        classUnderTest.getAdminByID(id).setUserId(3);

        assertThat(adminDTOFixture).isNotNull();
        assertThat(classUnderTest.getAdminByID(id).getUserId()).isEqualTo(adminFixture.getUserId());
        verify(mapperMock, times(2)).map(adminFixture, AdminDTO.class);
    }

    @Test
    public void createAdmin_WhenCalledWithValidDetails_ReturnsOk(){
        when(mapperMock.map(createAdminDTOFixture, Admin.class)).thenReturn(adminFixture);
        adminFixture.setUserId(3);
        when(adminRepositoryMock.save(adminFixture)).thenReturn(adminFixture);
        createAdminDTOFixture.setUserId(3);
        classUnderTest.createAdmin(createAdminDTOFixture);

        assertThat(createAdminDTOFixture).isNotNull();
        assertThat(adminFixture).isNotNull();
        assertThat(createAdminDTOFixture.getUserId()).isEqualTo(adminFixture.getUserId());
        verify(mapperMock, times(1)).map(createAdminDTOFixture, Admin.class);
        verify(adminRepositoryMock, times(1)).save(adminFixture);
    }


}
