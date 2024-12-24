package org.example.laboratoryappointmentsystemspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LaboratoryAppointmentSystemSpringApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoryAppointmentSystemSpringApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
}
