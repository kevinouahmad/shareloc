package main.webapp.controller;

import com.google.gson.Gson;
import main.webapp.model.Address;
import main.webapp.model.User;
import main.webapp.model.dao.AddressDao;
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

@WebServlet(name = "Addresses")
public class Addresses extends HttpServlet {
    private Gson gson = new Gson();
    private AddressDao addressDao;
    public static final String CONF_DAO_FACTORY = "daofactory";

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.addressDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getAddressDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String code = request.getParameter("code");

        if (id != null) {
            out.print(gson.toJson(addressDao.find(Integer.parseInt(id))));
        } else if (code != null) {
            out.print(gson.toJson(addressDao.findByZipCode(Integer.parseInt(code))));
        } else {
            out.print(gson.toJson(addressDao.findAll()));
        }
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Address toWrite = new Address();
        Integer numero = Integer.parseInt(request.getParameter("numero"));
        Integer code = Integer.parseInt(request.getParameter("code"));
        String rue = request.getParameter("rue");
        String ville = request.getParameter("ville");
        Integer idColoc = Integer.parseInt(request.getParameter("id_coloc"));

        toWrite.setNumber(numero);
        toWrite.setStreet(rue);
        toWrite.setZipCode(code);
        toWrite.setTown(ville);
        toWrite.setIdColoc(idColoc);

        addressDao.persist(toWrite);

        out.print(gson.toJson(toWrite));

        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Address toDelete = addressDao.find(Integer.parseInt(request.getParameter("id")));
        addressDao.remove(toDelete);
        out.print(gson.toJson(toDelete));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Address toUpdate = addressDao.find((Integer.parseInt(request.getParameter("id"))));

        Integer numero = Integer.parseInt(request.getParameter("numero"));
        Integer code = Integer.parseInt(request.getParameter("code"));
        String rue = request.getParameter("rue");
        String ville = request.getParameter("ville");
        Integer idColoc = Integer.parseInt(request.getParameter("id_coloc"));

        toUpdate.setNumber(numero);
        toUpdate.setStreet(rue);
        toUpdate.setZipCode(code);
        toUpdate.setTown(ville);
        toUpdate.setIdColoc(idColoc);
        addressDao.update(toUpdate);

        out.print(gson.toJson(addressDao.find(toUpdate.getId())));

        out.flush();
    }
}
