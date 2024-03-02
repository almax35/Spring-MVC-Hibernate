package alekseev.spring.controller;


import alekseev.spring.dao.BookDao;
import alekseev.spring.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String allBooks(Model model) {
        model.addAttribute("books", bookDao.showBooks());
        return "all_books";
    }
    @GetMapping("/new")
    public String createBook(Model model){
        model.addAttribute("book",new Book());
        return "/new_book";
    }
    @GetMapping("/{id}")
    public String showPersonById(Model model, @PathVariable Integer id){
        model.addAttribute("bookByID",bookDao.showBook(id));
        return "showBook";
    }
    @PostMapping()
    public String saveBook(@ModelAttribute Book book){
        bookDao.saveBook(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id){
        bookDao.deleteBook(id);
        return "redirect:/books";
    }
}