package com.example.demo.web;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping(value = "/restaurants")
    public ModelAndView getAllRestaurants(){
        ModelAndView model = new ModelAndView();
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        model.addObject("restaurants", restaurants);
        model.setViewName("flight");
        return model;
    }

    @GetMapping(value="/res", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<Restaurant> returnJson(){
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        return restaurants;
    }

    @RequestMapping(value = "restaurants/{id}", method = RequestMethod.GET)
    public ModelAndView getOneRestaurant(@PathVariable(name = "id") long id) {
        ModelAndView model = new ModelAndView();
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        for(int i=0; i< restaurants.size(); i++) {
            if(restaurants.get(i).getId()==id){
                model.addObject("restaurant", restaurants.get(i));
            }
        }
        model.setViewName("hotel");
        return model;

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search( @RequestParam(name = "name") String name, ModelAndView model){
        List<Restaurant> lista = (List<Restaurant>) restaurantRepository.findAll();
        List<Restaurant> lista1 = new ArrayList<>();
        for( int i=0; i<lista.size(); i++){
            if(lista.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                lista1.add(lista.get(i));
            }
        }
        model.addObject("restaurants", lista1);
        model.setViewName("search");
        return model;
    }

    @GetMapping(value = "/{slug_city}")
    public ModelAndView sortByCity(@PathVariable(name= "slug_city") String slug_city){
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        List<Restaurant> listCity = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        for(int i=0; i< restaurants.size(); i++){
            if(restaurants.get(i).getSlug_city().equals(slug_city)){
                listCity.add(restaurants.get(i));
            }
        }
            modelAndView.addObject("restaurants", listCity);
        modelAndView.setViewName("sort");
        return modelAndView;
    }

    @GetMapping(value = "/restaurant")
    public String getOneRestaurant(Model model){
        Iterable<Restaurant> restaurant =restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurant);
        return "hotel";
    }

    @GetMapping("/")
    public ModelAndView addRestaurants (ModelAndView modelAndView, Model model){
        Restaurant restaurant = new Restaurant(0, "Манаки", "Ресторанот Манаки се карактеризира со европска кујна, главно италијанската. Добро позната е врвната Манаки пица која е омилена кај гостите. Во прилог на ресторанот се вклучени затворен и отворен простор. Добредојдени се сите кои се љубители на добрата храна и сакаат пријатно место за релаксација.","Одлична атмосфера...", "manaki_logo.jpg", "/pic2", 5, "active","Битола","bitola");
        restaurantRepository.save(new Restaurant(restaurant.getId(),restaurant.getName(), restaurant.getDescription(),restaurant.getShort_description(), restaurant.getPicture1(),restaurant.getPicture2(),restaurant.getCapacity(),restaurant.getStatus(), restaurant.getCity(), restaurant.getSlug_city()));

        Restaurant restaurant1 = new Restaurant(0, "Аурум", "Aurum kitchen bar е стилски дизајниран бар ресторан каде отменоста и гастрономијата создаваат неповторлив амбиент во срцето на Битола.", "Модерно уживање...", "aurum_logo.jpg", "/pic2", 5, "active","Битола","bitola");
        restaurantRepository.save(new Restaurant(restaurant1.getId(),restaurant1.getName(), restaurant1.getDescription(), restaurant1.getShort_description(),  restaurant1.getPicture1(),restaurant1.getPicture2(),restaurant1.getCapacity(),restaurant1.getStatus(), restaurant1.getCity(), restaurant1.getSlug_city()));

        Restaurant restaurant2 = new Restaurant(0, "Вино Бар Буре", "Уникатност, автентичност, најбогат избор од необична храна и воодушевувачки амбиент. Вечерна музика и чаша вино. Со еден збор, Вино Бар Буре.","Уникатност, автентичност...", "vinobarbure_logo.jpg", "/pic2", 5, "active","Скопје","skopje");
        restaurantRepository.save(new Restaurant(restaurant2.getId(),restaurant2.getName(), restaurant2.getDescription(), restaurant2.getShort_description(), restaurant2.getPicture1(),restaurant2.getPicture2(),restaurant2.getCapacity(),restaurant2.getStatus(), restaurant2.getCity(), restaurant2.getSlug_city()));

        Restaurant restaurant3 = new Restaurant(0, "Бела куќа", "Бистро Бар Бела Куќа се наоѓа во строгиот центар на Битола на само 30 метри од култниот Широк Сокак. Летната тераса и ресторанот се со капацитет од по 100 седишта и нудат најразновидни салати, пасти и специјалитети од домашната и странската кујна.","Луксузно неодолив...", "belakuka_logo.jpg", "/pic2", 5, "active","Скопје", "skopje");
        restaurantRepository.save(new Restaurant(restaurant3.getId(),restaurant3.getName(), restaurant3.getDescription(), restaurant3.getShort_description(), restaurant3.getPicture1(),restaurant3.getPicture2(),restaurant3.getCapacity(),restaurant3.getStatus(),restaurant3.getCity(), restaurant3.getSlug_city()));

        Restaurant restaurant4 = new Restaurant(0, "Портал 2", "Портал 2 се наоѓа на Широк Сокак. Портал 2 ви нуди пријатна атмосфера, добра музика, вкусно кафе, голем избор на пијалоци и најпознатите коктели.","Кафе и мезе за совршен ден...", "/metro_logo.jpg", "/pic2", 5, "active", "Прилеп","prilep");
        restaurantRepository.save(new Restaurant(restaurant4.getId(),restaurant4.getName(), restaurant4.getDescription(), restaurant4.getShort_description(),  restaurant4.getPicture1(),restaurant4.getPicture2(),restaurant4.getCapacity(),restaurant4.getStatus(), restaurant4.getCity(), restaurant4.getSlug_city()));

        Restaurant restaurant5 = new Restaurant(0, "ГТ Lounge Bar", "GT Lounge Bar е еден од поновите ресторани во Битола и се карактеризира со извондредна кујна со нови и уникатни специјалитети. Ви нуди одлична атмосфера гледајќи го целиот Широк Сокак и уживање во менито приготвено со многу љубов.","Одлична атмосфера...",  "gt_logo.jpg", "/pic2", 5, "active", "Прилеп","prilep");
        restaurantRepository.save(new Restaurant(restaurant5.getId(),restaurant5.getName(), restaurant5.getDescription(), restaurant5.getShort_description(), restaurant5.getPicture1(),restaurant5.getPicture2(),restaurant5.getCapacity(),restaurant5.getStatus(), restaurant5.getCity(), restaurant5.getSlug_city()));

        Restaurant restaurant6 = new Restaurant(0, "Белведере", "Ако сакате да уживате во најпрекрасниот поглед кон центарот на Битола, а притоа го пиете утринското кафе или јадете чорба, или пак да имате најромантична вечера, ова е вистинското место за Вас! Белведере Ви овозможува поглед кон Саат Кулата и Широк Сокак. Воедно, ќе Ве освои со специјалитетите кои ги подготвуваат врвните готвачи во кујната. Погодно е за состаноци, семејства и пријателски средби. Добредојдени сте во секое време!","Поглед кон целиот град...", "belvedere_logo.jpg", "/pic2", 5, "active", "Битола", "bitola");
        restaurantRepository.save(new Restaurant(restaurant6.getId(),restaurant6.getName(), restaurant6.getDescription(),restaurant6.getShort_description(),  restaurant6.getPicture1(),restaurant6.getPicture2(),restaurant6.getCapacity(),restaurant6.getStatus(),restaurant6.getCity(), restaurant6.getSlug_city()));
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);
        return new ModelAndView("index");

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView newRestaurant(@RequestParam(name = "name") String name,
                                      @RequestParam(name = "description") String description,
                                      @RequestParam(name = "short_description") String short_description,
                                      @RequestParam(name = "capacity") int capacity,
                                      @RequestParam(name = "status") String status,
                                      @RequestParam(name = "city") String city,
                                      @RequestParam(name = "slug_city") String slug_city,
                                      @RequestParam(name = "picture1") String picture1,
                                      @RequestParam(name = "picture2") String picture2, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        Restaurant restaurant = new Restaurant(0, name, description, short_description, picture1, picture2, capacity, status, city, slug_city);
        int flag=0;
        for(int i=0; i<restaurants.size(); i++) {
            if(restaurant.getName().equals(restaurants.get(i).getName())){
                flag=1;
                break;
            }
        }
        if(flag==0) {
            restaurantRepository.save(new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.getShort_description(), restaurant.getPicture1(), restaurant.getPicture2(), restaurant.getCapacity(), restaurant.getStatus(), restaurant.getCity(), restaurant.getSlug_city()));
            modelAndView.addObject("restaurantAdded", true);
        }

        model.addAttribute("restaurants", restaurants);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
