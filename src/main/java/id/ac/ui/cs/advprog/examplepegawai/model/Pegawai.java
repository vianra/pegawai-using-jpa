package id.ac.ui.cs.advprog.examplepegawai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pegawai {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    private String division;
    private ArrayList<String> tasks;
}
