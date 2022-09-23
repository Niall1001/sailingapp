package com.unosquare.sailingapp.controller;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.AppUserDTO;
import com.unosquare.sailingapp.dto.CreateAppUserDTO;
import com.unosquare.sailingapp.dto.CreateEventDTO;
import com.unosquare.sailingapp.dto.EventDTO;
import com.unosquare.sailingapp.model.AppUserViewModel;
import com.unosquare.sailingapp.model.CreateAppUserViewModel;
import com.unosquare.sailingapp.model.CreateEventViewModel;
import com.unosquare.sailingapp.model.UpdateAppUserViewModel;
import com.unosquare.sailingapp.service.AppUserService;
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
public class AppUserControllerTest {
    private static final String CREATE_APP_USER_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/CreateAppUser.json");
    private static final String UPDATE_APP_USER_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/UpdateAppUser.json");

    @Fixture
    private List<AppUserViewModel> appUserViewModelListFixture;
    @Fixture
    private List<AppUserDTO> appUserDTOListFixture;
    @Fixture
    private AppUserDTO appUserDTOFixture;
    @Fixture
    private AppUserViewModel appUserViewModelFixture;
    @Fixture
    private CreateAppUserDTO createAppUserDTOFixture;
    @Mock
    private JFixture jFixture;
    @Mock
    private MockMvc mockMvc;
    @Mock
    private AppUserService appUserServiceMock;
    @Mock
    private Mapper mapperMock;
    @BeforeEach
    public void setup() {


        jFixture = new JFixture();
        jFixture.customise()
                .circularDependencyBehaviour().omitSpecimen();



        FixtureAnnotations.initFixtures(this, jFixture);



        mockMvc = MockMvcBuilders.standaloneSetup(
                        new AppUserController(appUserServiceMock, mapperMock))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getAppUsers_ReturnsOk() throws Exception{
        when(mapperMock.map(appUserDTOListFixture, AppUserViewModel.class)).thenReturn(appUserViewModelListFixture);
        when(appUserServiceMock.getAllAppUsers()).thenReturn(appUserDTOListFixture);


        mockMvc.perform(get("/app-users/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAppUserByID_ReturnsOk() throws Exception{
        when(mapperMock.map(appUserDTOFixture, AppUserViewModel.class)).thenReturn(appUserViewModelFixture);
        when(appUserServiceMock.getAppUserByID(anyInt())).thenReturn(appUserDTOFixture);

        mockMvc.perform(get("/app-users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createAppUser_ReturnsOk() throws Exception {
        when(mapperMock.map(any(CreateAppUserViewModel.class), any())).thenReturn(createAppUserDTOFixture);
        when(appUserServiceMock.createAppUser(createAppUserDTOFixture)).thenReturn(appUserDTOFixture);
        when(mapperMock.map(appUserDTOFixture, AppUserViewModel.class)).thenReturn(appUserViewModelFixture);

        mockMvc.perform(post("/app-users/create")
                .content(CREATE_APP_USER_VALID_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateAppUser_ReturnsOk() throws Exception{
        when(appUserServiceMock.updateAppUser(anyInt(), any(UpdateAppUserViewModel.class))).thenReturn(appUserDTOFixture);

        mockMvc.perform(put("/app-users/1")
                .content(UPDATE_APP_USER_VALID_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteAppUser_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/app-users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
