package org.example.laboratoryappointmentsystemspring.Repository;

import org.example.laboratoryappointmentsystemspring.dox.Appointment;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, String> {
    //根据预约号查找
    @Query("SELECT * from appointment where appointmentId=:appointmentId")
    public Appointment findByAppointmentId(String appointmentId);

    //添加预约信息
    @Query("insert into appointment(appointmentId,labName,labLocation,labCapacity,labType,appointmentDate,appointmentTime,appointmentPurpose,appointmentStatus) values(:appointmentId,:labName,:labLocation,:labCapacity,:labType,:appointmentDate,:appointmentTime,:appointmentPurpose,:appointmentStatus)")
    public Appointment addAppointment(String appointmentId, String labName, String labLocation, String labCapacity, String labType, String appointmentDate, String appointmentTime, String appointmentPurpose, String appointmentStatus);

    //删除预约信息
    @Query("delete from appointment where appointmentId=:appointmentId")
    public void deleteByAppointmentId(String appointmentId);

    //修改预约信息
    @Query("update appointment set labName=:labName,labLocation=:labLocation,labCapacity=:labCapacity,labType=:labType,appointmentDate=:appointmentDate,appointmentTime=:appointmentTime,appointmentPurpose=:appointmentPurpose,appointmentStatus=:appointmentStatus where appointmentId=:appointmentId")
    public Appointment updateAppointment(String appointmentId, String labName, String labLocation, String labCapacity, String labType, String appointmentDate, String appointmentTime, String appointmentPurpose, String appointmentStatus);

    //根据教师id查找
    @Query("SELECT * from appointment where teacherId=:teacherId")
    public Appointment findByTeacherId(String teacherId);
}
