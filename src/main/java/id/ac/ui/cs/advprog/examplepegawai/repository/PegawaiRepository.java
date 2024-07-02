package id.ac.ui.cs.advprog.examplepegawai.repository;

import id.ac.ui.cs.advprog.examplepegawai.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

}
