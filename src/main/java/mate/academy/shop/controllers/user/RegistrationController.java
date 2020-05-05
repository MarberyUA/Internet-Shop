package mate.academy.shop.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Role;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;
import mate.academy.shop.service.ShoppingCardService;
import mate.academy.shop.service.UserService;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    private ShoppingCardService shoppingCartService =
            (ShoppingCardService) INJECTOR.getInstance(ShoppingCardService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB_INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        if (password.equals(passwordRepeat)) {
            User newUser = new User();
            newUser.setName(login);
            newUser.setPassword(password);
            newUser.addRole(new Role(Role.RoleName.USER));
            userService.create(newUser);
            ShoppingCard shoppingCard = new ShoppingCard();
            shoppingCartService.create(shoppingCard, newUser);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            // shop correct data message
            req.setAttribute("message", "Your password and repeat password are not the same!");
            req.getRequestDispatcher("/WEB_INF/views/registration.jsp").forward(req, resp);
        }
        System.out.println(login + " " + password);
    }
}
