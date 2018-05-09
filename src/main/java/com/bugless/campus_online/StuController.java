package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.lang.*;

@Controller
@RequestMapping("/student")
public class StuController {

    @Autowired
    private ReserveRepository reserveRepository;

    public StuController(ReserveRepository reserveRepo) {
        this.reserveRepository = reserveRepo;
    }

    //获取当前可预约教师以及时间
    @RequestMapping(method = RequestMethod.GET)
    public String getReserve(Model model) {
        //reserv_flag为0代表可以预约
        List<Reservation> availableReserve = reserveRepository.findByResrvFlag(0);
        System.out.println("GET");
        model.addAttribute("stuReserves",availableReserve);
        System.out.println("StuController : Get the reservation info");

        return "student";
    }

    //提交预约
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitReserve(StuFetch stuFetch) {
        String stuID = stuFetch.getStuID();
        String availaTcherID = stuFetch.getAvailableTcherID();
        String availaTime = stuFetch.getAvailableTime();
        //提交预约即在已有表项中写入学生学号及预约标记
        reserveRepository.submitReserve(stuID,1,availaTcherID,availaTime);
        System.out.println("StuController : Submitted the reservation");

        return "student";
    }

    //编辑当前预约
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editReserve(StuFetch stuFetch) {
        String stuID = stuFetch.getStuID();
        String reserveTime = stuFetch.getReserveTime();
        String reserveTcher = stuFetch.getReserveTcherID();
        reserveRepository.editReserve(reserveTcher,reserveTime,stuID);
        System.out.println("StuController : Modified the reservation");

        return "student";
    }

    //删除当前预约
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteReserve(StuFetch stuFetch) {
        String stuID = stuFetch.getStuID();
        reserveRepository.deleteReserve(stuID);
        System.out.println("StuController : Deleted the reservation");

        return "student";
    }
}