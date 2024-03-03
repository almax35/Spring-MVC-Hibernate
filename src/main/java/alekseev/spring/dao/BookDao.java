package alekseev.spring.dao;


import alekseev.spring.entity.Book;
import alekseev.spring.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public class BookDao {
    private final SessionFactory sessionFactory;
    @Autowired
    public BookDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> showBooks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }
    @Transactional
    public void saveBook(Book book) {
        try(Session session= sessionFactory.openSession()){
            session.save(book);
        }
    }
    @Transactional(readOnly = true)
    public Book showBook(Integer id){
        try (Session session= sessionFactory.openSession()){
            return  session.get(Book.class, id);
        }
    }
    @Transactional
    public void deleteBook(Integer id){
        try (Session session= sessionFactory.openSession()){
            session.beginTransaction();
            session.remove(session.get(Book.class, id));
            session.getTransaction().commit();
        }
    }

    @Transactional
    public void updateBook(Integer id, Book book){
        try (Session session= sessionFactory.openSession()){
            session.beginTransaction();
            Book origonaBook=session.get(Book.class, id);
            origonaBook.setAuthor(book.getAuthor());
            origonaBook.setTitle(book.getTitle());
            session.update(origonaBook);
            session.getTransaction().commit();
        }
    }
}