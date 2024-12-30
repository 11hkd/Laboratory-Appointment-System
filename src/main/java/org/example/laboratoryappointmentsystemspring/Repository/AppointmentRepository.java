package org.example.laboratoryappointmentsystemspring.Repository;

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


}