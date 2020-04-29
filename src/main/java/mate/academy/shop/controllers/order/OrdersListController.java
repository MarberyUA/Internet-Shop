package mate.academy.shop.controllers.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Order;
import mate.academy.shop.service.OrderService;
import mate.academy.shop.service.UserService;

@WebServlet("/orders_list")
public class OrdersListController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);
    private UserService userService = (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Order> orders = orderService.getUserOrders(userService.get(USER_ID));
        if (orders.size() < 1) {
            String message = "You do not have complete orders";
            req.setAttribute("message", message);
        }
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB_INF/views/orders/orders_list.jsp").forward(req, resp);
    }
}
