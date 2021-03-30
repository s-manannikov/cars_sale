package carsale.controller;

import carsale.model.User;
import carsale.store.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index.jsp")
public class PostsServlet extends HttpServlet {
    private static final Repository REPOSITORY = Repository.instOf();
    private static final String POSTS_WITH_PHOTO = "1";
    private static final String YESTERDAY_POSTS = "2";
    private static final String USER_POSTS = "3";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", REPOSITORY.getList(
                "from Post p where status = 0 order by p.created DESC"
        ));
        String searchId = req.getParameter("id");
        if (searchId != null && searchId.equals(POSTS_WITH_PHOTO)) {
            req.setAttribute("posts", REPOSITORY.getPostsWithPhoto());
        } else if (searchId != null && searchId.equals(YESTERDAY_POSTS)) {
            req.setAttribute("posts", REPOSITORY.getYesterdayPosts());
        } else if (searchId != null && searchId.equals(USER_POSTS)) {
            User user = (User) req.getSession().getAttribute("user");
            int userId = user.getId();
            req.setAttribute("posts", REPOSITORY.getList(
                    "from Post p where p.user.id =" + userId + "order by p.created DESC"
            ));
        }
        String brandId = req.getParameter("brand");
        if (brandId != null) {
            req.setAttribute("posts", REPOSITORY.getPostsByBrand(brandId));
        }
        req.setAttribute("brands", REPOSITORY.getList("from Brand b order by b.name"));
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
