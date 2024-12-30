package org.example.laboratoryappointmentsystemspring.Repository;

import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends CrudRepository<Lab, Integer> {
    // 根据实验室名查找
    @Query("SELECT * from labs where name=:labName")
    public Lab findByLabName(String labName);

    // 添加实验室信息
    @Modifying
    @Transactional
    @Query("insert into labs(number, information, news, name) values(:number, :information, :news, :labName)")
    public void addLab(Integer number, String information, String news, String labName);

    // 删除实验室信息
    @Modifying
    @Transactional
    @Query("delete from labs where name=:labName")
    public void deleteByLabName(String labName);

    // 查看所有实验室信息
    @Query("SELECT * from labs")
    public List<Lab> findAllLabs();
//    11

    // 更新公告信息
    @Modifying
    @Transactional
    @Query("update labs set news=:news where name=:labName")
    public void updateNews(String labName,String news);
}