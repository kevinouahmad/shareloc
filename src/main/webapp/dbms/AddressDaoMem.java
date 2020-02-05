package main.webapp.dbms;

import main.webapp.exceptions.daoexceptions.DAOException;
import main.webapp.model.Address;
import main.webapp.model.User;
import main.webapp.model.dao.AddressDao;
import main.webapp.model.dao.DaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static main.webapp.dbms.utils.fermeturesSilencieuses;
import static main.webapp.dbms.utils.initialisationRequetePreparee;

public class AddressDaoMem implements AddressDao {
    private final Map<Long, User> store = Collections.synchronizedMap(new TreeMap<Long, User>());
    private static final String SQL_SELECT_PAR_ID = "SELECT * FROM Adresses WHERE id = ?";
    private static final String SQL_SELECT_PAR_CODE = "SELECT * FROM Adresses WHERE code = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM Adresses";
    private static final String SQL_INSERT = "INSERT INTO Adresses (numero, rue, code, ville, id_coloc) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Adresses WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Adresses SET numero = ?, rue = ?, code = ?, ville = ? WHERE id = ?";
    private DaoFactory daoFactory;

    public AddressDaoMem(DaoFactoryImpl daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public void persist(Address address) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, address.getNumber(), address.getStreet(), address.getZipCode(), address.getTown(), address.getIdColoc() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'adresse, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Address avec sa valeur */
                address.setId( valeursAutoGenerees.getInt( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de l'adresse en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    @Override
    public void remove(Address address) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, address.getId());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression de l'adresse, aucune ligne ajoutée dans la table." );
            }

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    @Override
    public void update(Address address) {

    }

    @Override
    public Address find(int id) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Address address = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                address = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return address;
    }

    @Override
    public Collection<Address> findByZipCode(int zipCode) {
        ArrayList<Address> addresses = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User address = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_CODE, false, zipCode);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                addresses.add(map( resultSet ));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return addresses;
    }

    @Override
    public Collection<Address> findAll() {
        ArrayList<Address> addresses = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {
                addresses.add(map( resultSet ));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return addresses;
    }

    private static Address map( ResultSet resultSet ) throws SQLException {
        Address address = new Address();
        address.setId( resultSet.getInt( "id" ) );
        address.setNumber( resultSet.getInt( "numero" ) );
        address.setStreet( resultSet.getString( "rue" ) );
        address.setZipCode( resultSet.getInt( "code" ) );
        address.setTown( resultSet.getString( "ville" ) );
        address.setIdColoc( resultSet.getInt( "id_coloc") );
        return address;
    }
}
