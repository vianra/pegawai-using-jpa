package id.ac.ui.cs.advprog.examplepegawai.service;

import id.ac.ui.cs.advprog.examplepegawai.model.Pegawai;

import java.util.List;

public interface PegawaiService {
    public Pegawai create(Pegawai pegawai);
    public Pegawai readById(Long id);
    public List<Pegawai> readAll();
    public Pegawai update(Pegawai pegawai, Long id);
    public void deleteById(Long id);
}
