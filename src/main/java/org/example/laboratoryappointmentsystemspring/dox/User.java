package org.example.laboratoryappointmentsystemspring.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.io.DataInput;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User implements Serializable {
    @Id
    @CreatedBy
    public static final String SUPPERADMIN_ROLE = "bala";
    public static final String ADMIN_ROLE = "venv";
    public static final String TEACHER_ROLE = "race";
    private String id;
    private String username;
    private String account;
    private String password;
    private String role;
    private String phone;
    private LocalDateTime inset_time;
    private LocalDateTime update_time;

}
