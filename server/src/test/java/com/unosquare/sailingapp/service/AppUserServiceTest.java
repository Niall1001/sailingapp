package com.unosquare.sailingapp.service;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.AppUserDTO;
import com.unosquare.sailingapp.dto.CreateAppUserDTO;
import com.unosquare.sailingapp.entity.AppUser;
import com.unosquare.sailingapp.model.AppUserViewModel;
import com.unosquare.sailingapp.model.UpdateAppUserViewModel;
import com.unosquare.sailingapp.repository.AppUserRepository;
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
public class AppUserServiceTest {
    @Fixture
    private List<AppUserViewModel> appUserViewModelListFixture;
    @Fixture
    private List<AppUserDTO> appUserDTOListFixture;
    @Fixture
    private AppUserDTO appUserDTOFixture;
    @Fixture
    private AppUser appUserFixture;
    @Fixture
    private AppUserViewModel appUserViewModelFixture;
    @Fixture
    private CreateAppUserDTO createAppUserDTOFixture;
    @Fixture
    private UpdateAppUserViewModel updateAppUserViewModelFixture;

    @Fixture
    private List<AppUser> appUserListFixture;
    @Mock
    private JFixture jFixture;
    @Mock
    private AppUserRepository appUserRepositoryMock;
    @Mock
    private Mapper mapperMock;
    @InjectMocks
    private AppUserService classUnderTest;
    private int id = 1;

    @BeforeEach
    public void setup() {


        jFixture = new JFixture();
        jFixture.customise()
                .circularDependencyBehaviour().omitSpecimen();



        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void getAllAppUsers_WhenCalledWithValidData_ReturnsOk(){
        when(appUserRepositoryMock.findAll()).thenReturn(appUserListFixture);
        when(mapperMock.map(appUserListFixture, AppUserDTO.class)).thenReturn(appUserDTOListFixture);
        classUnderTest.getAllAppUsers();

        assertThat(classUnderTest.getAllAppUsers()).isNotNull();
        assertThat(appUserListFixture).hasSize(3);
        verify(mapperMock, times(1)).map(appUserListFixture, AppUserDTO.class);

    }

    @Test
    public void getAppUsersByID_WhenCalledWithValidData_ReturnsOk(){
        when(appUserRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(appUserFixture));
        when(mapperMock.map(appUserFixture, AppUserDTO.class)).thenReturn(appUserDTOFixture);

        assertThat(appUserDTOFixture).isNotNull();
        assertThat(classUnderTest.getAppUserByID(id)).isNotNull();
        verify(appUserRepositoryMock, times(1)).findById(id);
    }

    @Test
    public void createAppUser_WhenCalledWithValidData_ReturnsOk(){
        when(mapperMock.map(createAppUserDTOFixture, AppUser.class)).thenReturn(appUserFixture);
        appUserFixture.setName("Niall Walters");
        appUserFixture.setEmailAddress("niallwalters135@gmail.com");
        when(appUserRepositoryMock.save(appUserFixture)).thenReturn(appUserFixture);
        createAppUserDTOFixture.setName("Niall Walters");
        createAppUserDTOFixture.setEmailAddress("niallwalters135@gmail.com");
        classUnderTest.createAppUser(createAppUserDTOFixture);

        assertThat(createAppUserDTOFixture).isNotNull();
        assertThat(createAppUserDTOFixture.getName()).isEqualTo(appUserFixture.getName());
        assertThat(createAppUserDTOFixture.getEmailAddress()).isEqualTo(appUserFixture.getEmailAddress());
        verify(appUserRepositoryMock, times(1)).save(appUserFixture);
    }

    @Test
    public void updateAppUser_WhenCalledWithValidData_ReturnsOk(){
        when(appUserRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(appUserFixture));
        appUserFixture.setEmailAddress("niallwalters135@gmail.com");
        when(appUserRepositoryMock.save(appUserFixture)).thenReturn(appUserFixture);
        updateAppUserViewModelFixture.setEmailAddress("niallwalters135@gmail.com");
        classUnderTest.updateAppUser(id, updateAppUserViewModelFixture);

        assertThat(updateAppUserViewModelFixture).isNotNull();
        assertThat(appUserFixture.getEmailAddress()).isEqualTo(updateAppUserViewModelFixture.getEmailAddress());
        verify(appUserRepositoryMock, times(1)).findById(id);
        verify(appUserRepositoryMock, times(1)).save(appUserFixture);
    }

    @Test
    public void deleteAppUser_WhenCalledWithValidData_ReturnsOk(){
        classUnderTest.deleteAppUser(id);

        assertThat(appUserRepositoryMock.findById(id)).isEmpty();
        verify(appUserRepositoryMock, times(1)).deleteById(id);
    }
}
