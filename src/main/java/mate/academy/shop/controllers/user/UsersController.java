package mate.academy.shop.controllers.user;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.User;
import mate.academy.shop.service.UserService;

@WebServlet("/users/all")
public class UsersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> allUsers = this.userService.getAll();

        req.setAttribute("allUsers", allUsers);
        req.getRequestDispatcher("/WEB_INF/views/users/all.jsp").forward(req, resp);
    }
}
