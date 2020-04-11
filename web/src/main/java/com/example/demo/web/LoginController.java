package com.example.demo.web;

import com.example.demo.model.Customer;
import com.example.demo.model.Reservation;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView contact() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

   @GetMapping(value = "/profile")
   public ModelAndView openProfile(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if(session.getAttribute("username")==null){
            modelAndView.setViewName("redirect:/login");
        } else {
            List<Reservation> legit = new ArrayList<>();
            modelAndView.addObject("username", session.getAttribute("username"));
            List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
            for (int i = 0; i < reservations.size(); i++) {
                if (session.getAttribute("username").toString().toLowerCase().equals(reservations.get(i).getCustomer_id().toLowerCase())) {
                    if(reservations.get(i).getStatus() == 1){
                        legit.add(reservations.get(i));
                        modelAndView.addObject("ok",true);
                        modelAndView.addObject("notok",false);
                        modelAndView.addObject("pending",false);
                    } else if(reservations.get(i).getStatus() == 2) {
                        legit.add(reservations.get(i));
                        modelAndView.addObject("notok",true);
                        modelAndView.addObject("ok",false);
                        modelAndView.addObject("pending",false);
                    } else {
                        legit.add(reservations.get(i));
                        modelAndView.addObject("pending",true);
                        modelAndView.addObject("notok",false);
                        modelAndView.addObject("ok",false);
                    }
                }
            }
            modelAndView.addObject("loggedout", false);
            modelAndView.addObject("legit", legit);
            modelAndView.setViewName("profile");
        }
        return modelAndView;
   }

   @GetMapping(value = "/admin")
   public ModelAndView adminPage(HttpSession session, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if(session.getAttribute("username").equals("admin")) {
            List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
            modelAndView.addObject("reservations", reservations);
            modelAndView.addObject("username", "admin");
            modelAndView.setViewName("admin");
        } else  {
            modelAndView.setViewName("redirect:/login");
        }
        modelMap.put("reservation", new Reservation());
        return modelAndView;
   }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView Accept(@RequestParam long id,
                               @RequestParam String accept){
        ModelAndView model = new ModelAndView();
        if(accept.equals("true")){
            this.reservationService.update(id,1);
            model.addObject("accepted",true);
        } else if(accept.equals("false")){
            this.reservationService.update(id,2);
            model.addObject("accepted",false);
        }
        model.setViewName("admin");
        return model;

    }

    @RequestMapping(value = "/flight", method = RequestMethod.GET)
    public ModelAndView flight() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/restaurants");
        return modelAndView;
    }

    @RequestMapping(value = "/hotel", method = RequestMethod.GET)
    public ModelAndView hotel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hotel");
        return modelAndView;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userSubmit(@RequestParam(name = "name") String name,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "email") String email, Model model) {

        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        for(int i=0;i< customers.size(); i++){
            Customer user = customers.get(i);
            if(user.getName().equals(name) || user.getEmail().equals(email)){
                model.addAttribute("exists", true);
                return "registration";
            }
        }
        customerRepository.save(new Customer(0, name, password, email));
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(Model model, @RequestParam(name = "username") String username,
                            @RequestParam(name = "pass") String pass,
                            HttpSession session) {
        String successful="";
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        for (int i = 0; i < customers.size(); i++) {
            Customer user = customers.get(i);
            if (user.getName().equals(username) && user.getPassword().equals(pass)) {
                session.setAttribute("username", username);
                model.addAttribute("loggedin", true);
                successful="redirect:/";
            } else {
                model.addAttribute("invalid", true);
                successful="registration";
            }
        }
        return successful;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(SessionStatus status,Model model, HttpSession session) {
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView();
        session.removeAttribute("username");//otstranuvanje na sesija
        model.addAttribute("loggedin", false);
        modelAndView.addObject("loggedout", true);
        modelAndView.setViewName("login");
        return modelAndView;
    }
}


