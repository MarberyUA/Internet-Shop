package mate.academy.shop.controllers.user;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.service.UserService;

@WebServlet("/user/delete")
public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        userService.delete(id);
        resp.sendRedirect("/users/all");
    }
}
