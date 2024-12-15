package org.example.laboratoryappointmentsystemspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.example.laboratoryappointmentsystemspring.dox.User;


@Repository
public interface AdminRepository extends CrudRepository<User, Integer> {

}