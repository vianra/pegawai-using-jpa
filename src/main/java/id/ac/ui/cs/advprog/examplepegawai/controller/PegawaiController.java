package id.ac.ui.cs.advprog.examplepegawai.controller;

import id.ac.ui.cs.advprog.examplepegawai.model.Pegawai;
import id.ac.ui.cs.advprog.examplepegawai.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PegawaiController {
    @Autowired
    PegawaiService pegawaiService;

    @GetMapping("/semua-pegawai")
    public ResponseEntity readAll() {
        ResponseEntity responseEntity = null;
        try {
            List<Pegawai> pegawaiList = pegawaiService.readAll();
            responseEntity = ResponseEntity.ok(pegawaiList);
        } catch (Exception e) {
            String errMessage = "Gagal akses pegawai.";
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMessage);
        }
        return responseEntity;
    }

    @PostMapping("/pegawai/{id}")
    public ResponseEntity readById(@PathVariable Long id) {
        ResponseEntity responseEntity = null;
        try {
            Pegawai pegawai = pegawaiService.readById(id);
            if (pegawai != null) {
                responseEntity = ResponseEntity.ok(pegawai);
            } else {
                responseEntity = ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            String errMessage = "Gagal menemukan";
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMessage);
        }
        return responseEntity;
    }

}