package org.example.laboratoryappointmentsystemspring.mapper.repository;

import org.example.laboratoryappointmentsystemspring.dox.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends CrudRepository<User, Integer> {


}