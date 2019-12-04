package ImageHoster.model;
import javax.persistence.*;
import java.util.Date;

//Added Comment Entity to persist the User Comment In Database
@Entity
@Table(name="Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="text")
    private String text;

    @Column(name = "date")
    private Date date;

    //The relation between comment and User table is specified as ManyToOne i.e one User can post multiple comments but one comment should belong to one user itself
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    //The relation between comment and Image table is specified as ManyToOne i.e one Image can have multiple comments but one comment should belong to one image itself
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="image_id")
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
