package org.example.laboratoryappointmentsystemspring.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
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
    private String details;
    private LocalDateTime insert_time;
    private LocalDateTime update_time;
//    1
}
