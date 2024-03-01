package alekseev.spring.controller;



import alekseev.spring.dao.PersonDao;
import alekseev.spring.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDao personDao;

    @Autowired
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }


    @GetMapping()
    public String allPeople(Model model) {
        model.addAttribute("people", personDao.showPeople());
        return "/all_person";
    }
    @GetMapping("/new")
    public String createPeople(Model model){
        model.addAttribute("person",new Person());
        return "/new_person";
    }
    @PostMapping()
    public String savePerson(@ModelAttribute Person person){
        personDao.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPersonById(Model model, @PathVariable Integer id){
        model.addAttribute("personByID",personDao.showPerson(id));
        return "showPerson";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Integer id){
        personDao.deletePerson(id);
        return "redirect:/people";
    }
}