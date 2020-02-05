package main.webapp.controller;

import com.google.gson.Gson;
import main.webapp.model.User;
import main.webapp.model.dao.DaoFactory;
import main.webapp.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.webapp.controller.Utils.isAuthorized;

@WebServlet(name = "Rights")
public class Rights extends HttpServlet {
    private Gson gson = new Gson();
    private UserDao userDao;
    public static final String CONF_DAO_FACTORY = "daofactory";

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.userDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAuthorized(1,null, request, userDao)) {
            User user = userDao.find(request.getParameter("mail"));
            userDao.grantAdmin(user);
        }
    }
}
