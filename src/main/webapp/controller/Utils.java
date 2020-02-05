package main.webapp.controller;


import io.jsonwebtoken.Claims;
import main.webapp.model.User;
import main.webapp.model.dao.UserDao;
import main.webapp.security.JWT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
    public static boolean isAuthorized(int level, User user, HttpServletRequest req, UserDao userDao) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            Claims jwt = JWT.decodeJWT((String) session.getAttribute("user"));
            String mail = jwt.getSubject();
            User toCheck = userDao.find(mail);

            switch(level) {
                case(1) :
                    //return true if admin
                    if (toCheck.getPower().compareTo("admin") == 0) {
                        return true;
                    }
                    break;

                case(2) :
                    //return true if user connected matches user to be updated or if admin connected
                    if (isAuthorized(1, null, req, userDao)) {
                        return true;
                    }

                    if (toCheck.getMail().compareTo(user.getMail()) == 0) {
                        return true;
                    }
                    break;

                default:
                    return false;
            }
        }
        return false;
    }
}
