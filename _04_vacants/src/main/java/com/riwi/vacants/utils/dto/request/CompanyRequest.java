package com.riwi.vacants.utils.dto.request;


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
public class CompanyRequest {

    @Size(min = 0, max = 40, message = "El nombre supera la cantidad de caracteres")
    @NotBlank(message = "El nombre de la compañia es requerido")
    private String name;
    @NotBlank(message = "El contacto de la compañia es requerido")
    @Size(min = 0, max = 20, message = "El contacto supera la cantidad de caracteres")
    private String contact;
    @Size(min = 0, max = 40, message = "La ubicacion supera la cantidad de caracteres")
    private String location;

}
