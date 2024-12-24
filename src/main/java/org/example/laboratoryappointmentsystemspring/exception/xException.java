package org.example.laboratoryappointmentsystemspring.exception;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.laboratoryappointmentsystemspring.exception.Code;

import java.beans.ConstructorProperties;

@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class xException extends RuntimeException {
    private Code code;
    private int number;
    private String message;

    @ConstructorProperties({"code"})
    public xException(Code code) {
        this.code = code;
    }
}