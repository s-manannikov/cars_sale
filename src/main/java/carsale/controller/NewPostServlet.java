package carsale.controller;

import carsale.model.*;
import carsale.store.Repository;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/new_post.jsp")
public class NewPostServlet extends HttpServlet {
    private static final Repository REPOSITORY = Repository.instOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("brands", REPOSITORY.getList("from Brand"));
        req.setAttribute("bodies", REPOSITORY.getList("from Body"));
        req.setAttribute("transmissions", REPOSITORY.getList("from Transmission"));
        req.setAttribute("engines", REPOSITORY.getList("from Engine"));
        req.setAttribute("cities", REPOSITORY.getList("from City"));
        req.getRequestDispatcher("/WEB-INF/new_post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repo = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repo);
        ServletFileUpload upload = new ServletFileUpload(factory);
        final Photo photo = new Photo();
        final Engine engine = new Engine();
        final Body body = new Body();
        final Transmission tm = new Transmission();
        final Brand brand = new Brand();
        final Car car = new Car();
        final City city = new City();
        final Post post = new Post();
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField() && !item.getName().isEmpty()) {
                    String photoName = UUID.randomUUID() + item.getName();
                    File file = new File(folder + File.separator + photoName);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                    photo.setName(photoName);
                } else {
                    switch (item.getFieldName()) {
                        case "model" -> car.setModel(item.getString());
                        case "year" -> car.setYear(item.getString());
                        case "hp" -> car.setHp(Integer.parseInt(item.getString()));
                        case "mileage" -> car.setMileage(Integer.parseInt(item.getString()));
                        case "color" -> car.setColor(item.getString());
                        case "price" -> car.setPrice(Integer.parseInt(item.getString()));
                        case "brand" -> {
                            brand.setId(Integer.parseInt(item.getString()));
                            car.setBrand(brand);
                        }
                        case "body" -> {
                            body.setId(Integer.parseInt(item.getString()));
                            car.setBody(body);
                        }
                        case "transmission" -> {
                            tm.setId(Integer.parseInt(item.getString()));
                            car.setTransmission(tm);
                        }
                        case "engine" -> {
                            engine.setId(Integer.parseInt(item.getString()));
                            car.setEngine(engine);
                        }
                        case "city" -> {
                            city.setId(Integer.parseInt(item.getString()));
                            post.setCity(city);
                        }
                        case "description" -> post.setDescription(item.getString());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        if (photo.getName() == null) {
            photo.setName("no_photo.png");
        }
        REPOSITORY.saveModel(photo);
        REPOSITORY.saveModel(car);
        final Date date = new Date(System.currentTimeMillis());
        post.setCreated(date);
        post.setCar(car);
        post.setPhoto(photo);
        final HttpSession session = req.getSession();
        post.setUser((User) session.getAttribute("user"));
        REPOSITORY.saveModel(post);
        resp.sendRedirect("index.jsp");
    }
}
