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
@Table("course")
@Component
public class Course implements Serializable{
    @Id
    @CreatedBy
    private Integer id;
    private Integer uid;
    private Integer lid;
    private Integer count;
    private String name;
    private Json information; // JSON格式数据在Java中可以用String类型先接收，后续按需处理
    private String week;
    private String time;
    private LocalDateTime insert_time;
    private LocalDateTime update_time;
}
