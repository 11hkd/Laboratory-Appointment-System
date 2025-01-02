package org.example.laboratoryappointmentsystemspring.dox;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AppointmentTime implements Serializable {
    private Integer week;
    private Integer section;
    private Integer day_of_week;
}
