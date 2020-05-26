package mate.academy.shop.controllers.card;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.service.ProductService;
import mate.academy.shop.service.ShoppingCardService;

@WebServlet("/shopping-card/product/add")
public class AddProductToShoppingCardController extends HttpServlet {

    private static Injector injector = Injector.getInstance("mate.academy.shop");
    private ShoppingCardService shoppingCardService = (ShoppingCardService)
            injector.getInstance(ShoppingCardService.class);
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Long userId = Long.valueOf(req.getSession().getAttribute("userId").toString());
        ShoppingCard shoppingCard = shoppingCardService.getByUserId(userId);
        Product product = productService.get(id);
        shoppingCardService.addProduct(shoppingCard, product);
        resp.sendRedirect("/shopping_card");
    }
}
