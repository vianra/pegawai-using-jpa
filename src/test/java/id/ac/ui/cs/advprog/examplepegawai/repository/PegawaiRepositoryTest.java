package id.ac.ui.cs.advprog.examplepegawai.repository;

import id.ac.ui.cs.advprog.examplepegawai.model.Pegawai;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class PegawaiRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PegawaiRepository pegawaiRepository;

    @BeforeEach
    public void setUp() {
        Pegawai pegawai1 = new Pegawai(null, "John Doe", "IT");
        Pegawai pegawai2 = new Pegawai(null, "Jane Smith", "Finance");

        entityManager.persist(pegawai1);
        entityManager.persist(pegawai2);
        entityManager.flush();
    }

    @AfterEach
    public void tearDown() {
        entityManager.clear();
    }

    @Test
    public void testFindAll() {
        List<Pegawai> allPegawai = pegawaiRepository.findAll();
        assertEquals(2, allPegawai.size());
    }

    @Test
    public void testFindById() {
        List<Pegawai> allPegawai = pegawaiRepository.findAll();
        Long idPegawai1 = allPegawai.get(0).getPegawaiId();
        Optional<Pegawai> foundPegawaiOptional = pegawaiRepository.findById(idPegawai1);
//        System.out.println("Pegawai: "+ foundPegawaiOptional);
        assertTrue(foundPegawaiOptional.isPresent());

        Pegawai foundPegawai = foundPegawaiOptional.get();
        assertEquals("John Doe", foundPegawai.getName());
        assertEquals("IT", foundPegawai.getDivision());
    }

    @Test
    public void testSave() {
        Pegawai pegawai = new Pegawai(null, "Emily Brown", "HR");
        Pegawai savedPegawai = pegawaiRepository.save(pegawai);

        assertThat(savedPegawai.getPegawaiId()).isNotNull();
        assertEquals("Emily Brown", savedPegawai.getName());
        assertEquals("HR", savedPegawai.getDivision());
    }

    @Test
    public void testDeleteById() {
        pegawaiRepository.deleteById(1L);
        Optional<Pegawai> deletedPegawai = pegawaiRepository.findById(1L);
        assertThat(deletedPegawai).isEmpty();
    }
}