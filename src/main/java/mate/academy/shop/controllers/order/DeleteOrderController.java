package mate.academy.shop.controllers.order;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.service.OrderService;

@WebServlet("/order/delete")
public class DeleteOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private final OrderService orderService = (OrderService)
            INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long orderId = Long.parseLong(req.getParameter("id"));
        orderService.delete(orderId);
        resp.sendRedirect("/orders_list");
    }
}
