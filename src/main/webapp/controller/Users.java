package main.webapp.controller;

import com.google.gson.Gson;
import main.webapp.security.Encoder;
import main.webapp.model.User;
import main.webapp.model.dao.DaoFactory;
import main.webapp.model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Home")
public class Home extends HttpServlet {
    private Gson gson = new Gson();
    private UserDao     userDao;
    public static final String CONF_DAO_FACTORY = "daofactory";

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.userDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String t = gson.toJson(userDao.findAll());
        String mail = request.getParameter("mail");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (mail != null ) {
            out.print(gson.toJson(userDao.find(mail)));
        }
        else {
            out.print(gson.toJson(userDao.findAll()));
        }

        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User toWrite = new User();
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = null;
        try {
            password = Encoder.encode(request.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        toWrite.setName(name);
        toWrite.setMail(mail);
        toWrite.setPass(password);
        userDao.persist(toWrite);

        String t = gson.toJson(toWrite);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(t);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User toDelete = userDao.find(request.getParameter("mail"));
        userDao.remove(toDelete);

        String t = gson.toJson(toDelete);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(t);
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User toUpdate = userDao.find(request.getParameter("mail"));
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        toUpdate.setPass(password);
        toUpdate.setName(name);
        userDao.update(toUpdate);

        String t = gson.toJson(userDao.find(toUpdate.getMail()));
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(t);
        out.flush();
    }
}
