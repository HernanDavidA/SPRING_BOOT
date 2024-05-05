package com.riwi.vacants.utils.dto.request;

import com.riwi.vacants.utils.enums.StateVacant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // Patron de diseño para creacion de clases
@AllArgsConstructor
@NoArgsConstructor

public class VacantRequest {
    @NotBlank(message = "El titulo es requerido")
    private String title;
    @NotBlank(message = "La descripcion es requerida")
    private String description;
    private StateVacant status;
    @Size(max = 32, min = 0)
    @NotBlank(message = "El id es requerido")
    private String companyId;
}
