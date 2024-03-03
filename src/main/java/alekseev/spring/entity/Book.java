package alekseev.spring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="book", schema="library")
public class Book {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "idBook", nullable=false)
    private Integer id;
    @Size(min = 2, max = 30, message = "Title should be between 2 and 30 characters")
    @Column (name = "title", nullable=false)
    private String title;
    @Size(min = 2, max = 30, message = "Author should be between 2 and 30 characters")
    @Column (name = "author", nullable=false)
    private String author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person", referencedColumnName = "idPerson")
    private Person person;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.person = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person getPerson() {
        return person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
