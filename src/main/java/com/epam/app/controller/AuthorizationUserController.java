
package com.epam.app.controller;

import com.epam.app.config.Config;
import com.epam.app.listeners.TestLog4jServlet;
import com.epam.app.model.User;
import com.epam.app.service.UserService;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.app.util.password.Password.hash;

@NoArgsConstructor
@WebServlet("/login")
public class AuthorizationUserController extends HttpServlet {

    static final Logger log = Logger.getLogger(AuthorizationUserController.class);

    @Override
    public void init() throws ServletException {
        Config.set(this.getServletContext(), "mysql");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String hashed = hash(password);
        User userAccount = UserService.findUser(login, password);

        if (UserService.isUserAllowed(login, password)) {
            if (userAccount != null) {
                session.setAttribute("loggedInUser", userAccount);
                request.getRequestDispatcher("/homePage.jsp").forward(request, response);
                log.info("User " + userAccount.getLogin() + " logged in.");
            }
        } else {
            String errMessage = "Incorrect Username or password!";
            request.setAttribute("errMessage", errMessage);
            request.getRequestDispatcher("/authorization.jsp").forward(request, response);
            log.info("Unsuccessful attempt to login by " + login);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/authorization.jsp");
        requestDispatcher.forward(req, resp);
    }
}