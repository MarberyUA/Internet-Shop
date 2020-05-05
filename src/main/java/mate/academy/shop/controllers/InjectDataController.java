package mate.academy.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.Role;
import mate.academy.shop.model.ShoppingCard;
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

        ShoppingCard alisaShoppingCard = new ShoppingCard();
        shoppingCartService.create(alisaShoppingCard, alisa);

        Product milk = new Product("milk", 1000.0);
        Product water = new Product("water", 150.0);
        Product phone = new Product("iPhone11", 1500.99);
        Product mask = new Product("Mask", 50.0);
        Product car = new Product("Audi", 10000.0);
        Product game = new Product("Dota2", 0.0);
        productService.create(milk);
        productService.create(water);
        productService.create(phone);
        productService.create(water);
        productService.create(mask);
        productService.create(car);
        productService.create(game);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
