package kea.sofie.touristguide.controller;

import kea.sofie.touristguide.model.TouristAttraction;
import kea.sofie.touristguide.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@WebMvcTest(TouristController.class) ----- Indlæser kun beans fra controlleren -- virker derfor ikke
@SpringBootTest // -------- Indlæser beans fra controller, service, repository!
@AutoConfigureMockMvc
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristService touristService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    


    @Test
    public void testGetTouristAttraction() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }

    @Test
    void getAttractionTags() throws Exception {
        when(touristService.getOneAttraction("Tivoli")).thenReturn(new TouristAttraction());

        mockMvc.perform(get("/attractions/Tivoli/tags"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"));
    }


    @Test
    public void testShowAddForm() throws Exception {
        // Udfør GET-request og tjek kun status og view-navn
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk()) // Tjek at statuskoden er 200 OK
                .andExpect(view().name("addAttraction")); // Tjek at view'et er "addAttraction"
    }



    /*// Ved ikke hvordan man gør når det er en POST
    @Test
    void saveAttraction() throws Exception {
    }

    // VIRKER IKKE - Ian kunne heller ikke løse den?
    @Test
    void showEditForm() throws Exception {
        mockMvc.perform(get("/attractions/edit/Tivoli"))
                .andExpect(status().isOk())
                .andExpect(view().name("editAttraction"));
    }

    // Ved ikke hvordan man gør når det er en POST
    @Test
    void updateAttraction() throws Exception {
    }

    // Ved ikke hvordan man gør når det er en POST
    @Test
    void deleteAttraction() {
    }*/
}