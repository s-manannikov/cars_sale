package carsale.store;

import carsale.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public List<Post> getYesterdayPosts() {
        Date date = new Date();
        date.setTime(date.getTime()-24*60*60*1000);
        return transaction(session -> session.createQuery("from Post p where Date(p.created) = :date")
                .setParameter("date", date)
                .list());
    }

    public List<Post> getPostsWithPhoto() {
        return transaction(session -> session.createQuery("from Post where photo is not null").list());
    }

    public List<Post> getPostsByBrand(String brand) {
        return transaction(session -> session.createQuery("from Post where car.brand = :brand")
                .setParameter("brand", brand)
                .list());
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
