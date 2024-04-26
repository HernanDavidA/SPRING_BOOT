package com.riwi.vacants.Entity;

import com.riwi.vacants.utils.enums.StateVacant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "vacant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 16, nullable = false)
    private String title;
    @Column(length = 255)
    private String description;
    @Enumerated(EnumType.STRING)
    private StateVacant status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="company_id", referencedColumnName = "id")
    private Company company;

}
