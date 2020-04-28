package mate.academy.shop.controllers.card;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.service.ShoppingCardService;

@WebServlet("/shopping_card")
public class GetShoppingCardItemsController extends HttpServlet {
    private static final Long USER_ID = 1L;

    private static final Injector injector = Injector.getInstance("mate.academy");
    private final ShoppingCardService shoppingCartService =
            (ShoppingCardService) injector.getInstance(ShoppingCardService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCard shoppingCard = shoppingCartService.getByUserId(USER_ID);
        req.setAttribute("cardItems", shoppingCard.getProductsInShopping());
        req.getRequestDispatcher("/WEB_INF/views/shopping-card/shopping_card_items.jsp")
                .forward(req, resp);
    }
}
