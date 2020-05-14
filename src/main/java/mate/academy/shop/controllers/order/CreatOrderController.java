package mate.academy.shop.controllers.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.service.OrderService;
import mate.academy.shop.service.ShoppingCardService;

@WebServlet("/order/create")
public class CreatOrderController extends HttpServlet {
    private static Injector injector = Injector.getInstance("mate.academy.shop");
    private ShoppingCardService shoppingCardService = (ShoppingCardService)
            injector.getInstance(ShoppingCardService.class);
    private OrderService orderService = (OrderService)
            injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        ShoppingCard shoppingCard = shoppingCardService.getByUserId(userId);
        String message;
        if (shoppingCard.getProductsInShopping().size() > 0) {
            List<Product> products = new ArrayList<>(shoppingCard.getProductsInShopping());
            Order order = orderService.completeOrder(products, shoppingCard.getUser());
            message = "You have make an order. Please, "
                    + "wait until our manager contact you to set details about order";
            shoppingCardService.clear(shoppingCard);
        } else {
            message = "You do not have items in your card!";
        }
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB_INF/views/orders/create_order.jsp").forward(req, resp);
    }
}
