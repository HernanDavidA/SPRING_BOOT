package com.riwi.vacants.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 40, nullable = false)
    private String contact;
    @Column(length = 15)
    private String location;
    @Column(length = 15, nullable = false)
    private String name;
    /*
    @OneToMany - uno a muchos Una empresa puede tener muchas vacantes
    @mappedBy Especificamos donde se guardara la informacion de la relacion
    fetch EAGER: Join Implicito || fetch LAZY: Join perezoso
    orphanRemoval - Remover objeto huerfano (Sin llave foranea)
    */
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Vacant> vacants;
}
