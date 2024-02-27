package alekseev.spring.entity;

import javax.persistence.*;
@Entity
@Table(name="book", schema="library")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idBook", nullable=false)
    private Integer id;
    @Column (name = "title", nullable=false)
    private String title;
    @Column (name = "author", nullable=false)
    private String author;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
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
