package mate.academy.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Role;
import mate.academy.shop.model.User;
import mate.academy.shop.service.ProductService;
import mate.academy.shop.service.ShoppingCardService;
import mate.academy.shop.service.UserService;

@WebServlet("/inject_data")
public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private UserService userService = (UserService)
            INJECTOR.getInstance(UserService.class);
    private ProductService productService = (ProductService)
            INJECTOR.getInstance(ProductService.class);
    private ShoppingCardService shoppingCartService =
            (ShoppingCardService) INJECTOR.getInstance(ShoppingCardService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User alisa = new User();
        alisa.setPassword("123");
        alisa.setName("Alisa");
        userService.create(alisa);

        User bob = new User();
        bob.setName("Bob");
        bob.setPassword("123");
        bob.addRole(new Role(Role.RoleName.ADMIN));
        userService.create(bob);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
