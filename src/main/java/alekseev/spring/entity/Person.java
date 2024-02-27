package alekseev.spring.entity;




import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="person", schema="library")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "idPerson", nullable=false)
    Integer id;
    @Column (name = "first name", nullable=false)
    String firstName;
    @Column (name = "second name", nullable=false)
    String secondName;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
    private Set<Book> books;

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
}
