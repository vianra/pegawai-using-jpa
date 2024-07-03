package id.ac.ui.cs.advprog.examplepegawai.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PegawaiTest {

    @Test
    public void testNoArgsConstructor() {
        Pegawai pegawai = new Pegawai();
        assertNull(pegawai.getPegawaiId());
        assertNull(pegawai.getName());
        assertNull(pegawai.getDivision());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String name = "John Doe";
        String division = "IT";
        Pegawai pegawai = new Pegawai(id, name, division);
        assertEquals(id, pegawai.getPegawaiId());
        assertEquals(name, pegawai.getName());
        assertEquals(division, pegawai.getDivision());
    }

    @Test
    public void testBuilder() {
        Long id = 1L;
        String name = "John Doe";
        String division = "IT";
        Pegawai pegawai = Pegawai.builder()
                .pegawaiId(id)
                .name(name)
                .division(division)
                .build();
        assertEquals(id, pegawai.getPegawaiId());
        assertEquals(name, pegawai.getName());
        assertEquals(division, pegawai.getDivision());
    }

    @Test
    public void testSetterGetter() {
        Pegawai pegawai = new Pegawai();
        Long id = 1L;
        String name = "John Doe";
        String division = "IT";

        pegawai.setPegawaiId(id);
        pegawai.setName(name);
        pegawai.setDivision(division);

        assertEquals(id, pegawai.getPegawaiId());
        assertEquals(name, pegawai.getName());
        assertEquals(division, pegawai.getDivision());
    }

    // You can add more tests as needed for custom methods or behaviors
}

