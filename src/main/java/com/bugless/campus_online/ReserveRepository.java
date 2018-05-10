package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;
import java.util.List;

public interface ReserveRepository extends JpaRepository<Reservation, Integer> {

    /*------------------------------------张博凯------------------------------------*/
    List<Reservation> findByTcherID(String tcherID);

    @Query(value = "select r from reservation r where r.tcher_id=?1 and r.resrv_time=?2",nativeQuery = true)
    Reservation findByTcherIDAndAndResrvTime(String tcherID,String resrvTime);

    @Query(value = "update reservation r set r.resrv_flag=1 where r.tcher_id=?1 and r.resrv_time=?2 ", nativeQuery = true)
    @Modifying
    public void updateReservation(String tcherID,String resrvTime);

    @Query(value = "delete from reservation where id=?1 ", nativeQuery = true)
    @Modifying
    public void delete(int id);
    //http://www.cnblogs.com/hawell/p/SpringDataJpa.html
    /*------------------------------------张博凯------------------------------------*/


    /*------------------------------------定理------------------------------------*/
    List<Reservation> findByResrvFlag(int reserv_flag);
    List<Reservation> findByStuID(String stuID);
    //学生提交预约
    @Query(value = "update reservation r set r.stu_id = ?1 and r.resrv_flag = ?2 where r.tcher_id = ?3 and r.resrv_time = ?4", nativeQuery = true)
    @Modifying
    @Transactional
    public void submitReserve(String stuID, int resrv_flag, String tcher_id, String resrv_time);

    //学生编辑预约
    @Query(value = "update reservation r set r.tcher_id = ?1 and r.resrv_time = ?2 where r.stu_id = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    public void editReserve(String tcher_id,String resrv_time,String stu_id);

    //学生删除预约
    @Query(value = "delete from reservation where stu_id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteReserve(String stu_id);
    /*------------------------------------定理------------------------------------*/
}