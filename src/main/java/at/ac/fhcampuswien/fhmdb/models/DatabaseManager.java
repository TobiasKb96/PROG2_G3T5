package at.ac.fhcampuswien.fhmdb.models;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

//All attributes for establishing the connection (URL, user, PW) as well as connection source and DAO
public class DatabaseManager {
    public static final String DB_URL = "jdbc:h2:file: ./db/movieDatabase";
    public static final String username = "user";
    public static final String password = "password";
    private static JdbcConnectionSource conn;
    private Dao<MovieEntity, Long> movieDao;
    private Dao<WatchlistMovieEntity, Long> watchlistDao;
    private static DatabaseManager instance;

    //Establish connection, create dao and create table if not yet available
    public DatabaseManager() throws SQLException {
        createConnectionSource();
        movieDao = DaoManager.createDao(conn, MovieEntity.class);
        createTables();
    }

    private static void createConnectionSource() throws SQLException {
        conn = new JdbcConnectionSource(DB_URL, username, password);
    }

    private static void createTables()throws SQLException{
        TableUtils.createTableIfNotExists(conn, MovieEntity.class);
    }

    public Dao<MovieEntity, Long> getMovieDao(){
        return this.movieDao;
    }

    public Dao<WatchlistMovieEntity, Long> getWatchlistDao(){
        return this.watchlistDao;
    }

    //Singleton Patter
    public static DatabaseManager getDatabaseInstance() throws SQLException {
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }
}