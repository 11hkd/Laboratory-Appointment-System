package org.example.laboratoryappointmentsystemspring.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    @Id
    @CreatedBy
    private Integer id;
    private Integer uid;
    private Integer lid;
    private Integer count;
    private String name;
    private String information; // JSON格式数据在Java中可以用String类型先接收，后续按需处理
    private String week;
    private String time;
    private Date insert_time;
    private Date update_time;
}
