package org.example.laboratoryappointmentsystemspring.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("user")//指定表名
public class User {
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
    private Data inset_time;
    private Data update_time;

}
