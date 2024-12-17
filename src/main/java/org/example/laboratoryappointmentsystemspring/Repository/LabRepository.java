package org.example.laboratoryappointmentsystemspring.Repository;

import org.example.laboratoryappointmentsystemspring.dox.Lab;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends CrudRepository<Lab,String> {
    //根据实验室名查找
    @Query("SELECT * from lab where labName=:labName")
    public Lab findByLabName(String labName);

    //添加实验室信息
    @Query("insert into lab(labName,labLocation,labCapacity,labType) values(:labName,:labLocation,:labCapacity,:labType)")
    public Lab addLab(String labName,String labLocation,String labCapacity,String labType);

    //删除实验室信息
    @Query("delete from lab where labName=:labName")
    public void deleteByLabName(String labName);

    //修改实验室信息
    @Query("update lab set labLocation=:labLocation,labCapacity=:labCapacity,labType=:labType where labName=:labName")
    public Lab updateLab(String labName,String labLocation,String labCapacity,String labType);

}
