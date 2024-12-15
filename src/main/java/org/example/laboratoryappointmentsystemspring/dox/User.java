package org.example.laboratoryappointmentsystemspring.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @CreatedBy
    public static final String SUPPERADMIN_ROLE = "bala";
    public static final String ADMIN_ROLE = "venv";
    public static final String TEACHER_ROLE = "race";
    private String id;
    private String username;
    private String password;
    private String phone;
    private String account;
    private String role;
    private Data inset_time;
    private Data update_time;

}
