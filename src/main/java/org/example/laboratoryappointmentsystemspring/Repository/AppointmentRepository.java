package org.example.laboratoryappointmentsystemspring.Repository;

import org.example.laboratoryappointmentsystemspring.dox.Appointment;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    // 根据预约号查找
    @Query("SELECT * from appointment where id=:appointmentId")
    public Appointment findByAppointmentId(Integer appointmentId);

    // 注意表中字段并没有名为appointmentId的，应该是id字段作为主键自增，这里假设业务逻辑不需要传递id参数插入（由数据库自动生成）
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

    // 修改预约信息
    // 同样修改更新语句的字段使其准确对应表结构，并且参数类型匹配
    @Modifying
    @Transactional
    @Query("update appointment set uid=:uid, lid=:lid, cid=:cid, week=:week, section=:section, day_of_week=:day_of_week, status=:status, details=:details where id=:appointmentId")
    public void updateAppointment(Integer appointmentId, Integer uid, Integer lid, Integer cid, Integer week, Integer section, Integer day_of_week, String status, String details);

    // 根据教师id查找（这里假设教师id在业务逻辑中有对应字段关联，比如存放在details等字段中以JSON格式存储相关信息，需要根据实际调整查询语句）
    @Query("SELECT * from appointment where details->>'$.teacherId' = :teacherId")
    public Appointment findByTeacherId(String teacherId);
}