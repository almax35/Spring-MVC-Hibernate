package alekseev.spring.entity;




import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="person", schema="library")
public class Person {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column (name = "idPerson", nullable=false)
    private Integer id;
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    @Column (name = "first_name", nullable=false)
    private String firstName;
    @Size(min = 2, max = 30, message = "Second name should be between 2 and 30 characters")
    @Column (name = "second_name", nullable=false)
    private String secondName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    private Set<Book> books;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Person() {
    }

    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
