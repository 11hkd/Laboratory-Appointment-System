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
public class Lab {
    @Id
    @CreatedBy
    private Integer id;
    private String name;
    private Integer number;
    private String information; // JSON格式数据在Java中可以用String类型先接收
    private String news; // JSON格式数据在Java中可以用String类型先接收
    private LocalDateTime insert_time;
    private LocalDateTime update_time;
}
