package org.example.laboratoryappointmentsystemspring.Repository;

import org.example.laboratoryappointmentsystemspring.dox.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    // 根据用户ID查找
    @Query("SELECT * from users where id=:id")
    public Optional<User> findById(Integer id);

    // 根据用户名查找
    @Query("SELECT * from users where username=:username")
    public User findByUsername(String username);

    // 添加用户
    @Modifying
    @Transactional
    @Query("insert into users(username, phone, account, password, role) values(:username, :phone, :account, :password, :role)")
    public void addUser(String username, String phone, String account, String password, String role);

    // 删除用户
    @Modifying
    @Transactional
    @Query("delete from users where username=:username")
    public void deleteByUsername(String username);

    // 根据账号Account获取用户
    @Query("SELECT * from users where account=:account")
    public User findByAccount(String account);

    //直接获取所有用户
    @Query("SELECT * from users")
    public List<User> findAll();
}