package carsale.store;

import carsale.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RepositoryTest {

    @Test
    public void whenFindPostsWithPhoto() {
        Repository repo = new Repository();
        Post post = new Post();
        post.setId(1);
        repo.saveModel(post);
        Post postWithPhoto = new Post();
        post.setId(2);
        Photo photo = new Photo();
        photo.setName("photo");
        postWithPhoto.setPhoto(photo);
        repo.saveModel(photo);
        repo.saveModel(postWithPhoto);
        List<Post> list = repo.getList("from Post");
        List<Post> list2 = repo.getPostsWithPhoto();
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list2.size(), 1);
    }

    @Test
    public void whenFindPostsByBrand() {
        Repository repo = new Repository();
        Post post = new Post();
        Car car = new Car();
        Brand brand = new Brand();
        brand.setName("bbb");
        car.setBrand(brand);
        repo.saveModel(brand);
        repo.saveModel(car);
        post.setId(1);
        post.setCar(car);
        repo.saveModel(post);
        List<Post> list = repo.getPostsByBrand("bbb");
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void whenSaveModel() {
        Repository repo = new Repository();
        Post post = new Post();
        repo.saveModel(post);
        List<Post> list = repo.getList("from Post");
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void whenFindUserByEmail() {
        Repository repo = new Repository();
        User user = new User();
        user.setId(1);
        user.setEmail("email");
        repo.saveModel(user);
        Assert.assertEquals(repo.findUserByEmail("email"), user);
    }

    @Test
    public void whenFindPhotoById() {
        Repository repo = new Repository();
        Photo photo = new Photo();
        photo.setId(1);
        repo.saveModel(photo);
        Assert.assertEquals(repo.findPhotoById(1), photo);
    }

    @Test
    public void whenSoldCar() {
        Repository repo = new Repository();
        Car car = new Car();
        car.setId(1);
        repo.saveModel(car);
        Post post = new Post();
        post.setId(1);
        post.setCar(car);
        repo.saveModel(post);
        Assert.assertEquals(post.getStatus(), 0);
        repo.sold(1);
        Assert.assertEquals(repo.getList("from Post").get(0).getStatus(), 1);
    }

    @Test
    public void whenGetAllPosts() {
        Repository repo = new Repository();
        Post post1 = new Post();
        post1.setId(1);
        Post post2 = new Post();
        post2.setId(2);
        repo.saveModel(post1);
        repo.saveModel(post2);
        List<Post> list = repo.getList("from Post");
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0), post1);
    }
}