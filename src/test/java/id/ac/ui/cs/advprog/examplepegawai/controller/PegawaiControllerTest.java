package id.ac.ui.cs.advprog.examplepegawai.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.examplepegawai.model.Pegawai;
import id.ac.ui.cs.advprog.examplepegawai.service.PegawaiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
public class PegawaiControllerTest {

    @Mock
    private PegawaiService pegawaiService;

    @InjectMocks
    private PegawaiController pegawaiController;

    private MockMvc mockMvc;

    @Test
    public void testCreatePegawai() throws Exception {
        Pegawai pegawai = new Pegawai(1L, "John Doe", "IT");
        when(pegawaiService.create(any(Pegawai.class))).thenReturn(pegawai);

        mockMvc = MockMvcBuilders.standaloneSetup(pegawaiController).build();

        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pegawai)))
                .andExpect(status().isOk());
    }

    @Test
    public void testReadAll() throws Exception {
        List<Pegawai> pegawaiList = Arrays.asList(
                new Pegawai(1L, "John Doe", "IT"),
                new Pegawai(2L, "Jane Smith", "Finance")
        );
        when(pegawaiService.readAll()).thenReturn(pegawaiList);

        mockMvc = MockMvcBuilders.standaloneSetup(pegawaiController).build();

        mockMvc.perform(get("/semua-pegawai"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].division").value("Finance"));
    }

    @Test
    public void testReadById() throws Exception {
        Long id = 1L;
        Pegawai pegawai = new Pegawai(id, "John Doe", "IT");
        when(pegawaiService.readById(id)).thenReturn(pegawai);

        mockMvc = MockMvcBuilders.standaloneSetup(pegawaiController).build();

        mockMvc.perform(get("/pegawai/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.division").value("IT"));
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = 1L;
        Pegawai updatedPegawai = new Pegawai(id, "Updated Name", "Updated Division");
        when(pegawaiService.update(any(Pegawai.class), any(Long.class))).thenReturn(updatedPegawai);

        mockMvc = MockMvcBuilders.standaloneSetup(pegawaiController).build();

        mockMvc.perform(post("/pegawai/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedPegawai)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.division").value("Updated Division"));
    }

    @Test
    public void testDelete() throws Exception {
        Long id = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(pegawaiController).build();

        mockMvc.perform(delete("/pegawai/{id}", id))
                .andExpect(status().isOk());
    }

    // Utility method to convert object to JSON string
    private String asJsonString(Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}

