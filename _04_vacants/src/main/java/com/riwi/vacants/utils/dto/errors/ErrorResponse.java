package com.riwi.vacants.utils.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
 *Para evitar que se dupliquen los identificiadores de las clases 
 * 
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ErrorResponse extends BaseErrorResponse{
    private String message;
}
