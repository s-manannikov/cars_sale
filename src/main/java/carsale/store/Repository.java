package carsale.store;

import carsale.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class Repository implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Holder {
        private static final Repository INST = new Repository();
    }

    public static Repository instOf() {
        return Holder.INST;
    }

    public List<Post> getYesterdayPosts() {
        final Date date = new Date();
        date.setTime(date.getTime() - 24*60*60*1000);
        return transaction(session -> session.createQuery("from Post p where Date(p.created) = :date and p.status = 0")
                .setParameter("date", date)
                .list());
    }

    public List<Post> getPostsWithPhoto() {
        return transaction(session -> session.createQuery(
                "from Post where photo.name != 'no_photo.png' and status = 0"
        ).list());
    }

    public List<Post> getPostsByBrand(String brand) {
        return transaction(session -> session.createQuery("from Post where car.brand.name = :brand and status = 0")
                .setParameter("brand", brand)
                .list());
    }

    public <T> void saveModel(T model) {
        transaction(session -> session.save(model));
    }

    public User findUserByEmail(String email) {
        return (User) transaction(session -> session.createQuery("from User where email = :email")
                .setParameter("email", email)
                .uniqueResult());
    }

    public Photo findPhotoById(int id) {
        return (Photo) transaction(session -> session.createQuery("from Photo where id = :id")
                .setParameter("id", id)
                .uniqueResult());
    }

    public void sold(int id) {
        transaction(session -> {
            final Query query = session.createQuery("update Post set status = 1 where car.id = :id")
                    .setParameter("id", id);
            return query.executeUpdate();
        });
    }

    public List<Post> getList(String query) {
        return transaction(session -> session.createQuery(query).list());
    }

    private <T> T transaction(final Function<Session, T> command) {
        final Session session = sf.openSession();
        session.beginTransaction();
        try {
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (final Exception ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
