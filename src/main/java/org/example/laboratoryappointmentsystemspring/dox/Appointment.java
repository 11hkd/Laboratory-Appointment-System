package org.example.laboratoryappointmentsystemspring.dox;

import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("appointment")
@Component
public class Appointment implements Serializable {
    @Id
    @CreatedBy
    private Integer id;
    private Integer uid;
    private Integer lid;
    private Integer cid;
    private Integer week;
    private Integer section;
    private Integer day_of_week;
    private String status;
    private Json details;
    private LocalDateTime insert_time;
    private LocalDateTime update_time;
//    1
}
