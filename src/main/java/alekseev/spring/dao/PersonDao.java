package alekseev.spring.dao;

import alekseev.spring.entity.Book;
import alekseev.spring.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDao {
    private SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> showPeople() {
        try (Session session = sessionFactory.openSession()) {
            System.out.println(session.createQuery("from Person", Person.class).list().get(0).getId());
            return session.createQuery("from Person", Person.class).list();
        }
    }

    public void savePerson(Person person) {
        try(Session session= sessionFactory.openSession()){
            session.save(person);
        }
    }
}