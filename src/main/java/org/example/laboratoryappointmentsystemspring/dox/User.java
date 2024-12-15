package org.example.laboratoryappointmentsystemspring.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public static final String SUPPERADMIN_ROLE = "bala";
    public static final String ADMIN_ROLE = "venv";
    public static final String TEACHER_ROLE = "race";

    private String id;
    private String username;
    private String password;

}
