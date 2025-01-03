package org.example.laboratoryappointmentsystemspring.dox;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@Table("lab")
@Component
public class Lab implements Serializable {
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
