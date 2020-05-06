package mate.academy.shop.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.shop.exceptions.AuthenticationException;
import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.User;
import mate.academy.shop.security.AuthenticationService;
import org.apache.log4j.Logger;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    public static final Logger logger = Logger.getLogger(LoginController.class);
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.shop");
    private AuthenticationService authenticationService =
            (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB_INF/views/users/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = authenticationService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getId());
        } catch (AuthenticationException e) {
            logger.error("Invalid password or login at the logging");
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB_INF/views/users/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
