package org.example.laboratoryappointmentsystemspring.Repository;

import io.swagger.v3.core.util.Json;
import org.example.laboratoryappointmentsystemspring.dox.Appointment;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    // 根据预约号查找
    @Query("SELECT * from appointment where id=:appointmentId")
    public Appointment findByAppointmentId(Integer appointmentId);

    // 添加预约信息
    // 调整插入语句的字段与数据表中的字段对应，参数类型也需匹配数据表字段类型
    @Modifying
    @Transactional
    @Query("insert into appointment(uid, lid, cid, week, section, day_of_week, status, details) values(:uid, :lid, :cid, :week, :section, :day_of_week, :status, :details)")
    public void addAppointment(Integer uid, Integer lid, Integer cid, Integer week, Integer section, Integer day_of_week, String status, String details);


    // 删除预约信息
    // 参数类型改为Integer与表中id字段类型一致
    @Modifying
    @Transactional
    @Query("delete from appointment where id=:appointmentId")
    public void deleteByAppointmentId(Integer appointmentId);


    //按用户id查找所有预约记录
    @Query("SELECT * from appointment where uid=:uid")
    //这里返回值类型应该是List<Appointment>，因为一个用户可能有多条预约记录
    List<Appointment> findByUid(Integer uid);

    //直接获得所有预约信息
    @Query("SELECT * from appointment")
    List<Appointment> findAllAppointments();

    //找出预约次数最多的用户
    @Query("SELECT uid from appointment group by uid order by count(*) desc limit 1")
    Integer findMostAppointmentUser();

    // 自定义查询方法用于检测预约冲突
    @Query("SELECT COUNT(*) > 0 FROM appointment a " +
            "WHERE a.lid = :lid " +
            "AND a.week = :week " +
            "AND a.day_of_week = :day_of_week " +
            "AND a.section = :section " +
            "AND a.status!= 'canceled'" +
            "FOR UPDATE")
    boolean isConflict(Integer lid, Integer cid, Integer week, Integer section, Integer day_of_week);

    //根据周数天数节数查询可用实验室
    @Query("SELECT l.name\n" +
            "FROM labs l\n" +
            "WHERE l.id NOT IN (\n" +
            "    SELECT a.lid\n" +
            "    FROM appointment a\n" +
            "    WHERE a.week = :week" +
            "      AND a.day_of_week = :day_of_week\n" +
            "      AND a.section = :section\n" +
            "      AND a.status = 'approved'\n" +
            ")\n" +
            "ORDER BY l.id;")
public List<String> findAvailableLab(Integer week,Integer day_of_week,Integer section);
}



