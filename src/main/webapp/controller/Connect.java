package main.webapp.controller;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import main.webapp.security.Encoder;
import main.webapp.model.dao.DaoFactory;
import main.webapp.model.dao.UserDao;
import main.webapp.model.User;
import main.webapp.security.JWT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Connect")
public class Connect extends HttpServlet {
    private UserDao userDao;
    public static final String CONF_DAO_FACTORY = "daofactory";

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.userDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null ) {
            String mail = request.getParameter("mail");
            String pass = request.getParameter("password");
            try {
                pass = Encoder.encode(pass);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            User user = userDao.find(mail);
            if (user == null) {
                out.write("invalid mail T-T");
            } else {
                if (user.getPass().compareTo(pass) == 0) {
                    String jwt = JWT.createJWT(user.getMail(), "shareloc", user.getMail(), 30*60000);
                    session.setAttribute("user", jwt);
                    //setting session to expiry in 30 mins
                    session.setMaxInactiveInterval(30 * 60);
                    //Cookie userName = new Cookie("user", jwt);
                    //userName.setMaxAge(30 * 60);
                    //response.addCookie(userName);
                    out.write("hello " + user.getName() + "\n you're now connected :D");
                } else {
                    out.write("invalid password T-T");
                }
            }
        }
        else {
            Claims jwt = JWT.decodeJWT((String)session.getAttribute("user"));
            out.write("you were already connected :o" );
        }
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String user = (String)session.getAttribute("user");
        if (user == null ) {
            out.write("you were not connected :/");
        }
        else {
            Claims jwt = JWT.decodeJWT(user);
            session.invalidate();
            out.write("you've been successfully disconnected :)");
        }
        out.flush();
    }
}
