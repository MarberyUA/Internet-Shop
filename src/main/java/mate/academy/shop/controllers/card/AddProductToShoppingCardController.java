package mate.academy.shop.controllers.card;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.service.ProductService;
import mate.academy.shop.service.ShoppingCardService;

@WebServlet("/products/toCard")
public class AddProductToShoppingCardController extends HttpServlet {
    private static final Long USER_ID = 1L;

    private static Injector injector = Injector.getInstance("mate.academy.shop");
    private ShoppingCardService shoppingCardService = (ShoppingCardService)
            injector.getInstance(ShoppingCardService.class);
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        ShoppingCard shoppingCard = shoppingCardService.getByUserId(USER_ID);
        shoppingCard.addProductToCard(productService.get(Long.valueOf(id)));
        resp.sendRedirect("/shopping_card");
    }
}
