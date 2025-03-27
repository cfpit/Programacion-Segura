package ar.edu.centro8.ps.ejemplobase2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autos")
@Getter @Setter
@NoArgsConstructor

public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca", nullable = false)
    private String marca;
    
    private int precio;

    //constructores
    public Auto(String marca, int precio) {
        this.marca = marca;
        this.precio = precio;
    }
}
