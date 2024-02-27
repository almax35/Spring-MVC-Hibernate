package alekseev.spring.dao;


import alekseev.spring.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private final SessionFactory sessionFactory;
    @Autowired
    public BookDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> showBooks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }
    public void saveBook(Book book) {
        try(Session session= sessionFactory.openSession()){
            session.persist(book);
        }
    }


}