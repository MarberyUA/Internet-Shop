package mate.academy.shop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.shop.lib.Injector;

@WebServlet("/")
public class IndexController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB_INF/views/index.jsp").forward(req, resp);
    }
}
