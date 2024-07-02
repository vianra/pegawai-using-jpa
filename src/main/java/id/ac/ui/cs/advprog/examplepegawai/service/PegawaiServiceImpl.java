package id.ac.ui.cs.advprog.examplepegawai.service;

import id.ac.ui.cs.advprog.examplepegawai.model.Pegawai;
import id.ac.ui.cs.advprog.examplepegawai.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PegawaiServiceImpl implements PegawaiService{
    @Autowired
    private PegawaiRepository pegawaiRepository;

    @Override
    public Pegawai create(Pegawai pegawai) {
        return pegawaiRepository.save(pegawai);
    }

    @Override
    public Pegawai readById(Long id) {
        Optional<Pegawai> optionalPegawai = pegawaiRepository.findById(id);
        return optionalPegawai.orElse(null);
    }

    @Override
    public List<Pegawai> readAll() {
        List<Pegawai> allPegawai = new ArrayList<Pegawai>(pegawaiRepository.findAll());
        return allPegawai;
    }

    @Override
    public Pegawai update(Pegawai pegawaiBaru, Long id) {
        Pegawai pegawaiLama = pegawaiRepository.findById(id).get();
        if(Objects.nonNull(pegawaiLama.getName())) {
            pegawaiLama.setName(pegawaiBaru.getName());
        }
        if(Objects.nonNull(pegawaiLama.getDivision())) {
            pegawaiLama.setDivision(pegawaiBaru.getDivision());
        }
        return pegawaiRepository.save(pegawaiLama);
    }
    @Override
    public void deleteById(Long id) {
        pegawaiRepository.deleteById(id);
    }
}
