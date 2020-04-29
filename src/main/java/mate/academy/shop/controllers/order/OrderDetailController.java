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
import mate.academy.shop.model.Product;
import mate.academy.shop.service.OrderService;

@WebServlet("/order/info")
public class OrderDetailController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.parseLong(req.getParameter("id"));
        Order order = orderService.get(orderId);
        List<Product> products = order.getProductsInOrder();
        Double totalCost = products.stream()
                .map(Product::getPrice)
                .reduce(Double::sum)
                .get();
        req.setAttribute("products", products);
        req.setAttribute("cost", totalCost);
        req.getRequestDispatcher("/WEB_INF/views/orders/order_detail.jsp").forward(req, resp);
    }
}
