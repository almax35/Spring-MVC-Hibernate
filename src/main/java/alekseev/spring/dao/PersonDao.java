package alekseev.spring.dao;

import alekseev.spring.entity.Book;
import alekseev.spring.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDao {
    private SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public List<Person> showPeople() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Person", Person.class).list();
        }
    }

    @Transactional
    public void savePerson(Person person) {
        try(Session session= sessionFactory.openSession()){
            session.save(person);
        }
    }
    @Transactional(readOnly = true)
    public Person showPerson(Integer id){
        try (Session session= sessionFactory.openSession()){
            return  session.get(Person.class, id);
        }
    }
    @Transactional()
    public void deletePerson(Integer id){
        try (Session session= sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("from Book where person.id = :value");
            query.setParameter("value", id);
            List<Book> books = query.list();
            for (Book book: books){
                book.setPerson(null);
            }
            session.remove(session.get(Person.class, id));
            session.getTransaction().commit();
        }
    }

}