package com.example.demo.web;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@Controller
@SessionAttributes("name")
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView userSubmit(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "email") String email,
                                   @RequestParam(name = "date") String date,
                                   @RequestParam(name = "time") String time,
                                   @RequestParam(name = "restaurant") String restaurant,
                                   @RequestParam(name = "persons") String persons,
                                   @RequestParam(name = "phone") String phone,
                                   @RequestParam(name = "status") int status) {
        reservationRepository.save(new Reservation(0, name, email, date, time, restaurant, persons, phone, 0));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("successful",true);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
