package carsale.model;

import javax.persistence.*;
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
                && Objects.equals(user, post.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, car, photo, status, user);
    }
}
