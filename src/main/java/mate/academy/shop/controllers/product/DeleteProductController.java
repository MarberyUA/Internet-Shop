package mate.academy.shop.controllers.product;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.service.ProductService;

@WebServlet("/products/delete")
public class DeleteProductController extends HttpServlet {
    private static Injector injector = Injector.getInstance("mate.academy.shop");
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long productId = Long.parseLong(req.getParameter("id"));
        productService.delete(productId);
        resp.sendRedirect("/products");
    }
}
