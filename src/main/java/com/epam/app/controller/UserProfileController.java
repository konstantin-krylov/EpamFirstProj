package com.epam.app.controller;

import com.epam.app.model.User;
import com.epam.app.model.enums.Role;
import com.epam.app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.app.util.password.Password.hash;

/**
 * Created by vladd on 28.04.2019
 */
@WebServlet("/cabinet/profile")
public class UserProfileController extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserService.get(Integer.parseInt(req.getParameter("id")));
        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User profileUpdatedUser = UserService.get(id);
        profileUpdatedUser.setName(req.getParameter("name"));
        profileUpdatedUser.setRole(Role.getRole(req.getParameter("role")));
        UserService.updateUser(profileUpdatedUser);
        req.getSession().setAttribute("loggedInUser", profileUpdatedUser);
        resp.sendRedirect("/cabinet/profile?id=" + id);
    }
}
