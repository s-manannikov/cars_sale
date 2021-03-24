package carsale.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="car_id")
    private Car car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="photo_id")
    private Photo photo;

    private int status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id
                && status == post.status
                && Objects.equals(description, post.description)
                && Objects.equals(car, post.car)
                && Objects.equals(photo, post.photo)
                && Objects.equals(user, post.user)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, car, photo, status, user, created);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", car=" + car +
                ", photo=" + photo +
                ", status=" + status +
                ", user=" + user +
                ", created=" + created +
                '}';
    }
}
