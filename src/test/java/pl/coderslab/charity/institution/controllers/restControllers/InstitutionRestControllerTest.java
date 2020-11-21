package pl.coderslab.charity.institution.controllers.restControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.coderslab.charity.institution.DTOS.InstitutionDTO;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.exceptions.ObjectNotFoundException;
import pl.coderslab.charity.institution.repository.InstitutionRepository;
import pl.coderslab.charity.institution.service.InstitutionService;

import javax.sql.DataSource;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InstitutionRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class InstitutionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DataSource dataSource;

    @MockBean
    private InstitutionService institutionService;
    @MockBean
    private InstitutionRepository institutionRepository;

    private List<Institution> institutions;
    private Institution institution;
    private Institution institution1;
    private InstitutionDTO institutionDTO;
    private long institutionId;

    @BeforeEach
    void init() {
        institutionId = 1;
        institution = new Institution();
        institution.setName("instytucja");
        institution.setDescription("opis");
        institution.setId(1L);
        institution1 = new Institution();
        institution1.setName("instytucja2");
        institution1.setDescription("opis2");
        institution1.setId(2L);
        institutionDTO = new InstitutionDTO();
        institutionDTO.setName("instytucja3");
        institutionDTO.setDescription("opis3");
        institutionDTO.setId(3L);

        institutions = List.of(institution, institution1);
    }


    @Test
    void givenNoIdShouldGetInstitutionList() throws Exception {
        String institutionListJson = objectMapper.writeValueAsString(institutions);

        when(institutionService.getAllByStatus(true)).thenReturn(institutions);
        mockMvc.perform(get("/api/institution"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(institutionListJson));
    }

    @Test
    void givenIdShouldGetInstitution() throws Exception {
        institution.setId(institutionId);
        when(institutionService.findInstitutionById(institutionId)).thenReturn(institution);

        mockMvc.perform(get("/api/institution/{id}", institutionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(institution.getName())));
    }

    @Test
    void givenInstitutionShouldCreateNew() throws Exception {
        when(institutionService.saveInstitutionFromDTO(any(InstitutionDTO.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        mockMvc.perform(post("/api/institution")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(institutionDTO)))
                .andExpect(status().isCreated());
    }


    @Test
    void givenInstitutionShouldUpdate() throws Exception {

        when(institutionService.update(any(Institution.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/api/institution/{id}", institutionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(institution));

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(institution.getName())))
                .andExpect(jsonPath("$.id", Matchers.is((int) institutionId)));
    }

    //
    @Test
    void shouldReturn404WhenObjectFindByIdNotFound() throws Exception {
        when(institutionService.findInstitutionById(institutionId)).thenThrow(ObjectNotFoundException.class);
        mockMvc.perform(get("/api/institution/{id}", institutionId))
                .andExpect(status().isNotFound());
    }




}