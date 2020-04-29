package mate.academy.shop.controllers.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Product;
import mate.academy.shop.service.ProductService;

@WebServlet("/products")
public class GetAllAdminProductsController extends HttpServlet {
    private static Injector injector = Injector.getInstance("mate.academy.shop");
    private ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> productList = productService.getAll();
        req.setAttribute("products", productList);
        req.getRequestDispatcher("/WEB_INF/views/admin_functionality/products/products_list.jsp")
                .forward(req, resp);
    }
}
