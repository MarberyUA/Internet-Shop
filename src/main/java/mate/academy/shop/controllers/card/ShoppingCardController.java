package mate.academy.shop.controllers.card;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;
import mate.academy.shop.service.ShoppingCardService;
import mate.academy.shop.service.UserService;

@WebServlet("/shopping_card")
public class ShoppingCardController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private final ShoppingCardService shoppingCartService =
            (ShoppingCardService) INJECTOR.getInstance(ShoppingCardService.class);
    private final UserService userService = (UserService)
            INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.parseLong(req.getSession().getAttribute("userId").toString());
        ShoppingCard shoppingCard;
        try {
            shoppingCard = shoppingCartService.getByUserId(userId);
        } catch (NoSuchElementException e) {
            shoppingCard = new ShoppingCard();
            User user = userService.get(userId);
            shoppingCartService.create(shoppingCard, user);
        }
        req.setAttribute("card", shoppingCard);
        req.getRequestDispatcher("/WEB_INF/views/shopping-card/shopping_card_items.jsp")
                .forward(req, resp);
    }
}
