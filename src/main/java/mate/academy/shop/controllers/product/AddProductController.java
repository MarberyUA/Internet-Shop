package mate.academy.shop.controllers.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Product;
import mate.academy.shop.service.ProductService;

@WebServlet("/products/add")
public class AddProductController extends HttpServlet {
    private static Injector injector = Injector.getInstance("mate.academy.shop");
    private final ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB_INF/views/products/product_add.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("prd-name");
        String price = req.getParameter("prd-price");
        Product product = new Product(name, Double.valueOf(price));
        productService.create(product);
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
