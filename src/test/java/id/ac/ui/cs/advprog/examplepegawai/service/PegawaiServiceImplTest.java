package id.ac.ui.cs.advprog.examplepegawai.service;

import id.ac.ui.cs.advprog.examplepegawai.model.Pegawai;
import id.ac.ui.cs.advprog.examplepegawai.repository.PegawaiRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PegawaiServiceImplTest {

    @Mock
    private PegawaiRepository pegawaiRepository;

    @InjectMocks
    private PegawaiServiceImpl pegawaiService;

    @Test
    public void testCreate() {
        Pegawai pegawai = new Pegawai(1L, "John Doe", "IT");
        when(pegawaiRepository.save(any(Pegawai.class))).thenReturn(pegawai);

        Pegawai createdPegawai = pegawaiService.create(pegawai);

        assertEquals(pegawai, createdPegawai);
    }

    @Test
    public void testReadById() {
        Long id = 1L;
        Pegawai pegawai = new Pegawai(id, "John Doe", "IT");
        when(pegawaiRepository.findById(id)).thenReturn(Optional.of(pegawai));

        Pegawai foundPegawai = pegawaiService.readById(id);

        assertEquals(pegawai, foundPegawai);
    }

    @Test
    public void testReadAll() {
        List<Pegawai> pegawaiList = Arrays.asList(
                new Pegawai(1L, "John Doe", "IT"),
                new Pegawai(2L, "Jane Smith", "Finance")
        );
        when(pegawaiRepository.findAll()).thenReturn(pegawaiList);

        List<Pegawai> allPegawai = pegawaiService.readAll();

        assertEquals(2, allPegawai.size());
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Pegawai existingPegawai = new Pegawai(id, "John Doe", "IT");
        Pegawai updatedPegawai = new Pegawai(id, "Updated Name", "Updated Division");

        when(pegawaiRepository.findById(id)).thenReturn(Optional.of(existingPegawai));
        when(pegawaiRepository.save(existingPegawai)).thenReturn(updatedPegawai);

        Pegawai result = pegawaiService.update(updatedPegawai, id);

        assertEquals(updatedPegawai.getName(), result.getName());
        assertEquals(updatedPegawai.getDivision(), result.getDivision());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        pegawaiService.deleteById(id);

        verify(pegawaiRepository, times(1)).deleteById(id);
    }
}

