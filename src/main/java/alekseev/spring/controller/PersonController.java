package alekseev.spring.controller;



import alekseev.spring.dao.PersonDao;
import alekseev.spring.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDao personDao;

    @Autowired
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }


    @GetMapping()
    public String allBooks(Model model) {
        model.addAttribute("people", personDao.showPeople());
        return "/all_person";
    }
    @GetMapping("/new")
    public String createBook(Model model){
        model.addAttribute("person",new Person());
        return "/new_person";
    }
    @PostMapping()
    public String saveBook(@ModelAttribute Person person){
        personDao.savePerson(person);
        return "redirect:/people";
    }
}