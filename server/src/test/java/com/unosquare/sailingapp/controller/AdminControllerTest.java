package com.unosquare.sailingapp.controller;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.AdminDTO;
import com.unosquare.sailingapp.dto.CreateAdminDTO;
import com.unosquare.sailingapp.model.AdminViewModel;
import com.unosquare.sailingapp.model.CreateAdminViewModel;
import com.unosquare.sailingapp.service.AdminService;
import com.unosquare.sailingapp.util.ResourceUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    private static final String CREATE_ADMIN_VALID_JSON
            = ResourceUtility.generateStringFromResource("requestJson/CreateAdmin.json");

    @Fixture
    private AdminDTO adminDTOFixture;
    @Fixture
    private List<AdminDTO> adminDTOListFixture;
    @Fixture
    private List<AdminViewModel> adminViewModelListFixture;
    @Fixture
    private AdminViewModel adminViewModelFixture;
    @Fixture
    private CreateAdminDTO createAdminDTOFixture;
    @Mock
    private AdminService adminServiceMock;
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
                        new AdminController(adminServiceMock, mapperMock))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void getAdmins_ReturnsOk() throws Exception{
        when(mapperMock.map(adminDTOListFixture, AdminViewModel.class)).thenReturn(adminViewModelListFixture);
        when(adminServiceMock.getAllAdmins()).thenReturn(adminDTOListFixture);

        mockMvc.perform(MockMvcRequestBuilders.get("/admins/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAdminsByID_ReturnsOk() throws Exception{
        when(mapperMock.map(adminDTOFixture, AdminViewModel.class)).thenReturn(adminViewModelFixture);
        when(adminServiceMock.getAdminByID(anyInt())).thenReturn(adminDTOFixture);

        mockMvc.perform(MockMvcRequestBuilders.get("/admins/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createAdmin_ReturnsOk() throws Exception{
        when(mapperMock.map(any(CreateAdminViewModel.class), any())).thenReturn(createAdminDTOFixture);
        when(adminServiceMock.createAdmin(createAdminDTOFixture)).thenReturn(adminDTOFixture);
        when(mapperMock.map(adminDTOFixture, AdminViewModel.class)).thenReturn(adminViewModelFixture);

        mockMvc.perform(post("/admins/create")
                        .content(CREATE_ADMIN_VALID_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
