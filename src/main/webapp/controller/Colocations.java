package main.webapp.controller;

import com.google.gson.Gson;
import main.webapp.model.Address;
import main.webapp.model.Colocation;
import main.webapp.model.User;
import main.webapp.model.dao.AddressDao;
import main.webapp.model.dao.ColocationDao;
import main.webapp.model.dao.DaoFactory;
import main.webapp.security.Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import static main.webapp.controller.Utils.isAuthorized;

@WebServlet(name = "Colocations")
public class Colocations extends HttpServlet {
    private Gson gson = new Gson();
    private ColocationDao colocationDao;
    private AddressDao addressDao;
    public static final String CONF_DAO_FACTORY = "daofactory";

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.colocationDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getColocationDao();
        this.addressDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getAddressDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        if (id != null) {
            out.print(gson.toJson(colocationDao.find(Integer.parseInt(id))));
        } else {
            out.print(gson.toJson(colocationDao.findAll()));
        }

        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Colocation toWrite = new Colocation();
        String name = request.getParameter("nom");

        toWrite.setName(name);

        colocationDao.persist(toWrite);

        out.print(gson.toJson(toWrite));

        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Colocation toDelete = colocationDao.find(Integer.parseInt(request.getParameter("id")));
        colocationDao.remove(toDelete);

        out.print(gson.toJson(toDelete));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Colocation toUpdate = colocationDao.find(Integer.parseInt(request.getParameter("id")));

        String name = request.getParameter("nom");

        toUpdate.setName(name);
        colocationDao.update(toUpdate);

        out.print(gson.toJson(colocationDao.find(toUpdate.getId())));
        out.flush();
    }
}
