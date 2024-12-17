package org.example.laboratoryappointmentsystemspring.Repository;

import org.example.laboratoryappointmentsystemspring.dox.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    //用户名查找
    @Query("SELECT * from user where username=:username")
    public User findByUsername(String username);

    //添加用户
    @Query("insert into user(username,password,role) values(:username,:password,:role)")
    public User addUser(String username,String password,String role);

    //删除用户
    @Query("delete from user where username=:username")
    public void deleteByUsername(String username);

    //修改用户
    @Query("update user set password=:password,role=:role where username=:username")
    public User updateUser(String username,String password,String role);








}
